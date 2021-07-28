/**
 * DoublyLinkedListTest.java
 *
 * You are required to implement addHistory and removeHistory.
 * 
 * DO NOT MODIFY OTHER METHODS !!!
 * 
 */

public class DoublyLinkedList {

    private Node head;
    private Node tail;

    private class Node {

        Node prev;
        Node next;
        String link;

        /**
         * Constructs a node
         * 
         * @param prev points to the previous node
         * @param next points to the next node
         * @param link a string stored in the current node
         */
        
        public Node(Node prev, Node next, String link) {
            this.prev = prev;
            this.next = next;
            this.link = link;
        }
    }

    /**
     * Constructs an empty doubly linked list
     *
     */
    
    public DoublyLinkedList() {}

    /**
     * Todo: Adds visited website to the history.
     *
     * @param link link to be added to the history
     */

    public void addHistory(String link) {
        //LL is empty, initialize as head
        Node node;
        if (head == null) {
            node = new Node(null, null, link);
            head = node;
            tail = node;
        }
        else {
            node = new Node(tail, null, link);
            tail.next = node;
            tail = node;
        }
    }

    public boolean contains(String link) {
        Node temp = head;
        while (temp != null) {
            if (temp.link.equals(link)) return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * Todo: Removes from the history. You need to search first, and then remove the history.
     * If no such history exists, then return.
     *
     * @param link link to be removed from the history
     */

    public void removeHistory(String link) {
        if (head == null) return;
        else if (!contains(link)) return;

        Node searchNode = head;
        while (searchNode != null) {
            if (searchNode.link.equals(link)) {
                //only 1 node
                if (head == tail) {
                    head = null;
                    tail = null;
                    return;
                }
                //if node is head
                if (searchNode == head) {
                    Node next = searchNode.next;
                    next.prev = null;
                    head = next;
                    return;
                }
                //if node is tail
                else if (searchNode == tail) {
                    Node prev = searchNode.prev;
                    prev.next = null;
                    tail = prev;
                    return;
                }
                else {
                    Node prev = searchNode.prev;
                    Node next = searchNode.next;
                    prev.next = next;
                    next.prev = prev;
                    return;
                }
            }
            else searchNode = searchNode.next;
        }
    }

    /**
     * Converts the doubly linked list into a String.
     * Used for testing.
     *
     * DO NOT MODIFY THIS !!!
     *
     * @return a String representing the doubly linkedlist
     */

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node cur = head;
        while(cur != null) {
            sb.append(cur.link + " -> ");
            cur = cur.next;
        }
        return sb.toString();
    }
}
