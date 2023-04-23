package org.srijan.algorithms;

public class InsertionSort extends SortMachine {
    private int i;
    private int j;
    private int key;

    public InsertionSort(int n, int lo, int hi) {
        super(n, lo, hi);
        i = 1;
        j = 0;
        key = data[i];
    }

    public InsertionSort(int[] data) {
        super(data);
        i = 1;
        j = 0;
        key = data[i];
    }

    @Override
    public void next() {
        if (isCompleted()) return;

        if (j >= 0 && data[j] > key) {
            data[j + 1] = data[j];
            j--;
            return;
        }

        data[j + 1] = key;
        i++;
        j = i - 1;

        if (i < data.length)
            key = data[i];
    }

    @Override
    public boolean isCompleted() {
        return i >= data.length;
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == i;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return !isCompleted() && x == j;
    }
}
