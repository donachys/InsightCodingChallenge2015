package com.insightdata.codechallenge;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.Writer;

import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordsTweeted {
    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    public static void main( String... args ) {

        if (args.length < 1) {
            System.err.println("Usage: WordsTweeted <input_file> > <output_file>");
            System.exit(1);
        }

        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("WordCount");
        JavaSparkContext spark = new JavaSparkContext(conf);

        JavaRDD<String> text_file = spark.textFile(args[0], 1);
        JavaRDD<String> words = text_file.flatMap(line -> Arrays.asList(
                        WHITESPACE.split(line)));
        JavaPairRDD<String, Integer> counts = words.mapToPair(
                        w -> new Tuple2<String, Integer>(w, 1))
                        .reduceByKey((x, y) -> x + y);


        List<Tuple2<String, Integer>> output = counts.sortByKey().collect();
        for (Tuple2<String,Integer> tuple : output) {
            System.out.println(tuple._1() + " " + tuple._2());
        }
        spark.stop();
    }
}
