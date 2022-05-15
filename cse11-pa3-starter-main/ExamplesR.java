class ExamplesR
{
    R rTest = new R("Hello", r);
    //ExamplesR.java:3: error: cannot find symbol
    //R rTest = new R("Hello", r);
    //                         ^
    //symbol:   variable r
    //location: class ExamplesR
//1 error
    //It is not possible to construct an R object because the R class constructor needs an initial object of R type as a parameter
    //which we do not have.
}

class R
{
    String str;
    R r;
    R(String str, R r)
    {
        this.str=str;
        this.r=r;
    }
}