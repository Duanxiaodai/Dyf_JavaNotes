复习

平衡二叉树的判断

和为S的连续正数序列

二叉搜索树的 第k的节点

不是很熟悉的

正则表达式匹配













[TOC]

# 数组中重复的数字Solution_3.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_3
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_3 {
    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

# 二维数组中的查找Solution_4.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_4
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_4 {
    public boolean Find(int target, int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1; // 从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c])
                return true;
            else if (target > matrix[r][c])
                r++;
            else
                c--;
        }
        return false;
    }
}
```

# 替换空格Solution_5.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_5
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_5 {
    public static String replaceSpace(StringBuffer str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' '){
                sum++;
            }
        }
        if (sum == 0){
            return str.toString();
        }
        char[] temp = new char[str.length()+2*sum];
        int key = 0;
        for (int i = 0; i <str.length(); i++) {
            if (str.charAt(i)!=' '){
                temp[i+key*2] = str.charAt(i);
            }
            else {
                temp[i+key*2] ='%';
                temp[i+key*2+1] ='2';
                temp[i+key*2+2] ='0';
                key++;
            }
            System.out.println(new String(temp));
        }
        return new String(temp);
    }
    public static String replaceSpace2(StringBuffer str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' '){
                sum++;
            }
        }
        if (sum == 0){
            return str.toString();
        }
        int oldindex = str.length()-1;
        str.setLength(str.length()+2*sum);
        int newindex = str.length()-1;
        for (;oldindex>=0 ; oldindex--) {
            if (str.charAt(oldindex)==' '){
                str.setCharAt(newindex--,'0');
                str.setCharAt(newindex--,'2');
                str.setCharAt(newindex--,'%');
            }
            else {
                str.setCharAt(newindex--,str.charAt(oldindex));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace2(new StringBuffer("hello  world")));
    }
}
```

# 从尾到头打印链表Solution_6.java

```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

import java.util.ArrayList;

/**
 * @ClassName Solution_6
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_6 {
    ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode==null){
            return new ArrayList<>();
        }
        else{
            printListFromTailToHead(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listNode;
        while(tmp!=null){
            list.add(0,tmp.val);
            tmp = tmp.next;
        }
        return list;
    }
}
```

# 重建二叉树Solution_7.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_7
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_7 {
}
```

# 二叉树的下一个结点Solution_8.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_8
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
public class Solution_8 {
    /*
    仔细观察，可以把中序下一结点归为几种类型：

        有右子树，下一结点是右子树中的最左结点，例如 B，下一结点是 H

        无右子树，且结点是该结点父结点的左子树，则下一结点是该结点的父结点，例如 H，下一结点是 E

        无右子树，且结点是该结点父结点的右子树，则我们一直沿着父结点追朔，直到找到某个结点是其父结点的左子树，
        如果存在这样的结点，那么这个结点的父结点就是我们要找的下一结点。例如 I，下一结点是 A；例如 G，
        并没有符合情况的结点，所以 G 没有下一结点
      */
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode.right!=null){
            TreeLinkNode temp = pNode.right;
            while(temp.left!=null){
                temp = temp.left;
            }
            return temp;
        }
        while(pNode.next!=null&&pNode.next.left != pNode){
            pNode = pNode.next;
        }
        if(pNode.next==null){
            return null;
        }
        return pNode.next;
    }
}
```

# 用两个栈实现队列Solution_9.java

```java
package A剑指Offer;
import java.util.Stack;
/**
 * @ClassName Solution_9
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_9 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {
        if(stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        else{
            return stack2.pop();
        }
    }
}
```



# 斐波那契数列Solution_10_1.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_10_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_10_1 {
    public int Fibonacci(int n) {
        if(n==0){
            return 0;
        }
        else if(n == 1){
            return 1;
        }
        int pro1 = 0;
        int pro2 = 1;
        for(int i = 2;i<=n;i++){
            int temp = pro1+pro2;
            pro1 = pro2;
            pro2 = temp;
        }
        return pro2;
    }
}
```
# 矩形覆盖Solution_10_2.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_11
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_10_2 {
    public int RectCover(int target) {
        int n = target;
        if(n==0){
            return 0;
        }
        else if(n == 1){
            return 1;
        }
        else if(n == 2){
            return 2;
        }
        int pro1 = 1;
        int pro2 = 2;
        for(int i = 3;i<=n;i++){
            int temp = pro1+pro2;
            pro1 = pro2;
            pro2 = temp;
        }
        return pro2;
    }
}
```
# 跳台阶Solution_10_3.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_12
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_10_3 {
    public int JumpFloorII(int target) {
        if(target == 0){
            return 0;
        }
        else if(target == 1){
            return 1;
        }
        else if(target == 2){
            return 2;
        }

        int []dp = new int[target+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3;i<=target;i++){
            for(int j = 0;j < i;j++){
                dp[i]+=dp[j];
            }
            dp[i]++;
        }
        return dp[target];
    }
	public static int jumpFloor2(int target) {
		if (target <= 0) return 0;
		if (target == 1) return 1;
		int a = 1;
		int b = 2;
		for (int i = 2; i <= target; i++) {
			b = 2 * a;
			a = b;
		}
		return b;
	}
    public int JumpFloorII3(int target) {
        if (target <= 0) return 0;
        return (int) Math.pow(2, target - 1);
    }
}
```
# 变态跳台阶Solution_10_4.java

```java
package A剑指Offer;

import java.util.Arrays;

