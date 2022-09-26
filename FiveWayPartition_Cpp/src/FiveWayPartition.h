// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2020 (1399 Hijri Shamsi)
//
// Authors: Kamaledin Ghiasi-Shirazi
// 			Ali Moghaddas-zadeh
//			Fateme Chaji

#pragma once

template <class T> void swap ( T& a, T& b )
{
  T c(a); a=b; b=c;
}

template <class T>
class FiveWayPartition {

public:
	/* Partition data such that 
		data < pivot1 are in range [p,q1)
		data == pivot1 are in range [q1,q2)
		data < pivot2 and > pivot1 are in range [q2,q3)
		data == pivot2 are in range [q3,q4)
		data > pivot2 are in range [q4+1,r]
	*/
	virtual void Partition(T* A, const T& pivot1, const T& pivot2,int p, int r, int& q1, int& q2, int& q3, int& q4) {

		q1 = p - 1;
		for (int i = r; i > q1; i--) {
			if (A[i]<pivot1) {
				bool sw = true;
				for (int j = q1 + 1; j < i; j++) {
					if (!(A[j]<pivot1)) {
						T temp = A[i];
						A[i] = A[j];
						A[j] = temp;
						q1 = j;
						sw = false;
						break;
					}

				}
				if (sw) {
					q1 = i;
					break;
				}
			}
		}
		q1++;
		
		q2 = q1-1;
		for (int i = r; i > q2; i--) {
			if (A[i]==pivot1) {
				bool sw = true;
				for (int j = q2 + 1; j < i; j++) {
					if (!(A[j]== pivot1)) {
						T temp = A[i];
						A[i] = A[j];
						A[j] = temp;
						q2 = j;
						sw = false;
						break;
					}
				}
				if (sw) {
					q2 = i;
					break;
				}
			}
		}
		q2++;
		
		q3 = q2 - 1;
		for (int i = r; i > q3; i--) {
			if (A[i]>pivot1 && A[i]<pivot2) {
				bool sw = true;
				for (int j = q3 + 1; j < i; j++) {
					if (!(A[j]>pivot1 && A[j]<pivot2)) {
						T temp = A[i];
						A[i] = A[j];
						A[j] = temp;
						q3 = j;
						sw = false;
						break;
					}
				}
				if (sw) {
					q3 = i;
					break;
				}
			}
		}
		q3++;
		
		q4 = q3-1;
		for (int i = r; i > q4; i--) {
			if (A[i] == pivot2) {
				bool sw = true;
				for (int j = q4 + 1; j < i; j++) {
					if (!(A[j] == pivot2)) {
						T temp = A[i];
						A[i] = A[j];
						A[j] = temp;
						q4 = j;
						sw = false;
						break;
					}
				}
				if (sw) {
					q4 = i;
					break;
				}
			}
		}
		q4++;
					}
};
