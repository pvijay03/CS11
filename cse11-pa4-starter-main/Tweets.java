import tester.*;
interface Tweet
{
    public boolean isReplyTo(Tweet other);
    public int totalLikes();
    public String allAuthors();
    public boolean textAppearsOnThread(String text);
}

class User
{
    String user;
    String display;
    int followers;
    User(String user, String display, int followers)
    {
        this.user=user;
        this.display=display;
        this.followers=followers;
    }

    String toText()
    {
        return this.display + " @" + this.user;
    }
}

class TextTweet implements Tweet
{   
    String contents;
    int likes;
    User author;
    TextTweet(User author, String contents, int likes)
    {
        this.contents=contents;
        this.likes=likes;
        this.author=author;
    }
    public boolean isReplyTo(Tweet other)
    {
        return false;
    }
    public int totalLikes()
    {
        return this.likes;
    }
    public String allAuthors()
    {
        return this.author.user;
    }
    public boolean textAppearsOnThread(String text)
    {
        if(!this.contents.contains(text))
            return false;
        else
            return true;
    }
}

class ReplyTweet implements Tweet
{
    String contents;
    int likes;
    User author;
    Tweet replyTo;
    ReplyTweet(User author, String contents, int likes, Tweet replyTo)
    {
        this.contents=contents;
        this.likes=likes;
        this.author=author;
        this.replyTo=replyTo;
    }
    public boolean isReplyTo(Tweet other)
    {
        return other==this.replyTo;
    }
    public int totalLikes()
    {
        return this.replyTo.totalLikes() + this.likes;
    }
    public String allAuthors()
    {
        return this.author.user + ";" + replyTo.allAuthors();
    }
    public boolean textAppearsOnThread(String text)
    {
        if(this.contents.contains(text) || this.replyTo.textAppearsOnThread(text))
            return true;
        else
            return false;
    }
}

class ExamplesTweets
{
    //EXPLORATION
    User pranav = new User("pranavvijay", "Pranav Vijay", 1252);
    User emma = new User("emmaharoldson", "Emma Haroldson", 865);
    User anders = new User("andersroback", "Rachel Leholm", 1103);
    Tweet t6 = new TextTweet(this.anders, "Programming fun!", 325);
    Tweet t7 = new TextTweet(this.emma, "I am in CSE 11", 543);
    Tweet t8 = new ReplyTweet(this.pranav, "I am in CSE 11", 284, this.t7);
    Tweet t9 = new ReplyTweet(this.pranav, "Hello World!", 311, this.t9);
    Tweet t10 = new ReplyTweet(this.anders, "I am in CSE 11", 172, this.t8);
    //Tweet t11 = new ReplyTweet(this.emma, "I like coding", 32, this.t12);
    //Tweet t12 = new ReplyTweet(this.pranav, "Coding is fun", 27, this.t11);

    void testExplorationIsReplyTo(Tester t)
    {
        t.checkExpect(this.t6.isReplyTo(this.t7),false);
        t.checkExpect(this.t9.isReplyTo(this.t9),false);
    }

    void testExplorationTotalLikes(Tester t)
    {
        t.checkExpect(this.t10.totalLikes(),172+284+543);
        t.checkExpect(this.t8.totalLikes(),284+543);
        //t.checkExpect(this.t11.totalLikes(),null); //will throw an exception because since t12 is instantiated after t11, this.t11.replyTo will equal null
    }

    void testExplorationAllAuthors(Tester t)
    {
        t.checkExpect(this.t10.allAuthors(),"andersroback;pranavvijay;emmaharoldson");
        //t.checkExpect(this.t12.allAuthors(),null); //will throw an exception for the same reason as the previous comment
        t.checkExpect(this.t7.allAuthors(),"emmaharoldson");
    }

    void testExplorationTextAppearsOnThread(Tester t)
    {
        t.checkExpect(this.t8.textAppearsOnThread("I CSE11"),false); //Even though those "words" separately are both in t8's string, it will still return false because "I CSE11" specifically is not found in the tweet
        t.checkExpect(this.t6.textAppearsOnThread("fun "),false); //"fun" is in the tweet, but since in the argument, there is a space after fun instead of an exclamation point like in the tweet, it will return false
    }

    //GIVEN TESTS
    User joe = new User("joepolitz", "Joe Gibbs Politz", 999);
    User greg = new User("gregory_miranda", "Greg Miranda", 9999);
    User rachel = new User("Rachel__Lim", "Rachel Lim", 1000000);
    Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
    Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
    Tweet t3 = new ReplyTweet(this.greg, "Thought about this more, probably not yet, too new.", 73, this.t2);
    Tweet t4 = new ReplyTweet(this.joe, "Yeah, good point. Maybe in 2022.", 10, this.t3);
    Tweet t5 = new ReplyTweet(this.rachel, "Yeah... I don't want to rewrite the book right this minute", 1005, this.t2);
    void testIsReplyTo(Tester t) 
    {
        t.checkExpect(this.t1.isReplyTo(this.t2), false);
        t.checkExpect(this.t2.isReplyTo(this.t1), true);
        t.checkExpect(this.t5.isReplyTo(this.t2), true);
        t.checkExpect(this.t2.isReplyTo(this.t2), false);
        t.checkExpect(this.t4.isReplyTo(this.t3), true);
    }

    void testTotalLikes(Tester t)
    {
        t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
        t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
        t.checkExpect(this.t1.totalLikes(), 77);       
    }

    void testAllAuthors(Tester t) {
        t.checkExpect(this.t1.allAuthors(), "joepolitz");
        t.checkExpect(this.t2.allAuthors(), "gregory_miranda;joepolitz");
        t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;joepolitz");
        t.checkExpect(this.t5.allAuthors(), "Rachel__Lim;gregory_miranda;joepolitz");
    }

    void testTextAppearsOnThread(Tester t) {
        t.checkExpect(this.t1.textAppearsOnThread("joepolitz"), false);
        t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
        t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
        t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
        t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
        t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
        t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
    }
}