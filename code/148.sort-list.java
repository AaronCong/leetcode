/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 *
 * https://leetcode.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (38.56%)
 * Likes:    1990
 * Dislikes: 101
 * Total Accepted:    222.8K
 * Total Submissions: 576.6K
 * Testcase Example:  '[4,2,1,3]'
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Example 1:
 * 
 * 
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
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
    public ListNode sortList(ListNode head) {
        // if (head == null || head.next == null) return head;
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        // Boolean done = false;
        // ListNode[] list = new ListNode[2];
        // for (int step = 1; !done; step *= 2) {
        //     done = true;
        //     ListNode prev = dummy;
        //     ListNode rest = prev.next;
        //     while (rest != null) {
        //         for (int i = 0; i < 2; i++) {
        //             list[i] = rest;
        //             ListNode tail = null;
        //             for (int j = 0; j < step && rest != null; j++) {
        //                 tail = rest;
        //                 rest = rest.next;
        //             }
        //             if (tail != null) {
        //                 tail.next = null;
        //             }
        //         }
        //         if (list[1] != null) {
        //             while (list[0] != null || list[1] != null) {
        //                 int idx = (list[1] == null || list[0] != null && list[0].val <= list[1].val) ? 0 : 1;
        //                 prev.next = list[idx];
        //                 list[idx] = list[idx].next;
        //                 prev = prev.next;
        //             }
        //             prev.next = null;
        //         }
        //         else {
        //             prev.next = list[0];
        //         }
        //         done = done && (rest == null);
        //     }
        // }
        // return dummy.next;
        if (head == null || head.next == null) return head;

        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
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
// @lc code=end

