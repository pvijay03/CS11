class AveragePositives
{
    public static void main(String[] args)
    {
        double total=0;
        double count=0;
        for(String x:args)
        {
            if(Double.parseDouble(x)>0)
            {
                total+=Double.parseDouble(x);
                count++;
            }
        }
        if(total==0)
        {
            System.out.println(total);
            return;
        }
        System.out.println(total/count);
    }
}