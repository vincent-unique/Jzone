package org.trump.vincent.algorithm.ranks;

/**
 * Created by Vincent on 2017/12/13 0013.
 */
public class PopRank {

    public static Integer[] generalPop(Integer [] raws ){
        if(raws!=null&&raws.length>0){
            /*for(int i=0;i<raws.length-1;i++){
                for(int j=0;j<raws.length-i-1;j++){
                    if(raws[j]>raws[j+1]){
                        Integer temp = raws[j+1];
                        raws[j+1] = raws[j];
                        raws[j] = temp;
                    }
                }
            }*/

            for(int i=raws.length-1;i>0;i--){
                for(int j=0;j<i;j++){
                    if(raws[j]>raws[j+1]){
                        Integer temp = raws[j+1];
                        raws[j+1] = raws[j];
                        raws[j]= temp;
                    }
                }
            }
        }
        return raws;
    }


    public static Integer[] optimizePop(Integer[] raws){
        if(raws!=null&& raws.length>0){
            for(int i=raws.length-1;i>0;i--){
                int optimizeFactor = i;
                for (int j=0;j<optimizeFactor;j++){
                    if(raws[j]>raws[j+1]){
                        optimizeFactor = j;
                        Integer temp = raws[j+1];

                    }
                }
            }
        }
        return raws;
    }
}
