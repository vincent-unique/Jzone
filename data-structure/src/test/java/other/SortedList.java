package other;


import java.util.ArrayList;
import java.util.Comparator;

public class SortedList extends ArrayList<CompareObject> {
    public static void main(String[] args) {
        SortedList compareObjects = new SortedList();
        for(int i=10;i<100;i++){
            if(i%2==0){
                compareObjects.add(new CompareObject(i-3));
            }else {
                compareObjects.add(new CompareObject(i));
            }
        }
       for (CompareObject object:compareObjects){
            System.out.print(object.getKey()+",");
       }
        System.out.print("\n");

        compareObjects.sort(new Comparator<CompareObject>() {
            @Override
            public int compare(CompareObject o1, CompareObject o2) {
                return o1.compareTo(o2);
            }
        });

        for (CompareObject object:compareObjects){
            System.out.print(object.getKey()+",");
        }
        System.out.print("\n");
    }

}

class CompareObject implements Comparable<CompareObject>{
    public CompareObject(Integer key){
        this.key = key;
    }

    public int compareTo(CompareObject next){
        if(next==null||next.getKey()==null){
            return 1;
        }else if(this.key==null){
            return -1;
        }
        return this.key-next.getKey();
    }
    public Integer getKey() {
        return key;
    }

    public CompareObject setKey(Integer key) {
        this.key = key;
        return this;
    }

    private Integer key;
}
