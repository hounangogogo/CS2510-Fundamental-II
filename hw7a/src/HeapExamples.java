import java.util.ArrayList;

/**
 * Class represents the examples of heaps.
 */
class HeapExamples {


  ArrayList<Integer> myheap;
  ArrayList<Integer> h1;
  ArrayList<Integer> h2;
  ArrayList<Integer> h3;

  void initHeap() {
    myheap = new ArrayList<>();
    h1 = new ArrayList<>();
    h2 = new ArrayList<>();
    h3 = new ArrayList<>();

    this.myheap.add(null); // the unused first item
    this.myheap.add(70);
    this.myheap.add(60);
    this.myheap.add(40);
    this.myheap.add(35);
    this.myheap.add(50);

    this.h1.add(null); // the unused first item
    this.h1.add(80);
    this.h1.add(50);
    this.h1.add(40);
    this.h1.add(45);
    this.h1.add(20);
    this.h1.add(30);


    this.h2.add(null); // the unused first item
    this.h2.add(50);
    this.h2.add(45);
    this.h2.add(40);
    this.h2.add(30);
    this.h2.add(20);


    this.h3.add(null); // the unused first item
    this.h3.add(70);
    this.h3.add(45);
    this.h3.add(50);
    this.h3.add(30);
    this.h3.add(20);
    this.h3.add(40);
  }

}
