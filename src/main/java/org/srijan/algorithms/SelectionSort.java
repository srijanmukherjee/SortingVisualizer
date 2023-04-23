package org.srijan.algorithms;

public class SelectionSort extends SortMachine {
    private int i;
    private int j;
    private int min;

    public SelectionSort(int[] data) {
        super(data);
        i = 0;
        j = 1;
        min = i;
    }

    @Override
    public void next() {
        if (isCompleted()) return;

        if (j < data.length) {
            if (data[j] < data[min])
                min = j;

            j++;
            return;
        }

        if (min != i) {
            int temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }

        i++;
        min = i;
        j = i + 1;
    }

    @Override
    public boolean isCompleted() {
        return i >= data.length - 1;
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == i;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return !isCompleted() && (x == j || x == min);
    }
}