/**
 * @ClassName Solution_10_4
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_10_4 {
    //dp
    public int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }
    //公式
    public int JumpFloorII2(int target) {
        return (int) Math.pow(2, target - 1);
    }
}
```

# 旋转数组的最小数字Solution_11.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_11
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_11 {
    //有bug
    public int minNumberInRotateArray(int [] array) {
        int i = 0;
        int j = array.length-1;
        while(i<j){
            int mid = i+(j-i)/2;
            if(array[mid]>array[j]){
                i = mid+1;
            }
            else{
                j = mid ;
            }
        }
        return array[i];
    }
    //正解
    public int minNumberInRotateArray2(int[] array) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] < array[j]) {
                return array[i];
            }
            int mid = (i + j) >> 1;
            if (array[mid] > array[i]) {
                i = mid + 1;
            } else if (array[mid] < array[j]) {
                j = mid; // 如果是mid-1，则可能会错过最小值，因为找的就是最小值
            } else i++;  // 巧妙避免了offer书上说的坑点（1 0 1 1 1）
        }
        return array[i];
    }
}
```
# 矩阵中的路径Solution_12.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_12
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_12 {

    //暴力搜索 dfs
    boolean [][]meno ;
    int [][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    int rows;
    int cols;
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        this.rows = rows;
        this.cols = cols;
        meno = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (core(matrix,i,j,str,0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean core(char[] matrix, int i, int j, char[] str, int index) {
        if (index>=str.length){
            return true;
        }
        if (i<0||i>=rows||j<0||j>=cols||meno[i][j]){
            return false;
        }
        if (matrix[to(i,j)]==str[index]){
            meno[i][j]=true;
        }
        else {
            return false;
        }
        for (int k = 0; k < dir.length; k++) {
            if (core(matrix,i+dir[k][0],j+dir[k][1],str,index+1))
                return true;
        }
        meno[i][j]=false;
        return false;
    }
    private int to(int i,int j){
        return i*cols+j;
    }
}
```
# 机器人的运动范围Solution_13.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_13
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_13 {
    //dfs 暴力搜索
    boolean [][] meno ;
    int rows ;
    int cols ;
    int res = 0;
    int k ;
    int [][]dir = {{1,0},{0,1},{0,-1},{-1,0}};
    public int movingCount(int threshold, int rows, int cols)
    {
        meno = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.k = threshold;
        core(0,0);
        return res;
    }

    private void core(int row,int col){
        if(row<0||row>=rows||col<0||col>=cols||meno[row][col]||trans(row)+trans(col)>k){
            return;
        }
        meno[row][col]= true;
        res++;
        for(int i = 0 ;i<dir.length;i++){
            core(row+dir[i][0],col+dir[i][1]);
        }
    }
    private int trans(int i){
        int res = 0;
        while(i!=0){
            int temp = i%10;
            res += temp;
            i = i/10;
        }
        return res;
    }
}
```
# 剪绳子Solution_14.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_14
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_14 {
    //dp
    public int cutRope2(int target) {
        if(target == 2){
            return 1;
        }
        else if(target == 3){
            return 2;
        }
        else if(target == 4){
            return 4;
        }
        int []dp = new int[target+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for(int i = 5;i<target+1;i++){
            for(int j = 1 ;j < i;j++){
                dp[i] = Math.max(dp[i],dp[i-j]*dp[j]);
            }
        }
        return dp[target];
    }
    //贪心
    public int cutRope(int target) {
        if(target == 2){
            return 1;
        }
        else if(target == 3){
            return 2;
        }
        else if(target == 4){
            return 4;
        }
        int res = 1;
        while(target>4){
            res = res*3;
            target -= 3;
        }
        return res*target;
    }
}
```
# 二进制中 1 的个数Solution_15.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_15
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_15 {
    //位运算
    public int NumberOf1(int n) {
        return Integer.bitCount(n);
    }
/***
 *     n&(n-1)
 *     该位运算去除 n 的位级表示中最低的那一位。
 *
 *     n       : 10110100
 *     n-1     : 10110011
 *     n&(n-1) : 10110000
 *     时间复杂度：O(M)，其中 M 表示 1 的个数
 */
    public int NumberOf12(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }
}
```
# 数值的整数次方Solution_16.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_16
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_16 {
    public double Power(double base, int exponent) {
        double res = 1;
        if(exponent>0){
            for(int i = 0;i<exponent;i++){
                res*=base;
            }
            return res;
        }
        for(int i = 0;i<-exponent;i++){
            res*=1/base;
        }
        return res;
    }
    public double Power2(double base, int exponent) {
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }
        boolean isNegative = false;
        if(exponent < 0){
            exponent = -exponent;
            isNegative = true;
        }
        double pow = Power2(base*base,exponent/2);
        if(exponent%2!=0){
            pow = pow*base;
        }
        return isNegative?1/pow:pow;
    }
}
```
# 打印从 1 到最大的 n 位数Solution_17.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_17
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_17 {
    public void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }
    private void print1ToMaxOfNDigits(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }
    private void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0')
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }
}
```
# 在 O(1) 时间内删除链表节点Solution_18_1.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_18_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_18_1 {
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null)
            return null;
        if (tobeDelete.next != null) {
            // 要删除的节点不是尾节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            if (head == tobeDelete)
                // 只有一个节点
                head = null;
            else {
                ListNode cur = head;
                while (cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }
}
```
# 删除链表中重复的结点Solution_18_2.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_18_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_18_2 {
    //迭代
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode last = first;
        ListNode temp = pHead;
        while(temp!=null&&temp.next!=null){
            int key = temp.val;
            if(key == temp.next.val){
                while(temp!=null&& key == temp.val){
                    temp = temp.next;
                }
                last.next = temp;
            }
            else{
                last = temp;
                temp = temp.next;
            }
        }
        return first.next;
    }
    //递归 更清晰
    public ListNode deleteDuplication2(ListNode pHead)
    {
        if(pHead == null||pHead.next ==null){
            return pHead;
        }
        ListNode next = pHead.next;
        if(pHead.val == next.val){
            int temp = pHead.val;
            while(pHead!=null&&temp == pHead.val){
                pHead = pHead.next;
            }
            return deleteDuplication(pHead);
        }
        else{
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }
}
```
# 正则表达式匹配Solution_19.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_19
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_19 {
    //顺序迭代  过不了
    public boolean match(char[] str, char[] pattern)
    {
        int length1 = str.length;
        int length2 = pattern.length;
        int index1 = 0;
        int index2 = 0;
        while(index1<length1&&index2<length2){
            if(pattern[index2]=='.'||pattern[index2]==str[index1]){
                index1++;
                index2++;
                continue;
            }
            if(pattern[index2]=='*'&&index2>0&&((pattern[index2-1]==str[index1])||(pattern[index2-1]=='.'))){
                index1++;
            }
            else if((pattern[index2+1]==str[index1])||(pattern[index2+1]=='.')){
                index2+=2;
                index1++;
            }
            else{
                return false;
            }
        }
        while(index2<length2){
            if(pattern[index2]!='*'){
                if(index2+1<length2){
                    if(pattern[index2+1]!='*'){
                        return false;
                    }
                    else{
                        index2+=2;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                index2++;
            }
        }
        if(index1==length1&&index2==length2){
            return true;
        }
        return false;
    }

    // 递归
    public boolean match2(char[] str, char[] pattern)
    {
        return helper(str,0,pattern,0);
    }
    public boolean helper(char []str,int index1,char[]pattern,int index2){
        if(index1==str.length&&index2==pattern.length){
            return true;
        }
        if(index2==pattern.length){
            return false;
        }
        if(index2+1<pattern.length&&pattern[index2+1]=='*'){
            if(index1<str.length && (pattern[index2]==str[index1]||pattern[index2]=='.')){//这里得判断index1越界  因为str可能已经匹配完了
                return helper(str,index1+1,pattern,index2)||helper(str,index1+1,pattern,index2+2)||helper(str,index1,pattern,index2+2);
            }
            else{
                return helper(str,index1,pattern,index2+2);
            }
        }
        else{
            //这里得判断index1越界  因为str可能已经匹配完了
            if(index1<str.length && (pattern[index2]=='.'||pattern[index2]==str[index1])){
                return helper(str,index1+1,pattern,index2+1);
            }
            else{
                return false;
            }
        }
    }
    public boolean matchStr(char[] str, int i, char[] pattern, int j) {
        // 边界
        if (i == str.length && j == pattern.length) { // 字符串和模式串都为空
            return true;
        } else if (j == pattern.length) { // 模式串为空
            return false;
        }
        boolean next = (j + 1 < pattern.length && pattern[j + 1] == '*'); // 模式串下一个字符是'*'
        if (next) {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) { // 要保证i<str.length，否则越界
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }
}
```
# 调整数组顺序使奇数位于偶数前面Solution_21.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_21
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_21 {
    //类似于插入排序的思想
    public void reOrderArray(int [] array) {
        int pro = 0;//记录第一个偶数
        int cur = 0;
        while(pro<array.length&&cur<array.length){
            while(cur<array.length&&array[cur]%2==1){
                cur++;
            }
            if (cur==array.length){
                return;
            }
            pro = cur;
            while(cur<array.length&&array[cur]%2==0){
                cur++;
            }
            if (cur==array.length){
                return;
            }
            int temp = array[cur];
            for(int i = cur;i>pro;i--){
                array[i]=array[i-1];
            }
            array[pro] = temp;
            cur = pro;
        }
    }
    //冒泡的思想
    public void reOrderArray2(int[] nums) {
        int N = nums.length;
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (isEven(nums[j]) && !isEven(nums[j + 1])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private boolean isEven(int x) {
        return x % 2 == 0;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
# 链表中倒数第 K 个结点Solution_22.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_22
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_22 {
    //双指针  快慢指针
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode pro = head;
        int i =0;
        while(pro != null&&i<k){
            pro = pro.next;
            i++;
        }
        if(i==k){
            while(pro!=null){
                pro = pro.next;
                head = head.next;
            }
            return head;
        }
        return null;
    }
}
```
# 链表中环的入口结点Solution_23.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_23
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_23 {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        ListNode pro = pHead;
        ListNode end = pHead;
        if(end!=null&&end.next!=null){//得先走一步,起始位置相同
            pro = pro.next;
            end = end.next.next;
        }
        while(end!=null&&end.next!=null&&pro!=end){
            pro = pro.next;
            end = end.next.next;
        }
        if(end == null||end.next==null){
            return null;
        }
        //找到得位置 到环入口检点的距离于头节点到入口节点的距离相同。可以根据距离公式推导出结果
        end = pHead;
        while(end!=pro){
            pro = pro.next;
            end = end.next;
        }
        return pro;
    }


        //方法二:  先求出相遇节点,以及环的大小 k ,,,可以利用 倒数第k个节点的求法.
        //找到一快一满指针相遇处的节点，相遇的节点一定是在环中
        public static ListNode meetingNode(ListNode head) {
            if(head==null)
                return null;

            ListNode slow = head.next;
            if(slow==null)
                return null;

            ListNode fast = slow.next;
            while (slow != null && fast != null) {
                if(slow==fast){
                    return fast;
                }
                slow=slow.next;
                fast=fast.next;

                if(fast!=slow){
                    fast=fast.next;
                }
            }
            return null;
        }
        public ListNode EntryNodeOfLoop2(ListNode pHead) {
            ListNode meetingNode=meetingNode(pHead);
            if(meetingNode==null)
                return null;
//      得到环中的节点个数
            int nodesInLoop=1;
            ListNode p1=meetingNode;
            while(p1.next!=meetingNode){
                p1=p1.next;
                ++nodesInLoop;
            }
//      移动p1
            p1=pHead;
            for(int i=0;i<nodesInLoop;i++){
                p1=p1.next;
            }
//      移动p1，p2
            ListNode p2=pHead;
            while(p1!=p2){
                p1=p1.next;
                p2=p2.next;
            }
            return p1;
        }
}
```
# 反转链表Solution_24.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_24
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_24 {
    //递归
    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        ListNode pro = head.next;
        ListNode temp = ReverseList(head.next);
        pro.next = head;
        head.next =null ;
        return temp;
    }
    //三指针迭代
    public ListNode ReverseList2(ListNode head) {
        ListNode pro = null;
        ListNode cur = head;
        if(cur==null||cur.next==null){
            return cur;
        }
        ListNode next = head.next;

        while(next!=null){
            cur.next = pro;
            pro = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pro;
        return cur;
    }
}
```
# 合并两个排序的链表Solution_25.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_25
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_25 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode res ;
        if(list1.val<list2.val){
            res = list1;
            list1 = list1.next;
        }
        else{
            res = list2;
            list2 = list2.next;
        }
        ListNode cur = res;
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }
            else{
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if(list1!=null){
            cur.next = list1;
        }
        else{
            cur.next = list2;
        }
        return res;
    }
    //递归写法 比较优雅
    public ListNode Merge2(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode res ;
        if(list1.val<list2.val){
            res = list1;
            res.next = Merge(list1.next,list2);
        }
        else{
            res = list2;
            res.next = Merge(list1,list2.next);
        }
        return res;
    }
}
```
# 树的子结构Solution_26.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_26
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Solution_26 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null){
            return false;
        }
        return helper(root1,root2);
    }
    //类似先序遍历
    private boolean helper(TreeNode root1,TreeNode root2){
        if(root2==null){
            return true;
        }
        if(root1 == null){
            return false;
        }
        //不能判断 root1 == root2   因为是两个不同的树,   数值也有可能重复  所以得比较值
        if(root1.val == root2.val){
            return (helper(root1.left,root2.left)&&helper(root1.right,root2.right))||helper(root1.left,root2)||helper(root1.right,root2);
        }
        else{
            return helper(root1.left,root2)||helper(root1.right,root2);
        }
    }
}
```
# 二叉树的镜像Solution_27.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_27
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_27 {
    public void Mirror(TreeNode root) {
        if(root == null){
            return ;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }
}
```
# 对称的二叉树Solution_28.java
```java
package A剑指Offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName Solution_28
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_28 {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null){
            return true;
        }
        return helper(pRoot.left,pRoot.right);
    }
    //先序  与  反先序  遍历  同时进行
    boolean helper(TreeNode rootleft,TreeNode rootright){
        if(rootleft == null&&rootright==null){
            return true;
        }
        else if(rootleft == null||rootright==null){
            return false;
        }
        if(rootleft.val != rootright.val){
            return false;
        }
        return helper(rootleft.left,rootright.right)&&helper(rootleft.right,rootright.left);
    }
    //===================非递归算法，利用DFS和BFS=============================//
    /*
    * DFS使用stack来保存成对的节点
    * 1.出栈的时候也是成对成对的 ，
    1.若都为空，继续；
    2.一个为空，返回false;
    3.不为空，比较当前值，值不等，返回false；
    * 2.确定入栈顺序，每次入栈都是成对成对的，如left.left， right.right ;left.rigth,right.left
    */
    boolean isSymmetricalDFS(TreeNode pRoot)
    {
        if(pRoot == null) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(pRoot.left);
        s.push(pRoot.right);
        while(!s.empty()) {
            TreeNode right = s.pop();//成对取出
            TreeNode left = s.pop();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            //成对插入
            s.push(left.left);
            s.push(right.right);
            s.push(left.right);
            s.push(right.left);
        }
        return true;
    }
    /*
    * BFS使用Queue来保存成对的节点，代码和上面极其相似
    * 1.出队的时候也是成对成对的
    1.若都为空，继续；
    2.一个为空，返回false;
    3.不为空，比较当前值，值不等，返回false；
    * 2.确定入队顺序，每次入队都是成对成对的，如left.left， right.right ;left.rigth,right.left
    */
    boolean isSymmetricalBFS(TreeNode pRoot)
    {
        if(pRoot == null) return true;
        Queue<TreeNode> s = new LinkedList<>();
        s.offer(pRoot.left);
        s.offer(pRoot.right);
        while(!s.isEmpty()) {
            TreeNode left= s.poll();//成对取出
            TreeNode right= s.poll();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            //成对插入
            s.offer(left.left);
            s.offer(right.right);
            s.offer(left.right);
            s.offer(right.left);
        }
        return true;
    }
}
```
# 顺时针打印矩阵Solution_29.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_29
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_29 {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> res = new ArrayList();
        int rowup = 0;
        int rowdown = matrix.length-1;
        int colup = 0;
        if(rowdown<0){
            return res;
        }
        int coldown = matrix[0].length-1;
        while(rowup<=rowdown&&colup<=coldown){
            int i = rowup;
            int j = colup;
            while(rowup<=rowdown&&colup<=coldown&&j<=coldown){
                res.add(matrix[i][j++]);
            }
            rowup++;
            i = rowup;
            j = coldown;
            while(rowup<=rowdown&&colup<=coldown&&i<=rowdown){
                res.add(matrix[i++][j]);
            }
            coldown--;
            i = rowdown;
            j = coldown;
            while(rowup<=rowdown&&colup<=coldown&&j>=colup){
                res.add(matrix[i][j--]);
            }
            rowdown--;
            i = rowdown;
            j = colup;
            while(rowup<=rowdown&&colup<=coldown&&i>=rowup){
                res.add(matrix[i--][j]);
            }
            colup++;
        }
        return res;
    }

