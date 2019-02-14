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
    //No tests for null values since null is not permitted, in accordance with JavaDOCS

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
    public void testCalculateColumnTotalForASingleColumn()
    {
        mockValues2D.checking(new Expectations()
        {
            {
                one(values2D).getRowCount();
                will(returnValue(1));

                one(values2D).getValue(0, 0);
                will(returnValue(7.5));
            }
        });

        final double result = DataUtilities.calculateColumnTotal(values2D, 0);

        assertEquals("Column Total of 7.5 should be 7.5\n", 7.5, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotalForMultipleColumns()
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

        assertEquals("Column Total of 7.5 and 2.5 should be 10.0\n", 10.0, result, .000000001d);
    }

    @Test
    public void testCalculateRowTotalForASingleRow() // Currently Failing
    {
        mockValues2D.checking(new Expectations()
        {
            {
                one(values2D).getColumnCount();
                will(returnValue(1));

                one(values2D).getRowCount();
                will(returnValue(1));

                one(values2D).getValue(0, 0);
                will(returnValue(5.5));

            }
        });

        final double result = DataUtilities.calculateRowTotal(values2D,  0);

        assertEquals("Row Total of 5.5 should be 5.5\n", 5.5, result, .000000001d);
    }

    @Test
    public void testCalculateRowTotalForMultipleRows() // Currently Failing
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

        assertEquals("Row Total of 5.5 and 6.5 should be 12.0\n", 12.0, result, .000000001d);
    }

    @Test
    public void testCreateNumberArrayCreatesArrayWithGivenArgument() // Currently Failing
    {
        final double[] data = new double[] {6.1, 2.6, 5.5, 10.0};
        final Number[] results = DataUtilities.createNumberArray(data);
        final Number[] expected = new Number[] {6.1, 2.6, 5.5, 10.0};

        assertEquals("Size of the resulting array should be 4\n", 4, results.length);
        assertEquals("Elements of the resulting array should be the same as those passed in\n", expected, results);
    }

    @Test
    public void testCreateNumberArray2DCreates2DArrayWithGivenArgument() // Currently Failing
    {
        final double[][] data = new double[][] {{0.0, 1.2, 2.4}, {3.6, 4.8, 10.0}};

        final Number[][] results = DataUtilities.createNumberArray2D(data);
        final Number[][] expected = new Number[][] {{0.0, 1.2, 2.4}, {3.6, 4.8, 10.0}};

        assertEquals("Size of the first array in the resulting 2D array should be 3\n", 3, results[0].length);
        assertEquals("Size of the second array in the resulting 2D array should be 3\n", 3, results[1].length);
        assertEquals("Size of the resulting 2D array should be 2\n", 2, results.length);
        assertEquals("Elements of the resulting 2D array should be the same as those passed in\n", expected, results);
    }

    @Test
    public void testGetCumulativePercentagesCumulatesOneValue() // Currently Failing
    {
        mockKeyedValues.checking(new Expectations()
        {
            {
                atLeast(1).of(keyedValues).getItemCount();
                will(returnValue(1));

                atLeast(1).of(keyedValues).getKey(0);
                will(returnValue(0));

                atLeast(1).of(keyedValues).getValue(0);
                will(returnValue(3));
            }
        });

        
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        double totalValue = 0.0;

        for (int i = 0; i < keyedValues.getItemCount(); i++) {
            totalValue += (keyedValues.getValue(i)).intValue();
        }

        // Calculating this based on the example shown in the JavaDOCS
        final double expectedValue = (keyedValues.getValue(0)).intValue() / totalValue;

        assertEquals("Cumulative percentage of a single value should be 1.0\n", expectedValue, result.getValue(0));
    }

    @Test
    public void testGetCumulativePercentagesCumulatesTwoValues() // Currently Failing
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

        double totalValue = 0.0;

        for (int i = 0; i < keyedValues.getItemCount(); i++) {
            totalValue += (keyedValues.getValue(i)).intValue();
        }

        // Calculating this based on the example shown in the JavaDOCS
        final double expectedValue1 = ((keyedValues.getValue(0)).intValue() / totalValue);
        final double expectedValue2 = (((keyedValues.getValue(0)).intValue() + (keyedValues.getValue(1)).intValue()) / totalValue);

        assertEquals("Cumulative percentage of the first value should be 0.3\n", expectedValue1, result.getValue(0));
        assertEquals("Cumulative percentage of all values should be 0.1\n", expectedValue2, result.getValue(1));
    }
}
