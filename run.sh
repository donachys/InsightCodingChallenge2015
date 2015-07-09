#!/usr/bin/env bash

mvn package
../bin/spark-submit --master local[*] --class com.insightdata.codechallenge.WordsTweeted ./target/CodingChallenge-1.0.jar ./tweet_input/tweets.txt > ./tweet_output/ft1.txt
java -cp ./target/CodingChallenge-1.0.jar com.insightdata.codechallenge.MedianUnique < ./tweet_input/tweets.txt > ./tweet_output/ft2.txt
