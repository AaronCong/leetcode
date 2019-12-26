### LeetCode 19. Remove Nth Node From End of List



#### 题目描述

Given a linked list, remove the *n*-th node from the end of list and return its head.

**Example:**

```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```



#### 思路

- 先遍历一遍链表得到链表长度 *length* ，再遍历一遍删除第 *length - n* 个节点，需要遍历两次
- 定义两个指针 *first second*，*first* 比 *second* 先走n步，这样当 *first* 走到终点时 *second* 的下一个就是要删除的节点

\# two pointers        \# linkedlist



#### 代码

```Java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 在head前增加了一个dummy节点，避免一些边界问题
        dummy.next = head;
        ListNode second = dummy;
        ListNode first = dummy;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
```



