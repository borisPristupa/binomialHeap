package com.itmo.study;

import com.itmo.study.verification.BinomialHeapTestingSuit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;

public class TestBinomialHeapRemoveSmallest {
    private BinomialHeap<Integer> heap;
    private final BinomialHeapTestingSuit suit = new BinomialHeapTestingSuit();

    @Before
    public void setUp() {
        heap = new BinomialHeap<>();

        suit.init();
    }

    @After
    public void tearDown() {
        suit.exit();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyHeap() {
        heap.removeSmallest();
    }

    @Test
    public void testRemoveFromSingleElementHeap() {
        heap.insert(1);

        heap.LOG_ENABLED = true;
        long removedValue = heap.removeSmallest();

        suit.assertOutputMatches(SINGLE);
        assertEquals("The deleted item has the correct value", removedValue, 1);
    }

    @Test
    public void testRemoveFromUsualHeap() {
        heap.insert(1);
        heap.insert(2);
        heap.insert(2);

        heap.LOG_ENABLED = true;
        int smallest = heap.removeSmallest();

        suit.assertOutputMatches(USUAL);
        assertEquals("The deleted item has the correct value", smallest, 1);
    }

    @Test
    public void testRemoveFromHeapWithSeparateSmallestElement() {
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        heap.LOG_ENABLED = true;
        int smallest = heap.removeSmallest();

        suit.assertOutputMatches(WITH_SEPARATE_SMALLEST_ELEMENT);
        assertEquals("The deleted item has the correct value", smallest, 1);
    }

    @Test
    public void testRemoveFromHeapWithRootSmallestElement() {
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.insert(5);

        heap.LOG_ENABLED = true;
        int smallest = heap.removeSmallest();

        suit.assertOutputMatches(WITH_ROOT_SMALLEST_ELEMENT);
        assertEquals("The deleted item has the correct value", smallest, 1);
    }

    @Test
    public void testRemoveRootOfOneTree() {
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);

        heap.LOG_ENABLED = true;
        int smallest = heap.removeSmallest();

        suit.assertOutputMatches(ROOT_OF_ONE_TREE);
        assertEquals("The deleted item has the correct value", smallest, 1);
    }

    private static final String USUAL = "remove/usual heap.txt";
    private static final String SINGLE = "remove/single.txt";
    private static final String WITH_SEPARATE_SMALLEST_ELEMENT = "remove/with separate smallest element.txt";
    private static final String WITH_ROOT_SMALLEST_ELEMENT = "remove/with root smallest element.txt";
    private static final String ROOT_OF_ONE_TREE = "remove/root of one tree.txt";
}
