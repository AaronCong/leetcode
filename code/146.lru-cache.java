/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (28.97%)
 * Likes:    4263
 * Dislikes: 182
 * Total Accepted:    409.3K
 * Total Submissions: 1.4M
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * 
 * The cache is initialized with a positive capacity.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * 
 * LRUCache cache = new LRUCache( 2 /* capacity */ );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 * 
 * 
 */

// @lc code=start
class LRUCache {

    class DoubleNode {
        DoubleNode pre, next;
        int key, value;
    }
    
    private void addNode(DoubleNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DoubleNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(DoubleNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DoubleNode removeTail() {
        DoubleNode lastNode = tail.pre;
        this.removeNode(lastNode);
        return lastNode;
    }

    Map<Integer, DoubleNode> hashmap = new HashMap<Integer, DoubleNode>();
    int capacity;
    int count;
    DoubleNode head;
    DoubleNode tail;
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new DoubleNode();
        tail = new DoubleNode();
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;        
        
    }
    
    public int get(int key) {
        DoubleNode node = this.hashmap.get(key);
        if (node != null) {
            this.moveToHead(node);
            return node.value;
        }
        else return -1;
    }
    
    public void put(int key, int value) {
        DoubleNode node = this.hashmap.get(key);
        if (node != null) {
            node.value = value;
            this.moveToHead(node);
            return;
        }
        else {
            node = new DoubleNode();
            node.key = key; node.value = value;
            this.hashmap.put(key, node);
            this.addNode(node);
            count++;
            if (count > capacity) {
                hashmap.remove(this.removeTail().key);
                count--;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

