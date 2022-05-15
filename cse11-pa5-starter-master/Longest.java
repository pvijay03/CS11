class Longest
{
    public static void main(String[] args)
    {
        if(args.length==0)
            return;
        String longest=args[0];
        for(String x:args)
        {
            if(x.length()>longest.length())
                longest=x;
        }
        System.out.println(longest);
    }
}