### LeetCode 143. Reorder List 



#### 题目描述

Given a singly linked list *L*: *L*0→*L*1→…→*L**n*-1→*L*n,
reorder it to: *L*0→*L**n*→*L*1→*L**n*-1→*L*2→*L**n*-2→…

You may **not** modify the values in the list's nodes, only nodes itself may be changed.

**Example 1:**

```
Given 1->2->3->4, reorder it to 1->4->2->3.
```

**Example 2:**

```
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
```





#### 思路

- 参考**92题**的递归方法，定义left和right两个指针，初始都指向head，先让right走到尾节点，然后不断将尾节点插入到left右边。
- 分三部分。第一步找到链表的中间节点，然后将后半部分反转，最后将右半部分依次插入左边

#### 代码

```Java
class Solution {
    // 方法一
    public ListNode left;
    public Boolean stop;
    public void recurse(ListNode right) {
        if (right == null || right.next == null) return;
        right = right.next;
        recurse(right);
        if (!this.stop && (this.left == right || this.left.next == right)){
            right.next = null;
            this.stop = true;
        }
        if (!this.stop){
            right.next = this.left.next;
            this.left.next = right;
            this.left = right.next;
        }
    }
    public void reorderList(ListNode head) {
        this.left = head;
        this.stop = false;
        recurse(head);
    }

    // 方法二
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) { // 找到中间节点p1
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode middle = p1;
        ListNode preCurrent = p1.next;
        while (preCurrent.next != null) { // 反转右半部分 1->2->3->4->5->6 to 1->2->3->6->5->4，方法是每次把preCurrent右移一位，preCurrent的下一个节点放到右半部分第一个节点，与middle相连
            ListNode current = preCurrent.next;
            preCurrent.next = current.next;
            current.next = middle.next;
            middle.next = current;
        }
        p1 = head;
        p2 = middle.next;
        while (p1 != middle) { // 依次将右半部分插入左边  1->2->3->6->5->4 to 1->6->2->5->3->4，方法是对每个右半部分的节点p2，将middle连接到p2.next，然后将p2插入到p1和p1.next之间，然后右移p1和p2
            middle.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = middle.next;
        }
    }

}

```



