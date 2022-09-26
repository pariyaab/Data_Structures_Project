// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2020 (1399 Hijri Shamsi)
//
// Authors: Kamaledin Ghiasi-Shirazi
//		  	Ali Moghaddaszadeh

package ac.um.ds.HeapSort;

public class HeapSort< T extends Comparable< T > > implements ISort< T > {
    public void sort(T[] data, int n) {
        // Write your code here
        Heap heap = new Heap();
        heap.initialize(data,n);
        for (int i = n - 1; i >= 0; i--) {
            T temp = data[0];
            data[0] = data[i];
            data[i] = temp;

            // Heapify root element
            heap.heapify(data, i, 0);
        }
    }
}
