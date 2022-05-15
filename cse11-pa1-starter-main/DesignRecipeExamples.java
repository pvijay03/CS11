//import tester.*;

class DesignRecipeExamples {
  
  public int perimeter(int width, int height)
  {
    return width * 2 + height * 2;
  }

  int x=perimeter(5,5); //20
  int y=perimeter(3,6); //18

  public int borderArea(int width1,int height1, int width2, int height2)
  {
    //rectangle 1 is larger than rectangle 2
    return width1*height1-width2*height2;
  }

  int a=borderArea(5,10,3,7); //29
  int b=borderArea(5,5,3,3); //16

  public int KelvinToCelsius (int K) 
  {
    return K-273;
  }
  //This method converts temperature from Kelvin to Celsius and returns 
  //the temperature in Celsius as an int.

  int temp1=KelvinToCelsius(0); //-273
  int temp2=KelvinToCelsius(15); //-258
  //The only difference is that the reference converter I used (Google)
  //subtracts 273.15 from Kelvin to convert to Celsius. We cannot do 
  //that here since we are returning an int and 273.15 is not an int.

  public int bill(int entree, int appetizer, int desert)
  {
    return entree+appetizer+desert;
  }
  //This method adds up the int prices for the entree, appetrizer, and 
  //desert for the total bill and returns that int.

  int bill1=bill(20,7,7); //34
  int bill2=bill(50,13,15); //78
  //The justification of the output of this code is just that it is the
  //sum of the three inputted values for the prices of each components.
  //This method is not very complex.
}


