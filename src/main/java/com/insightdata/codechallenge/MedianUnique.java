package com.insightdata.codechallenge;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.text.DecimalFormat;

public class MedianUnique{
    private static final Pattern WHITESPACE = Pattern.compile("\\s");
    private static final DecimalFormat fmt = new DecimalFormat("0.##");
    public static void main(String... args) {
        try{
            long[] unique_word_occurences = new long[70];//buckets for storing frequency of occurences
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
            String line = reader.readLine();
            long num_lines = 0;
            while( line != null ){//read all lines
                num_lines++;
                String[] words = WHITESPACE.split(line);
                int num_unique = new HashSet<String>(Arrays.asList(words)).size();
                unique_word_occurences[num_unique]++;
                System.out.println(fmt.format(computeMedian(unique_word_occurences, num_lines)));
                line = reader.readLine();
            }
        }catch(IOException e){
            System.err.println("ERROR: Could not read from stdin. Usage: MedianUnique < <input_file> > <output_file>");
        }
    }
    /**
    * @param uwo the unique wordcount occurrences
    * @param total_seen the total occurrences seen (this will be the sum of all uwo elements)
    **/
    public static float computeMedian(long[] uwo, long total_seen){
        long half = total_seen/2;
        int cursor = 0;
        long sum = 0;
        if(total_seen == 1){//special case, first entry
            while(uwo[cursor] <= 0){
                cursor++;
            }
            return cursor;
        }
        while( sum < half ){//sum the elements until we reach the middle
            sum += uwo[cursor];
            if( sum < half ){//only move our cursor if we aren't done
                cursor++;
            }
        }
        if(sum == half && total_seen%2 == 0){//we landed on a boundary and have an even number of values
            int next = cursor+1;
            while(uwo[next] <= 0){
                next++;
            }
            return computeMean(cursor, next);
        }else if(sum == half){//we landed on a boundary but have an odd number of values, the next bucket with an element is the median
            int next = cursor+1;
            while(uwo[next] <= 0){
                next++;
            }
            return next;
        }else{
            return cursor;
        }
    }
    private static float computeMean(int a, int b){
        return (a+b) / 2.0f;
    }
}