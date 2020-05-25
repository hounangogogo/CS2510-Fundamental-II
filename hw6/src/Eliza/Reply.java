package Eliza;

import java.util.ArrayList;
import java.util.Random;

/**
 * class Reply that holds a keyword for a question, and an ArrayList of answers to a the question
 * that starts with this keyword.
 */
class Reply {
  String keyword;
  ArrayList<String> answers;

  Reply(String keyword, ArrayList<String> answers) {
    this.keyword = keyword;
    this.answers = answers;
  }

  /**
   * produces one of the possible answers each time it is invoked with the user’s question as its
   * argument.
   *
   * @return the possible answer.
   */
  String randomAnswer() {
    Random rand = new Random();
    int i = rand.nextInt(answers.size());
    return answers.get(i);
  }
}
