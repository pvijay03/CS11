class Stats
{  
    public static void main(String[] args)
    {
        double result;
        int count;
        if(args[0].equals("--product"))
        {
            result=1;
            for(int i=1;i<args.length;i++)
            {
                result*=Double.parseDouble(args[i]);
            }
            System.out.println(result);
            
        }
        else if(args[0].equals("--mean"))
        {
            result=0;
            count=args.length-1;
            for(int i=1;i<args.length;i++)
            {
                result+=Double.parseDouble(args[i]);
            }
            result/=count;
            System.out.println(result);

        }
        else if(args[0].equals("--total"))
        {
            result=0;
            for(int i=1;i<args.length;i++)
            {
                result+=Double.parseDouble(args[i]);
            }
            System.out.println(result);
        }
        else if(args[0].equals("--max"))
        {
            result=Double.parseDouble(args[1]);
            for(int i=2;i<args.length;i++)
            {
                if(Double.parseDouble(args[i])>result)
                    result=Double.parseDouble(args[i]);
            }
            System.out.println(result);
        }
        else if(args[0].equals("--min"))
        {
            result=Double.parseDouble(args[1]);
            for(int i=2;i<args.length;i++)
            {
                if(Double.parseDouble(args[i])<result)
                    result=Double.parseDouble(args[i]);
            }
            System.out.println(result);
        }
        else 
            System.out.println("Bad option " + args[0]);

    }
}