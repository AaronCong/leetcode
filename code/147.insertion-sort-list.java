/*
 * @lc app=leetcode id=147 lang=java
 *
 * [147] Insertion Sort List
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
// @lc code=end

