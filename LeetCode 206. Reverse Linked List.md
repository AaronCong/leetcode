### LeetCode 206. Reverse Linked List 



#### 题目描述

Reverse a singly linked list.

**Example:**



```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```



#### 思路

- 遍历链表，把每个节点连接到它前一个节点上，但这样就到不了原来连接的节点了，所以要把下一个节点提前存起来，需要三个变量同时保存当前节点，前一个和下一个节点。

- 递归的思想。假设当前节点node后面的链表已经被反转了，也就是说node.next开头的链表是一个反转的链表，那怎么把node.next和node连起来呢， *node.next.next = node*

  两种方法的时间复杂度都是 $$O(n)$$的，但是由于递归要用到函数栈所以空间复杂度是$$O(n)$$，而循环不需要额外空间，空间复杂度是$$O(1)$$ 

#### 代码

```Java
class Solution {

    // 循环
     public ListNode reverseList(ListNode head) {
         ListNode first = null;
         ListNode second = head;
         while (second != null) {
             ListNode third = second.next;
             second.next = first;
             first = second;
             second = third;
         }
         return first;
     }

    // 递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
```



