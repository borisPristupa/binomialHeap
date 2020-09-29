package com.itmo.study;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestBinomialHeapGetSmallest {
    private BinomialHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new BinomialHeap<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetSmallestFromEmptyHeap() {
        heap.getSmallest();
    }

    @Test
    public void testGetSmallestFromUsualHeap() {
        heap.insert(1);
        heap.insert(2);

        long smallest = heap.getSmallest();

        assertEquals(smallest, 1);
    }

    @Test
    public void testGetSmallestFromNegativeHeap() {
        heap.insert(-1);
        heap.insert(0);
        heap.insert(500);
        heap.insert(-2);

        long smallest = heap.getSmallest();

        assertEquals(smallest, -2);
    }

    @Test
    public void testGetSmallestFromBigHeap() {
        heap.insert(1);
        heap.insert(0);
        heap.insert(500);
        heap.insert(2);
        heap.insert(13);
        heap.insert(0);
        heap.insert(5000);
        heap.insert(20);
        heap.insert(90);
        heap.insert(11);
        heap.insert(40);
        heap.insert(5000);
        heap.insert(20);

        long smallest = heap.getSmallest();

        assertEquals(smallest, 20);
    }
}