/*  思路一样,知识在判断越界的地方不同.
    刷 LeetCode 看到的大神题解，感觉容易理解且好写
    简单来说，就是不断地收缩矩阵的边界
    定义四个变量代表范围，up、down、left、right

    向右走存入整行的值，当存入后，该行再也不会被遍历，代表上边界的 up 加一，同时判断是否和代表下边界的 down 交错
    向下走存入整列的值，当存入后，该列再也不会被遍历，代表右边界的 right 减一，同时判断是否和代表左边界的 left 交错
    向左走存入整行的值，当存入后，该行再也不会被遍历，代表下边界的 down 减一，同时判断是否和代表上边界的 up 交错
    向上走存入整列的值，当存入后，该列再也不会被遍历，代表左边界的 left 加一，同时判断是否和代表右边界的 right 交错*/
    public ArrayList<Integer> printMatrix2(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(true){
            // 最上面一行
            for(int col=left;col<=right;col++){
                list.add(matrix[up][col]);
            }
            // 向下逼近
            up++;
            // 判断是否越界
            if(up > down){
                break;
            }
            // 最右边一行
            for(int row=up;row<=down;row++){
                list.add(matrix[row][right]);
            }
            // 向左逼近
            right--;
            // 判断是否越界
            if(left > right){
                break;
            }
            // 最下面一行
            for(int col=right;col>=left;col--){
                list.add(matrix[down][col]);
            }
            // 向上逼近
            down--;
            // 判断是否越界
            if(up > down){
                break;
            }
            // 最左边一行
            for(int row=down;row>=up;row--){
                list.add(matrix[row][left]);
            }
            // 向右逼近
            left++;
            // 判断是否越界
            if(left > right){
                break;
            }
        }
        return list;
    }

/*
    很扎心，看了一会讨论和题解，没看到一个用标记数组写的...

    随便画图可知，走的方向dir有规律：向右->向下->向左->向上->向右->向下->向左->... 是一个圆圈
    判断哪一步是否可以走，首先，它没越界；其次，它没被走过（vis标记数组，为false代表没走过）*/
    public class Solution {

        // 走的方向：向右、向下、向左、向上
        private final int[] dx = {0, 1, 0, -1};
        private final int[] dy = {1, 0, -1, 0};

        public ArrayList<Integer> printMatrix(int[][] matrix) {
            int n = matrix.length, m = matrix[0].length;
            boolean[][] vis = new boolean[n][m];
            ArrayList<Integer> list = new ArrayList<>();

            int x = 0, y = 0, dir = 0;
            while (x >= 0 && x < n && y >= 0 && y < m && !vis[x][y]) {
                list.add(matrix[x][y]);
                vis[x][y] = true;

                // 试着继续向dir的方向走
                while (x + dx[dir] >= 0 && x + dx[dir] < n && y + dy[dir] >= 0 && y + dy[dir] < m && !vis[x + dx[dir]][y + dy[dir]]) {
                    x += dx[dir];
                    y += dy[dir];
                    list.add(matrix[x][y]);
                    vis[x][y] = true;
                }
                // 走不动了换方向
                dir = (dir + 1) % 4;
                x += dx[dir];
                y += dy[dir];
            }
            return list;
        }
    }
}
```
# 包含 min 函数的栈Solution_30.java
```java
package A剑指Offer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName Solution_30
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_30 {
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> min = new LinkedList<>();
    public void push(int node) {
        if(stack.isEmpty()){
            stack.push(node);
            min.push(node);
        }
        else{
            stack.push(node);
            if(node < min.peek()){
                min.push(node);
            }
            else {
                min.push(min.peek());
            }
        }
    }
    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }
}
class Solution {
    //用一个栈实现目的  和 双栈类似  只不过将最小值历史值记录在了原来元素的后一个,用于最小值出栈恢复小值.

