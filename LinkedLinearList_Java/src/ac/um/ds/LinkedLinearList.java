        /* Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
         * 1 Ordibehesht 1399(Hijri shamsi)
         * 20 April 2020
         * Authors: Kamaledin Ghiasi-Shirazi
         *          Ali Moghadaszadeh
         *          Pooya Hosseinzadeh
         *          Fatemeh Tabadkani
         */
        package ac.um.ds;


        public class LinkedLinearList<T> implements LinearList<T> {
            private class Node<T> {
                public T data;
                public Node prev;
                public Node next;
            }

            public class LinkedListForwardIterator<T> implements LinearList.LinearListForwardIterator<T> {
                private Node<T> currNode;

                //constructors:
                public LinkedListForwardIterator() {
                    this.currNode = null;
                }

                public LinkedListForwardIterator(Node<T> node) {
                    this.currNode = node;
                }

                @Override
                public T next() {
                    currNode = currNode.next;
                    return currNode.data;
                }

                @Override
                public boolean hasNext() {
                    return currNode.next != headerNode;
                }
            }

            public class LinkedListBackwardIterator<T> implements LinearList.LinearListBackwardIterator<T> {
                Node<T> currNode;

                public LinkedListBackwardIterator() {
                    this.currNode = null;
                }

                public LinkedListBackwardIterator(Node<T> node) {
                    this.currNode = node;
                }

                @Override
                public T next() {
                    currNode = currNode.prev;
                    return currNode.data;
                }

                @Override
                public boolean hasNext() {
                    return currNode.prev != headerNode;

                }
            }

            public LinkedLinearList() {
                mSize = 0;
                headerNode = new Node<T>();
                headerNode.next = headerNode;
                headerNode.prev = headerNode;
            }

            public LinearListForwardIterator<T> forwardIterator() {
                return new LinkedListForwardIterator<T>(headerNode);
            }

            public LinearListBackwardIterator<T> backwardIterator() {
                return new LinkedListBackwardIterator<T>(headerNode);
            }

            @java.lang.Override
            public LinearListForwardIterator<T> insert(LinearListForwardIterator<T> it, T data) throws IndexOutOfBoundsException {
                if(it instanceof LinkedListForwardIterator) {
                    Node<T> node_ = new Node<T>();
                    node_.data = data;
                    node_.next = ((LinkedListForwardIterator<T>) it).currNode.next;
                    node_.prev = ((LinkedListForwardIterator<T>) it).currNode;
                    ((LinkedListForwardIterator<T>) it).currNode.next = node_;
                    node_.next.prev = node_;
                    mSize++;
                    return it;
                }
                return null;
            }

            @java.lang.Override
            public LinearListBackwardIterator<T> insert(LinearListBackwardIterator<T> it, T data) throws IndexOutOfBoundsException {

                if(it instanceof LinkedListBackwardIterator) {
                    Node<T> node_ = new Node<T>();
                    node_.data = data;
                    node_.next = ((LinkedListBackwardIterator<T>) it).currNode;
                    node_.prev = ((LinkedListBackwardIterator<T>) it).currNode.prev;
                    ((LinkedListBackwardIterator<T>) it).currNode.prev = node_;
                    node_.prev.next = node_;
                    mSize++;
                    return it;
                }
                return null;
            }

            @java.lang.Override
            public LinearListForwardIterator<T> remove(LinearListForwardIterator<T> it) throws IndexOutOfBoundsException {
                ((LinkedListForwardIterator<T>) it).currNode.prev.next = ((LinkedListForwardIterator<T>) it).currNode.next;
                ((LinkedListForwardIterator<T>) it).currNode.next.prev = ((LinkedListForwardIterator<T>) it).currNode.prev;
                mSize--;
                ((LinkedListForwardIterator<T>) it).currNode = ((LinkedListForwardIterator<T>) it).currNode.next;
                return it;
            }


            @java.lang.Override
            public LinearListBackwardIterator<T> remove(LinearListBackwardIterator<T> it) throws IndexOutOfBoundsException {

                ((LinkedListBackwardIterator<T>) it).currNode.prev.next = ((LinkedListBackwardIterator<T>) it).currNode.next;
                ((LinkedListBackwardIterator<T>) it).currNode.next.prev = ((LinkedListBackwardIterator<T>) it).currNode.prev;
                mSize--;
                ((LinkedListBackwardIterator<T>) it).currNode = ((LinkedListBackwardIterator<T>) it).currNode.prev;
                return it;
            }


            public int size() {
                return mSize;
            }

            @Override
            public String toString() {
                String str = "";
                Node currNode = headerNode;
                for (int i = 0; i < mSize; i++) {
                    currNode = currNode.next;
                    str += (" " + currNode.data);
                }
                return str;
            }

            private Node<T> headerNode;
            private int mSize;
        }