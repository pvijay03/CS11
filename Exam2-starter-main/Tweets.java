// EXAM INSTRUCTIONS:
// All of your code for Task 2 goes in this file.
// Add new method headers and implementations as appropriate to these classes
// Add examples to the ExamplesTweets class.
import tester.*;
interface Tweet 
{
  public int lengthOfLongestTweetInThread();
  public int timesAuthorPostedInThread(User user);
}

class User
{
  String username, displayName;
  User(String username, String displayName) 
  {
    this.username = username;
    this.displayName = displayName;
  }
}

class TextTweet implements Tweet 
{
  User author;
  String contents;
  int likes;
  TextTweet(User author, String contents, int likes) 
  {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
  }

  public int lengthOfLongestTweetInThread()
  {
    return contents.length();
  }

  public int timesAuthorPostedInThread(User user)
  {
    if(this.author==user)
      return 1;
    return 0;
  }
}

class ReplyTweet implements Tweet 
{
  User author;
  String contents;
  int likes;
  Tweet replyTo;
  ReplyTweet(User author, String contents, int likes, Tweet replyTo) 
  {
    this.author = author;
    this.contents = contents;
    this.likes = likes;
    this.replyTo = replyTo;
  }

  public int lengthOfLongestTweetInThread()
  {
    if(this.contents.length()>this.replyTo.lengthOfLongestTweetInThread())
      return contents.length();
    else
      return replyTo.lengthOfLongestTweetInThread();
  }

  public int timesAuthorPostedInThread(User user)
  {
    int count=0;
    if(this.author==user)
      count++;
    return count+replyTo.timesAuthorPostedInThread(user);
  }
}

class ExamplesTweets 
{
  User joe = new User("joepolitz", "Joe Gibbs Politz");
  User greg = new User("gregory_miranda", "Greg Miranda");
  Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);  //42
  Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11?", 12, this.t1);  //41
  Tweet t3 = new ReplyTweet(this.joe, "I like computer science", 53, this.t2);  //23

  void testLongestTweetInThread(Tester t) {
    t.checkExpect(this.t2.lengthOfLongestTweetInThread(), 42);
    //VIDEO TEST
    t.checkExpect(this.t3.lengthOfLongestTweetInThread(),42);
    //
  }
  //VIDEO TEST
  int videoTest = this.t3.lengthOfLongestTweetInThread();
  //
  void testAuthorPostedInThread(Tester t) {
    t.checkExpect(this.t1.timesAuthorPostedInThread(joe), 1);
    t.checkExpect(this.t1.timesAuthorPostedInThread(greg), 0);
  }
  /*
  class method   name method                              reference   return value
  ReplyTweet     lengthOfLongestTweetInThread             t3   :6     41
  ReplyTweet     lengthOfLongestTweetInThread             t2   :5     42
  TextTweet      lengthOfLongestTweetInThread             t1   :4     42
  
  videoTest = 42
  */
}
    