    //需要这样写来初始化stack，不然会报空指针push的时候
    private static Stack<Integer> stack = new Stack<Integer>();
    private static Integer minElement = Integer.MAX_VALUE;
    public void push(int node) {
        if(stack.empty()){
            minElement = node;
            stack.push(node);
        }else{
            if(node <= minElement){
                stack.push(minElement);//在push更小的值时需要保留在此值之前的最小值
                minElement = node;
            }
            stack.push(node);
        }
    }
    public void pop() {
        //增加最后一个元素以及栈为空时候的检测
        if(stack.size() == 0)return;
        if( minElement == stack.peek()){//最小值与出栈值相同,所以进行上一次的最小值恢复
            if(stack.size() >1){
                stack.pop();
                minElement = stack.peek();
            }else{
                minElement = Integer.MAX_VALUE;
            }

        }
        stack.pop();
    }
    public int top() {
        return stack.peek();
    }

    public int min() {
        return minElement;
    }
}

```
# 栈的压入、弹出序列Solution_31.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_31
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_31 {
    //用一个栈来模拟  出入栈的过程
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        ArrayList<Integer> stack = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<pushA.length&&j<popA.length){
            stack.add(pushA[i]);
            while(!stack.isEmpty()&&stack.get(stack.size()-1)==popA[j]){
                stack.remove(stack.size()-1);
                j++;
            }
            i++;
        }
        return stack.isEmpty();
    }
}
```
# 从上往下打印二叉树Solution_32_1.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_32_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_32_1 {
    // 队列  层次 遍历
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        ArrayList<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode temp = queue.remove(0);
            if(temp == null){
                continue;
            }
            res.add(temp.val);
            queue.add(temp.left);
            queue.add(temp.right);
        }
        return res;
    }
}
```
# 把二叉树打印成多行Solution_32_2.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_32_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_32_2 {
    // 队列 层次遍历
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(pRoot);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();//记录当前层的数目
            ArrayList<Integer> row = new ArrayList<>();
            while(size-->0){
                TreeNode temp = queue.remove(0);
                if(temp == null){
                    continue;
                }
                row.add(temp.val);
                queue.add(temp.left);
                queue.add(temp.right);
            }
            res.add(row);
        }
        res.remove(res.size()-1);
        return res;
    }
}
```
# 按之字形顺序打印二叉树Solution_32_3.java
```java
package A剑指Offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName Solution_32_3
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_32_3 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(pRoot);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();//记录当前层的数目
            ArrayList<Integer> row = new ArrayList<>();
            while(size-->0){
                TreeNode temp = queue.remove(0);
                if(temp == null){
                    continue;
                }
                if (flag){
                    row.add(temp.val);
                }
                else {
                    row.add(0,temp.val);//逆序
                }
                queue.add(temp.left);
                queue.add(temp.right);
            }
            res.add(row);
            flag = !flag;
        }
        res.remove(res.size()-1);
        return res;
    }
    //控制逆序的地方不一样
    public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(pRoot);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();//记录当前层的数目
            ArrayList<Integer> row = new ArrayList<>();
            while(size-->0){
                TreeNode temp = queue.remove(0);
                if(temp == null){
                    continue;
                }
                row.add(temp.val);
                queue.add(temp.left);
                queue.add(temp.right);
            }
            if (flag){
                res.add(row);
            }
            else {
                Collections.reverse(row);//逆序
                res.add(row);
            }
            flag = !flag;
        }
        res.remove(res.size()-1);
        return res;
    }
}
```
# 二叉搜索树的后序遍历序列Solution_33.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_33
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_33 {
    //  时间复杂度 O(nlog(n))
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0){
            return false;
        }
        return helper(sequence,0,sequence.length-1);
    }
    private boolean helper(int [] nums,int begin,int end){
        if(begin >= end){
            return true;
        }
        int mid = nums [end];
        int i = end - 1;
        while(i >= begin){
            if(nums[i]>mid){
                i--;
            }
            else{
                break;
            }
        }
        int midindex = i;
        while(i>=begin){
            if(nums[i]<mid){
                i--;
            }
            else{
                return false;
            }
        }
        return helper(nums,begin,midindex)&&helper(nums,midindex+1,end-1);
    }
}
```
# 二叉树中和为某一值的路径Solution_34.java
```java
package A剑指Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @ClassName Solution_34
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_34 {
    ArrayList<ArrayList<Integer>>res = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();
    //回溯法
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        backtrack(root,target);
        Collections.sort(res, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size()-o1.size();
            }
        });
        return res;
    }
    private void backtrack(TreeNode root,int target){
        if (root==null){
            return;
        }
        else if (root.left == null&&root.right == null){
            if (target == root.val){
                temp.add(root.val);
                res.add(new ArrayList(temp));
                temp.remove(temp.size()-1);
            }
            return;
        }
        temp.add(root.val);
        backtrack(root.left,target - root.val);
//        temp.remove(temp.size()-1);// 这两行不要要不要都可以
//        temp.add(root.val);
        backtrack(root.right,target - root.val);
        temp.remove(temp.size()-1);
    }

    //非递归版本
}
```
# 复杂链表的复制Solution_35.java
```java
package A剑指Offer;

import java.util.HashMap;

