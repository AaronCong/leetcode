/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode start = head;
        while (start.next != null) {
            if (start.val == start.next.val)
                start.next = start.next.next;
            else
                start = start.next;
        }
        return head;
    }
}
// @lc code=end

