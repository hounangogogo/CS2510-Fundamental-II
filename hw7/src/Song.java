
// Represents a song
public class Song {
  String title;
  String artist;
  int duration;  // in seconds

  public Song(String title, String artist, int duration) {
    this.title = title;
    this.artist = artist;
    this.duration = duration;
  }

  /* Template
   *   Fields
   *     ... this.title ...       -- String
   *     ... this.artist ...      -- String
   *     ... this.duration ...    -- int
   *
   *   Methods
   *     ... this.value() ...       -- int
   *     ... this.makeString() ...  -- String
   */

  // Compute the value of this Song, i.e., its duration
  public int value() {
    return this.duration;
  }
}