import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Arlind Hoxha on 12/14/2023.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;

    private int size;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }

        int random = StdRandom.uniformInt(size);
        Item item = array[random];
        while (item == null) {
            random = StdRandom.uniformInt(size);
            item = array[random];
        }
        array[random] = null;
        size--;
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return array[StdRandom.uniformInt(size)];
    }
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (array[i] != null) {
                copy[j] = array[i];
                j++;
            }
        }
        array = copy;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int count = size;
        private Item[] trueArray;
        private boolean[] used;

        public RandomizedQueueIterator() {
            trueArray = (Item[]) new Object[count];
            used = new boolean[count];
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    trueArray[i] = array[i];
                }
            }
        }

        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public Item next() {
            if (count == 0) {
                throw new NoSuchElementException();
            }

            int random = StdRandom.uniformInt(size);
            while (used[random]) {
                random = StdRandom.uniformInt(size);
            }
            Item item = trueArray[random];
            used[random] = true;
            count--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        print(queue);
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size);

        StdOut.println(queue.sample());
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size);

        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size);
    }

    private static void print(RandomizedQueue<Integer> queue) {
        for (Integer n : queue) {
            if (n == null) {
                StdOut.print("[N]");
            } else {
                StdOut.print("[" + n + "]");
            }
        }
        StdOut.println();
    }
}
