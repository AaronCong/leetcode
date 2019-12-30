/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
 *
 * https://leetcode.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (33.50%)
 * Likes:    1268
 * Dislikes: 92
 * Total Accepted:    187.1K
 * Total Submissions: 558.4K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example 1:
 * 
 * 
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * 
 * Example 2:
 * 
 * 
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
    // public ListNode left;
    // public Boolean stop;
    // public void recurse(ListNode right) {
    //     if (right == null || right.next == null) return;
    //     right = right.next;
    //     recurse(right);
    //     if (!this.stop && (this.left == right || this.left.next == right)){
    //         right.next = null;
    //         this.stop = true;
    //     }
    //     if (!this.stop){
    //         right.next = this.left.next;
    //         this.left.next = right;
    //         this.left = right.next;
    //     }
    // }
    // public void reorderList(ListNode head) {
    //     this.left = head;
    //     this.stop = false;
    //     recurse(head);
    // }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode middle = p1;
        ListNode preCurrent = p1.next;
        while (preCurrent.next != null) {
            ListNode current = preCurrent.next;
            preCurrent.next = current.next;
            current.next = middle.next;
            middle.next = current;
        }
        p1 = head;
        p2 = middle.next;
        while (p1 != middle) {
            middle.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = middle.next;
        }
    }

}

// @lc code=end

