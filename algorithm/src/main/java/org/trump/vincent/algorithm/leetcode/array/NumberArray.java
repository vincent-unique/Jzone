package org.trump.vincent.algorithm.leetcode.array;

public class NumberArray {

    /**
     * Problem_1:
     * Desc: 从Number数组中移除特定值ele，并返回最终的数组长度；要求不允许扩充多余的空间
     *
     * Solution：以双游标的方式遍历数组（游标i正常移动，游标j仅在值不为ele时才移动），
     *即， 当index当前值等于ele时，使用i的值覆盖j位
     *
     * @param nums
     * @param ele
     * @return
     */
    public int removeElement(int[] nums ,int ele){
        int i = 0, j= 0;
        for(;i<nums.length;i++){
           /* if(nums[i]==ele){
                continue;
            }
            nums[j] = nums[i];
            j++;*/

           if(nums[i]!=ele){
               j++;
           }
           nums[j] = nums[i];
        }
        return j;
    }

    /**
     * Problems_2:
     * Desc:c从有序数组中移除重复的元素，并返回最终数组长度；要求不允许扩展多余的空间
     *
     * Solution:
     *
     * @param nums
     * @return
     */
    public int removeDuplicate(int[] nums){

    }
}
