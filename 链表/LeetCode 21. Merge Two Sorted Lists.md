### LeetCode 21. Merge Two Sorted Lists



#### 题目描述

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Example:**

```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```



#### 思路

- 因为是有序的链表，所以直接逐个比较大小插到新链表就可以了，本来想不用额外链表的，但写了一下有点复杂就放弃了，留给以后实现吧。
- 讨论区看到一个很cool的递归做法，但貌似链表过长的话会栈溢出



#### 代码

```Java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 递归
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

        // 循环
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            }
            else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        dummy.next = l1 == null ? l2 : l1;
        return result.next;
    }
}
```



