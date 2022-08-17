package com.company;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.company.GUI.t2;
import static com.company.GUI.t3;

public class SlidingPuzzle extends Component {
    
    // Initializing variables
    Queue queue = new Queue(20);                 // set the queue to puzzleQueue with a capacity of 20
    String[][] puzzle;                                  // puzzle 2d array
    ArrayList<Node> allNodes = new ArrayList<>();       // array list to store the nodes
    long rows;                                          // no of rows
    long columns;                                       // no of columns
    static int dimension = 0;                           // dimensions of sliding puzzle is set to 0
    int[] beginNode, endNode;

    /*
     * Defining the sliding puzzle class
     * Takes 2 parameters - file name and the folder name
     */
    public SlidingPuzzle(BufferedReader bufferedReader, String filePath, String folderName) {

        // if the folder name is examples_2
        if(folderName.equals("examples_2")){
            // set the dimensions to 1
            dimension++;
        }
        readFile(bufferedReader);

        // A newPuzzle is created and set to an empty string of the same size as dimension arrays
        String[][] newPuzzle = new String[dimension][dimension];
        try {
            File file = new File(filePath);
            // useDelimiter is used to read the contents of the file
            Scanner scanner = new Scanner(file).useDelimiter("");
            int x = 0;
            int y = 0;

            // loops through the contents of the file
            // reads each character
            // stores it in the currentChar variable
            while (scanner.hasNext())
            {
                String  presentChar = scanner.next();

                // if the currentChar variable is equal to the letter "S"
                // startNode variable is set to an array
                // i represents the current position in the file
                // j represents the the position of the first letter "S"
                if ( presentChar.equals("S")) {
                    beginNode = new int[]{x, y};
                }

                // if the currentChar variable is equal to the letter "F"
                // finishNode variable is set to an array
                // i represents the current position in the file
                // j represents the the position of the last letter "F"
                if ( presentChar.equals("F")) {
                    endNode = new int[]{x, y};
                }

                // if j is less than the dimension of the sliding puzzle
                // sets value of newPuzzle array to the current character
                if (y < dimension) {
                    // stores the current character
                    newPuzzle[x][y] =  presentChar;
                    // positions increment
                    y++;
                }
                else {
                    x++;
                    y = 0;
                    newPuzzle[x][y] =  presentChar;
                }
            }
        } catch (Exception e2)
        {
            System.out.println();
        }

        puzzle = newPuzzle;
        columns = puzzle[0].length;
        rows = puzzle.length;
    }

    /*
     * ifExists method
     * Checks if the toFind variable exists
     */
    public boolean ifExists(Node toFind) {
        for (Node allNode : allNodes) {
            // checks if the coordinates in the toFind variable matches the coordinates in allNode
            if (Arrays.equals(toFind.coordinatesArray, allNode.coordinatesArray)) {
                return true;
            }
        }
        return false;
    }

    /*
     * addIfNew method
     * Adds node
     * Parameters - the node to add, coordinates of the new node, direction of the previous node
     */
    public void addIfNew(Node node, int[] coordinates, String prevNodeDirection) {
        // creates a temp node with the parameters
        Node temp = new Node(coordinates, prevNodeDirection, node);

        // if the node doesn't exist, it's added to the list of nodes and to the next node of the given direction
        // updates all Nodes list with the new node
        if (!ifExists(temp)) {
            node.addNextNode(temp);
            allNodes.add(temp);
        }

    }