/**
 * @ClassName Solution_35
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
public class Solution_35 {
    //原地复制
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return null;
        }
        RandomListNode cur = pHead;
        while(cur!=null){
            RandomListNode temp = new RandomListNode(cur.label);
            temp.next = cur.next;
            temp.random = cur.random;
            cur.next = temp;
            cur = cur.next.next;
        }
        cur = pHead;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        RandomListNode original = pHead;
        RandomListNode res = pHead.next;
        cur = pHead.next;
        while(original!=null&&cur!=null){
            if(original.next!=null){
                original.next = original.next.next;
            }
            original = original.next;
            if(cur.next!=null){
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return res;
    }

    //hashmap
    public RandomListNode Clone2(RandomListNode pHead)
    {
        HashMap<RandomListNode,RandomListNode> map = new HashMap<>();
        RandomListNode temp = pHead;
        while(temp!=null){
            map.put(temp,new RandomListNode(temp.label));
            temp = temp.next;
        }
        temp = pHead;
        while(temp!=null){
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(pHead);
    }
}
```
# 二叉搜索树与双向链表Solution_36.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_36
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_36 {

    //分治而治之
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        TreeNode end = helper1(pRootOfTree.left);
        if (end != null){
            pRootOfTree.left = end;
            end.right = pRootOfTree;
        }
        TreeNode begin = helper2(pRootOfTree.right);
        if (begin != null){
            pRootOfTree.right = begin;
            begin.left = pRootOfTree;
        }
        while (pRootOfTree.left != null){
            pRootOfTree = pRootOfTree.left;
        }
        return pRootOfTree;
    }
    public TreeNode helper1(TreeNode root){
        if (root == null){
            return root;
        }
        TreeNode end = helper1(root.left);
        if (end != null){
            end.right = root;
            root.left = end;
        }
        TreeNode begin = helper2(root.right);
        if (begin!=null){
            root.right = begin;
            begin.left = root;
        }
        while (root.right != null){
            root = root.right;
        }
        return root;
    }
    private TreeNode helper2(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode end = helper1(root.left);
        if (end != null){
            root.left = end;
            end.right = root;
        }
        TreeNode begin = helper2(root.right);
        if (begin != null){
            root.right = begin;
            begin.left = root;
        }
        while (root.left != null){
            root = root.left;
        }
        return root;
    }


    TreeNode pre  = null;
    TreeNode head = null;
    private void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        if (pre!=null){
            pre.right = root;
            root.left = pre;
        }
        pre = root;
        if(head == null){
            head = pre;
        }
        inOrder(root.right);
    }
}
```
# 序列化二叉树Solution_37.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_37
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_37 {
    String Serialize(TreeNode root) {
        StringBuilder pro = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        pro(root,pro);
        mid(root,mid);
        return pro.toString()+mid.toString();
    }
    void pro (TreeNode root,StringBuilder pro){
        if(root == null){
            return ;
        }
        pro.append((char) root.val);
        pro(root.left,pro);
        pro(root.right,pro);
    }
    void mid (TreeNode root,StringBuilder mid){
        if(root == null){
//            mid.append('#');
            return ;
        }
        mid(root.left,mid);
        mid.append((char)root.val);
//        mid.append('!');
        mid(root.right,mid);
    }
    TreeNode Deserialize(String str) {
        int length = str.length()/2;
        String pro = str.substring(0,length);
        String mid = str.substring(length,str.length());
        char[] prochar = pro.toCharArray();
        char[] midchar = mid.toCharArray();
        return helper(prochar,0,length-1,midchar,0,length-1);
    }

    private TreeNode helper(char[] pro, int begin, int end, char[] mid, int left, int right) {
        if (begin<0||begin>end||left<0||left>right){
            return null;
        }
        if (begin == end||left == right){
            return new TreeNode(pro[ begin]);
        }
        TreeNode root = new TreeNode(pro[begin]);
        int index = -1;
        for (int i = left; i <=right ; i++) {
            if (mid[i] == pro[begin]){
                index = i;
                break;
            }
        }
        root.left = helper(pro,begin+1,begin + index - left  ,mid,left,index-1);
        root.right=helper(pro,begin+index - left+1,end,mid,index+1,right);
        return root;
    }

    public static void main(String[] args) {
        StringBuilder temp = new StringBuilder();

        temp.append((char) 12);
        System.out.println(temp);


    }






}
```
# 字符串的排列Solution_38.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_38
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_38 {

    //回溯 解 排列组合
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if(str == null||str.length() == 0){
            return res;
        }
        StringBuilder temp = new StringBuilder();
        meno = new boolean[str.length()];
        backtrack(temp,res,str);
        return res;
    }
    boolean meno [];
    private void backtrack(StringBuilder temp,ArrayList<String> res,String str){
        if(temp.length()==str.length()){
            res.add(temp.toString());
        }
        for(int i = 0;i<str.length();i++){
            if (i>0&&str.charAt(i)==str.charAt(i-1)&&!meno[i-1]){ // 去重
                continue;
            }
            if(meno[i]){
                continue;
            }
            meno[i] = true;
            temp.append(str.charAt(i));
            backtrack(temp,res,str);
            meno[i] = false;
            temp.deleteCharAt(temp.length()-1);
        }
    }
}
```
# 数组中出现次数超过一半的数字Solution_39.java
```java
package A剑指Offer;

import java.util.HashMap;

/**
 * @ClassName Solution_39
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_39 {
    //  空间消耗大  可以优化
    public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int m = array.length/2;
        for(int i = 0 ;i<array.length;i++){
            map.put(array[i],map.getOrDefault(array[i],0)+1);
            if(map.get(array[i])>m){
                return array[i];
            }
        }
        return 0;
    }

    //多数投票问题 摩尔投票算法
    //先提条件是  出现最多的数的次数要大于 nums.length/2
    //否则得出的结果不一定是出现次数最多的元素
    //https://blog.csdn.net/isunn/article/details/50735232
    //    Boyer-Moore Majority Vote Algorithm的更一般性问题
    //    1) 问题描述
    //    基本问题：对于一个给定数组A[0:n-1],找出出现次数大于⌊n/2⌋⌊n/2⌋ 的元素，称为Majority element.
    //    一般问题：对于一个给定数组A[0:n-1],找出出现次数大于⌊n/k⌋(k≥2)⌊n/k⌋(k≥2)的所有元素.
    //
    //            LeetCode问题链接：
    //    Majority Element： https://leetcode.com/problems/majority-element/
    //    Majority Element II: https://leetcode.com/problems/majority-element-ii/
    public static  int MoreThanHalfNum_Solution2(int []array ){
        int m = array[0];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if ( count == 0){
                m = array[i];
                count++;
            }
            else if (m == array[i]){
                count++;
            }
            else {
                count--;
            }
        }
        System.out.println(m);
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==m){
                count++;
            }
        }
        System.out.println(count);
        return count>(array.length/2)? m :0;
    }

    public static void main(String[] args) {
        int nums[]= {1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalfNum_Solution2(nums));;
    }
}
```
# 最小的 K 个数Solution_40.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_40
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_40 {
    //1.排序
    //2.维护最小堆
    //3.partition 算法
    int k;
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        this.k = k-1;
//        int helper = helper(input, 0, input.length - 1);
//        while (helper!=k){
//            if (helper>k){
//                helper = helper(input,0,helper);
//            }
//            else {
//                helper = helper(input,helper,input.length-1);
//            }
//        }
        ArrayList<Integer> res = new ArrayList<>();
        if (k>input.length){
            return res;
        }
        qSort(input,0,input.length-1);
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }
    public void qSort(int []input,int begin,int end){
        if (begin >= end){
            return;
        }
        int helper = helper(input, begin, end);
        if (helper > k){
            qSort(input,begin,helper-1);
        }
        else if (helper<k){
            qSort(input,helper+1,end);
        }
    }
    public int helper(int []input,int left,int right){
        int mid = input[left];
        while (left<right){
            while (left<right&&input[right]>=mid){
                right--;
            }
            input[left] = input[right];
            while (left<right&&input[left]<=mid){
                left++;
            }
            input[right] = input[left];
        }
        input[left] = mid;
        return left;
    }
}
```
# 数据流中的中位数Solution_41_1.java
```java
package A剑指Offer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @ClassName Solution_41_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_41_1 {
    ArrayList<Integer> temp = new ArrayList<>();
    public void Insert(Integer num) {
        if (temp.isEmpty()){
            temp.add(num);
        }
        else {
            for (int i = 0; i < temp.size(); i++) {
                if (num<temp.get(i)){
                    temp.add(i,num);
                    return;
                }
            }
            temp.add(num);
        }
    }
    public Double GetMedian() {
        if (temp.size()%2==1){
            return (double) temp.get(temp.size()/2);
        }
        else {
            return ((double) temp.get(temp.size()/2-1)+ (double) temp.get(temp.size()/2))/2;
        }
    }

    /* 大顶堆，存储左半边元素 */
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    /* 当前数据流读入的元素个数 */
    private int N = 0;

    public void Insert2(Integer val) {
        /* 插入要保证两个堆存于平衡状态 */
        if (N % 2 == 0) {
            /* N 为偶数的情况下插入到右半边。
             * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
             * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
            left.add(val);
            right.add(left.poll());
        } else {
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian2() {
        if (N % 2 == 0)
            return (left.peek() + right.peek()) / 2.0;
        else
            return (double) right.peek();
    }
}
```
# 字符流中第一个不重复的字符Solution_41_2.java
```java
package A剑指Offer;

import java.util.LinkedHashMap;

/**
 * @ClassName Solution_40_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_41_2 {
    //只能做小写字母
    Flag [] meno = new Flag[127];
    int sum = 0;
    public void Insert(char ch)
    {
        if(meno[ch - 'a']==null ){
            meno[ch - 'a'] = new Flag(1,sum++);
        }
        else{
            meno[ch - 'a'].count++;
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char res = '#';
        int in = Integer.MAX_VALUE;
        for(int i = 0;i<26;i++){
            if(meno[i]!=null&&meno[i].count==1&&meno[i].index<in){
                in = meno[i].index;
                res = (char)('a'+i);
            }
        }
        return res;
    }


    //记录单个出现的字母，按顺序记录  第一个为最先出现的。
    // 大于一次出现的直接删除
    LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
    public void Insert2(char ch)
    {
        if (map.containsKey(ch)){
            map.remove(ch);
        }
        else {
            map.put(ch,sum++);
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce2()
    {
        if (map.isEmpty()){
            return '#';
        }
        else {
            for (char c: map.keySet()) {
                return c;
            }
        }
        return '#';
    }
}
class Flag{
    int count;
    int index;
    Flag(int a,int n){
        count = a;
        index = n;
    }
}
```
# 连续子数组的最大和Solution_42.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_42
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_42 {
    ///还是老思路  效率差
    public int FindGreatestSumOfSubArray(int[] array) {
        int []dp = new int[array.length];
        dp[0] = array[0];
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = dp[i-1]+array[i];
            res = Math.max(res,array[i]);
            res = Math.max(res,dp[i]);
            for (int j = 0; j < i; j++) {
                res =  Math.max(res,dp[i]-dp[j]);
            }
        }
        return res;
    }

    //动态规划
    public int FindGreatestSumOfSubArray3(int[] array) {
        int len = array.length;
        int[] dp = new int[len];
        int max = array[0];
        dp[0] = array[0];
        for(int i=1; i < len; i++){
            int newMax = dp[i-1] + array[i];
            if(newMax > array[i])
                dp[i] = newMax;
            else
                dp[i] = array[i];
            if(dp[i] > max)
                max = dp[i];
        }
        return max;

    }
    //dp
    public int FindGreatestSumOfSubArray2(int[] array) {
        int sum = array[0];//局部和最大
        int res = array[0];//全局 最大
        for (int i = 1; i <array.length ; i++) {
            sum = Math.max(sum+array[i],array[i]);
            res = Math.max(sum,res);
        }
        return res;
    }
}
```
# 把数组排成最小的数Solution_45.java
```java
package A剑指Offer;

import java.util.Arrays;

/**
 * @ClassName Solution_45
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_45 {
    //方法1  排序组合 求最值
    public String PrintMinNumber(int [] numbers) {
        return null;
    }
    //方法2 数字补齐空余位补0   排序
    public String PrintMinNumber2(int [] numbers) {
        String [] temp = new String[numbers.length];
        int max = 0;
        for(int i= 0;i<numbers.length;i++){
            temp[i] = numbers[i]+"";
            if (temp[i].length()>max){
                max=temp[i].length();
            }
        }
        for(int i= 0;i<temp.length;i++){
            for (int j = temp[i].length(); j <max ; j++) {
                temp[i]+='0';
            }
        }
        Arrays.sort(temp,((o1, o2) -> (o2).compareTo(o1)));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            s.append(temp[i]);
        }
        return s.toString();
    }
    //方法3 按要求  拼接 排序
    public String PrintMinNumber3(int [] numbers) {
        String [] temp = new String[numbers.length];
        for(int i= 0;i<numbers.length;i++){
            temp[i] = numbers[i]+"";
        }
//        Arrays.sort(temp, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return (o1+o2).compareTo(o2+o1);
//            }
//        });
        Arrays.sort(temp,((o1, o2) -> (o1+o2).compareTo(o2+o1)));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            s.append(temp[i]);
        }
        return s.toString();
    }
}
```
# 把数字翻译成字符串Solution_46.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_46
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_46 {
    //dp
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0)
                dp[i] += dp[i - 1];
            if (s.charAt(i - 2) == '0')
                continue;
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }
    //递归
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return digui(s, 0);
    }
    //递归的套路，加一个index控制递归的层次
    private int digui(String s, int start) {
        //递归的第一步，应该是加终止条件，避免死循环。
        if (s.length() == start) {
            return 1;
        }
        //以0位开始的数是不存在的
        if (s.charAt(start) == '0') {
            return 0;
        }
        //递归的递推式应该是如果index的后两位小于等于26，
        // digui(s, start) = digui(s, start+1)+digui(s, start+2)
        // 否则digui(s, start) = digui(s, start+1)
        int ans1 = digui(s, start + 1);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = (s.charAt(start + 1) - '0');
            if (ten + one <= 26) {
                ans2 = digui(s, start + 2);
            }
        }
        return ans1 + ans2;
    }
}
```
# 礼物的最大价值Solution_47.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_47
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_47 {
    public int core(int nums[][]){
        int m = nums.length;
        int n = nums[0].length;
        int [][]dp = new int[m+1][n+1];
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <=n ; j++) {
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+nums[i-1][j-1];
            }
        }
        return dp[m][n  ];
    }
    public int core2(int nums[][]){
        int m = nums.length;
        int n = nums[0].length;
        int []dp = new int[n+1];
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <=n ; j++) {
                dp[j] = Math.max(dp[j],dp[j-1])+nums[i-1][j-1];
            }
        }
        return dp[n];
    }

}
```
# 最长不含重复字符的子字符串Solution_48.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_48
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_48 {
    // 滑动窗口
    char [] meno = new char[26];
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0){
            return 0;
        }
        int i  = 0;
        meno[s.charAt(0)] = 1;
        int j  = 1;
        int res = 1;
        while (j < s.length()){
            while (meno[s.charAt(j)-'a']==0){
                meno[s.charAt(j)-'a']++;
                j++;
            }
            res = Math.max(res,j-i);
            while (s.charAt(i)!=s.charAt(j)){
                meno[s.charAt(i)-'a']--;
                i++;
            }
        }
        return res;
    }
}
```
# 丑数Solution_49.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_49
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_49 {
    //从小到大生成
    public int GetUglyNumber_Solution(int N) {
        if (N <= 6)
            return N;
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2)
                i2++;
            if (dp[i] == next3)
                i3++;
            if (dp[i] == next5)
                i5++;
        }
        return dp[N - 1];
    }
}
```
# 第一个只出现一次的字符位置Solution_50.java
```java
package A剑指Offer;

import java.util.*;

/**
 * @ClassName Solution_50
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_50 {
    /*最直观的解法是使用 HashMap 对出现次数进行统计，但是考虑到要统计的字符范围有限，
    因此可以使用整型数组代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。
    */
    public int FirstNotRepeatingChar(String str) {
        int meno[] = new int [128];
        Arrays.fill(meno,-1);
        for(int i =0 ;i<str.length();i++){
            if(meno[str.charAt(i)]==-1){
                meno[str.charAt(i)] = i;
            }
            else{
                meno[str.charAt(i)] = -2;
            }
        }
        int index = str.length();
        for(int i = 0 ;i<meno.length;i++){
            if(meno[i]!=-1&&meno[i]!=-2&&meno[i]<index){
                index = meno[i];
            }
        }
        if(index != str.length()){
            return index;
        }
        return -1;
    }
/*
    以上实现的空间复杂度还不是最优的。考虑到只需要找到只出现一次的字符，
    那么需要统计的次数信息只有 0,1,更大，使用两个比特位就能存储这些信息。
*/
    public int FirstNotRepeatingChar2(String str) {
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c))
                bs1.set(c);     // 0 0 -> 0 1
            else if (bs1.get(c) && !bs2.get(c))
                bs2.set(c);     // 0 1 -> 1 1
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))  // 0 1
                return i;
        }
        return -1;
    }
}
class StringOne {
    public static void main(String[] args) {
        String str = "google";
        StringOne so = new StringOne();
        System.out.println(so.FirstNotRepeatingChar(str));
    }
    public int FirstNotRepeatingChar(String str) {
        LinkedHashMap<String, Integer> res = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < str.length(); i++) {
            if (res.containsKey(String.valueOf(str.charAt(i)))) {
                int temp = res.get(String.valueOf(str.charAt(i))) + 1;
                res.put(String.valueOf(str.charAt(i)), temp);
            } else {
                res.put(String.valueOf(str.charAt(i)), 1);
            }
        }
//        res.keySet().iterator();
        Iterator<Map.Entry<String, Integer>> iter = res.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
            if (entry.getValue() == 1) {
                return str.indexOf(entry.getKey());
            }
        }
        return -1;
    }
}
```
# 两个链表的第一个公共结点Solution_52.java
```java
package A剑指Offer;

