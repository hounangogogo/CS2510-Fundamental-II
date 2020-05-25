// Guo Hong
// guohong
// Zhao Haonan
// haonan


import java.util.*;

import tester.*;

/**
 * Tests and Examples for USMap and Graphs
 */
public class Examples {

  /**
   * data for the methods and tests
   */
  HashMap<City, State> newEngland = new HashMap<City, State>();
  ArrayList<String> nhNeighbors = new ArrayList<String>();
  ArrayList<String> vtNeighbors = new ArrayList<String>();
  ArrayList<String> meNeighbors = new ArrayList<String>();
  ArrayList<String> maNeighbors = new ArrayList<String>();
  ArrayList<String> riNeighbors = new ArrayList<String>();
  ArrayList<String> ctNeighbors = new ArrayList<String>();


  City concord = new City(3301, "Concord", "NH", 71.527734, 43.218525);
  City montpellier = new City(5602, "Montpelier", "VT", 72.576992, 44.264082);
  City augusta = new City(4330, "Augusta", "ME", 69.766548, 44.323228);
  City boston = new City(02115, "Boston", "MA", 71.092215, 42.342706);
  City providence = new City(2908, "Providence", "RI", 71.437684, 41.838294);
  City hartford = new City(06120, "Hartford", "CT", 72.675807, 41.78596);
  City boston2 = new City(02115, "Boston", "MA", 71.092215, 42.342706);


  State nh = new State("NH", concord, nhNeighbors);
  State vt = new State("VT", montpellier, vtNeighbors);
  State me = new State("ME", augusta, meNeighbors);
  State ma = new State("MA", boston, maNeighbors);
  State ri = new State("RI", providence, riNeighbors);
  State ct = new State("CT", hartford, ctNeighbors);

  void reset() {
    concord = new City(3301, "Concord", "NH", 71.527734, 43.218525);
    montpellier = new City(5602, "Montpelier", "VT", 72.576992, 44.264082);
    augusta = new City(4330, "Augusta", "ME", 69.766548, 44.323228);
    boston = new City(02115, "Boston", "MA", 71.092215, 42.342706);
    providence = new City(2908, "Providence", "RI", 71.437684, 41.838294);
    hartford = new City(06120, "Hartford", "CT", 72.675807, 41.78596);
    boston2 = new City(02115, "Boston", "MA", 71.092215, 42.342706);

    nh = new State("NH", concord, nhNeighbors);
    vt = new State("VT", montpellier, vtNeighbors);
    me = new State("ME", augusta, meNeighbors);
    ma = new State("MA", boston, maNeighbors);
    ri = new State("RI", providence, riNeighbors);
    ct = new State("CT", hartford, ctNeighbors);

  }


  /**
   * Generate lists of neighboring states
   */
  public void makeNewEngland() {
    nhNeighbors.add("ME");
    nhNeighbors.add("MA");
    nhNeighbors.add("VT");

    vtNeighbors.add("NH");
    vtNeighbors.add("MA");

    meNeighbors.add("NH");

    ctNeighbors.add("MA");

    maNeighbors.add("CT");
    maNeighbors.add("RI");
    maNeighbors.add("NH");

    riNeighbors.add("MA");

  }


  /**
   * Generate New England states
   */
  public void makeStates() {
    newEngland.put(concord, nh);
    newEngland.put(montpellier, vt);
    newEngland.put(augusta, me);

    newEngland.put(boston, ma);
    newEngland.put(hartford, ct);
    newEngland.put(providence, ri);
    newEngland.put(boston2, ma);

  }

  /**
   * add state and city information to hashmap
   */
  public void initMap() {
    reset();
    newEngland.put(concord, nh);
    newEngland.put(montpellier, vt);
    newEngland.put(augusta, me);

    newEngland.put(boston, ma);
    newEngland.put(hartford, ct);
    newEngland.put(providence, ri);
    newEngland.put(boston2, ma);

  }

  ArrayList<City> cities = new ArrayList<City>();

  /** initialize cities with the data from a file */
    /*
    public void initCities() {
        Traversal<City> tr = new InFileCityTraversal();

        try {
            while (!tr.isEmpty()) {
                cities.add(tr.getFirst());
                tr = tr.getRest();
            }
        }
        catch (IllegalUseOfTraversalException e) {
            System.out.println(
                    "IllegalUseOfTraversalException: cannot happen" +
                            e.getMessage());
        }
    }
     */

  /**
   * Display the instances using toString...
   */
  public void testPrint(Tester t) {
    this.makeNewEngland();
    this.makeStates();

    System.out.println("After initialization:");
    System.out.println("Concord: " + newEngland.get(concord));
    System.out.println("Montpellier: " + newEngland.get(montpellier));
    System.out.println("Augusta: " + newEngland.get(augusta));

        /*
        initCities();
        for (City c: cities) {
            System.out.println(c.toString());
        }
         */
  }

  public void testInitMap(Tester t) {

    t.checkExpect(newEngland.get(boston), ma);
    t.checkExpect(newEngland.get(hartford), ct);
    t.checkExpect(newEngland.get(concord), nh);
    t.checkExpect(newEngland.size(), 6);
  }

  void testinit(Tester t) {
    initMap();
    t.checkExpect(newEngland.get(concord), nh);
    t.checkExpect(newEngland.get(montpellier), vt);
    t.checkExpect(newEngland.get(augusta), me);

    t.checkExpect(newEngland.get(boston), ma);
    t.checkExpect(newEngland.get(hartford), ct);
    t.checkExpect(newEngland.get(providence), ri);
    t.checkExpect(newEngland.get(boston2), ma);
  }

  void testreset(Tester t) {
    reset();
    t.checkExpect(nh.name, "NH");
    t.checkExpect(vt.name, "VT");
    t.checkExpect(me.name, "ME");
    t.checkExpect(ma.name, "MA");
    t.checkExpect(ri.name, "RI");
    t.checkExpect(ct.name, "CT");

    t.checkExpect(concord.name, "Concord");
    t.checkExpect(montpellier.name, "Montpelier");
    t.checkExpect(augusta.name, "Augusta");
    t.checkExpect(boston.name, "Boston");
    t.checkExpect(providence.name, "Providence");
    t.checkExpect(concord.name, "Concord");
    t.checkExpect(hartford.name, "Hartford");
    t.checkExpect(boston2.name, "Boston");
  }


}