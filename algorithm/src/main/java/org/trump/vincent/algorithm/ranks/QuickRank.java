package org.trump.vincent.algorithm.ranks;

public class QuickRank {

    public void quickRank(int start,int end,int[] nums){
        if(start<end){
            int origin = nums[start];
            int i = start, j = end;
            while (i!=j){

                while (i<j && nums[j]>origin) --j;
                if(start<end){
                    nums[i] = nums[j];
                    ++i;
                }

                while (i<j && nums[i]<origin) ++i;
                if(start<end){
                    nums[j] = nums[i];
                    --j;
                }
            }

            nums[i] = origin;

            quickRank(start,i-1,nums);
            quickRank(i+1,end,nums);
        }
    }
}
