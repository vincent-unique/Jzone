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
    public static int removeElement(int[] nums ,int ele){
        int i = 0, j= 0;
        for(;i<nums.length;i++){
            if(nums[i]==ele){
                continue;
            }
            nums[j] = nums[i];
            j++;

          /* if(nums[i]!=ele){
               j++;
           }else {
               continue;
           }
           nums[j-1] = nums[i];*/
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
    public static int removeDuplicate(int[] nums){
        int i = 1, j = 1;
        for(i=1;i<nums.length;i++){
            if( nums[i]==nums[i-1]){
                continue;
            }
            nums[j] = nums[i];
            j++;
        }
        return j;
    }

    /**
     * Problem_3:
     * Desc:在一个有序整形数组中（有重复），只允许结果中只存在k组以下的重复
     *
     * Solution：和上一问题类似，只是加上一个重复率的技术器
     *
     * @param nums
     * @param k
     * @return
     */
    public static int removeDuplicate_k(int[] nums,int k){
        int i = 1, j = 1 ,
                count = 0;
        for(i=1;i<nums.length;i++){
            if( nums[i]==nums[i-1]){
                count++;
                if(count<=k){
                    j++;
                }
                continue;
            }
            nums[j] = nums[i];
            j++;
        }
        return j;
    }

    /**
     * Problem_4:
     * Desc: 一个用数组表示的整数（数组的每一位对应整数digit的一位，高位在数组O位），求对该整数加1的数组表示
     *
     * Solution：从末尾开始，加一操作（如果末尾小于9，则加一后返回；如果末尾等于9，向前一位做加一操作）
     *
     * @param digit
     * @return
     */
    public static int[] plusOne(int[] digit){
        for (int i=digit.length-1;i>=0;i--){
            if(digit[i]<9){
                digit[i]+=1;
                return digit;
            }
            digit[i]=0;
        }
        int[] result = new int[digit.length+1];
        result[0] = 1; //结果是一个以1开头的后续皆为0的整数；
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3,4};
//        System.out.println(removeDuplicate(nums));
        int l = removeDuplicate_k(nums,2);
        for(int i=0;i<l;i++){
            System.out.println(nums[i]);
        }
    }
}
