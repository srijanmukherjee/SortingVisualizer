package org.srijan.algorithms;

public class HeapSort extends SortMachine {
    private boolean creatingHeap = true;
    private int k;
    private int n;

    public HeapSort(int[] data) {
        super(data);
        k = data.length / 2;
        n = data.length;
    }

    @Override
    public void next() {
        if (isCompleted()) return;

        // max heapify phase
        if (creatingHeap) {
            nextCreateHeap();
            return;
        }

        // sorting phase
        if (n > 1) {
            int swap = data[0];
            data[0] = data[--n];
            data[n] = swap;
            sink(1);
        }
    }

    private void nextCreateHeap() {
        if (!creatingHeap || k < 1) return;

        sink(k--);

        if (k < 1) creatingHeap = false;
    }

    private void sink(int x) {
        while (2 * x <= n) {
            int j = 2 * x;
            if (j < n && data[j - 1] < data[j]) j++;
            if (data[x - 1] >= data[j - 1]) break;
            int temp = data[j - 1];
            data[j - 1] = data[x - 1];
            data[x - 1] = temp;
            x = j;
        }
    }

    @Override
    public boolean isCompleted() {
        return k < 1 && n == 1;
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == k;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return !isCompleted() && x == n;
    }
}