    /*
     * getAdjacent method
     * Adds node
     * Parameters - the node to add, coordinates of the new node, direction of the previous node
     */
    public void getAdjacency(Node currentNode) 
    {
        // get coordinates of the current node
        // starts from top left
        int y = currentNode.coordinatesArray[0]; // y coordinate
        int x = currentNode.coordinatesArray[1]; // x coordinate

        // move current node to new location
        int moveLeft = x;
        int moveRight = x;
        int moveUp = y;
        int moveDown = y;

        // moving left
        while (moveLeft > -1) {

            // if left is F
            if (puzzle[y][moveLeft].equals("F")) {
                // assign the value of x coordinate to it
                int[] coordinates = {y, moveLeft};
                // add the new node
                addIfNew(currentNode, coordinates, "left");
            }

            // if left is 0
            if (puzzle[y][moveLeft].equals("0")) 
            {
                // move one to the right
                int[] coordinates = {y, moveLeft + 1};
                // add the new node
                if (moveLeft + 1 != x) {
                    addIfNew(currentNode, coordinates, "left");
                }
                break;
            } 
            else if (moveLeft == 0) {
                
                int[] coordinates = {y, moveLeft};
                if (moveLeft != x) {
                    addIfNew(currentNode, coordinates, "left");
                }
                break;
            }
            // else move left
            moveLeft--;
        }

        // moving up
        while (moveUp > -1) {

            // if up is F
            if (puzzle[moveUp][x].equals("F")) {
                // assign the value of y coordinate to it
                int[] coordinates = {moveUp, x};
                // add the new node
                addIfNew(currentNode, coordinates, "up");
            }

            // if up is 0
            if (puzzle[moveUp][x].equals("0")) {
                // move one down
                int[] coordinates = {moveUp + 1, x};
                // add the new node
                if (moveUp + 1 != y) {
                    addIfNew(currentNode, coordinates, "up");
                }
                break;
            } 
            else if (moveUp == 0) {
                int[] coordinates = {moveUp, x};
                if (moveUp != y) {
                    addIfNew(currentNode, coordinates, "up");
                }
                break;
            }
            // else move up
            moveUp--;
        }

        // until less than the number of columns
        while (moveRight < columns) {

            // move to left if right node has "0"
            if (puzzle[y][moveRight].equals("F")) {
                int[] coordinates = {y, moveRight};
                // add new node
                addIfNew(currentNode, coordinates, "right");
            }

            if (puzzle[y][moveRight].equals("0")) {
                int[] coordinates = {y, moveRight - 1};
                if (moveRight - 1 != x) {
                    addIfNew(currentNode, coordinates, "right");
                }
                break;
            } 
            else if (moveRight == columns - 1) {
                int[] coordinates = {y, moveRight};
                if (moveRight != x) {
                    addIfNew(currentNode, coordinates, "right");
                }
                break;
            }
            // else move right
            moveRight++;
        }

        while (moveDown < rows) {
            if (puzzle[moveDown][x].equals("F")) {
                int[] coordinates = {moveDown, x};
                addIfNew(currentNode, coordinates, "down");

            }
            // move up if down has "0"
            if (puzzle[moveDown][x].equals("0")) {
                int[] coordinates = {moveDown - 1, x};
                if (moveDown - 1 != y) {
                    addIfNew(currentNode, coordinates, "down");
                }
                break;

            } 
            else if (moveDown == rows - 1) {
                int[] coordinates = {moveDown, x};
                if (moveDown != y) {
                    addIfNew(currentNode, coordinates, "down");
                }
                break;
            }
            moveDown++;
        }
    }

    /*
     * Breadth first search algorithm
     */
    public void breadthFirstSearch() {

        // creates a new node object and sets it to start
        Node Start = new Node(beginNode);
        // queues start to list
        queue.enqueue(Start);
        // adds start to allNodes list
        allNodes.add(Start);

        // while the queue is not empty it will dequeue the currentNode and process it
        while (!queue.isEmpty()) {

            Node currentNode = queue.dequeue();

            // checks if the coordinates of the current node is same as the finish node
            if (Arrays.equals(currentNode.coordinatesArray, endNode)) {
                // prints the path to the current node
                getPath(currentNode);
                System.out.println();
                System.out.println("Execution successfully completed!");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                break;
            } 
            else {
                // else get adjacent node of the current node and queue it
                getAdjacency(currentNode);
                queue.enqueueAll(currentNode);
            }
        }
    }

    public void getPath(Node node) {
        if (node.preNode != null) {
            // if previousNode is not null, it gets the path from the current node to the previous node
            getPath(node.preNode);
            // prints to the user to move to the previous node in the direction to the given coordinates
            System.out.println("Move " + node.preNodeDir + " to " + Arrays.toString(node.coordinatesArray));
            t3.append("Move " + node.preNodeDir + " to " + Arrays.toString(node.coordinatesArray) + "\n");
        }
    }

    public void readFile(BufferedReader bufferedReader) {
        // initializing a list to store the lines of the text file
        String line;
        StringBuilder text = new StringBuilder();
        try {
            // read each line and add it to the list
            // increments dimensions by 1
            while((line = bufferedReader.readLine()) != null) {
                dimension++;
                System.out.println(line);
                text.append(line).append('\n');
            }
            t2.setText(text.toString());
        } 
        catch (IOException e) {
            System.out.println("Error occurred during file execution! Please try again...");
        }
    }
}