package com.company.leetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode pro = new ListNode(-1);
        ListNode cur = pro;
        while (temp1!=null&&temp2!=null){
            if (temp1.val<temp2.val){
                cur.next = temp1;
                temp1 = temp1.next;
            }
            else {
                cur.next = temp2;
                temp2 = temp2.next;
            }
            cur = cur.next;
        }
        if (temp1!=null){
            cur.next = temp1;
        }
        if (temp2!=null){
            cur.next = temp2;
        }
        return pro.next;
    }
}
