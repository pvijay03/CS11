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

        if(args.length==3)
        {
            Query q = readQuery(args[1]);
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            List<String> newLines = new ArrayList<String>();
            List<String> transformLines = new ArrayList<String>();
            for(String x:lines)
            {
                if(args[1].contains("not"))
                {
                    if(!q.matches(x))
                        newLines.add(x);
                }
                else
                {
                    if(q.matches(x))
                        newLines.add(x);
                }
            }
            Transform t = readTransform(args[2]);
            for(String x:newLines)
            {
                transformLines.add(t.transform(x));
            }
            for(String x:transformLines)
            {
                System.out.println(x);
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
        else
            return new Ends(q);
    }
    public static Transform readTransform(String t)
    {
        if(t.contains("upper"))
            return new Upper();
        else if(t.contains("lower"))
            return new Lower();
        else if(t.contains("first"))
            return new First(t);
        else if(t.contains("last"))
            return new Last(t);
         else 
            return new Replace(t);
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
        this.c=c.substring(c.indexOf("=")+1,c.indexOf(")"));
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
interface Transform
{
    String transform(String s);
}
class Upper implements Transform
{
    public Upper()
    {
    }
    public String transform(String s)
    {
        return s.toUpperCase();
    }
}
class Lower implements Transform
{
    public Lower()
    {
    }
    public String transform(String s)
    {
        return s.toLowerCase();
    }
}
class First implements Transform
{
    String c;
    public First(String c)
    {
        this.c=c.substring(c.indexOf("=")+1);
    }
    public String transform(String s)
    {
        if(s.length()<Integer.parseInt(c))
            return s;
        return s.substring(0,Integer.parseInt(c));
    }
}
class Last implements Transform
{
    String c;
    public Last(String c)
    {
        this.c=c.substring(c.indexOf("=")+1);
    }
    public String transform(String s)
    {
        if(s.length()<Integer.parseInt(c))
            return s;
        return s.substring(s.length()-Integer.parseInt(c));
    }
}
class Replace implements Transform
{
    String replace;
    String replacement;
    public Replace(String t)
    {
        this.replace=t.substring(t.indexOf("=")+2,t.indexOf(";")-1);
        this.replacement=t.substring(t.indexOf(";")+2,t.length()-1);
    }
    public String transform(String s)
    {
        return s.replaceAll(replace,replacement);
    }
}