// CS 2510 Fall 2013
// Assignment 3

import tester.*;

// to represent different files in a computer
interface IFile {

  // compute the size of this file
  int size();

  // compute the time (in seconds) to download this file
  // at the given download rate
  int downloadTime(int rate);

  // is the owner of this file the same
  // as the owner of the given file?
  boolean sameOwner(IFile that);

  /**
   * Get the owner of the file.
   *
   * @return the owner of this file.
   */
  String getOwner();

}

abstract class AbstractFile implements IFile {
  String name;
  String owner;

  /**
   * Constructor of a file.
   *
   * @param name  the name of the file.
   * @param owner the owner of the file.
   */
  AbstractFile(String name, String owner) {
    this.name = name;
    this.owner = owner;
  }

  // compute the time (in seconds) to download this file
  // at the given download rate
  public int downloadTime(int rate) {
    return size() / rate;
  }

  /**
   * Get the owner of a file.
   *
   * @return the owner of a file.
   */
  public String getOwner() {
    return owner;
  }

  /**
   * Check whether the file has the same owner of the given one.
   *
   * @param that the other file.
   * @return whether two files have the same owner.
   */
  public boolean sameOwner(IFile that) {
    return owner.equals(that.getOwner());
  }
}


// to represent a text file
class TextFile extends AbstractFile {
  int length;   // in bytes

  TextFile(String name, String owner, int length) {
    super(name, owner);
    this.length = length;
  }

  // compute the size of this file
  public int size() {
    return this.length;
  }
}

//to represent an image file
class ImageFile extends AbstractFile {
  int width;   // in pixels
  int height;  // in pixels

  ImageFile(String name, String owner, int width, int height) {
    super(name, owner);
    this.width = width;
    this.height = height;
  }

  // compute the size of this file
  public int size() {
    return this.width * this.height;
  }
}


//to represent an audio file
class AudioFile extends AbstractFile {

  int speed;   // in bytes per second
  int length;  // in seconds of recording time

  AudioFile(String name, String owner, int speed, int length) {
    super(name, owner);
    this.speed = speed;
    this.length = length;
  }

  // compute the size of this file
  public int size() {
    return this.speed * this.length;
  }
}

/**
 * Example and test for files.
 */
class ExamplesFiles {

  IFile text2 = new TextFile("Northeastern", "Me", 2000);
  IFile image2 = new ImageFile("sea", "Me", 500, 400);
  IFile song2 = new AudioFile("No help", "Yes it's me", 100, 100);
  IFile text1 = new TextFile("English paper", "Maria", 1234);
  IFile picture = new ImageFile("Beach", "Maria", 400, 200);
  IFile song = new AudioFile("Help", "Pat", 200, 120);

  // test the method size for the classes that represent files
  boolean testSize(Tester t) {
    return
            t.checkExpect(this.text1.size(), 1234) &&
                    t.checkExpect(this.picture.size(), 80000) &&
                    t.checkExpect(this.song.size(), 24000) &&
                    t.checkExpect(this.text2.size(), 2000) &&
                    t.checkExpect(this.image2.size(), 200000) &&
                    t.checkExpect(this.song2.size(), 10000);
  }

  boolean testDownloadTime(Tester t) {
    return
            t.checkExpect(this.text1.downloadTime(2), 617) &&
                    t.checkExpect(this.song2.downloadTime(100), 100);


  }

  boolean testSameOwner(Tester t) {
    return
            t.checkExpect(this.text2.sameOwner(this.image2), true) &&
                    t.checkExpect(this.image2.sameOwner(this.song), false);

  }
}