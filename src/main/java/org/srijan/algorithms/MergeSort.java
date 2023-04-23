package org.srijan.algorithms;

import java.util.Stack;

public class MergeSort extends SortMachine {
    private final int[] aux;
    private boolean copiedToAux = false;
    private final Stack<MergeParams> mergeStack;
    private int k;
    private int i;
    private int j;

    public MergeSort(int n, int lo, int hi) {
        super(n, lo, hi);
        aux = new int[n];
        mergeStack = new Stack<>();
        createMergeOrder(0, n - 1);
    }

    public MergeSort(int[] data) {
        super(data);
        aux = new int[data.length];
        mergeStack = new Stack<>();
        createMergeOrder(0, data.length - 1);
    }

    @Override
    public void next() {
        if (isCompleted()) return;

        MergeParams params = mergeStack.peek();
        int lo = params.lo;
        int hi = params.hi;
        int mid = params.mid;

        if (!copiedToAux && hi + 1 - lo >= 0) {
            System.arraycopy(data, lo, aux, lo, hi + 1 - lo);
            k = lo;
            i = lo;
            j = mid + 1;
            copiedToAux = true;
        }

        if (i > mid) data[k] = aux[j++];
        else if (j > hi) data[k] = aux[i++];
        else if (aux[i] < aux[j]) data[k] = aux[i++];
        else data[k] = aux[j++];

        k++;

        if (k > hi) {
            copiedToAux = false;
            mergeStack.pop();
        }
    }

    private void createMergeOrder(int lo, int hi) {
        if (lo >= hi) return;

        int mid = lo + (hi - lo) / 2;
        mergeStack.push(new MergeParams(lo, mid, hi));
        createMergeOrder(mid + 1, hi);
        createMergeOrder(lo, mid);
    }

    @Override
    public boolean isCompleted() {
        return mergeStack.empty();
    }

    @Override
    public boolean isTargetElement(int x) {
        return !isCompleted() && x == k;
    }

    @Override
    public boolean isComparedToElement(int x) {
        return !isCompleted() && (x == i || x == j);
    }

    static class MergeParams {
        int lo;
        int hi;
        int mid;

        MergeParams(int lo, int mid, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.mid = mid;
        }
    }
}
