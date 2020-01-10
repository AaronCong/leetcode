### LeetCode 147. Insertion Sort List



#### 题目描述

Sort a linked list using insertion sort.



![img](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)
A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 



**Algorithm of Insertion Sort:**

1. Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
3. It repeats until no input elements remain.


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



#### 代码

```Java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode prev = head;
        dummy.next = head;
        while (cur != null) {
            ListNode start = dummy;
            while (start != cur) {
                if (cur.val >= start.val && cur.val < start.next.val) {
                    prev.next = cur.next;
                    cur.next = start.next;
                    start.next = cur;
                    break;
                }
                start = start.next;
            }
            if (start == cur) {
                cur = cur.next;
                prev = prev.next;
            }
            else
                cur = prev.next;
            
        }
        return dummy.next;

    }
}
```



