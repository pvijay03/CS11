import java.util.*;
import tester.*;
class ComparatorLookupTable <K,V>
{
    List<K> keys;
    List<V> values;
    Comparator<K> c;
    ComparatorLookupTable(List<K> keys,List<V> values, Comparator<K> c)
    {
        this.keys=keys;
        this.values=values;
        this.c=c;
    }
    boolean contains(K key)
    {
        for(K x:keys)
        {
            if(c.compare(x,key)==0)//if(x.equals(key))   
                return true;
        }
        return false;
    }
    void add(K key,V value)
    {
        for(K x:keys)
        {
            if(c.compare(x,key)==0)
            {
                throw new IllegalArgumentException();
            }
        }
        keys.add(key);
        values.add(value);
    }
    V find(K key)
    {
        if(keys.indexOf(key)==-1)
            throw new NoSuchElementException(); //This line on stack 
        return values.get(keys.indexOf(key));
    }
    void update(K key,V value)
    {
        if(keys.indexOf(key)==-1)
            throw new NoSuchElementException();
        values.set(keys.indexOf(key),value);
    }
}
class StringComparator implements Comparator<String> 
{
    public int compare(String s1, String s2) 
    {
      return s1.compareTo(s2);
    }
}
class ComparatorLookupTableExamples 
{
    void testUpdate(Tester t)
    {
      List<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c"));
      List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
      ComparatorLookupTable<String,Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());
      t.checkExpect(ctl.contains("a"), true);
      ctl.update("a", 9);
      t.checkExpect(ctl.find("a"), 9);
      ctl.add("z", 10);
      t.checkExpect(ctl.keys, Arrays.asList("a", "b", "c", "z"));
      t.checkExpect(ctl.values, Arrays.asList(9, 2, 3, 10));
  
      t.checkException(new IllegalArgumentException(), ctl, "add", "z", 5);
      t.checkException(new NoSuchElementException(), ctl, "find", "y");
    }

    //VIDEO TEST
    void testFind(Tester t)
    {
        List<String> strs = new ArrayList<>(Arrays.asList("Hello", "World", "Programming","CSE11"));
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3,4));
        ComparatorLookupTable<String,Integer> ctl = new ComparatorLookupTable<>(strs, nums,new StringComparator());
        //t.checkException(new NoSuchElementException(), ctl, "find", "CSE12");
        int v=ctl.find("CSE12"); //This line on stack
    }
  }
  
  /*
  class                                 method          this reference          other variables
  ComparatorLookupTableExamples         testFind        <ignore>                strs = :1; nums = :2; ctl = :3
  ComparatorLookupTable                 find            :3                       -
  */