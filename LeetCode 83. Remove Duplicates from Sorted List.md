### LeetCode 83. Remove Duplicates from Sorted List 



#### 题目描述

Given a sorted linked list, delete all duplicates such that each element appear only *once*.

**Example 1:**

```
Input: 1->1->2
Output: 1->2
```

**Example 2:**

```
Input: 1->1->2->3->3
Output: 1->2->3
```



#### 思路

- 因为是有序链表所以直接循环遍历，注意指针越界的判断



#### 代码

```Java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode start = head;
        while (start.next != null) {
            if (start.val == start.next.val)
                start.next = start.next.next;
            else
                start = start.next;
        }
        return head;
    }
}
```



