// EXAM INSTRUCTIONS:
// All of your code for Task 3 goes in this file.
class WordFilter
{
     public static void main(String[] args)
    {
        boolean none=true;
        if(args.length==0)
        {
            System.out.println("Provide at least one command-line argument");
            return;
        }
        for(int i=1;i<args.length;i++)
        {
            if(args[i].contains(args[0]))
            {
                none=false;
                System.out.println(args[i]);
            }
        }
        if(none)
            System.out.println("0 words contained \"" + args[0] + "\"");
    } 
}