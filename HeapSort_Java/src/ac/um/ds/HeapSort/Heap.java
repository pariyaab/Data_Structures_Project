// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2020 (1399 Hijri Shamsi)
//
// Authors: Kamaledin Ghiasi-Shirazi
//		  	Ali Moghaddaszadeh

package ac.um.ds.HeapSort;

import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> implements PriorityQueue<T> {
    private int mSize;
    private T[] mData;
    public int getSize() {
        return mSize;
    }

    public  void initialize(T[] data, int n) {
        int startIdx = (n / 2) - 1;
        mSize = n;
        for (int i = startIdx; i >= 0; i--) {
            heapify(data, n, i);
        }
        mData = data;
    }

    public T findMax() {
        return mData[0];
    }

    public void insert(T data) {
        if(mData.length == mSize)
            throw new NoSuchElementException("Heap's underlying storage is overflow");
        else {
            mSize++;
            mData[mSize-1] = data;
            siftUp(mSize - 1);
        }

    }

    public T deleteMax() {
        T lastElement = mData[mSize - 1];
        mData[0] = lastElement;
        mSize--;
        heapify(mData,mSize,0);
        return mData[0];
    }

    protected void heapify(T[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && (arr[l].compareTo(arr[largest])) > 0)
            largest = l;

        if (r < n && (arr[r].compareTo(arr[largest])) > 0)
            largest = r;

        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
    private void siftUp(int nodeIndex) {

        int parentIndex;
        T tmp;

        if (nodeIndex != 0) {

            parentIndex = (nodeIndex -1) /2;

            if (mData[parentIndex].compareTo(mData[nodeIndex]) < 0) {

                tmp = mData[parentIndex];

                mData[parentIndex] = mData[nodeIndex];

                mData[nodeIndex] = tmp;

                siftUp(parentIndex);

            }

        }

    }
}
