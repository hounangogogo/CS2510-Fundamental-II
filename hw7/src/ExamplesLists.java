import tester.*;

// Examples and tests for Books, Songs, Images and lists of Books,
//   Songs, and Images
class ExamplesLists {
  ExamplesLists() {
  }

  Book oms = new Book("Old Man and the Sea", "Hemingway", 30);
  Book eos = new Book("Elements of Style", "EBW", 20);
  Book htdp = new Book("HtDP", "MF", 60);
  Book ll = new Book("Little Lisper", "MF", 25);

  ILo<Book> mtlob = new MtLo<Book>();
  ILo<Book> blist2 = new ConsLo<Book>(this.oms,
          new ConsLo<Book>(this.eos, this.mtlob));
  ILo<Book> blist3 = new ConsLo<Book>(this.htdp, this.blist2);

  Song help = new Song("Help", "Beatles", 283);
  Song hotelc = new Song("Hotel California", "Eagles", 276);
  Song yesterday = new Song("Yesterday", "Beatles", 195);

  ILo<Song> mtlos = new MtLo<Song>();
  ILo<Song> slist2 = new ConsLo<Song>(this.help,
          new ConsLo<Song>(this.hotelc, this.mtlos));
  ILo<Song> slist3 = new ConsLo<Song>(this.yesterday, this.slist2);

  Image beach = new Image("Beach", "jpeg", 24348);
  Image park = new Image("Park", "gif", 33246);
  Image party = new Image("Party", "png", 4532);

  ILo<Image> mtloi = new MtLo<Image>();
  ILo<Image> ilist2 = new ConsLo<Image>(this.beach,
          new ConsLo<Image>(this.park, this.mtloi));
  ILo<Image> ilist3 = new ConsLo<Image>(this.party, this.ilist2);

  // Test the size method for all lists
  void testSize(Tester t) {
    t.checkExpect(mtlob.size(), 0);
    t.checkExpect(blist2.size(), 2);
    t.checkExpect(blist3.size(), 3);

    t.checkExpect(mtlos.size(), 0);
    t.checkExpect(slist2.size(), 2);
    t.checkExpect(slist3.size(), 3);

    t.checkExpect(mtloi.size(), 0);
    t.checkExpect(ilist2.size(), 2);
    t.checkExpect(ilist3.size(), 3);
  }

  // Test the method value method for classes Book, Image, and Song
  void testValue(Tester t) {
    t.checkExpect(oms.value(), 30);
    t.checkExpect(htdp.value(), 60);

    t.checkExpect(help.value(), 283);
    t.checkExpect(hotelc.value(), 276);

    t.checkExpect(beach.value(), 24348);
    t.checkExpect(party.value(), 4532);
  }
  
    /*
    // Test the totalValue method for all lists
    void testTotalValue(Tester t){
        t.checkExpect(mtlob.totalValue(), 0);
        t.checkExpect(blist2.totalValue(), 50);
        t.checkExpect(blist3.totalValue(), 110);

        t.checkExpect(mtlos.totalValue(), 0);
        t.checkExpect(slist2.totalValue(), 559);
        t.checkExpect(slist3.totalValue(), 754);
  
        t.checkExpect(mtloi.totalValue(), 0);
        t.checkExpect(ilist2.totalValue(), 57594);
        t.checkExpect(ilist3.totalValue(), 62126);
    }
    */
    
    /*
    // Test the method makeString method for classes Book, Image, and Song
    void testMakeString(Tester t){
        t.checkExpect(oms.makeString(), 
            "Book: Old Man and the Sea by Hemingway\ncosts: $30");
        t.checkExpect(eos.makeString(), 
            "Book: Elements of Style by EBW\ncosts: $20");

        t.checkExpect(help.makeString(), 
            "Song: Help by Beatles\nduration: 283 minutes");
        t.checkExpect(hotelc.makeString(), 
            "Song: Hotel California by Eagles\nduration: 276 minutes");
  
        t.checkExpect(beach.makeString(), 
            "Image: Beach.jpeg\nsize: 24348MB");
        t.checkExpect(park.makeString(), 
            "Image: Park.gif\nsize: 33246MB");
    }
    */
    
