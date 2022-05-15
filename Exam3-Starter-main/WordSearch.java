import java.nio.file.*;
import java.io.*;
import java.util.*;
class WordSearch
{
    public static void main(String[] args) throws IOException
    {   
        String[] words = Files.readString(Paths.get(args[0])).split("\n");
        Map<String,Integer> docMap = new LinkedHashMap<String,Integer>();
        for(int i=1;i<args.length;i++)
        {
            docMap.put(args[i],0);
        }
        Map<String,Integer> wordMap = new LinkedHashMap<String,Integer>();
        for(String k:words)
        {
            wordMap.put(k,0);
        }
        String search=words[0];
        for(String k:wordMap.keySet())
        {
            for(int i=1;i<args.length;i++)
            {
                if(Files.readString(Paths.get(args[i])).contains(k))
                    wordMap.put(k,wordMap.get(k)+1);
            }
        }
        boolean b=false;
        for(String k:wordMap.keySet())
        {
            if(wordMap.get(k)!=0)
                b=true;
        }
        if(b==false)
        {
            System.out.println("No matches found.");
            return;
        }
        for(String k:wordMap.keySet())
        {
            if(wordMap.get(k)>wordMap.get(search))
                search=k;
        }
        System.out.println("Most relevant search term: " + search);
        for(String k:wordMap.keySet())
        {
            for(int i=1;i<args.length;i++)
            {
                if(Files.readString(Paths.get(args[i])).contains(k))
                    docMap.replace(args[i],docMap.get(args[i])+1);
            }
        }
        String document=args[1];
        for(String k:docMap.keySet())
        {
            if(docMap.get(k)>docMap.get(document))
                document=k;
        }
        System.out.println("Most relevant document: " + document+"\n");
        String w;
        List<String> docs=new ArrayList<String>();
        for(String k:wordMap.keySet())
        {
            w=k+": "+wordMap.get(k)+" ";
            for(String j:docMap.keySet())
            {
                if(Files.readString(Paths.get(j)).contains(k))
                    docs.add(j);
            }
            System.out.println(w+docs.toString());
            docs.clear();
        }
        System.out.println("\n");
        List<String> ws=new ArrayList<String>();
        for(String k:docMap.keySet())
        {
            w=k+": "+docMap.get(k)+" ";
            for(String j:wordMap.keySet())
            {
                if(Files.readString(Paths.get(k)).contains(j))
                    ws.add(j);
            }
            System.out.println(w+ws.toString());
            ws.clear();
        }
    }
}