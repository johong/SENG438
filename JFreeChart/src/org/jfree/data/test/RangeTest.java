package org.jfree.data.test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class RangeTest
{
    private Range range1;
    private Range range2;
    private Range nullRange;

    @Before
    public void setUp() throws Exception
    {
        range1 = new Range(-2.2, 2.2);
        range2 = new Range(0.0, 3.0);
        nullRange = null;
    }

    @Test (expected=NullPointerException.class)
    public void testGetCentralValueOfNullRange()
    {
        nullRange.getCentralValue();
    }

    @Test
    public void testGetCentralValueOfValidRange()
    {
        final double result = range1.getCentralValue();
        final double expected = 0.0;

        assertEquals("Central value or range (-2.2, 2.2) should be 0.0\n", expected, result, .000000001d);
    }

    @Test (expected=NullPointerException.class)
    public void testContrainWithNullRange()
    {
        nullRange.constrain(0.1);
    }

    @Test
    public void testContrainWithValueOverRange()
    {
        final double overValue = range1.constrain(4.0);

        assertEquals("Constraining range (-2.2, 2.2) with 4.0 should be 2.2\n", 2.2, overValue, .000000001d);
    }

    @Test
    public void testContrainWithValueWithinRange()
    {
        final double withinValue = range1.constrain(1.0);

        assertEquals("Constraining range (-2.2, 2.2) with 1.0 should be 1.0\n", 1.0, withinValue, .000000001d);
    }

    @Test
    public void testContrainWithValueUnderRange() // Currently failing
    {
        final double underValue = range1.constrain(-3.0);

        assertEquals("Constraining range (-2.2, 2.2) with -3.0 should be -2.2\n", -2.2, underValue, .000000001d);
    }

    @Test (expected=NullPointerException.class)
    public void testContainsWithNullRange()
    {
        nullRange.contains(0.1);
    }

    @Test
    public void testContainsWithValueHigherThanRange()
    {
        final boolean higherValue = range1.contains(3.0);

        assertFalse("Checking if range (-2.2, 2.2) contains 3.0 should be false\n", higherValue);
    }

    @Test
    public void testContainsWithValueWithinRange()
    {
        final boolean withinValue = range1.contains(1.5);

        assertTrue("Checking if range (-2.2, 2.2) contains 1.5 should be true\n", withinValue);
    }

    @Test
    public void testContainsWithValueLowerThanRange()
    {
        final boolean lowerValue = range1.contains(-3.0);

        assertFalse("Checking if range (-2.2, 2.2) contains -3.0 should be false\n", lowerValue);
    }

    @Test (expected=NullPointerException.class)
    public void testEqualsWithNullRangeAndNullRange()
    {
        final Range nullRange2 = null;

        nullRange2.equals(nullRange);
    }

    @Test (expected=NullPointerException.class)
    public void testEqualsWithNullRangeAndValidRange()
    {
        nullRange.equals(range1);
    }

    @Test
    public void testEqualsWithValidRangeAndNullRange()
    {
        final boolean validRangeEqualsNullRange = range1.equals(nullRange);

        assertFalse("Checking if range (-2.2, 2.2) equals a null range should be false\n", validRangeEqualsNullRange);
    }

    @Test
    public void testEqualsForSameRange()
    {
        final Range sameRange = new Range(-2.2, 2.2);
        final boolean sameRangeResult = range1.equals(sameRange);

        assertTrue("Checking if range (-2.2, 2.2) equals range (-2.2, 2.2) should be true\n", sameRangeResult);
    }

    @Test
    public void testEqualsReturnsFalseForDifferentRange()
    {
        final Range differentRange = new Range(0.0, 1.0);
        final boolean differentRangeResult = range1.equals(differentRange);

        assertFalse("Checking if range (-2.2, 2.2) equals range (0.0, 1.0) should be false\n", differentRangeResult);
    }

    @Test
    public void testGetUpperBoundForValidRange() // Currently failing
    {
        final double upperBound = range1.getUpperBound();

        assertEquals("The upper bound of range (-2.2, 2.2) should be 2.2\n", 2.2, upperBound, .000000001d);
    }

    @Test (expected=NullPointerException.class)
    public void testGetUpperBoundForNullRange()
    {
        nullRange.getUpperBound();
    }

    @Test
    public void testGetLowerBoundForValidRange()
    {
        final double lowerBound = range1.getLowerBound();

        assertEquals("The lower bound of range (-2.2, 2.2) should be -2.2\n", -2.2, lowerBound, .000000001d);
    }

    @Test (expected=NullPointerException.class)
    public void testGetLowerBoundForNullRange()
    {
        nullRange.getLowerBound();
    }
}
