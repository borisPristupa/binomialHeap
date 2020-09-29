package com.itmo.study;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBinomialHeapGetSmallest {
    private BinomialHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new BinomialHeap<>();
    }

    @Test
    public void testInsertWithSingleMerge() {
        heap.insert(1);
        heap.insert(2);

        long smallest = heap.getSmallest();

        assertEquals("", smallest, 1);
    }
}
