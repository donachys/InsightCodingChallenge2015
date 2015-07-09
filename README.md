# InsightCodingChallenge2015
Insight Data Engineering Coding Challenge

##Dependencies 

| Name   |     Version |
|:---------:|:--------------:|
| Apache Spark | 1.4.0 |
| Apache Maven | 3.3.3 |
| Java  | 1.8 |

Tested on:
OS X Yosemite 10.10.4
Java 1.8.0_31

##Instructions:

Download Maven 3.3.3: see https://maven.apache.org/download.cgi

Install Maven 3.3.3: see https://maven.apache.org/install.html

Download Spark 1.4.0: see https://spark.apache.org/downloads.html select pre-built for hadoop 2.6 and later

Install Spark 1.4.0: see https://spark.apache.org/docs/latest/ 

Extract spark, clone this repository into the spark-1.4.0-bin-hadoop2.6 directory

cd to the InsightCodingChallenge2015 directory (within the spark-1.4.0-bin-hadoop2.6 directory) and execute run.sh with ./run.sh

##WordsTweeted Description

I chose to use Apache Spark for this problem. Spark is awesome. Spark permits the counting of unique words for this problem to be done in parallel on a cluster. Then, the word sorting can also be done in parallel, so it scales well. Thanks to Resilient Distributed Datasets, the system is resistant to failures on a node. I hope to learn more about Spark and map/reduce. I implemented a very similar version to the WordCount example at http://spark.apache.org/examples.html using Java 8 lambda expressions.

##MedianUnique Algorithm Description

The MedianUnique implementation is based on counting sort. We will keep track of the number of times that we have seen tweets with 1 unique word, tweets with 2 unique words, and so on.

The number of unique words for a given tweet are produced by splitting the tweet on white spaces and then constructing a HashSet from the resulting array of Strings. The HashSet will prevent duplicate words, and the number of elements remaining is the unique word count for that tweet.

Next, since we know that a tweet can not be more than 140 characters, the maximum number of unique words that may exist in a tweet is 70 (single character words followed by single white spaces) so we only need 70 'buckets'. We also know that the result of a count will be from the set of natural numbers. Taking advantage of this knowledge we can keep a count of the number of times we have seen each of the unique word counts from 1 to 70. We also separately keep track of the total number of tweets we have seen. When we need to compute the median (after every tweet is parsed), we can go through the elements of each bucket until the sum of elements is greater than or equal to half of the tweets we have seen so far. This will indicate which bucket the middle value resides in. The index of the bucket that the middle value resides in is the median. There are special cases for when the average of two buckets must be computed, and when there is only one element. 

Inserting a new unique word count in this way is an O(1) operation. Computing the median will in the worst case involve summing over 70 buckets, no matter how many tweets we see. Since we do not need to keep track of one number for every tweet we increase our memory efficiency.

##Acknowledgements:

Thank you to Apache Spark for providing the distributed computing software and the starting example for this word counting solution.

Thanks to the Insight Data Engineering group for providing this opportunity.