import 数据结构相关.链表.ListNode;

/**
 * @ClassName Solution_52
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_52 {
    //跑两边
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int length1 = 0;
        int length2 = 0;
        ListNode temp = pHead1;
        while(temp != null){
            temp = temp.next;
            length1++;
        }
        temp = pHead2;
        while(temp != null){
            temp = temp.next;
            length2++;
        }
        temp = length1>length2?pHead1:pHead2;
        int k = Math.abs(length1-length2);
        while(k-->0){
            temp = temp.next;
        }
        if(length1>length2){
            while(temp!=null&&pHead2!=null&&temp!=pHead2){
                temp = temp.next;
                pHead2 = pHead2.next;
            }
        }
        else{
            while(temp!=null&&pHead2!=null&&temp!=pHead2){
                temp = temp.next;
                pHead2 = pHead2.next;
            }
        }
        return temp;
    }
}
```
# 数字在排序数组中出现的次数Solution_53.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_53
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_53 {
    //方法一 遍历
    public int GetNumberOfK(int [] array , int k) {
        return 0;
    }
    // 方法二 二分查找 快速选择
    public int GetNumberOfK2(int [] array , int k) {
        int partition = partition(array, k);
        int res= 0;
        for (int i = partition; i < array.length; i++) {
            if (array[i]==k){
                res++;
            }
            else {
                break;
            }
        }
        for (int i = partition -1 ; i >=0; i--) {
            if (array[i]==k){
                res++;
            }
            else {
                break;
            }
        }
        return res;
    }
    private int partition(int [] array,int k){
        int i = 0;
        int j = array.length-1;
        while (i<j){
            int mid = i+(j-i)/2;
            if (array[mid]==k){
                return mid;
            }
            else if (array[mid]>k){
                j = mid -1;
            }
            else{
                i = mid +1;
            }
        }
        return i;
    }
    //方法三 二分查找 算距离 找到 第一个k  第一个k+1
    public int GetNumberOfK3(int[] nums, int K) {
        int first = binarySearch(nums, K);
        int last = binarySearch(nums, K + 1);
        return (first == nums.length || nums[first] != K) ? 0 : last - first;
    }
    private int binarySearch(int[] nums, int K) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= K)
                h = m;
            else
                l = m + 1;
        }
        return l;
    }
}
```
# 二叉查找树的第 K 个结点Solution_54.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_54
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_54 {
    //中序遍历
    int index = 0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null){
            return null;
        }
        TreeNode treeNode = KthNode(pRoot.left, k);
        if (treeNode != null){
            return treeNode;
        }
        index++;
        if (index == k){
            return pRoot;
        }
        return KthNode(pRoot.right,k);
    }
}
```
# 二叉树的深度Solution_55_1.java
```java
package A剑指Offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Solution_51_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_55_1 {
    //递归
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
    }
    //队列 层次遍历 求层数
    public int TreeDepth1(TreeNode root) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.add(root);
        int d=0,count=0,nextcount=q.size();
        while(q.size()!=0) {
            TreeNode t=q.poll();
            count++;
            if(t.left!=null) {
                q.add(t.left);
            }
            if(t.right!=null) {
                q.add(t.right);
            }
            if(count==nextcount) {
                d++;
                count=0;
                nextcount=q.size();
            }
        }
        return d;
    }
}
```
# 平衡二叉树Solution_55_2.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_55_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_55_2 {
    // 求深度  但是很多重复计算
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null){
            return true;
        }
        int i = TreeDepth(root.left);
        int j = TreeDepth(root.right);
        if (Math.abs(i-j)>1){
            return false;
        }
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
    }
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
    }
    //后续遍历+ 剪枝
    public boolean IsBalanced_Solution2(TreeNode root) {
        if (root == null){
            return true;
        }
        deep(root);
        return flag;
    }
    boolean flag = true;
    private int deep(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = deep(root.left);
        if (!flag){
            return 0;
        }
        int right = deep(root.right);
        if (!flag){
            return 0;
        }
        if (Math.abs(left -right)>1){
            flag = false;
        }
        return Math.max(left,right)+1;
    }
}
```
# 数组中只出现一次的数字Solution_56.java
```java
package A剑指Offer;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @ClassName Solution_56
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_56 {
    //方法1  暴力解 空间复杂度大
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {

    }
    //方法2  BitSet 降低空间复杂度
    public void FindNumsAppearOnce2(int [] array,int num1[] , int num2[]) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i]>max)
                max = array[i];
        }
        BitSet s1 = new BitSet(max);
        BitSet s2 = new BitSet(max);
        for (int i = 0; i <array.length ; i++) {
            if (!s1.get(array[i])){
                s1.set(array[i]);
            }
            else {
                s2.set(array[i]);
            }
        }
        ArrayList<Integer> res =new ArrayList<>();
        for (int i = 0; i <array.length ; i++) {
            if (s1.get(array[i])&&!s2.get(array[i])){
                res.add(array[i]);
            }
        }
        num1[0] = res.get(0);
        num2[0] = res.get(1);
    }
    //方法2  位异或 降低空间复杂度
    // 利用 最低位来区分 两个数
    public void FindNumsAppearOnce3(int [] array,int num1[] , int num2[]) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp^=array[i];
        }
        int key = temp&(-temp);
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i]&key)!=0){
                num1[0]&=array[i];
            }
            else {
                num2[0]&=array[i];
            }
        }
    }
}
```
# 和为 S 的两个数字Solution_57_1.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_57_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_57_1 {
    //双指针
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int i = 0;
        int j = array.length-1;
        ArrayList<Integer> res = new ArrayList<>();
        while(i<j){
            int temp = array[i]+array[j];
            if(temp==sum){
                res.add(array[i]);
                res.add(array[j]);
                return res;
            }
            else if(temp >sum){
                j--;
            }
            else{
                i++;
            }
        }
        return res;
    }
}
```
# 和为 S 的连续正数序列Solution_57_2.java
```java
package A剑指Offer;

import java.util.ArrayList;

/**
 * @ClassName Solution_57_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_57_2 {

    //递归 回溯 穷举
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i <=sum/2 ; i++) {
            backtrack(res,temp,sum,0,i);
        }
        return res;
    }
    private void backtrack(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp, int sum, int tempsum,int now) {
        if (tempsum>sum){
            return;
        }
        if (tempsum == sum){
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(now);
        backtrack(res,temp,sum,tempsum+now,now+1);
        temp.remove(temp.size()-1);
    }
    //双指针  滑动窗口
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(i);
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }


   /* 思路：
    因为要求连续的数列和，所以这是一个等差数列，并且我们想到用双指针来做，slow，high。
            1.等差数列：current（当前值）=(high-slow+1)(high+slow)/2
            2.初始化slow=1和high=2.(因为考虑要覆盖到所有情况，所以赋值为两个较小的数)
            3.只要满足slow<high,循环就可进行。
            4.不断地比较current和slow
            4.1.current==slow,即slow和high之间的数满足序列要求，所以遍历slow和high之间的所有数，存入一个数组。
            *之后slow++**（因为要求的所有的连续正数序列，所以要不断的右移）
   */
   public ArrayList<ArrayList<Integer> > FindContinuousSequence3(int sum) {
        ArrayList<ArrayList<Integer>> p=new ArrayList<ArrayList<Integer>>();
        //有两个指针，一个指向头，一个指向尾
        //因为是连续的，构成等差数列，用等差数列的求和公式
        int low=1,hight=2;
        while(low<hight) {
            int temp=(low+hight)*(hight-low+1)/2;
            //如果相等，说明这个连续的数列可以构成和为sum
            if(temp==sum) {
                ArrayList<Integer> a=new ArrayList<Integer>();
                for(int i=low;i<=hight;i++) {
                    a.add(i);
                }
                p.add(a);
                //继续找下一组
                low++;
            }else if (temp<sum) {
                hight++;
            }else {
                low++;
            }
        }
        return p;
    }
}
```
# 翻转单词顺序列Solution_58_1.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_58_1
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_58_1 {
    //有问题  不能ac
    public String ReverseSentence(String str) {
        String[] s = str.split(" ");
        if (s.length == 0) {
            return str;
        }
        int i = 0;
        int j = s.length-1;
        while (i<j){
            String temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = 0; k <s.length ; k++) {
            stringBuilder.append(s[k]);
            stringBuilder.append(' ');
        }
        if (s.length>=1)
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    //还是有问题
    public String ReverseSentence2(String str) {
        if(str.length() == 0){
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0 ;
        int j = 0 ;
        while (i<str.length()&&j<str.length()){
            if (str.charAt(j)!=' '){
                j++;
            }
            else {
                stringBuilder.insert(0,str.substring(i,j+1));
                i = j+1;
                j = i;
            }
        }
        if(str.charAt(str.length()-1)!=' '){
            stringBuilder.insert(0,str.substring(i));
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    public String ReverseSentence4(String str) {
        String[] s = str.split(" ");
        if (s.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length-1; i >=0 ; i--) {
            stringBuilder.append(s[i]);
            stringBuilder.append(' ');
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    
    //ac
    public String ReverseSentence3(String str) {
        char[] chars = str.toCharArray();
        helper(chars,0,chars.length-1);
        int i = 0;
        int j = 0;
        while (i<chars.length&&j<chars.length){
            if (chars[j]!=' '){
                j++;
            }
            else {
                helper(chars,i,j-1);
                i = j+1;
                j = i;
            }
        }
        helper(chars,i,j-1);
        return new String(chars);
    }
    private void helper(char [] chars,int begin,int end ){
        while (begin<end){
            char temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end --;
        }
    }

    public static void main(String[] args) {
        String[] s = "I am    a    student.".split(" ");
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]+"---"+s[i].length());
        }
    }
}
```
# 左旋转字符串Solution_58_2.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_58_2
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_58_2 {
    //生成新的字符串
    public String LeftRotateString(String str,int n) {
        if(str.length() == 0){
            return str;
        }
        //int key = n%str.length();
        return str.substring(n)+str.substring(0,n);
    }
    // 本地翻转 不适用额外空间
    public String LeftRotateString2(String str, int n) {
        if (n >= str.length())
            return str;
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j)
            swap(chars, i++, j--);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
```
# 滑动窗口的最大值Solution_59.java
```java
package A剑指Offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @ClassName Solution_59
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_59 {
    //方法1 大顶堆
    // 不对 移除的不是最大的元素  是最左边的元素
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        ArrayList<Integer> res = new ArrayList<>();
        if(size == 0||size>num.length){
            return res;
        }
        for (int i = 0; i < size; i++) {
            queue.add(num[i]);
        }
        res.add(queue.peek());
        for (int i = size; i < num.length; i++) {
            queue.remove(num[i - size]);
            queue.add(num[i]);
            res.add(queue.peek());
        }

        return res;
    }
    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (size > num.length || size < 1)
            return ret;
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
        for (int i = 0; i < size; i++)
            heap.add(num[i]);
        ret.add(heap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
            heap.remove(num[i]);
            heap.add(num[j]);
            ret.add(heap.peek());
        }
        return ret;
    }
    /**
     * 题目：滑动窗口的最大值
     * 思路：滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
     *     原则：
     *     对新来的元素k，将其与双端队列中的元素相比较
     *     1）前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
     *     2）前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
     *     队列的第一个元素是滑动窗口中的最大值
     */
    public ArrayList<Integer> maxInWindows3(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null) {
            return ret;
        }
        if (num.length < size || size < 1) {
            return ret;
        }

        LinkedList<Integer> indexDeque = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }

        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }
}
```
# n 个骰子的点数Solution_60.java

```java
package A剑指Offer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Solution_60
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_60 {
    //dp
    /*
    方法1
    动态规划
    使用一个二维数组 dp 存储点数出现的次数，其中 dp[i][j] 表示前 i 个骰子产生点数 j 的次数。
    空间复杂度：O(N2)*/
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Write your code here
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair
        double dp[][] = new double[n+1][6*n+1];
        for (int i = 1; i <= 6 ; i++) {
            dp[1][i] = 1/6;
        }
        for (int i = 2; i <=n ; i++) {
            for (int j = i; j <=6*i ; j++) {
                for (int k = 1; k <=6 ; k++) {
                    if (j - k >= i-1){
                        dp[i][j]=dp[i][j]+dp[i-1][j-k]*1/6;
                    }else {
                        break;
                    }
                }
            }
        }
        //结果输出
        ArrayList<Map.Entry<Integer,Double>> res = new ArrayList<>();
        for (int i = n; i <=6 * n; i++) {
            res.add(new AbstractMap.SimpleEntry<Integer, Double>(i,dp[n][i]));
        }
        return res;
    }
    //思路类似 方法1
    public List<Map.Entry<Integer, Double>> dicesSum2(int n) {
        final int face = 6;
        final int pointNum = face * n;
        long[][] dp = new long[n + 1][pointNum + 1];

        for (int i = 1; i <= face; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = i; j <= pointNum; j++)     /* 使用 i 个骰子最小点数为 i */
                for (int k = 1; k <= face && k <= j; k++)  // 这不一样，，，个人绝对  这个地方 k<=j 不对
                    dp[i][j] += dp[i - 1][j - k];

        final double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / totalNum));
        return ret;
    }

    /*
    思路和上面一样，因为dp[i][j]  只用到了最近的一层 dp[i-1][j-1]
    所以空间优化为  dp[2][]
    动态规划 + 旋转数组
    空间复杂度：O(N)
    */
    public List<Map.Entry<Integer, Double>> dicesSum3(int n) {
        final int face = 6;
        final int pointNum = face * n;
        long[][] dp = new long[2][pointNum + 1];

        for (int i = 1; i <= face; i++)
            dp[0][i] = 1;

        int flag = 1;                                     /* 旋转标记 */
        for (int i = 2; i <= n; i++, flag = 1 - flag) {
            for (int j = 0; j <= pointNum; j++)
                dp[flag][j] = 0;                          /* 旋转数组清零 */

            for (int j = i; j <= pointNum; j++)
                for (int k = 1; k <= face && k <= j; k++)
                    dp[flag][j] += dp[1 - flag][j - k];
        }

        final double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[1 - flag][i] / totalNum));

        return ret;
    }
}
```
# 扑克牌顺子Solution_61.java
```java
package A剑指Offer;

