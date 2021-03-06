package com.insightdata.codechallenge;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.text.DecimalFormat;

public class MedianUnique{
    private static final Pattern WHITESPACE = Pattern.compile( "\\s" );
    private static final DecimalFormat fmt = new DecimalFormat( "0.##" );
    public static void main( String... args ) {
        try(BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter (System.out ));){
            long[] uniqueWordOccurrences = new long[70];//buckets for storing frequency of occurences
            long numLines = 0;
            String line = reader.readLine();
            while( line != null ){//read all lines
                numLines++;//counting number of lines begins at 1
                String[] words = WHITESPACE.split( line );
                int numUnique = new HashSet<String>(Arrays.asList( words )).size();
                uniqueWordOccurrences[numUnique]++;
                writer.write(fmt.format(computeMedian( uniqueWordOccurrences, numLines )));
                writer.newLine();
                writer.flush();
                line = reader.readLine();
            }
        }catch( IOException e ) {
            System.err.println( "ERROR: Could not read from stdin. "
                                + "Usage: MedianUnique < <input_file> > <output_file>" );
        }
    }
    /**
    * The computeMedian method represents an algorithm specific to the problem of
    * computing a median every time a new value is added to an array of occurences. 
    * Constraints are that values are in a finite set of natural numbers. How often
    * a value is seen is tracked in uwo.
    * @param  uwo        the unique wordcount occurrences
    * @param  totalSeen the total occurrences seen (this will be the sum of all uwo elements)
    * @return            the median
    **/
    public static float computeMedian( long[] uwo, long totalSeen ) {
        long half = totalSeen/2;
        int cursor = 0;
        long sum = 0;
        if( totalSeen == 1 ){//special case, first entry
            while( uwo[cursor] <= 0 ){
                cursor++;
            }
            return cursor;
        }
        while( sum < half ){//sum the elements until we reach the middle value
            sum += uwo[cursor];
            if( sum < half ){//only move our cursor if we aren't done
                cursor++;
            }
        }
        if( sum == half && totalSeen%2 == 0 ){//we landed on a boundary and have an even number of values
            int next = cursor+1;
            while( uwo[next] <= 0 ) {
                next++;
            }
            return computeMean(cursor, next);
        }else if( sum == half ){//we landed on a boundary but have an odd number of values, 
                               //the next bucket with an element is the median
            int next = cursor+1;
            while( uwo[next] <= 0 ){
                next++;
            }
            return next;
        }else{
            return cursor;
        }
    }
    /**
    * computeMean is a helper method for the computeMedian method
    * @param     a first number to be averaged
    * @param     b second number to be averaged 
    **/
    private static float computeMean(int a, int b) {
        return (a+b) / 2.0f;
    }
}