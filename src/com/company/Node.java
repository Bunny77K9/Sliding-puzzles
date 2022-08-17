package com.company;
import java.util.ArrayList;

public class Node {

    // Initializing Variables
    int[] coordinatesArray;                     // declare array of integers with the name coordinate
    ArrayList<Node> next = new ArrayList<>();   // create an arrayList of type Node to store the nodes
    Node preNode = null;                        // previous node
    String preNodeDir;                          // previous node direction

    // Default Constructor contains coordinates parameter
    Node(int[] coordinates) {
        this.coordinatesArray = coordinates;
    }

    // Overloaded constructor contains coordinates, preNodeDir and preNode parameters.
    Node(int[] coordinates, String preNodeDir, Node preNode) {
        this.coordinatesArray = coordinates;
        this.preNodeDir = preNodeDir;
        this.preNode = preNode;
    }

    // Add node method adds the next node to the array list which keeps track of the nodes
    public void addNextNode(Node nextNode) {
        next.add(nextNode);
    }
}
