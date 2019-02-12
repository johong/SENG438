package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class RangeTest 
{
    private Range range1;
    private Range range2;

    @Before
    public void setUp() throws Exception
    {
        range1 = new Range(-2.2, 2.2);
        range2 = new Range(0.0, 3.0);
    }

    @Test
    public void testGetCentralValueReturnsCentralValue()
    {
        final double result = range1.getCentralValue();
        final double expected = 0.0;

        assertEquals(expected, result, .000000001d);
    }

    @Test
    public void testContrainWithValueOverRange()
    {
        final double overValue = range1.constrain(4.0);

        assertEquals(2.2, overValue, .000000001d);
    }

    @Test
    public void testContrainWithValueWithinRange()
    {
        final double withinValue = range1.constrain(1.0);

        assertEquals(1.0, withinValue, .000000001d);
    }

    @Test
    public void testContrainWithValueUnderRange() // Currently failing
    {
        final double underValue = range1.constrain(-3.0);

        assertEquals(-2.2, underValue, .000000001d);
    }

    @Test
    public void testContainsWithValueHigherThanRange()
    {
        final boolean higherValue = range1.contains(3.0);

        assertFalse(higherValue);
    }

    @Test
    public void testContainsWithValueWithinRange()
    {
        final boolean withinValue = range1.contains(1.5);

        assertTrue(withinValue);
    }

    @Test
    public void testContainsWithValueLowerThanRange()
    {
        final boolean lowerValue = range1.contains(-3.0);

        assertFalse(lowerValue);
    }

    @Test
    public void testEqualsReturnsTrueForSameRange()
    {
        final Range sameRange = new Range(-2.2, 2.2);
        final boolean sameRangeResult = range1.equals(sameRange);

        assertTrue(sameRangeResult);
    }

    @Test
    public void testEqualsReturnsFalseForDifferentRange()
    {
        final Range differentRange = new Range(0.0, 1.0);
        final boolean differentRangeResult = range1.equals(differentRange);

        assertFalse(differentRangeResult);
    }

    @Test
    public void testGetUpperBoundReturnsUpperBound() // Currently failing
    {
        final double upperBound = range1.getUpperBound();

        assertEquals(2.2, upperBound, .000000001d);
    }

    @Test
    public void testGetLowerBoundReturnsLowerBound()
    {
        final double lowerBound = range1.getLowerBound();

        assertEquals(-2.2, lowerBound, .000000001d);
    }

}
