package com.company.leetcode;

public class Solution_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null||root == p||root==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode rigit = lowestCommonAncestor(root.right,p,q);
        if (left == null){
            return rigit;
        }
        if (rigit == null){
            return left;
        }
        return root;
    }
}
