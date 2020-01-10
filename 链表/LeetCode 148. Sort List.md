### LeetCode 148. Sort List



#### 题目描述

Sort a linked list in *O*(*n* log *n*) time using constant space complexity.

 

**Example 1:**

```
Input: 4->2->1->3
Output: 1->2->3->4
```

**Example 2:**

```
Input: -1->5->3->4->0
Output: -1->0->3->4->5
```

#### 思路

​	因为要*O*(*n* log *n*)的时间复杂度，因此想到归并排序，但是递归好像不满足常数的空间复杂度，在讨论区看到一种循环代替递归的做法，每趟从头依次选step长度的两个链表，把两个链表合并，step从1开始，第一趟完成后链表两两有序，然后将step乘2，重复这个过程，直到整个链表有序

#### 代码

```Java
class Solution {
    public ListNode sortList(ListNode head) {
        // 循环
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Boolean done = false;
        ListNode[] list = new ListNode[2];
        for (int step = 1; !done; step *= 2) {
            done = true;
            ListNode prev = dummy;
            ListNode rest = prev.next;
            while (rest != null) {
                for (int i = 0; i < 2; i++) {
                    list[i] = rest;
                    ListNode tail = null;
                    for (int j = 0; j < step && rest != null; j++) {
                        tail = rest;
                        rest = rest.next;
                    }
                    if (tail != null) {
                        tail.next = null;
                    }
                }
                if (list[1] != null) {
                    while (list[0] != null || list[1] != null) {
                        int idx = (list[1] == null || list[0] != null && list[0].val <= list[1].val) ? 0 : 1;
                        prev.next = list[idx];
                        list[idx] = list[idx].next;
                        prev = prev.next;
                    }
                    prev.next = null;
                }
                else {
                    prev.next = list[0];
                }
                done = done && (rest == null);
            }
        }
        return dummy.next;
        
        // 递归
        if (head == null || head.next == null) return head;

        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 切断链表
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        ListNode dummy = new ListNode(0);
        prev = dummy;
        while (l1 != null || l2 != null) {
            if (l2 == null || l1 != null && l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
                prev = prev.next;
            }
            else {
                prev.next = l2;
                l2 = l2.next;
                prev = prev.next;
            }
        }
        return dummy.next;
    }
}
```



