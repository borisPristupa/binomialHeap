package com.itmo.study;

import com.itmo.study.verification.BinomialHeapTestingSuit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBinomialHeapDouble {
    private BinomialHeap<Double> heap;
    private final BinomialHeapTestingSuit suit = new BinomialHeapTestingSuit();

    @Before
    public void setUp() {
        heap = new BinomialHeap<>();
        suit.init();
    }

    @Test
    public void testGetSmallestFromDoubleHeap() {
        heap.insert(1.01);
        heap.insert(1.02);
        heap.insert(2.0);
        heap.insert(2.0);

        double smallest = heap.getSmallest();

        assertEquals(smallest, 1.01, 0);
    }

    @Test
    public void testInsertDoubleHeap() {
        heap.LOG_ENABLED = true;

        heap.insert(2.0);
        heap.insert(1.0);
        heap.insert(3.3);
        heap.insert(3.5);
        heap.insert(3.54);

        suit.assertOutputMatches(INSERT);
    }

    @Test
    public void testRemoveRootOfOneTreeDoubleHeap() {
        heap.insert(1.03);
        heap.insert(1.05);
        heap.insert(1.03);
        heap.insert(1.01);
        heap.insert(1.04);
        heap.insert(1.05);
        heap.insert(1.02);

        heap.LOG_ENABLED = true;
        double smallest = heap.removeSmallest();

        suit.assertOutputMatches(REMOVE);
        assertEquals("The deleted item has the correct value", smallest, 1.01, 0);
    }


    private static final String INSERT = "double/insert.txt";
    private static final String REMOVE = "double/remove.txt";
}
