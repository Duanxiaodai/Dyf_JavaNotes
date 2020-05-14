package com.company.leetcode;

import java.util.LinkedList;
import java.util.List;

public class Solution_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.push(root);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            while (size-->0){
                TreeNode pop = queue.pop();
                temp.add(pop.val);
                if (pop.left!=null){
                    queue.add(pop.left);
                }
                if (pop.right!=null){
                    queue.add(pop.right);
                }
            }
            res.add(new LinkedList<>(temp));
        }
        return res;
    }
}
