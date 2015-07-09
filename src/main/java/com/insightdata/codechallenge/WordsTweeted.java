package com.insightdata.codechallenge;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class WordsTweeted {
    private static final Pattern WHITESPACE = Pattern.compile( "\\s" );

    public static void main( String... args ) {

        if (args.length < 1){
            System.err.println( "Usage: WordsTweeted <input_file> > <output_file>" );
            System.exit(1);
        }
        try(BufferedWriter writer = new BufferedWriter( new OutputStreamWriter (System.out ));){
            SparkConf conf = new SparkConf().setAppName( "WordsTweeted" );
            JavaSparkContext spark = new JavaSparkContext( conf );

            JavaRDD<String> text_file = spark.textFile( args[0], 1 );//turn our input into RDD
            JavaRDD<String> words = text_file.flatMap( line -> Arrays.asList(
                            WHITESPACE.split( line )));//separate into words
            JavaPairRDD<String, Integer> counts = words.mapToPair(
                            w -> new Tuple2<String, Integer>( w, 1 ))
                            .reduceByKey(( x, y ) -> x + y );//combine words and count
            //sort by the key(unique word) and collect the results to print to a file
            //note that for large amounts of tweets, collecting could be problematic
            List<Tuple2<String, Integer>> output = counts.sortByKey().collect();
            for ( Tuple2<String,Integer> tuple : output ){
                writer.write( tuple._1() + " " + tuple._2() );//print results
                writer.newLine();
                writer.flush();
            }
            spark.stop();
        }catch(IOException e){
            System.err.println( "ERROR: Could not write to stdout" );
        }
    }
}
