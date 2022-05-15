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
            String[] s=args[1].split("&");
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            for(String x:lines)
            {
                if(matchesAll(s,x))  
                    System.out.println(x);      
            }
        }

        if(args.length==3)
        {
            String[] s=args[1].split("&");
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            List<String> newLines = new ArrayList<String>();
            for(String x:lines)
            {
                if(matchesAll(s,x))  
                    newLines.add(x);      
            }
            String[] t=args[2].split("&");
            for(String x:newLines)
            {
                System.out.println(applyAll(t,x));
            }
        }
    }
    public static Query readQuery(String q)
    {
        if(q.contains("contains='"))
            return new Contains(q);
        else if(q.contains("length="))
            return new Length(q);
        else if(q.contains("greater="))
            return new Greater(q);
        else if(q.contains("less="))
            return new Less(q);
        else if(q.contains("starts='"))
            return new Starts(q);
        else if(q.contains("ends='"))
            return new Ends(q);
        else 
            return new InvalidQuery(q);
    }
    public static Transform readTransform(String t)
    {
        if(t.equals("upper"))
            return new Upper();
        else if(t.equals("lower"))
            return new Lower();
        else if(t.contains("first="))
            return new First(t);
        else if(t.contains("last="))
            return new Last(t);
         else if(t.contains("replace='"))
            return new Replace(t);
        else 
            return new InvalidTransform();
    }
    public static boolean matchesAll(String[] qs, String s)
    {
        boolean b = false;
        Query[] q=new Query[qs.length];
        for(int i=0;i<qs.length;i++)
        {
            q[i]=readQuery(qs[i]);
        }
        for(int i=0;i<q.length;i++)
        {
            if(qs[i].contains("not"))
            {
                if(!q[i].matches(s))
                    b=true;
                else    
                    return false;
            }
            else
            {
                if(q[i].matches(s))
                    b=true;
                else
                    return false;
            }
        }
        return b;
    }
    public static String applyAll(String[] ts, String s)
    {
        String newS=s;
        Transform[] t=new Transform[ts.length];
        for(int i=0;i<ts.length;i++)
        {
            t[i]=readTransform(ts[i]);
        }
        for(Transform x:t)
        {
            newS=x.transform(newS);
        }
        return newS;
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
class InvalidQuery implements Query
{
    String c;
    public InvalidQuery(String c)
    {
        this.c=c;
    }
    public boolean matches(String s)
    {
        if(c.contains("not("))
            return true;
        return false;
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
class InvalidTransform implements Transform
{
    public InvalidTransform()
    {
    }
    public String transform(String s)
    {
        return s;
    }
}