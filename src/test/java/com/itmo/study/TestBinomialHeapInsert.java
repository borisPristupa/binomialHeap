package com.itmo.study;

import com.itmo.study.verification.BinomialHeapTestingSuit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBinomialHeapInsert {
    private BinomialHeap<Integer> heap;
    private final BinomialHeapTestingSuit suit = new BinomialHeapTestingSuit();

    @Before
    public void setUp() {
        heap = new BinomialHeap<>();
        heap.LOG_ENABLED = true;

        suit.init();
    }

    @After
    public void tearDown() {
        suit.exit();
    }

    @Test
    public void testInsertSingle() {
        heap.insert(1);
        suit.assertOutputMatches(SINGLE);
    }

    @Test
    public void testInsertWithSingleMerge() {
        heap.insert(1);
        heap.insert(2);
        suit.assertOutputMatches(WITH_SINGLE_MERGE);
    }

    @Test
    public void testInsertWithSingleMergeInverseOrder() {
        heap.insert(2);
        heap.insert(1);
        suit.assertOutputMatches(WITH_SINGLE_MERGE_INVERSE_ORDER);
    }

    @Test
    public void testInsertMany() {
        heap.insert(1);
        heap.insert(5);
        heap.insert(4);
        heap.insert(2);
        heap.insert(-4);
        heap.insert(0);
        heap.insert(20);
        heap.insert(1);
        suit.assertOutputMatches(MANY);
    }

    @Test
    public void testInsertDuplicates() {
        heap.insert(1);
        heap.insert(1);
        heap.insert(1);
        heap.insert(1);
//        heap.insert(1);
        suit.assertOutputMatches(DUPLICATES);
    }

    private static final String SINGLE = "insert/single.txt";
    private static final String WITH_SINGLE_MERGE = "insert/with single merge.txt";
    private static final String WITH_SINGLE_MERGE_INVERSE_ORDER = "insert/with single merge inverse order.txt";
    private static final String MANY = "insert/many.txt";
    private static final String DUPLICATES = "insert/duplicates.txt";
}
