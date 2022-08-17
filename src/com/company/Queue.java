package com.company;
public class Queue {

    // Initializing Variables
    private static Node[] nodeQ; // queue array to store the nodes
    private int head;
    private int back;
    private int size;
    private int volume;

    public Queue(int volume){
        
        nodeQ = new Node[volume]; // stores capacities in the array Node
        this.volume = volume;
        size = 0;
        head = 0;
        back = -1;
    }

    /*
     * enqueueAll method
     * takes one argument which is node
     */
    public void enqueueAll(Node node) {
        
        for(int i = 0; i < node.next.size(); i++) {

            // adds the node to the end of the queue
            enqueue(node.next.get(i));
        }
    }

    /*
     * enqueue method
     */
    public void enqueue(Node data) {

        // if the queue is full
        if (isFull()) {

            // prints a message that the queue is full
            System.out.println("Queue Full");
        } 
        else {
            // increments the size of the queue by 1
            // checks the remaining when a new node is queued in the list
            back = (back + 1) % volume;
            nodeQ[back] = data;
            size++;
        }
    }

    /*
     * dequeue method
     */
    public Node dequeue() {
        if (isEmpty()) {
            System.out.println("Queue empty");
            return null;
        } 
        else {
            // removes the node from the queue from the font
            Node nodeRemoved = nodeQ[head];
            // update front of the queue
            // checks the remaining when the node is removed from the queue
            head = (head + 1) % volume;
            size--;
            return nodeRemoved;
        }
    }

    /*
     * isFull method
     * returns true if the size of the queue is equal to the capacity
     * False if less than the capacity
     */
    public boolean isFull() {
        return size == volume;
    }

    /*
     * isEmpty method
     * returns true if the size of the storage is equal to 0
     * False if not equal to 0
     */
    public boolean isEmpty() {
        return size == 0;
    }
}