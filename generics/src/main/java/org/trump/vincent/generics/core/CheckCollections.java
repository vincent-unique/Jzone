package org.trump.vincent.generics.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vincent on 2017/11/28 0028.
 */
public class CheckCollections {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList();
        for(int i=1;i<20;i++){
            ints.add(i);
        }
        Iterator<Integer> iterator = ints.iterator();
        while (iterator.hasNext()){
            if(iterator.next()%2==0){
                iterator.remove();
            }
        }

        for(Integer le:ints){
            System.out.println(le);
        }
    }
}
