package com.company.leetcode;

import java.util.LinkedList;

public class MinStack {
    LinkedList<Integer> stack  = new LinkedList<>();
    LinkedList<Integer> minstack  = new LinkedList<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (!minstack.isEmpty()&&minstack.peekFirst()<x){
            minstack.push(minstack.peek());
        }
        else {
            minstack.push(x);
        }
    }

    public void pop() {
        stack.pop();
        minstack.pop();
    }

    public int top() {
        return stack.peekFirst();
    }

    public int getMin() {
        return minstack.peekFirst();
    }
}
