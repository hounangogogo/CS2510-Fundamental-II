package Eliza;

import java.util.ArrayList;

/**
 * Train our Eliza to be a mock psychiatrist.
 */
class Eliza {

  private ArrayList<Reply> replies;

  /**
   * The constructor for Eliza.
   */
  Eliza() {
    replies = initReplies();
  }

  /**
   * Find the first words in the given sentence.
   *
   * @param question question sentence.
   * @return the first word in sentence.
   */
  String firstWord(String question) {
    String key = question.split(" ")[0];
    key = key.toLowerCase();
    return key;
  }

  /**
   * Random choose a answer base on the question asked.
   *
   * @param key key word of the sentence.
   * @return the answer for that question.
   */
  String getAnswer(String key) {
    String answer = "";
    System.out.println(replies.size());
    for (int i = 0; i < replies.size(); i++) {
      if (key.equals(replies.get(i).keyword)) {
        answer = replies.get(i).randomAnswer();
        break;

      }
    }
    return answer;
  }


  /**
   * create list of answers.
   *
   * @return the list of answers for question.
   */
  private ArrayList<Reply> initReplies() {
    String what = "what";
    String fox = "It is a fox";
    String work = "He is doing his work";
    String red = "This is red";
    ArrayList<String> whatAnswer = new ArrayList<>();


    whatAnswer.add(work);
    whatAnswer.add(fox);
    whatAnswer.add(red);
    Reply whatAns = new Reply(what, whatAnswer);



    String who = "who";
    String hounan = "Hounan";
    String kim = "kim";
    String lin = "lin";
    ArrayList<String> whoAnswer = new ArrayList<>();


    whoAnswer.add(hounan);
    whoAnswer.add(kim);
    whoAnswer.add(lin);
    Reply whoAns = new Reply(who, whoAnswer);


    ArrayList<Reply> replies = new ArrayList<>();
    replies.add(whatAns);
    replies.add(whoAns);


    return replies;

  }
}
