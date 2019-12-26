### LeetCode 61. Rotate List



#### 题目描述

Given a linked list, rotate the list to the right by *k* places, where *k* is non-negative.

**Example 1:**

```
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
```

**Example 2:**

```
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
```



#### 思路

- 用一个cur指针遍历统计链表长度count，**将链表连成环**，此时cur在尾节点的位置，实际要旋转k % count次，因此将cur指针再向前移动count - k % count次将链表断开就得到了旋转后的链表。



#### 代码

```Java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int count = 0; ListNode cur = head;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        cur.next = head;
        count++;
        for (int i = 0; i < count - k % count; i++)
            cur = cur.next;
        ListNode newhead = cur.next;
        cur.next = null;
        return newhead;
    }
}
```



