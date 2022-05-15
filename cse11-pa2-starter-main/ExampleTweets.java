
class ExampleTweets
{
    User user1 = new User("UCSDJacobs","UCSD Engineering",12500);
    User user2 = new User("acmucsd","ACM @ UCSD",38);
    User user3 = new User("kprather88","Kimberly Prather, Ph.D.",39900);

    Tweet tweet1 = new Tweet("CR boxes...going in classrooms @UCSanDiego right now! What an amazing grass root effort to add an extra layer of protection for our students, staff, and faculty. #COVIDisAirborne", user3, 1100,"1440835854050549764");
    //https://twitter.com/kprather88/status/1440835854050549764
    //Not included: Picture, date, time, @'s, hashtag, retweets, quote tweets, comments, platform for post
    Tweet tweet2 = new Tweet("Ooooh...I am inching towards that 40k follower number...never imagined this would happen when I started tweeting more back in early 2020. Let's see how long it takes...",user3,77,"1446562977319030785");
    //https://twitter.com/kprather88/status/1446562977319030785
    //Not included: Date, time, retweets, quote tweets, comments, platform to post
    Tweet tweet3 = new Tweet("ONE MORE DAY TILL KICKOFF!!!",user2,1,"1442345648422998019");
    //https://twitter.com/acmucsd/status/1442345648422998019
    //Not included: Picture, date, time, retweets, quote tweets, comments, platform to post
    Tweet tweet4 = new Tweet("Very cool research from Engineers for Exploration, a program that allows undergrads to deploy research projects in the wild! #experientiallearning",user1,4,"1441160447063781385");
    //https://twitter.com/UCSDJacobs/status/1441160447063781385
    //Not included: date, time, hasthag, retweets, quote tweets, comments, platform to post

    String userExample1 = user1.toText();
    //this.userExample1 = "UCSD Engineering @UCSDJacobs"
    String userExample2 = user2.toText();
    //this.userExample2 = "ACM @ UCSD @acmucsd"
    boolean tweetLength1 = tweet1.longerThan(tweet2);
    //this.tweetLength1 = true
    boolean tweetLength2 = tweet3.longerThan(tweet4);
    //this.tweetLength2 = false
    boolean tweetLikes1 = tweet3.moreLikes(tweet2);
    //this.tweetLikes1 = false
    boolean tweetLikes2 = tweet1.moreLikes(tweet4);
    //this.tweetLikes2 = true
    String textExample1 = tweet2.toText();
    //this.textExample1 = "Kimberly Prather, Ph.D. @kprather88 : Ooooh...I am inching towards that 40k follower number...never imagined this would happen when I started tweeting more back in early 2020. Let's see how long it takes... : 77 Likes"
    String textExample2 = tweet4.toText();
    //this.textExample2 = "UCSD Engineering @UCSDJacobs : Very cool research from Engineers for Exploration, a program that allows undergrads to deploy research projects in the wild! #experientiallearning : 4 Likes"
    String link1 = tweet3.toLink();
    //this.link1 = "https://twitter.com/acmucsd/status/1442345648422998019"
    String link2 = tweet4.toLink();
    //this.link2 = "https://twitter.com/UCSDJacobs/status/1441160447063781385"
    
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
        return display + " @" + user;
    }
}

class Tweet
{
    String text;
    String user;
    String display;
    String id;
    int likes;
    Tweet(String text, User user, int likes, String id)
    {
        this.text=text;
        this.user=user.user;
        this.display=user.display;
        this.likes=likes;
        this.id=id;
    }

    boolean longerThan(Tweet other)
    {
        if(this.text.length()>other.text.length())
            return true;
        else    
            return false;
    }

    boolean moreLikes(Tweet other)
    {
        if(this.likes>other.likes)
            return true;
        else
            return false;
    }

    String toText()
    {
        return this.display + " @" + user + " : " + text + " : " + likes + " Likes";
    }

    String toLink()
    {
        return "https://twitter.com/" + user  + "/status/" + id;
    }
}