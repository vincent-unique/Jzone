package org.trump.vincent.algorithm.ranks;

public class QuickRank {

    public static void quickRank(int low,int high,int[] nums){

        int pivot = nums[low];
        int i=low,j=high;
        while (i<j){

            while (i<j&& nums[j]>= pivot) --j;

            while (i<j && nums[i]<= pivot) ++i;

            if(i<j){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        nums[low] = nums[i];
        nums[i] = pivot;
        if(low < i-1) {
            quickRank(low, i-1,nums);
        }
        if(high>i+1){
            quickRank(i+1,high,nums);
        }
    }

    public static void main(String[] args) {
        int[] numbers = { 10, 39,15, 20, 1, 3,0,40,7,4,19};
        quickRank(0,numbers.length-1,numbers);

        for(int i=0;i<numbers.length;i++){
            System.out.print(Integer.toBinaryString(numbers[i])+"\t,");
        }

    }
}
