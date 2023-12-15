import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by Arlind Hoxha on 12/14/2023.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }

        Iterator<String> iterator = queue.iterator();
        while (k > 0) {
            if (iterator.hasNext()) {
                StdOut.println(iterator.next());
                k--;
            }
        }
    }
}
