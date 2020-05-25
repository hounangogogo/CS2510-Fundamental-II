// assignment 5
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import java.util.ArrayList;

import tester.Tester;

/**
 * Example and test class for search.
 */
public class ExamplesAlgorithms {

  Comparator<String> comps = new CompareString();
  Comparator<Integer> compi = new CompareInteger();
  Algorithms al = new Algorithms();


  ArrayList<String> slist1;
  ArrayList<String> slist2;

  ArrayList<Integer> ilist1;
  ArrayList<Integer> ilist2;

  /**
   * EFFECT: Initialize the <code>ArrayList</code> of String and integer sorted by lexicographical
   * ordering and magnitude
   */

  void initData() {
    slist1 = new ArrayList<>();
    ilist1 = new ArrayList<>();
    slist2 = new ArrayList<>();
    ilist2 = new ArrayList<>();

    this.slist1.add("a");
    this.slist1.add("b");
    this.slist1.add("c");
    this.slist1.add("d");
    this.slist1.add("e");

    this.slist2.add("a");
    this.slist2.add("d");
    this.slist2.add("f");
    this.slist2.add("g");
    this.slist2.add("h");
    this.slist2.add("k");


    this.ilist1.add(1);
    this.ilist1.add(2);
    this.ilist1.add(3);
    this.ilist1.add(4);
    this.ilist1.add(5);

    this.ilist2.add(5);
    this.ilist2.add(6);
    this.ilist2.add(7);
    this.ilist2.add(8);
    this.ilist2.add(9);
  }

  void testbinarySearch(Tester t) {
    initData();
    t.checkExpect(al.binarySearch(0, 4, slist1, comps, "d"), 3);
    t.checkExpect(al.binarySearch(0, 6, slist2, comps, "d"), 1);
    t.checkExpect(al.binarySearch(0, 4, ilist1, compi, 3), 2);
    t.checkExpect(al.binarySearch(0, 4, ilist1, compi, 100), -1);
  }

}