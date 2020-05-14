package com.company.leetcode;

public class Solution_572 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t ==null) {
            return true;
        }
        else if (s==null||t==null){
            return false;
        }
        return helper(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    private boolean helper(TreeNode s, TreeNode t) {
        if (s == null && t ==null) {
            return true;
        }
        else if (s==null||t==null){
            return false;
        }
        else if (s.val!=t.val){
            return false;
        }
        return helper(s.left,t.left)&&helper(s.right,t.right);
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
