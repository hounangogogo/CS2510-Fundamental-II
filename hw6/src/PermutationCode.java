import java.util.*;

import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding and decoding of the
 * messages that use this code.
 */
public class PermutationCode {
  /**
   * The original list of characters to be encoded
   */
  ArrayList<Character> alphabet =
          new ArrayList<Character>(Arrays.asList(
                  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                  'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                  't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  /**
   * A random number generator
   */
  Random rand = new Random();

  /**
   * Create a new instance of the encoder/decoder with a new permutation code
   */
  PermutationCode() {
    this.code = this.initEncoder();
  }

  /**
   * Create a new instance of the encoder/decoder with the given code
   */
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  /**
   * Initialize the encoding permutation of the characters
   */
  ArrayList<Character> initEncoder() {

    ArrayList<Character> copy =
            new ArrayList<Character>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                    't', 'u', 'v', 'w', 'x', 'y', 'z'));

    return initEncoderHelper(new ArrayList<Character>(26), copy);
  }

  /**
   * Produces a random permutation of the 26 letters of the alphabet and returns it as an ArrayList
   * of Characters.
   *
   * @param result the result list.
   * @param copy   the copy of the list.
   * @return the random order list.
   */
  private ArrayList<Character> initEncoderHelper(ArrayList<Character> result,
                                                 ArrayList<Character> copy) {
    if (copy.size() == 0) {
      return result;
    }
    else {
      int i = rand.nextInt(copy.size());
      result.add(copy.get(i));
      copy.remove(i);
      return initEncoderHelper(result, copy);
    }

  }

  /**
   * produce an encoded <code>String</code> from the given <code>String</code>
   *
   * @param source the <code>String</code> to encode
   * @return the secretly encoded <String>
   */
  String encode(String source) {
    return encodeHelper(source, "");
  }

  /**
   * Encode the message with the secret map.
   *
   * @param source the message origin message.
   * @return the encoded message.
   */
  private String encodeHelper(String source, String answer) {
    for (char ch : source.toCharArray()) {
      if (alphabet.contains(ch)) {
        int index = alphabet.indexOf(ch);
        answer = answer.concat(code.get(index).toString());
      } else {
        answer = answer.concat(Character.toString(ch));
      }
    }
    return answer;
  }

  /**
   * produce an decoded <code>String</code> from the given <code>String</code>
   *
   * @param source the <code>String</code> to decode
   * @return the revealed <String>
   */
  String decode(String source) {
    return decodeHelper(source, "");
  }

  /**
   * Decode the message with the secret map.
   *
   * @param source the message encoded already.
   * @return the decoded message.
   */
  private String decodeHelper(String source, String answer) {
    for (char ch : source.toCharArray()) {
      if (alphabet.contains(ch)) {
        int index = code.indexOf(ch);
        answer = answer.concat(alphabet.get(index).toString());
      } else {
        answer = answer.concat(Character.toString(ch));
      }
    }
    return answer;
  }
}


/**
 * The test cases for methods.
 */
class PermutationCodeExamples {
  ArrayList<Character> code1 = new ArrayList<Character>(Arrays.asList(
          'b', 'e', 'a', 'c', 'd'));
  PermutationCode p1 = new PermutationCode(this.code1);

  // test decode method
  void testDecode(Tester t) {
    t.checkExpect(p1.decode("abe edc"), "cab bed");
  }


  void testEncode(Tester t) {
    t.checkExpect(p1.encode("bad ace"), "ebc bad");
  }


  // test initEncoder method
  boolean testInitEncoder(Tester t) {
    return t.checkExpect(p1.initEncoder().size(), 26);
  }
}