/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
 *
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (36.76%)
 * Likes:    1675
 * Dislikes: 112
 * Total Accepted:    230.6K
 * Total Submissions: 627.4K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 * 
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * 
 * Example:
 * 
 * 
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 循环
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 1; i < m; i++) {
            prev = curr;
            curr = curr.next;
        }
        ListNode start = prev; ListNode tail = curr;
        for (int i = 0; i <= n - m; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (start != null)   start.next = prev;
        else    head = prev;
        tail.next = curr;

        return head;
    }

    // 递归
    public ListNode left;
    public Boolean stop = false;
    public void recurse(int m, int n, ListNode right) {
        if (n == 1) return;
        right = right.next;
        if (m > 1)
            this.left = this.left.next;
        recurse(m - 1, n - 1, right);
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }
        if (!this.stop) {
            int val = right.val;
            right.val = this.left.val;
            this.left.val = val;
            left = left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        Solution s = new Solution();
        s.left = head; ListNode right = head;
        s.recurse(m, n, right);
        return head;
    }
}
// @lc code=end

