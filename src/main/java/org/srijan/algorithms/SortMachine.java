package org.srijan.algorithms;

import java.util.Random;

public abstract class SortMachine {
    protected int[] data;

    public SortMachine(int n, int lo, int hi) {
        data = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++)
            data[i] = rand.nextInt(hi - lo) + lo;
    }

    public SortMachine(int[] data) {
        this.data = new int[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    public abstract void next();
    public abstract boolean isCompleted();
    public abstract boolean isTargetElement(int x);
    public abstract boolean isComparedToElement(int x);

    public int getValue(int i) {
        return data[i];
    }

    public int size() {
        return data.length;
    }
}
