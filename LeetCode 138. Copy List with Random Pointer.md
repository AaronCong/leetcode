### 138. Copy List with Random Pointer 



#### 题目描述

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a [**deep copy**](https://en.wikipedia.org/wiki/Object_copying#Deep_copy) of the list.

The Linked List is represented in the input/output as a list of `n` nodes. Each node is represented as a pair of `[val, random_index]` where:

- `val`: an integer representing `Node.val`
- `random_index`: the index of the node (range from `0` to `n-1`) where random pointer points to, or `null` if it does not point to any node.

 

**Example 1:**

```
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**Example 2:**

```
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
```

**Example 3:**

**![img](https://assets.leetcode.com/uploads/2019/12/18/e3.png)**

```
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
```

**Example 4:**

```
Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.
```

 

**Constraints:**

- `-10000 <= Node.val <= 10000`
- `Node.random` is null or pointing to a node in the linked list.
- Number of Nodes will not exceed 1000.
- 

#### 思路

- 遍历一遍链表，复制每一个节点，用一个HashMap保存原节点和复制的节点的对应关系，再遍历一次链表对复制的节点的next和random赋值
- 把复制的节点直接连在原节点后面，省掉了HashMap，详细见代码

#### 代码

```Java
class Solution {
    public Node copyRandomList(Node head) {
        // 方法一
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<Node, Node>(); // 用一个map保存<oldNode, newNode>
        Node cur = head;
        int index = 0;
        while (cur != null) {
            Node newCur = new Node(0);
            newCur.val = cur.val;
            map.put(cur, newCur);
            cur = cur.next;
        }
        cur = head; index = 0;
        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            entry.getValue().next = map.get(entry.getKey().next);
            entry.getValue().random = map.get(entry.getKey().random);
        }
        return map.get(head);

        // 方法二
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node newCur = new Node(cur.val); // 将复制的节点连在原节点后面
            newCur.next = cur.next;
            cur.next = newCur;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next; // 为random赋值
            cur = cur.next.next;
        }
        Node dummy = new Node(0);
        Node newHead = dummy;
        Node next;
        cur = head;
        while (cur != null) { // 抽出复制的链表并还原原链表
            next = cur.next.next; // 原链表的下一个节点
            newHead.next = cur.next; // 新链表的当前节点
            newHead = cur.next;
            cur.next = next; // 还原原链表的顺序
            cur = cur.next;
        }
        return dummy.next;
    }
}
```



