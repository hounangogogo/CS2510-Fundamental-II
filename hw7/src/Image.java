
// Represent an Image file
public class Image {
  String title;
  String fileType;
  int fileSize;  // in bytes

  public Image(String title, String fileType, int fileSize) {
    this.title = title;
    this.fileType = fileType;
    this.fileSize = fileSize;
  }

  /* Template
   *   Fields
   *     ... this.title ...       -- String
   *     ... this.fileType ...    -- String
   *     ... this.fileSize ...    -- int
   *
   *   Methods
   *     ... this.value() ...       -- int
   *     ... this.makeString() ...  -- String
   */

  // Compute the value of this Image, i.e., its fileSize
  public int value() {
    return this.fileSize;
  }
}