import tester.*;
interface Number
{
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toText();
    double toDouble();
}

class WholeNumber implements Number
{
    int n;
    WholeNumber(int n)
    {
        this.n=n;
    }
    public int numerator()
    {
        return n;
    }
    public int denominator()
    {
        return 1;
    }
    public Number add(Number other)
    {
        return new Fraction(this.denominator()*other.numerator()+other.denominator()*this.numerator(),this.denominator()*other.denominator());
    }
    public Number multiply(Number other)
    {
        return new Fraction(this.numerator()*other.numerator(),this.denominator()*other.denominator());
    }
    public String toText()
    {
        return "" + n;
    }
    public double toDouble()
    {
        return n;
    }
}

class Fraction implements Number
{
    int n;
    int d;
    Fraction(int n, int d)
    {
        this.n=n;
        this.d=d;
    }
    public int numerator()
    {
        return n;
    }
    public int denominator()
    {
        return d;
    }
    public Number add(Number other)
    {
        return new Fraction(this.denominator()*other.numerator()+other.denominator()*this.numerator(),this.denominator()*other.denominator());
    }
    public Number multiply(Number other)
    {
        return new Fraction(this.numerator()*other.numerator(),this.denominator()*other.denominator());
    }
    public String toText()
    {
        return "" + n + "/" + d;
    }
    public double toDouble()
    {
        return 1.0*n/d;
    }

}

class ExamplesNumbers
{
    //GIVEN TESTS
    Number n1 = new WholeNumber(5);
    Number n2 = new WholeNumber(7);
    Number n3 = new Fraction(7, 2);
    Number n4 = new Fraction(1, 2);

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 12.0);
        t.checkExpect(this.n1.add(this.n3).toDouble(), 5 + 7.0/2.0);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 7.0);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 2.5);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 7.0/4.0);
    }

    void testNumDem(Tester t) {
        t.checkExpect(this.n3.numerator(), 7);
        t.checkExpect(this.n1.numerator(), 5);
        t.checkExpect(this.n4.denominator(), 2);
        t.checkExpect(this.n2.denominator(), 1);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n4.toText(), "1/2");
        t.checkExpect(this.n3.toText(), "7/2");
        t.checkExpect(this.n2.toText(), "7");
    }

    //EXPLORATION
    double ex1 = 0.1+0.2+0.3;
    double ex2 = 0.1+(0.2+0.3);
    Number n5 = new Fraction(1,10);
    Number n6 = new Fraction(1,5);
    Number n7 = new Fraction(3,10);
    String ex3 = n5.add(n6).add(n7).toText();
    String ex4 = n5.add(n6.add(n7)).toText();  
}
