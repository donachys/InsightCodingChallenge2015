# InsightCodingChallenge2015
Insight Data Engineering Coding Challenge

##Dependencies 

| Name   |     Version |
|:---------:|:--------------:|
| Apache Spark | 1.4.0 |
| Apache Maven | 3.3.0 |
| Java  | 1.8 |

Tested on:
OS X Yosemite 10.10.4
Java 1.8.0_31

##Instructions:

Download Maven ... [TODO]
Install Maven ...[TODO]

Download Spark ...[TODO]
Install Spark ...[TODO]

clone this repository and move the InsightCodingChallenge2015 directory to the spark directory

execute run.sh with ./run.sh

##MedianUnique Algorithm Description

The MedianUnique implementation is based on counting sort.
The number of unique words for a given tweet are produced by splitting the tweet on white spaces and then constructing a HashSet from the resulting array of Strings. The HashSet will prevent duplicate words, and the number of elements remaining is the unique word count. Next, since we know that a tweet can not be more than 140 characters, the maximum number of unique words that may exist in a tweet is 70 (single character words followed by single white spaces). We also know that the result of a count will be from the set of natural numbers. Taking advantage of this knowledge we can keep a count of the number of times we have seen each of the unique word counts from 1 to 70. We also keep track of the number of tweets we have seen. When we need to compute the median (after every tweet is parsed), we can go through the elements of each bucket until the sum of elements is greater than or equal to half of the tweets we have seen so far. This will indicate which bucket the median resides in. The index of the bucket that the median resides in is the median. There are special cases for when the average of two buckets must be computed, and when there is only one element. 

##Acknowledgements:

Thank you to Apache Spark for providing the distributed computing software and the starting example for this word counting solution.

Thanks to the Insight Data Engineering group for providing this opportunity.
