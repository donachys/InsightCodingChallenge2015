package com.insightdata.codechallenge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MedianUnique.
 */
public class MedianUniqueTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MedianUniqueTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MedianUniqueTest.class );
    }

    /**
     * 
     */
    public void testComputeMedian()
    {
        long[] values = {10l, 10l, 11l};
        long element_sum = 31;
        System.err.println(MedianUnique.computeMedian(values, element_sum));
        assertTrue( MedianUnique.computeMedian(values, element_sum) == 1.0f );
    }
}
