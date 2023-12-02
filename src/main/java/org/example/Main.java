package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        final long start = 10000;
        final long end = 100000;
        final long delta = 10000;

        new ListTest(new ArrayList<>()).test(start, end, delta);
        System.out.println();
        new ListTest(new LinkedList<>()).test(start, end, delta);
    }
}