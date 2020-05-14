package com.company.leetcode;

import com.sun.xml.internal.ws.api.client.WSPortInfo;
import sun.plugin.javascript.navig.Link;

import javax.xml.bind.annotation.XmlInlineBinaryData;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Solution_69 {
    public int mySqrt(int x) {
        int res =-1;
        int left = 0;
        int right = x;
        while (left<=right){
            int mid = (left+right)>>>1;
            if (mid*mid==x){
                return mid;
            }
            else if ( mid*mid > right ){
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LinkedList<Integer> res = new LinkedList<>();
        System.out.println(res);
        helper(res);
        System.out.println(res);
        int count = 2;
        System.out.println(count+"单预约单，"+(res.size()-count)+"单非预约单");
    }

    private static void helper(LinkedList<Integer> res) {
        for (int i = 0; i < 10; i++) {
            res.add(i);
        }
    }
}
