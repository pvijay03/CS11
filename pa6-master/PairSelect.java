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
class PairSelect
{
    int[] getAs(Pair[] pairs)
    {
        int count=0;
        for(Pair x:pairs)
        {
            if(x!=null)
            {
                count++;
            }
        }
        int[] allAs = new int[count];
        int index=0;
        for(int i=0;i<pairs.length;i++)
        {
            if(pairs[i]!=null)
            {
                allAs[index]=pairs[i].a;
                index++;
            }
        }
        return allAs;
    }

    void testGetAs(Tester t)
    {
        Pair[] example1={};
        int[] example1Return={};
        t.checkExpect(this.getAs(example1),example1Return);
        Pair[] example2={new Pair(1,3),new Pair(4,2),new Pair(3,1)};
        int[] example2Return={1,4,3};
        t.checkExpect(this.getAs(example2),example2Return);
        Pair[] example3=example2;
        t.checkExpect(this.getAs(example3),example2Return);
        Pair[] example4=new Pair[5];
        int[] example4Return=new int[0];
        t.checkExpect(this.getAs(example4),example4Return);
        Pair[] example5=new Pair[5];
        example5[0]=new Pair(1,3);
        example5[1]=new Pair(4,2);
        example5[4]=new Pair(3,1);
        int[] example5Return={1,4,3};
        t.checkExpect(this.getAs(example5),example5Return);
    }
}