import java.util.Arrays;

/**
 * @ClassName Solution_61
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_61 {
    int meno[] = new int[14];
    //hashmap
    public boolean isContinuous(int [] numbers) {
        if(numbers.length == 0){
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            meno[numbers[i]]++;
        }
        Arrays.sort(numbers);
        int zeroSum = 0;
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]==0){
                zeroSum++;
                index++;
            }
            else {
                break;
            }
        }
        for (int i = index; i < numbers.length; i++) {
            if (meno[numbers[i]]>=2){
                return false;
            }
            else if (i>index&&numbers[i]-numbers[i-1]>=2){
                zeroSum = zeroSum - ((numbers[i]-numbers[i-1]) - 1);
            }
            if (zeroSum<0){
                return false;
            }
        }
        return true;
    }
    //思路与方法1 一样
    public boolean isContinuous2(int[] nums) {
        if (nums.length < 5)
            return false;
        Arrays.sort(nums);
        // 统计癞子数量
        int cnt = 0;
        for (int num : nums)
            if (num == 0)
                cnt++;
        // 使用癞子去补全不连续的顺子
        for (int i = cnt; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i])
                return false;
            cnt -= nums[i + 1] - nums[i] - 1;
        }
        return cnt >= 0;
    }
}
```
# 圆圈中最后剩下的数Solution_62.java
```java
package A剑指Offer;

import java.util.LinkedList;

/**
 * @ClassName Solution_62
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_62 {
    //队列模拟
    //有点慢
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 ){
            return -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.addLast(i);
        }
        int index = 0;
        while (queue.size()>1){
            if (index == m -1){
                queue.pollFirst();
                index = 0;
                continue;
            }
            index++;
            queue.addLast(queue.pollFirst());
        }
        return queue.pop();
    }
    //这个On 快了一些
    public int LastRemaining_Solution2(int n, int m) {
        if (n <= 0 ){
            return -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.addLast(i);
        }
        int index = 0;
        while (queue.size()>1){
            index  = (m+index-1)%queue.size();
            queue.remove(index);
        }
        return queue.pop();
    }
    //不用list 模拟
    //直接计算
    public int LastRemaining_Solution4(int n, int m) {
        if (n == 0)     /* 特殊输入的处理 */
            return -1;
        int index = 0;
        for (int i = n ; i >=2 ; i--) {
            index = (index+m-1)%i;
//            index
        }
        return index;
    }
    //这个更快
    public int LastRemaining_Solution3(int n, int m) {
        if (n == 0)     /* 特殊输入的处理 */
            return -1;
        if (n == 1)     /* 递归返回条件 */
            return 0;
        return (LastRemaining_Solution3(n - 1, m) + m) % n;
    }
}
```
# 股票的最大利润Solution_63.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_63
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_63 {
    //dp
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        int curmax = 0;
        for (int i = 0; i < prices.length; i++) {
            if (min>prices[i]){
                min = prices[i];
            }
            curmax = prices[i] - min;
            max = Math.max(max,curmax);
        }
        return max;
    }
    public int maxProfit2(int[] prices) {
        int min = prices[0];
        int max = 0;
        int curmax = 0;
        for (int i = 0; i < prices.length; i++) {
            if (min>prices[i]){
                min = prices[i];
            }
            curmax = prices[i] - min;
            max = Math.max(max,curmax);
        }
        return max;
    }
}
```
# 求 1+2+3+...+nSolution_64.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_64
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_64 {


/*
    解题思路：
            1.需利用逻辑与的短路特性实现递归终止。
            2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
            3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
*/
    public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n>0)&&((sum+=Sum_Solution(n-1))>0);
        return sum;
    }
}
```
# 不用加减乘除做加法Solution_65.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_65
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_65 {
    /*
    a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
    递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，
    进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止。
    */

    public int Add(int num1,int num2) {
        if(num2 == 0){
            return num1;
        }
        return Add(num1^num2,(num1&num2)<<1);
    }
}
```
# 构建乘积数组Solution_66.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_66
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_66 {
    //用 meno 记录
    public int[] multiply(int[] A) {
        int b[] = new int[A.length];
        int temp = 1;
        for (int i = 0; i < A.length; i++) {
            b [i] =  temp;
            temp = temp*A[i];
        }
        temp = 1;
        for (int i = A.length-1; i >= 0; i--) {
            b [i] =b [i]*  temp;
            temp = temp*A[i];
        }
        return b;
    }
}
```
# 把字符串转换成整数Solution_67.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_67
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_67 {
    //写的太繁琐了
    public int StrToInt(String str) {
        int res = 0;
        int index = str.length()-1;
        int begin1 = 0;
        int begin2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)=='+'){
                begin1++;
            }
            else if (str.charAt(i)=='-'){
                begin2++;
            }
            else {
                break;
            }
        }
        if (begin1!=0&&begin2!=0){
            return 0;
        }
        int base = 1;
        if (begin1 != 0){
            base = 1;
        }
        else if (begin2!=0){
            base = -1;
            begin1 = begin2;
        }
        while (index>=begin1){
            if (str.charAt(index)>='0'&&str.charAt(index)<='9'){
                res+=base*(str.charAt(index)-'0');
                base*=10;
            }
            else {
                return 0;
            }
            index--;
        }
        if ((base>0&&res>0)||(base<0&&res<0)){
            return res;
        }
        else {
            return 0;
        }
    }
}
```
# 树中两个节点的最低公共祖先Solution_68.java
```java
package A剑指Offer;

/**
 * @ClassName Solution_68
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class Solution_68 {
    //通用的搜索
    //二叉树的公共祖先
    //在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p||root == q||root==null){
            return root;
        }
        TreeNode treeNode1 = lowestCommonAncestor(root.left, p, q);
        TreeNode treeNode2 = lowestCommonAncestor(root.right, p, q);
        if (treeNode1==null){
            return treeNode2;
        }
        if (treeNode2==null){
            return treeNode1;
        }
        return root;
    }
    //二叉搜索树的 最近公共祖先
    //二叉查找树中，两个节点 p, q 的公共祖先 root 满足 root.val >= p.val && root.val <= q.val。
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return root;
        }
        if (root == q||root ==p){
            return root;
        }
        if (p.val>q.val){
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        if (root.val<p.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        else if (root.val>q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}
```
# 表示数值的字符串NotSolution_20.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_20
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class NotSolution_20 {
    public boolean isNumeric(char[] str) {
        return false;
    }
}
```

# 从 1 到 n 整数中 1 出现的次数NotSolution_43.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_43
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class NotSolution_43 {
    //数学归纳法

}
```

# 数字序列中的某一位数字NotSolution_44.java

```java
package A剑指Offer;

/**
 * @ClassName NotSolution_44
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class NotSolution_44 {
    //数学归纳法
}
```

# 数组中的逆序对NotSolution_51.java

```java
package A剑指Offer;

/**
 * @ClassName Solution_51
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class NotSolution_51 {
    //暴力解  超时
    public int InversePairs(int [] array) {
        long res = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j <array.length; j++) {
                if (array[i]>array[j]){
                    res++;
                }
            }
        }
        return (int)(res%1000000007);
    }
    //dp
    public int InversePairs2(int [] array) {
        int dp[]  = new int[array.length];
        dp[0] = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (array[i]<array[j]){
                    dp[i]=dp[i]+dp[j]+1;
                    break;
                }
            }
        }
        return dp[array.length-1];
    }
}
```

