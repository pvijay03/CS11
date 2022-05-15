class User 
{
  String name;
  String email;
  int followers;
  User(String name, String email, int followers) 
  {
    this.name = name;
    this.email = email;
    this.followers = followers;
  }
}

class Tweet
{
  User author;
  String content;
  String tweetId;
  int likes;
  Date date;
  Tweet(User author, Date date, String content, String tweetId, int likes) 
  {
    this.author = author;
    this.content = content;
    this.tweetId = tweetId;
    this.likes = likes;
    this.date = date;
  }
  
  boolean before(Tweet t) 
  {
    if(this.date.year<t.date.year) 
      return true;
    else if(this.date.year>t.date.year)
      return false;
    else if(this.date.month<t.date.month) 
      return true;
    else if(this.date.month>t.date.month) 
      return false;
    else if(this.date.day<t.date.day) 
      return true; 
    else
      return false;
  }
}

class Date
{
  int month;
  int day;
  int year;
  Date(int month, int day, int year)
  {
    this.month=month;
    this.day=day;
    this.year=year;
  }
}

class ExamplesTweets 
{
  Tweet t1 = new Tweet(new User("Pranav","pvijay@ucsd.edu",50), new Date(5, 20, 2003),"World!", "16743255", 34);
  Tweet t2 = new Tweet(new User("Pranav","pvijay@ucsd.edu",63), new Date(6, 20, 2003),"HelloWorld!", "16743255", 47);
  Tweet t3 = new Tweet(new User("Pranav","pvijay@ucsd.edu",49), new Date(5, 16, 2003),"Hello!", "16743255", 31);
  Tweet t4 = new Tweet(new User("Pranav","pvijay@ucsd.edu",103), new Date(4, 10, 2005),"CSE11!", "16743255", 76);

  boolean tweetExample1 = t2.before(t1); //Expected value is false
  boolean tweetExample2 = t3.before(t1); //Expected value is true
  boolean tweetExample3 = t2.before(t2); //Expected value is false
  boolean tweetExample4 = t4.before(t1); //Expected value is true

}