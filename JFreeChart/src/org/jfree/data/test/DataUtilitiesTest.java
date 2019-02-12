package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jmock.Expectations;
import org.jmock.Mockery;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {

    Mockery mockValues2D;
    Mockery mockKeyedValues;
    Values2D values2D;
    KeyedValues keyedValues;

    @Before
    public void setUp() throws Exception
    {
        mockValues2D = new Mockery();
        values2D = mockValues2D.mock(Values2D.class);

        mockKeyedValues = new Mockery();
        keyedValues = mockKeyedValues.mock(KeyedValues.class);
    }

    @Test
    public void testCalculateColumnTotalReturnsCorrectColumnTotal()
    {
        mockValues2D.checking(new Expectations()
        {
            {
                one(values2D).getRowCount();
                will(returnValue(2));

                one(values2D).getValue(0, 0);
                will(returnValue(7.5));

                one(values2D).getValue(1, 0);
                will(returnValue(2.5));
            }
        });

        final double result = DataUtilities.calculateColumnTotal(values2D, 0);

        assertEquals(10.0, result, .000000001d);
    }

    @Test
    public void testCalculateRowTotalReturnsCorrectRowTotal() // Currently Failing
    {
        mockValues2D.checking(new Expectations()
        {
            {
                one(values2D).getColumnCount();
                will(returnValue(2));

                one(values2D).getRowCount();
                will(returnValue(1));

                one(values2D).getValue(0, 0);
                will(returnValue(5.5));

                one(values2D).getValue(0, 1);
                will(returnValue(6.5));
            }
        });

        final double result = DataUtilities.calculateRowTotal(values2D,  0);

        assertEquals(12.0, result, .000000001d);
    }

    @Test
    public void testCreateNumberArrayCreatesArrayWithGivenArgument() // Currently Failing
    {
        final double[] data = new double[] {6.1, 2.6, 5.5, 10.0};
        final Number[] results = DataUtilities.createNumberArray(data);
        final Number[] expected = new Number[] {6.1, 2.6, 5.5, 10.0};

        assertEquals(4, results.length);
        assertEquals(expected, results);
    }

    @Test
    public void testCreateNumberArray2DCreates2DArrayWithGivenArgument() // Currently Failing
    {
        final double[][] data = new double[][] {{0.0, 1.2, 2.4}, {3.6, 4.8, 10.0}};

        final Number[][] results = DataUtilities.createNumberArray2D(data);
        final Number[][] expected = new Number[][] {{0.0, 1.2, 2.4}, {3.6, 4.8, 10.0}};

        assertEquals(3, results[0].length);
        assertEquals(3, results[1].length);
        assertEquals(2, results.length);
        assertEquals(expected, results);
    }

    @Test
    public void testGetCumulativePercentagesCumulatesPercentages() // Currently Failing
    {
        mockKeyedValues.checking(new Expectations()
        {
            {
                atLeast(2).of(keyedValues).getItemCount();
                will(returnValue(2));

                atLeast(1).of(keyedValues).getKey(0);
                will(returnValue(0));

                atLeast(1).of(keyedValues).getValue(0);
                will(returnValue(3));

                atLeast(1).of(keyedValues).getKey(1);
                will(returnValue(1));

                atLeast(1).of(keyedValues).getValue(1);
                will(returnValue(7));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        final double expectedValue1 = ((keyedValues.getValue(0)).intValue() / keyedValues.getItemCount());
        final double expectedValue2 = (((keyedValues.getValue(0)).intValue() + (keyedValues.getValue(1)).intValue()) /
                                   keyedValues.getItemCount());

        assertEquals(expectedValue1, result.getValue(0));
        assertEquals(expectedValue2, result.getValue(1));
    }
}
