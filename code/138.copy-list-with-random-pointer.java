/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (31.16%)
 * Likes:    2279
 * Dislikes: 555
 * Total Accepted:    323.5K
 * Total Submissions: 1M
 * Testcase Example:  '[[7,null],[13,0],[11,4],[10,2],[1,0]]\r'
 *
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * The Linked List is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 * 
 * 
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random
 * pointer points to, or null if it does not point to any node.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: head = []
 * Output: []
 * Explanation: Given linked list is empty (null pointer), so return null.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -10000 <= Node.val <= 10000
 * Node.random is null or pointing to a node in the linked list.
 * Number of Nodes will not exceed 1000.
 * 
 * 
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        // if (head == null) return null;
        // Map<Node, Node> map = new HashMap<Node, Node>();
        // Node cur = head;
        // int index = 0;
        // while (cur != null) {
        //     Node newCur = new Node(0);
        //     newCur.val = cur.val;
        //     map.put(cur, newCur);
        //     cur = cur.next;
        // }
        // cur = head; index = 0;
        // for (Map.Entry<Node, Node> entry : map.entrySet()) {
        //     entry.getValue().next = map.get(entry.getKey().next);
        //     entry.getValue().random = map.get(entry.getKey().random);
        // }
        // return map.get(head);

        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node newCur = new Node(cur.val);
            newCur.next = cur.next;
            cur.next = newCur;
            cur = cur.next.next;
        }

        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        Node dummy = new Node(0);
        Node newHead = dummy;
        Node next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            newHead.next = cur.next;
            newHead = cur.next;
            cur.next = next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
// @lc code=end

