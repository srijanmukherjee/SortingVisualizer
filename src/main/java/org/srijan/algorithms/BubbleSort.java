package org.srijan.algorithms;

public class BubbleSort extends SortMachine {
    private int i;
    private int j;

    public BubbleSort(int[] data) {
        super(data);
        i = 0;
        j = i;
    }

    @Override
    public void next() {
        if (isCompleted()) return;

        if (j < data.length - 1 - i) {
            if (data[j] > data[j + 1]) {
                int temp = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temp;
            }

            j++;
            return;
        }

        i++;
        j = 0;
    }

    @Override
    public boolean isCompleted() {
        return i >= data.length - 1;
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == j;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return false;
    }
}
