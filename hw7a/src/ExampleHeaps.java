import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import tester.Tester;

/**
 * ExamplesHeaps that will contain the tests for the methods.
 */
public class ExampleHeaps {


  void test(Tester t) {
    HeapExamples h = new HeapExamples();
    h.initHeap();
    t.checkExpect(7, h.h1.size());


    PriorityQueue<Integer> pi =
            new PriorityQueue<>(h.h1, (Comparator<Integer>) (t1, t2) -> t1 - t2);
    t.checkExpect(false, pi.isLeaf(1));
    t.checkExpect(false, pi.isLeaf(2));
    t.checkExpect(false, pi.isLeaf(3));
    t.checkExpect(true, pi.isLeaf(4));
    t.checkExpect(true, pi.isLeaf(5));
    t.checkExpect(true, pi.isLeaf(6));

    t.checkExpect(2, pi.higherPriorityChild(1));
    t.checkExpect(4, pi.higherPriorityChild(2));
    t.checkExpect(6, pi.higherPriorityChild(3));

    pi.insert(20);
    t.checkExpect(8, pi.list.size());
    ArrayList<Integer> result1 = new ArrayList<>(
            Arrays.asList(null, 80, 50, 40, 45, 20, 30, 20));

    t.checkExpect(result1, pi.list);
    pi.insert(60);

    ArrayList<Integer> result2 = new ArrayList<>(
            Arrays.asList(null, 80, 60, 40, 50, 20, 30, 20, 45));
    t.checkExpect(result2, pi.list);

    pi.remove();
    ArrayList<Integer> result3 = new ArrayList<>(
            Arrays.asList(null, 60, 50, 40, 45, 20, 30, 20));
    t.checkExpect(result3, pi.list);

  }
}
