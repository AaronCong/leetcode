### 142. Linked List Cycle II 



#### 题目描述

Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Note:** Do not modify the linked list.

 

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

 

**Follow-up**:
Can you solve it without using extra space?

#### 思路

- 定义fast和slow两个指针遍历链表，fast每次走两步，slow每次走一步，如果fast和slow相遇，则有环，如果fast走到终点则无环。

  设链表无环的部分长度是a，相遇时slow在环上走了b，环的长度是c，因为fast的速度是slow的两倍，应该有$2(a+b) = a+b+c$，即$a+b=c$。由图可以看出，当slow和fast相遇时，slow与环起点的距离应该等于链表起点到环起点的距离，因此当两指针相遇时，再定义一指针i从链表起点出发，每次走一步，当slow与i相遇时，相遇的节点就是环的起点。
  
  <img src='../img/142.png' align='center'>

#### 代码

```Java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode i = head;
                while (i != slow) {
                    i = i.next;
                    slow = slow.next;
                }
                return i;
            }
        }
        return null;
    }
}
```



