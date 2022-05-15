import tester.*;
import java.util.*;
class Point 
{
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}
class PointCompare implements Comparator<Point>
{
  public int compare(Point p1,Point p2)
  {
    if(p1.y<p2.y)
      return -1;
    else if(p1.y>p2.y)
      return 1;
    else if(p1.x<p2.x)
      return -1;
    else if(p1.x>p2.x)
      return 1;
    return 0;
  }
  void testPointCompare(Tester t)
  {
    t.checkExpect(this.compare(new Point(1,1),new Point(2,2)),-1);
    t.checkExpect(this.compare(new Point(1,1),new Point(1,1)),0);
    t.checkExpect(this.compare(new Point(4,3),new Point(3,3)),1);
    t.checkExpect(this.compare(new Point(5,7),new Point(8,6)),1);
  }
}
class PointDistanceCompare implements Comparator<Point>
{
  public int compare(Point p1,Point p2)
  {
    if(p1.distance(new Point(0,0))<p2.distance(new Point(0,0)))
      return -1;
    else if(p1.distance(new Point(0,0))>p2.distance(new Point(0,0)))
      return 1;
    else 
      return 0;
  }
  void testPointDistanceCompare(Tester t)
  {
    t.checkExpect(this.compare(new Point(1,1),new Point(2,2)),-1);
    t.checkExpect(this.compare(new Point(1,1),new Point(1,1)),0);
    t.checkExpect(this.compare(new Point(4,3),new Point(3,3)),1);
    t.checkExpect(this.compare(new Point(3,7),new Point(7,3)),0);
  }
}
class StringCompare implements Comparator<String>
{
  public int compare(String s1,String s2)
  {
    return s1.compareTo(s2);
  }
  void testStringCompare(Tester t)
  {
    t.checkExpect(this.compare("a","aa"),-1);
    t.checkExpect(this.compare("hello","world"),-15);
    t.checkExpect(this.compare("hello","Hello"),32);
    t.checkExpect(this.compare("hi","hi"),0);
  }
}
class StringLengthCompare implements Comparator<String>
{
  public int compare(String s1,String s2)
  {
    if(s1.length()<s2.length())
      return -1;
    if(s1.length()>s2.length())
      return 1;
    else  
      return 0;
  }
  void testStringLengthCompare(Tester t)
  {
    t.checkExpect(this.compare("a","aa"),-1);
    t.checkExpect(this.compare("hello","world"),0);
    t.checkExpect(this.compare("hello","Hello"),0);
    t.checkExpect(this.compare("She","he"),1);
  }
}
class BooleanCompare implements Comparator<Boolean>
{
  public int compare(Boolean b1,Boolean b2)
  {
    if(b1==true&&b2==false)
      return 1;
    else if(b1==false&&b2==true)
      return -1;
    else 
      return 0;
  }
  void testBooleanCompare(Tester t)
  {
    t.checkExpect(this.compare(true,true),0);
    t.checkExpect(this.compare(false,false),0);
    t.checkExpect(this.compare(true,false),1);
    t.checkExpect(this.compare(false,true),-1);
  }
}
class CompareLists <E>
{
  public static <E> E minimum(List<E> l,Comparator<E> c)
  {
    if(l.size()==0)
      return null;
    E min=l.get(0);
    for(int i=0;i<l.size();i++)
    {
      if(c.compare(l.get(i),min)<0)
        min=l.get(i);
    }
    return min;
  }
  void testMinimum1(Tester t)

