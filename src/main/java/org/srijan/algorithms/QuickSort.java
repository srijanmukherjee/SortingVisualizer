package org.srijan.algorithms;

import java.util.Random;
import java.util.Stack;

public class QuickSort extends SortMachine {
    private final Stack<MergeParams> mergeStack;
    private int x;
    private int i;
    private int j;
    private int lo;
    private int hi;
    private MergeParams currentParams = null;
    public QuickSort(int n, int lo, int hi) {
        super(n, lo, hi);
        mergeStack = new Stack<>();
        mergeStack.push(new MergeParams(0, n - 1));
    }

    public QuickSort(int[] data) {
        super(data);
        shuffle();
        mergeStack = new Stack<>();
        mergeStack.push(new MergeParams(0, data.length - 1));
    }

    @Override
    public void next() {
        if (isCompleted()) return;
        if (currentParams == null) {
            currentParams = mergeStack.peek();
            lo = currentParams.lo;
            hi = currentParams.hi;
            x = data[lo];
            i = hi + 1;
            j = hi;
        }

        if (j > lo) {
            if (data[j] > x) exch(--i, j);
            j--;
            return;
        }

        exch(i - 1, lo);
        mergeStack.pop();

        int pivot = i - 1;
        if (pivot + 1 < hi) mergeStack.push(new MergeParams(pivot + 1, hi));
        if (lo < pivot - 1) mergeStack.push(new MergeParams(lo, pivot - 1));
        currentParams = null;
    }

    private void exch(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public boolean isCompleted() {
        return mergeStack.empty();
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == lo;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return !isCompleted() && (x == j || x == i);
    }

    private void shuffle() {
        Random rand = new Random();
        for (int i = 1; i < data.length; i++) {
            int j = rand.nextInt(i);
            int temp = data[j];
            data[j] = data[i];
            data[i] = temp;
        }
    }

    static class MergeParams {
        int lo;
        int hi;

        MergeParams(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }
}
