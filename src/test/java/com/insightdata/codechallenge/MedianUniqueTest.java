package com.insightdata.codechallenge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MedianUnique.
 */
public class MedianUniqueTest 
    extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MedianUniqueTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( MedianUniqueTest.class );
    }

    public void testNormalComputeMedian() {
        long[] values = { 10l, 10l, 11l };
        long element_sum = 31;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 1.0f );
    }
    public void testOneElementComputeMedian() {
        long[] values = { 0l, 1l, 0l };
        long element_sum = 1;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 1.0f );
    }
    public void testEmptyBucketEvenComputeMedian() {
        long[] values = { 12l, 3l, 0l, 10l };
        long element_sum = 30;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 2.0f );
    }
    public void testEmptyBucketOddComputeMedian() {
        long[] values = { 12l, 8l, 0l, 1l, 20l };
        long element_sum = 41;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 3.0f );
    }
    public void testLastBucketEvenComputeMedian() {
        long[] values = { 12l, 0l, 0l, 18l };
        long element_sum = 30;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 3.0f );
    }
    public void testLastBucketOddComputeMedian() {
        long[] values = { 12l, 0l, 0l, 17l };
        long element_sum = 29;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 3.0f );
    }public void testFirstBucketEvenComputeMedian() {
        long[] values = { 12l, 0l, 0l, 2l };
        long element_sum = 14;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 0.0f );
    }
    public void testFirstBucketOddComputeMedian() {
        long[] values = { 2l, 1l, 0l, 0l };
        long element_sum = 3;
        assertEquals( MedianUnique.computeMedian(values, element_sum), 0.0f );
    }
}