    /*
    // Test the method makeString method for classes Book, Image, and Song
    void testMakeStrings(Tester t){
        t.checkExpect(mtlob.makeStrings(), new MtLo<String>());
        t.checkExpect(blist2.makeStrings(), 
        new ConsLo<String>("Book: Old Man and the Sea by Hemingway\ncosts: $30",
        new ConsLo<String>("Book: Elements of Style by EBW\ncosts: $20",
        new MtLo<String>())));

        t.checkExpect(slist2.makeStrings(), 
        new ConsLo<String>("Song: Help by Beatles\nduration: 283 minutes",
        new ConsLo<String>("Song: Hotel California by Eagles\nduration: 276 minutes",
        new MtLo<String>())));
  
        t.checkExpect(ilist2.makeStrings(), 
        new ConsLo<String>("Image: Beach.jpeg\nsize: 24348MB",
        new ConsLo<String>("Image: Park.gif\nsize: 33246MB",
        new MtLo<String>())));
    }
    */
    
    /*
    ITransform<String, Book> bTitle = new BookTitle();
    ITransform<Integer, Image> iSize = new ImageSize();
    ITransform<Boolean, Song> sType = new SongType();
    
    // Test the implementation of the Transform interface 
    // by classes BookTitle, ImageSize, and SongType
    void testTransform(Tester t){
      t.checkExpect(this.bTitle.transform(oms), "Old Man and the Sea");
      t.checkExpect(this.bTitle.transform(eos), "Elements of Style");

      t.checkExpect(this.iSize.transform(beach), 24348);
      t.checkExpect(this.iSize.transform(park), 33246);
      

      t.checkExpect(this.sType.transform(help), false);
      t.checkExpect(this.sType.transform(yesterday), true);
    }
    */

  // Test the method map method for lists of Book-s, Image-s, and Song-s
  void testMap(Tester t) {
    //.......
  }

    /*
    ISelect<Book> cheapBook = new CheapBook(25);
    ISelect<Song> shortSong = new ShortSong();
    ISelect<Image> jpegFile = new ImageJpeg();
    */

  // Test the method map method for lists of Book-s, Image-s, and Song-s
  void testFilter(Tester t) {
    // ......
  }

  // test for the method that throws an exception
  // using the try-catch clause
  void testAuthorName(Tester t) {
    // ...
  }

  // produce the visitor that computes the image download time in seconds
  // at the download speed of 200 MB/sec
  ILoVisitor<Integer, Image> imageDownloads =
          new ILoImageDownloadTimeVisitor(200);

  // test the use of the ILoVisitor by computing the image download times
  void testILoVisitor(Tester t) {
    t.checkExpect(this.mtloi.accept(this.imageDownloads), 0);
    t.checkExpect(this.ilist2.accept(this.imageDownloads), 287);
  }

  // produce the visitor that computes the image download time in seconds
  // at the download speed of 100 MB/sec
  ILoVisitor<Integer, Image> iDownloads =
          new ILoImageDownloadTimeVisitor(100);

  // test the use of the ILoVisitor by computing the image download times
  void testILoVisitorI100(Tester t) {
    t.checkExpect(this.mtloi.accept(this.iDownloads), 0);
    t.checkExpect(this.ilist3.accept(this.iDownloads), 620);
  }


  ILoVisitor<ILo<String>, Song> songTitle = new ILoListOfSongTitleVisitor();

  void testILoVisitorSongTitle(Tester t) {
    t.checkExpect(this.mtlos.accept(this.songTitle), new MtLo<String>());
    ILo<String> songNames =
            new ConsLo<>("Yesterday",
                    new ConsLo<>("Help",
                            new ConsLo<>("Hotel California",
                                    new MtLo<>())));
    t.checkExpect(this.slist3.accept(this.songTitle), songNames);
  }


  ILoVisitor<String, Book> bookTitle = new ILoLongStringVisitor();

  void testILoVisitorBookTitle(Tester t) {
    t.checkExpect(this.mtlob.accept(this.bookTitle), "");
    String bookNames = "HtDP\nOld Man and the Sea\nElements of Style\n";
    t.checkExpect(this.blist3.accept(this.bookTitle), bookNames);
  }

}