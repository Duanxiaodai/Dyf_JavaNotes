package com.company.leetcode;

import java.util.HashSet;

public class Solution_3 {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int i = 0;
        int j = 0;
        int res = -1;
        while (i<=j&&j<s.length()){
            res = Math.max(j-i+1,res);
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
            }
            else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }
}
