class ExamplesMethods 
{
    double ringArea(double innerR, double outerR)
    {
        return Math.PI*(outerR*outerR-innerR*innerR);
    }

    String rotate(String str, int n) 
    {
        if(str.length()<n)  
            return str; 
        else
            return str.substring(n)+str.substring(0,n); 
    }

    double ringAreaEx1 = this.ringArea(2,3); //Expected value about 15.708
    double ringAreaEx2 = this.ringArea(2,5); //Expected value about 65.973
    double ringAreaEx3 = this.ringArea(4,1); //Expected value about -47.124
    double ringAreaEx4 = this.ringArea(5,3); //Expected value about -50.265

    String rotateEx1 = this.rotate("Hello",2); //Expected value "lloHe"
    String rotateEx2 = this.rotate("World",3); //Expected value "ldWor"
    String rotateEx3 = this.rotate("Exam",6); //Expected value "Exam"
    String rotateEx4 = this.rotate("Programming",15); //Expected value "Programming"
}
