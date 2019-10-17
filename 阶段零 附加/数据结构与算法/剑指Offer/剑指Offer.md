#### Solution_3.java
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
#### Solution_4.java
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
#### Solution_5.java
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
#### Solution_6.java
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
#### Solution_7.java
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
#### Solution_8.java
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
#### Solution_9.java
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
#### Solution_10_1.java

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

#### Solution_10_2.java

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

#### Solution_10_3.java

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

#### Solution_11.java

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

#### Solution_12.java

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

#### Solution_13.java

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

#### Solution_14.java

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

#### 