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
                if(args[1].contains("not"))
                {
                    if(!q.matches(x))
                        System.out.println(x);
                }
                else
                {
                    if(q.matches(x))
                        System.out.println(x);
                }
            }
        }
    }
    public static Query readQuery(String q)
    {
        if(q.contains("contains"))
            return new Contains(q);
        else if(q.contains("length"))
            return new Length(q);
        else if(q.contains("greater"))
            return new Greater(q);
        else if(q.contains("less"))
            return new Less(q);
        else if(q.contains("starts"))
            return new Starts(q);
        else//(q.contains("ends"))
            return new Ends(q);
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
        this.c=c.substring(c.indexOf("'"),c.lastIndexOf("'")+1);
    }
    public boolean matches(String s)
    {
        return s.contains(c.substring(1,c.length()-1));
    }
}
class Length implements Query
{
    String c;
    public Length(String c)
    {
        if(c.contains("not"))
            this.c=c.substring(c.indexOf("=")+1,c.indexOf(")"));
        else
            this.c=c.substring(c.indexOf("=")+1);
    }
    public boolean matches(String s)
    {
        return s.length()==Integer.parseInt(c.substring(c.indexOf("=")+1));
    }
}
class Greater implements Query
{
    String c;
    public Greater(String c)
    {
        if(c.contains("not"))
            this.c=c.substring(c.indexOf("=")+1,c.indexOf(")"));
        else
            this.c=c.substring(c.indexOf("=")+1);
    }
    public boolean matches(String s)
    {
        return s.length()>Integer.parseInt(c.substring(c.indexOf("=")+1));
    }
}
class Less implements Query
{
    String c;
    public Less(String c)
    {
        if(c.contains("not"))
            this.c=c.substring(c.indexOf("=")+1,c.indexOf(")"));
        else
            this.c=c.substring(c.indexOf("=")+1);
    }
    public boolean matches(String s)
    {
        return s.length()<Integer.parseInt(c.substring(c.indexOf("=")+1));
    }
}
class Starts implements Query
{
    String c;
    public Starts(String c)
    {
        this.c=c.substring(c.indexOf("'"),c.lastIndexOf("'")+1);
    }
    public boolean matches(String s)
    {
        return s.startsWith(c.substring(1,c.length()-1));
    }
}
class Ends implements Query
{
    String c;
    public Ends(String c)
    {
        this.c=c.substring(c.indexOf("'"),c.lastIndexOf("'")+1);
    }
    public boolean matches(String s)
    {
        return s.endsWith(c.substring(1,c.length()-1));
    }
}
