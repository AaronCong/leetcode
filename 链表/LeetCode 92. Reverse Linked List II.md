### LeetCode 92. Reverse Linked List II 



#### 题目描述

Reverse a linked list from position *m* to *n*. Do it in one-pass.

**Note:** 1 ≤ *m* ≤ *n* ≤ length of list.

**Example:**

```
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
```



#### 思路

- 思路和反转整个链表一样，定义三个指针分别指向当前节点，前一个和下一个节点。不同的地方是需要先将prev和curr向前移m-1步，并且记下反转部分的起点和终点，最后重新调整顺序。

  ​	1 \<- 2 \<- 3 \<- 4 -\> 5

  ​	|	|	       |      |

  ​     start	tail	     prev   curr

  start是反转部分的前一个节点，tail是反转后的最后一个节点，prev和curr是反转停止时对应的节点，调整顺序使start.next = prev, tail.next = curr

- 参考数组的反转，可以定义头尾两个指针，不断交换两个指针的值。但是链表没法直接找到尾指针，所以用递归的方法模拟这个过程。定义一个函数```recurse(int m, int n, ListNode right) ```作用是交换m和n位置的节点的值，right是n位置的节点，另外需要一个成员变量left保存m位置的节点，因为多次递归中left的值需要传递下去，而Java方法的参数传递是值传递，所以用成员变量保存这个值。

  递归的终止条件是n==1，这时候不需要替换直接返回。初始状态下left和right都指向head，目标是让left指向第m个节点，right指向第n个节点，因此每次将left和right向右移动后都调用recurse(m - 1, n - 1, right)，这样当n=1也就是递归终止时right的位置就是原来第n个节点了（left先到达m后就不移动了）。这时候判断left和right的位置是否交叉或相等，如果没有就交换left和right的值，否则代表反转完成。每次交换完成后将left向右移动一位，而right由于跳回了上一层函数自动向左移动了一位。

#### 代码

```Java
class Solution {
    // 循环
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 1; i < m; i++) { // 先向前移动m-1步
            prev = curr;
            curr = curr.next;
        }
        ListNode start = prev; ListNode tail = curr; // 记下反转部分的前一个节点start和反转后的尾节点tail
        for (int i = 0; i <= n - m; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (start != null)   start.next = prev; // start==null代表从第一个开始反转
        else    head = prev;
        tail.next = curr;

        return head;
    }

    // 递归
    public ListNode left;
    public Boolean stop = false;
    public void recurse(int m, int n, ListNode right) {
        if (n == 1) return;
        right = right.next;
        if (m > 1)
            this.left = this.left.next;
        recurse(m - 1, n - 1, right);
        if (this.left == right || right.next == this.left) { // 停止条件，奇数个节点时left == right，偶数个时right.next == left
            this.stop = true;
        }
        if (!this.stop) {
            int val = right.val;
            right.val = this.left.val;
            this.left.val = val;
            left = left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        Solution s = new Solution();
        s.left = head; ListNode right = head;
        s.recurse(m, n, right);
        return head;
    }
}
```



