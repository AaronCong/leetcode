### LeetCode 141. Linked List Cycle 



#### 题目描述

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

 

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

 

**Follow up:**

Can you solve it using *O(1)* (i.e. constant) memory?

#### 思路

- 定义fast和slow两个指针遍历链表，fast每次走两步，slow每次走一步，如果fast和slow相遇，则有环，如果fast走到终点则无环。

  为什么有环一定会相遇呢？考虑当fast只在slow后面一步时，那么下一步两指针就会相遇，如果是相差更多步，总是会走到差一步的情况（因为fast每次比slow多走一步）

  时间复杂度当无环时是O(n)的，当有环时，从slow指针的视角看可以分两步计算：

   	1. 进入环之前走的时间N
   	2. 进入环之后，fast追上slow的时间，等于两指针的距离/速度差，距离最大等于环的长度K，速度差是1,

  因此总的时间复杂度最坏是O(N+K)，也就是O(1)的

  

#### 代码

```Java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
```



