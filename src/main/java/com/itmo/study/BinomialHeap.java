package com.itmo.study;

import java.util.NoSuchElementException;

public class BinomialHeap<T extends Number & Comparable<T>> {
    public boolean LOG_ENABLED = false;

    private Node head;

    public void insert(T i) {
        Node newNode = new Node(i, null, null, 1);
        mergeWith(newNode);
    }

    public T getSmallest() {
        log("Heap is " + (head != null ? "NOT " : "") + "empty");
        if (head == null)
            throw new NoSuchElementException();

        Node curr = head;
        Node min = curr;
        log("Minimum initialized with " + min.value);

        while (curr.sibling != null) {
            boolean foundNewMin = min.value.compareTo(curr.sibling.value) > 0;

            String consequence = foundNewMin ? "new value set" : "value not changed";
            log("Minimum compared with " + curr.sibling.value + ": " + consequence);

            min = foundNewMin ? curr.sibling : min;
            curr = curr.sibling;
        }

        return min.value;
    }

    public T removeSmallest() {
        if (head == null)
            throw new NoSuchElementException();

        Node curr = head;
        Node prev = null;
        Node min = curr;
        log("Minimum initialized with " + min.value);

        while (curr.sibling != null) {
            String msg = "Minimum compared with " + curr.sibling.value + ": ";
            String consequence;

            if (min.value.compareTo(curr.sibling.value) > 0) {
                min = curr.sibling;
                prev = curr;

                consequence = "new value set";
            } else consequence = "value not changed";

            log(msg + consequence);
            curr = curr.sibling;
        }

        // remove smallest from the heap
        if (prev == null)
            head = min.sibling;
        else
            prev.sibling = min.sibling;

        log("Smallest value removed from the heap");
        logHeap();

        // merge the heap with the children of the removed
        log("Creating a new heap from the children of the removed node");

        Node childHeap = min.child;
        curr = childHeap;
        logHeap(curr);

        while (curr.sibling != null) {
            Node oldChildHeap = childHeap;
            childHeap = curr.sibling;
            Node next = curr.sibling.sibling;

            childHeap.sibling = oldChildHeap;
            curr.sibling = next;

            logHeap(curr);
        }

        log("Merging the heap with the children of the removed node");
        mergeWith(childHeap);
        return min.value;
    }

    private void mergeWith(Node other) {
        if (other == null) {
            return;
        }
        if (head == null) {
            head = other;
            logHeap();
            return;
        }

        // Merge heaps
        log("Merge step 1: adding all binomial trees to a single heap");
        Node newHead;
        if (head.degree < other.degree) {
            newHead = head;
            head = head.sibling;
        } else {
            newHead = other;
            other = other.sibling;
        }
        newHead.sibling = null;
        logHeap(newHead);

        Node curr = newHead;
        while (head != null && other != null) {
            if (head.degree < other.degree) {
                curr.sibling = head;
                head = head.sibling;
            } else {
                curr.sibling = other;
                other = other.sibling;
            }
            curr = curr.sibling;
            curr.sibling = null;
            logHeap(newHead);
        }

        Node tail = head != null ? head : other;
        while (tail != null) {
            curr.sibling = tail;
            tail = tail.sibling;
            curr = curr.sibling;
            curr.sibling = null;
            logHeap(newHead);
        }

        // Merge trees of same degree
        log("Merge step 2: merging the trees of same degree");
        curr = newHead;
        Node prev = null;
        while (curr.sibling != null) {
            if (curr.sibling.degree != curr.degree) {
                prev = curr;
                curr = curr.sibling; // 1 2 2 4
                continue;
            }

            Node smaller, greater;
            Node next = curr.sibling.sibling;
            if (curr.value.compareTo(curr.sibling.value) < 0) {
                smaller = curr;
                greater = curr.sibling;
            } else {
                smaller = curr.sibling;
                greater = curr;
            }

            smaller.sibling = next;
            greater.sibling = smaller.child;
            smaller.child = greater;
            if (prev == null) {
                newHead = smaller;
            } else {
                prev.sibling = smaller;
            }
            smaller.degree++;

            curr = smaller;
            logHeap(newHead);
        }

        head = newHead;
    }

    private class Node {
        private final T value;
        private Node sibling;
        private Node child;
        private int degree;

        public Node(T value, Node sibling, Node child, int degree) {
            this.value = value;
            this.sibling = sibling;
            this.child = child;
            this.degree = degree;
        }
    }

    private void log(String msg) {
        if (LOG_ENABLED)
            System.out.println(msg);
    }

    private void logHeap() {
        logHeap(head);
    }

    private void logHeap(Node heap) {
        if (!LOG_ENABLED) return;

        StringBuilder logBuilder = new StringBuilder();
        buildNodesStringIndented(heap, logBuilder, "");
        System.out.print(logBuilder.toString());
    }

    private void buildNodesStringIndented(Node node, StringBuilder builder, String indent) {
        if (node == null) {
//            builder.append("[]\n");
            builder.append("[]");
            builder.append(System.getProperty("line.separator"));
            return;
        }
//        builder.append("[\n");
        builder.append("[");
        builder.append(System.getProperty("line.separator"));

        while (node != null) {
            builder.append("  ").append(indent).append(node.value).append(" -> ");
            buildNodesStringIndented(node.child, builder, indent + "  ");
            node = node.sibling;
        }

//        builder.append(indent).append("]\n");
        builder.append(indent).append("]");
        builder.append(System.getProperty("line.separator"));
    }
}
