import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Arlind Hoxha on 12/14/2023.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private Node last;

    private int size;

    public Deque() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = first;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (last == null) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node newLast = new Node();
            newLast.item = item;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldFirst = first;
        first = first.next;

        if (first == null) {
            last = null;
        }

        size--;

        return oldFirst.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        size--;
        if (first == last) {
            Item item = last.item;
            first = null;
            last = null;
            return item;
        }

        Node oldLast = last;
        Node ptr = first;
        while (ptr.next != last) {
            ptr = ptr.next;
        }
        ptr.next = null;
        last = ptr;

        return oldLast.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node ptr = first;

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public Item next() {
            if (ptr == null) {
                throw new NoSuchElementException();
            }

            Item item = ptr.item;
            ptr = ptr.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        StdOut.println("Test 0: Constructor");
        Deque<Integer> deque = new Deque<>();
        print(deque);

        StdOut.println("Test 1: Add first");
        deque.addFirst(1);
        print(deque);

        StdOut.println("Test 2: Remove first");
        deque.removeFirst();
        print(deque);

        StdOut.println("Test 3: Add last");
        deque.addLast(5);
        print(deque);

        StdOut.println("Test 4: Remove last");
        deque.removeLast();
        print(deque);

        StdOut.println("Test 5: Alternate adding");
        deque.addLast(1);
        deque.addFirst(2);
        deque.addFirst(5);
        deque.addFirst(8);
        deque.addLast(4);
        deque.addLast(6);
        print(deque);

        StdOut.println("Test 6: Alternate removing");
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();
        print(deque);
    }

    private static void print(Deque<Integer> deque) {
        for (Integer n : deque) {
            StdOut.print(n + " -> ");
        }
        StdOut.println();
    }

}
