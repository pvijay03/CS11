import java.nio.file.*;
import java.io.*;
import java.util.*;
class StringSearch
{
    public static void main(String[] args) throws IOException
    {
        if(args.length<1||args.length>3)
        {
            throw new IllegalArgumentException("Enter 1-3 arguments only");
        }

        if(args.length==1)
        {
            System.out.println(Files.readString(Paths.get(args[0])));
        }   

        if(args.length==2)
        {
            Query q = readQuery(args[1]);
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            for(String x:lines)
            {
                if(q.matches(x))
                    System.out.println(x);
            }
        }
    }
    public static Query readQuery(String q)
    {
            return new Contains(q);
    }
}
interface Query
{
    boolean matches(String s);
}
class Contains implements Query
{
    String c;
    public Contains(String c)
    {
        this.c=c.substring(c.indexOf("'"));
    }
    public boolean matches(String s)
    {
        return s.contains(c.substring(1,c.length()-1));
    }
}