  {
    Comparator<Point> c1=new PointCompare();
    List<Point>ex1=new ArrayList<Point>();
    ex1.add(new Point(2,8));
    ex1.add(new Point(1,8));
    ex1.add(new Point(3,9));
    ex1.add(new Point(1,10));
    ex1.add(new Point(8,0));
    ex1.add(new Point(0,8));
    t.checkExpect(CompareLists.minimum(ex1,c1),ex1.get(4));
    Comparator<Point> c2=new PointDistanceCompare();
    List<Point>ex2=new ArrayList<Point>();
    ex2.add(new Point(2,4));
    ex2.add(new Point(3,2));
    ex2.add(new Point(1,3));
    ex2.add(new Point(4,1));
    ex2.add(new Point(2,2));
    t.checkExpect(CompareLists.minimum(ex2,c2),ex2.get(4));
    Comparator<String> c3=new StringCompare();
    List<String>ex3=new ArrayList<String>();
    ex3.add("hello");
    ex3.add("Hello");
    ex3.add("hi");
    ex3.add("Hi");
    t.checkExpect(CompareLists.minimum(ex3,c3),ex3.get(1));
    Comparator<String> c4=new StringLengthCompare();
    List<String>ex4=new ArrayList<String>();
    t.checkExpect(CompareLists.minimum(ex4,c4),null);
    Comparator<Boolean> c5=new BooleanCompare();
    List<Boolean>ex5=new ArrayList<Boolean>();
    ex5.add(true);
    ex5.add(false);
    t.checkExpect(CompareLists.minimum(ex5,c5),false);
  }
  public static <E> E minimum(E[] arr,Comparator<E> c)
  {
    if(arr.length==0)
      return null;
    E min=arr[0];
    for(int i=0;i<arr.length;i++)
    {
      if(c.compare(arr[i],min)<0)
        min=arr[i];
    }
    return min;
     
  }
  void testMinimum2(Tester t)
  {
    Comparator<Point> c1=new PointCompare();
    Point[] ex1={};
    t.checkExpect(CompareLists.minimum(ex1,c1),null);
    Comparator<Point> c2=new PointDistanceCompare();
    Point[] ex2={new Point(2,2),new Point(1,3),new Point(2,4),new Point(4,1),new Point(3,2)};
    t.checkExpect(CompareLists.minimum(ex2,c2),ex2[0]);
    Comparator<String> c3=new StringCompare();
    String[] ex3={"hi","Hello","Hi","hello"};
    t.checkExpect(CompareLists.minimum(ex3,c3),ex3[1]);
    Comparator<String> c4=new StringLengthCompare();
    String[] ex4={"hello","programming","CSE","world"};
    t.checkExpect(CompareLists.minimum(ex4,c4),ex4[2]);
    Comparator<Boolean> c5=new BooleanCompare();
    Boolean[] ex5={true,true};
    t.checkExpect(CompareLists.minimum(ex5,c5),true);
  }
  public static <E> List<E> greaterThan(List<E> l,Comparator<E> c,E e)
  {
    if(l.size()==0)
      return null;
    List<E> greater=new ArrayList<E>();
    for(E x:l)
    {
      if(c.compare(x,e)>0)
        greater.add(x);
    }
    return greater;
  }
  void testGreaterThan(Tester t)
  {
    Comparator<Point> c1=new PointCompare();
    List<Point>ex1=new ArrayList<Point>();
    ex1.add(new Point(2,8));
    ex1.add(new Point(1,8));
    ex1.add(new Point(3,9));
    ex1.add(new Point(1,10));
    ex1.add(new Point(8,0));
    ex1.add(new Point(0,8));
    t.checkExpect(CompareLists.greaterThan(ex1,c1,new Point(0,0)),ex1);
    Comparator<Point> c2=new PointDistanceCompare();
    List<Point>ex2=new ArrayList<Point>();
    ex2.add(new Point(2,4)); 
    ex2.add(new Point(3,2)); 
    ex2.add(new Point(1,3)); 
    ex2.add(new Point(4,1)); 
    ex2.add(new Point(2,2)); 
    List<Point>ex2Final=new ArrayList<Point>();
    ex2Final.add(new Point(2,4));
    ex2Final.add(new Point(4,1));
    t.checkExpect(CompareLists.greaterThan(ex2,c2,new Point(2,3)),ex2Final);
    Comparator<String> c3=new StringCompare();
    List<String>ex3=new ArrayList<String>();
    t.checkExpect(CompareLists.greaterThan(ex3,c3,"Hello"),null);
    Comparator<String> c4=new StringLengthCompare();
    List<String>ex4=new ArrayList<String>();
    ex4.add("Hello");
    ex4.add("World");
    List<String>ex4Final=new ArrayList<String>();
    t.checkExpect(CompareLists.greaterThan(ex4,c4,"Programming"),ex4Final);
    Comparator<Boolean> c5=new BooleanCompare();
    List<Boolean>ex5=new ArrayList<Boolean>();
    ex5.add(false);
    ex5.add(false);
    List<Boolean>ex5Final=new ArrayList<Boolean>();
    t.checkExpect(CompareLists.greaterThan(ex5,c5,true),ex5Final);
  }
  public static <E> boolean inOrder(List<E> l,Comparator<E> c)
  {
    if(l.size()==0)
      return true;
    for(E x:l)
    {
      if(x==null)
        throw new IllegalArgumentException("null value in list"); 
    }
    for(int i=0;i<l.size()-1;i++)
    {
      if(c.compare(l.get(i),l.get(i+1))>0)
        return false;
    }
    return true;
  }
  void testInOrder1(Tester t)
  {
    Comparator<Point> c1=new PointCompare();
    List<Point>ex1=new ArrayList<Point>();
    ex1.add(null);
    t.checkException(new IllegalArgumentException("null value in list"),this,"inOrder", ex1,c1);
    Comparator<Point> c2=new PointDistanceCompare();
    List<Point>ex2=new ArrayList<Point>();
    ex2.add(new Point(0,1));
    ex2.add(new Point(1,0));
    ex2.add(new Point(2,1));
    ex2.add(new Point(4,1));
    t.checkExpect(CompareLists.inOrder(ex2,c2),true);
    Comparator<String> c3=new StringCompare();
    List<String>ex3=new ArrayList<String>();
    ex3.add("aaaa");
    ex3.add("aaa");
    ex3.add("aa");
    ex3.add("a");
    t.checkExpect(CompareLists.inOrder(ex3,c3),false);
    Comparator<String> c4=new StringLengthCompare();
    List<String>ex4=new ArrayList<String>();
    ex4.add("Hello");
    ex4.add("World");
    ex4.add(null);
    t.checkException(new IllegalArgumentException("null value in list"),this,"inOrder",ex4,c4);
    Comparator<Boolean> c5=new BooleanCompare();
    List<Boolean>ex5=new ArrayList<Boolean>();
    ex5.add(true);
    ex5.add(false);
    t.checkExpect(CompareLists.inOrder(ex5,c5),false);
  }
  public static <E> boolean inOrder(E[] arr,Comparator<E> c)
  {
    if(arr.length==0)
      return true;
    for(E x:arr)
    {
      if(x==null)
        throw new IllegalArgumentException("null value in array");
    }
    for(int i=0;i<arr.length-1;i++)
    {
      if(c.compare(arr[i],arr[i+1])>0)
        return false;
    }
    return true;
  }
  void testInOrder2(Tester t)
  {
    Comparator<Point> c1=new PointCompare();
    Point[] ex1={};
    t.checkExpect(CompareLists.inOrder(ex1,c1),true);
    Comparator<Point> c2=new PointDistanceCompare();
    Point[] ex2={new Point(0,0),new Point(1,0),null,new Point(0,1),new Point(1,1)};
    t.checkException(new IllegalArgumentException("null value in array"),this,"inOrder", ex2,c2);
    Comparator<String> c3=new StringCompare();
    String[] ex3={null};
    t.checkException(new IllegalArgumentException("null value in array"),this,"inOrder",ex3,c3);
    Comparator<String> c4=new StringLengthCompare();
    String[] ex4={"I","Am","Programming","PA8"};
    t.checkExpect(CompareLists.inOrder(ex4,c4),false);
    Comparator<Boolean> c5=new BooleanCompare();
    Boolean[] ex5={true,true};
    t.checkExpect(CompareLists.minimum(ex5,c5),true);
  }
  public static <E> List<E> merge(Comparator<E> c,List<E> l1,List <E> l2)
  {
    for(E x:l1)
    {
      if(x==null)
        throw new IllegalArgumentException("null value in first list");
    }
    for(E x:l2)
    {
      if(x==null)
        throw new IllegalArgumentException("null value in second list");
    }
    l1.addAll(l2);
    l1.sort(c);
    return l1;
  }
  void testMerge(Tester t)
  {
    Comparator<Point> c1=new PointCompare();
    List<Point> ex1l1=new ArrayList<Point>();
    ex1l1.add(new Point(0,0));
    ex1l1.add(new Point(0,2));
    List<Point> ex1l2=new ArrayList<Point>();
    ex1l2.add(new Point(0,1));
    ex1l2.add(new Point(0,3));
    List<Point> ex1Merge=new ArrayList<Point>();
    ex1Merge.add(new Point(0,0));
    ex1Merge.add(new Point(0,1));
    ex1Merge.add(new Point(0,2));
    ex1Merge.add(new Point(0,3));
    t.checkExpect(CompareLists.merge(c1,ex1l1,ex1l2),ex1Merge);
    Comparator<Point> c2=new PointDistanceCompare();
    List<Point> ex2l1=new ArrayList<Point>();
    ex2l1.add(new Point(0,0));
    ex2l1.add(null);
    List<Point> ex2l2=new ArrayList<Point>();
    ex2l2.add(new Point(0,1));
    ex2l2.add(new Point(0,3));
    t.checkException(new IllegalArgumentException("null value in first list"),this,"merge", c2,ex2l1,ex2l2);
    Comparator<String> c3=new StringCompare();
    List<String> ex3l1=new ArrayList<String>();
    ex3l1.add("a");
    ex3l1.add("aaa");
    ex3l1.add("aaaaa");
    List<String> ex3l2=new ArrayList<String>();
    ex3l2.add("aa");
    ex3l2.add("aaaa");
    ex3l2.add("aaaaaa");
    List<String> ex3Merge=new ArrayList<String>();
    ex3Merge.add("a");
    ex3Merge.add("aa");
    ex3Merge.add("aaa");
    ex3Merge.add("aaaa");
    ex3Merge.add("aaaaa");
    ex3Merge.add("aaaaaa");
    t.checkExpect(CompareLists.merge(c3,ex3l1,ex3l2),ex3Merge);
    Comparator<String> c4=new StringLengthCompare();
    List<String>ex4l1=new ArrayList<String>();
    ex4l1.add("aaaa");
    ex4l1.add("aaa");
    ex4l1.add("aa");
    ex4l1.add("a");
    List<String>ex4l2=new ArrayList<String>();
    ex4l2.add("Hello");
    ex4l2.add("World");
    ex4l2.add(null);
    t.checkException(new IllegalArgumentException("null value in second list"),this,"merge", c4,ex4l1,ex4l2);
    Comparator<Boolean> c5=new BooleanCompare();
    List<Boolean>ex5l1=new ArrayList<Boolean>();
    ex5l1.add(false);
    ex5l1.add(false);
    ex5l1.add(true);
    ex5l1.add(true);
    List<Boolean>ex5l2=new ArrayList<Boolean>();
    ex5l2.add(false);
    ex5l2.add(false);
    ex5l2.add(true);
    List<Boolean> ex5Merge=new ArrayList<Boolean>();
    ex5Merge.add(false);
    ex5Merge.add(false);
    ex5Merge.add(false);
    ex5Merge.add(false);
    ex5Merge.add(true);
    ex5Merge.add(true);
    ex5Merge.add(true);
    t.checkExpect(CompareLists.merge(c5,ex5l1,ex5l2),ex5Merge);
  }
}
class CompareListsExamples
{
  String[] arr={"Hello",null,"World"};
  Comparator<String> c=new StringLengthCompare();
  boolean b=CompareLists.inOrder(arr, c);
}

