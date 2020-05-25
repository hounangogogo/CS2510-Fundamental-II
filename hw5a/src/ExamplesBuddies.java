// assignment 5
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import tester.*;

// runs tests for the buddies problem
public class ExamplesBuddies {
  Person ann;
  Person bob;
  Person cole;
  Person dan;
  Person ed;
  Person fay;
  Person gabi;
  Person hank;
  Person jan;
  Person kim;
  Person len;
  Person hanmeimei;

  ILoBuddy empty = new MTLoBuddy();

  void initBuddies() {
    ann = new Person("Ann");
    bob = new Person("Bob");
    cole = new Person("Cole");
    dan = new Person("Dan");
    ed = new Person("Ed");
    fay = new Person("Fay");
    gabi = new Person("Gabi");
    hank = new Person("Hank");
    jan = new Person("Jan");
    kim = new Person("Kim");
    len = new Person("Len");
    hanmeimei = new Person("Hanmeimei");
    ann.addBuddy(this.bob);
    ann.addBuddy(this.cole);
    bob.addBuddy(this.ed);
    bob.addBuddy(this.ann);
    bob.addBuddy(this.hank);
    cole.addBuddy(this.dan);
    dan.addBuddy(this.cole);
    ed.addBuddy(this.fay);
    fay.addBuddy(this.ed);
    fay.addBuddy(this.gabi);
    gabi.addBuddy(this.fay);
    gabi.addBuddy(this.ed);
    jan.addBuddy(this.kim);
    jan.addBuddy(this.len);
    kim.addBuddy(this.len);
    kim.addBuddy(this.jan);
    len.addBuddy(this.jan);
    len.addBuddy(this.kim);
  }

  void testHasDirectBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.kim.hasDirectBuddy(this.len), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.jan.hasDirectBuddy(this.len), true);
    t.checkExpect(this.len.hasDirectBuddy(this.kim), true);
    t.checkExpect(this.gabi.hasDirectBuddy(this.fay), true);
    t.checkExpect(this.len.hasDirectBuddy(this.gabi), false);
    t.checkExpect(this.cole.hasDirectBuddy(this.dan), true);
    t.checkExpect(this.hank.hasDirectBuddy(this.gabi), false);
    t.checkExpect(this.len.hasDirectBuddy(this.len), false);
  }


  void testCountCommonBuddies(Tester t) {
    initBuddies();
    t.checkExpect(this.kim.countCommonBuddies(this.hank), 0);
    t.checkExpect(this.jan.countCommonBuddies(this.gabi), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.cole), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.hank.countCommonBuddies(this.gabi), 0);

    t.checkExpect(this.ed.countCommonBuddies(this.ed), 1);
    t.checkExpect(this.fay.countCommonBuddies(this.bob), 1);
    t.checkExpect(this.ann.countCommonBuddies(this.ann), 2);

    t.checkExpect(this.ann.buddies.containsBuddy(bob), true);
    t.checkExpect(this.ann.buddies.countCommon(ann.buddies), 2);
    t.checkExpect(this.bob.buddies.countCommon(ann.buddies), 0);
    t.checkExpect(this.hank.buddies.countCommon(ann.buddies), 0);
    t.checkExpect(this.dan.buddies.countCommon(cole.buddies), 0);
    t.checkExpect(this.kim.buddies.countCommon(len.buddies), 1);
  }


  void testHasDistantBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasDistantBuddy(ed), true);
    t.checkExpect(this.ann.hasDistantBuddy(fay), true);
    t.checkExpect(this.ann.hasDistantBuddy(gabi), true);
    t.checkExpect(this.ann.hasDistantBuddy(hanmeimei), false);
  }


  void testPartyCount(Tester t) {
    initBuddies();
    t.checkExpect(this.jan.partyCount(), 3);
    t.checkExpect(this.dan.partyCount(), 2);

  }
}