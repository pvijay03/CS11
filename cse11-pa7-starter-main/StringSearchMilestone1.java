import java.nio.file.*;
import java.io.*;
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
    }
}