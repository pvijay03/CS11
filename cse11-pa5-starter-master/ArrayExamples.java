import tester.*;
class Pair
{
    int a,b;
    Pair(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
}

class ArrayExamples
{
    static String joinWith(String [] strs, String sep)
    {
        if(strs.length==0)
            return "";
        String joint=strs[0];
        for(int i=1;i<strs.length;i++)
        {
            joint+=sep + strs[i];
        }
        return joint;
    }

    static boolean somethingFalse(boolean [] bools)
    {
        if(bools.length==0)
            return false;
        for(boolean x:bools)
        {
            if(x==false)
                return true;
        }
        return false;
    }

    static int countWithinRange(double [] dbls,double low, double high)
    {
        int count=0;
        for(double x:dbls)
        {
            if(x>=low&&x<=high)
                count++;
        }
        return count;
    }

    static double [] numsWithinRange(double [] dbls, double low, double high)
    {
        double [] nums = new double [countWithinRange(dbls,low,high)];
        int i=0;
        for(double x:dbls)
        {
            if(x>=low&&x<=high)
            {
                nums[i]=x;
                i++;
            }
        }
        return nums;
    }

    static Pair maxmin(int [] ints)
    {
        int min=ints[0];
        int max=ints[0];
        for(int x:ints)
        {
            if(x<=min)
                min=x;
            if(x>=max)
                max=x;
        }
        return new Pair(min,max);
    }

    static String earliest(String [] strs)
    {
        String earliest=strs[0];
        for(int i=1;i<strs.length;i++)
        {
            if(earliest.compareTo(strs[i])>0)
                earliest = strs[i];
        }
        return earliest;
    }
}

//MY TEST EXAMPLES
class MyArrayExamples
{
    void testMyJoinWith(Tester t){
        String[] example1 = {"Hello", "World!"};
        String[] example2 = {"I", "Love", "Programming!"};
        String[] example3 = {};
        t.checkExpect(ArrayExamples.joinWith(example1, ", "), "Hello, World!");
        t.checkExpect(ArrayExamples.joinWith(example2, " "), "I Love Programming!");
        t.checkExpect(ArrayExamples.joinWith(example3, ", "), "");
      }
    
      void testMySomethingFalse(Tester t){
        boolean[] example1 = {true, true, true};
        boolean[] example2 = {false, false, false};
        boolean[] example3 = {};
        t.checkExpect(ArrayExamples.somethingFalse(example1), false);
        t.checkExpect(ArrayExamples.somethingFalse(example2), true);
        t.checkExpect(ArrayExamples.somethingFalse(example3), false);
      }
    
      void testMyCountWithinRange(Tester t){
        double[] example1 = {100.23, 33.4, 55.4132};
        double[] example2 = {-1.3,-5.4,-2.1};
        double[] example3 = {};
        t.checkExpect(ArrayExamples.countWithinRange(example1, 55.4131, 100.231), 2);
        t.checkExpect(ArrayExamples.countWithinRange(example2, -1.5,0), 1);
        t.checkExpect(ArrayExamples.countWithinRange(example3, 0,5), 0);
      }
    
      void testMyNumsWithinRange(Tester t){
        double[] example1 = {1.3, 8.7, 2.4, 3.2, 5.8, 10.4, 8.3};
        double[] expected1 = {8.7, 5.8, 8.3};
        double[] example2 = {3.2,4.4,6.4};
        double[] expected2 = {};
        double[] example3 = {};
        double[] expected3 = {};
        t.checkExpect(ArrayExamples.numsWithinRange(example1, 5.1, 9.0), expected1);
        t.checkExpect(ArrayExamples.numsWithinRange(example2, 0.0, 3.0), expected2);
        t.checkExpect(ArrayExamples.numsWithinRange(example3, 1.0, 100.0), expected3);
      }
    
      void testMyMaxmin(Tester t){
        int[] example1 = {4, 4, 4, 3, 1};
        int[] example2 = {4, -4, 2, 3, 1,7};
        int[] example3 = {3,3};
        t.checkExpect(ArrayExamples.maxmin(example1), new Pair(1, 4));
        t.checkExpect(ArrayExamples.maxmin(example2), new Pair(-4, 7));
        t.checkExpect(ArrayExamples.maxmin(example3), new Pair(3, 3));
      }
    
      void testMyEarliest(Tester t){
        String[] example1 = {"xz", "xy", "xz", "xyz"};
        String[] example2 = {"alligator", "crocodile", "address", "corner"};
        String[] example3 = {"b", "d", "e", "d", "b"};
        t.checkExpect(ArrayExamples.earliest(example1), "xy");
        t.checkExpect(ArrayExamples.earliest(example2), "address");
        t.checkExpect(ArrayExamples.earliest(example3), "b");
      }
}

class ProvidedArrayExamples {
  void testJoinWith(Tester t){
    String[] example1 = {"a", "b","c"};
    t.checkExpect(ArrayExamples.joinWith(example1, ":"), "a:b:c");
  }

  void testSomethingFalse(Tester t){
    boolean[] example1 = {true, false};
    t.checkExpect(ArrayExamples.somethingFalse(example1), true);
  }

  void testCountWithinRange(Tester t){
    double[] example = {0.1, 1.3, 2.6};
    t.checkExpect(ArrayExamples.countWithinRange(example, 1.1, 2.2), 1);
  }

  void testNumsWithinRange(Tester t){
    double[] example = {0.0, 3.0, 1.4, 1.5, 2.7, 9.1, 2.1};
    double[] expected = {1.4, 1.5, 2.1};
    t.checkExpect(ArrayExamples.numsWithinRange(example, 1.1, 2.2), expected);
  }

  void testMaxmin(Tester t){
    int[] example = {4, 5, 2, 3, 1};
    t.checkExpect(ArrayExamples.maxmin(example), new Pair(1, 5));
  }

  void testEarliest(Tester t){
    String[] example = {"aa", "aab", "abcd", "a"};
    t.checkExpect(ArrayExamples.earliest(example), "a");
  }
}