package com.company.leetcode;

public class Solution_50 {
    //快速幂
    public double myPow(double x, int n) {
        boolean flag = true;
        if (n<0){
            flag = false;
            n = -n;
        }
        boolean flag2 = true;
        if ((n&1) == 1){
            flag2 = false;
            n = n-1;
        }
        double result = 1;
        double temp = x;
        while (n != 0){
            if ((n&1)==1){
                result *= temp;
            }
            n /= 2;
            temp *= temp;
        }
        if (!flag2){
            result*=x;
        }
        if (!flag){
            result = 1/result;
        }
        return result;
    }
}
