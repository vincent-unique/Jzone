package org.trump.vincent.algorithm.leetcode.array;

/**
 * 帕斯卡三角
 * 二维数组
 */
public class PascalTriangle {


    /**
     * Problem_2:
     * Desc: 限于一维数组内求第K层的帕斯卡三角行
     * <p>
     * Solution:
     *
     * @param rowNum
     * @return
     */
    public int[] computeRow(int rowNum) {
        // 规律发现，帕斯卡数组每一个的数字个数与行数对应
        int[] row = new int[rowNum];

        return row;
    }

    public void _computeRow(int[] num, int rowNum) {

        if (rowNum == 1) {
            num[0] = 1;
        } else if (rowNum == 2) {
            num[0] = 1;
            num[1] = 1;
        } else {
            for (int i = 3; i <= rowNum; i++) {

            }
        }
    }


}
