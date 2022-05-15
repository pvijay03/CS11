// EXAM INSTRUCTIONS:
// All of your code for Task 1 goes in this file.
import tester.*;
class ExamplesArrays
{
    double averageWithoutLowest(double[] dbls)
    {
        double lowest=dbls[0];
        double mean=0;
        if(dbls.length==0)
            return 0;
        for(double x:dbls)
        {
            if(x<lowest)
            {
                lowest=x;
            }
            mean+=x;
        }
        return (mean-lowest)/(dbls.length-1);

        //VIDEO EXAMPLE TABLE for double[] videoExample = {4.0,5.0,2.0,6.0}
        /*
        if the length of dbls was 0, we would return 0
        x start   x end   lowest start   lowest end   mean start   mean end
        4.0        4.0     4.0             4.0          0.0         4.0
         5.0        5.0     4.0             4.0         4.0         9.0
         2.0        2.0     4.0             2.0         9.0         11.0
         6.0        6.0     2.0             2.0          11.0       17.0

        return (17.0-2.0)/(3)
        expect to be 5.0
        */
    }

    int[] sumOfPairs(Pair[] pairs)
    {
        int[] sums = new int[pairs.length];
        for(int i=0;i<pairs.length;i++)
        {
            sums[i]=pairs[i].a+pairs[i].b;
        }
        return sums;
    }

    Region[] regionsWithPoint(Region[] regions, Point p)
    {
        int count=0;
        int index=0;
        for(Region x:regions)
        {
            if(x.contains(p))
                count++;
        }
        Region[] within = new Region[count];
        for(Region x:regions)
        {
            if(x.contains(p))
            {
                within[index]=x;
                index++;
            }
        }
        return within;
    }

    //TESTS
    void testAverageWithoutLowest(Tester t) {
		double[] unique = {1.0,2.0,3.0};
		t.checkExpect(averageWithoutLowest(unique), 2.5);
        //VIDEO TEST
        double[] videoExample = {4.0,5.0,2.0,6.0};
        t.checkExpect(averageWithoutLowest(videoExample),5.0);
	};
	void testRegionsWithPoint(Tester t) {
		Region[] regions = {new CircleRegion(new Point(0, 0), 5), new CircleRegion(new Point(0, 0), 10)};
		Region[] result = {new CircleRegion(new Point(0, 0), 10)};
		t.checkExpect(regionsWithPoint(regions, new Point(9, 0)), result);
	};
	void testSumOfPairs(Tester t) {
		Pair[] pairs = {new Pair(1, 2), new Pair(3, 4)};
		int[] result = {3, 7};
		t.checkExpect(sumOfPairs(pairs), result);
	};
}

class Pair
{
    int a;
    int b;
    Pair(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
}
