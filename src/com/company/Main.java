package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static Scanner input = new Scanner(System.in);

    /*
     * Main method
     * Method to display the menu
     * Displays the welcome message
     * Asks for the choice of the user
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("""
                ██ ██ ██ ██ ██ ██ ██ ██     ███████ ██      ██ ██████  ██ ███    ██  ██████      ██████  ██    ██ ███████ ███████ ██      ███████     ██ ██ ██ ██ ██ ██ ██ ██\s
                ██ ██ ██ ██ ██ ██ ██ ██     ██      ██      ██ ██   ██ ██ ████   ██ ██           ██   ██ ██    ██    ███     ███  ██      ██          ██ ██ ██ ██ ██ ██ ██ ██\s
                ██ ██ ██ ██ ██ ██ ██ ██     ███████ ██      ██ ██   ██ ██ ██ ██  ██ ██   ███     ██████  ██    ██   ███     ███   ██      █████       ██ ██ ██ ██ ██ ██ ██ ██\s
                ██ ██ ██ ██ ██ ██ ██ ██          ██ ██      ██ ██   ██ ██ ██  ██ ██ ██    ██     ██      ██    ██  ███     ███    ██      ██          ██ ██ ██ ██ ██ ██ ██ ██\s
                ██ ██ ██ ██ ██ ██ ██ ██     ███████ ███████ ██ ██████  ██ ██   ████  ██████      ██       ██████  ███████ ███████ ███████ ███████     ██ ██ ██ ██ ██ ██ ██ ██\s
                                                                                                                                                                             \s
                                                                                                                                                                             \s""");

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("""
                ║  ╔═╗┌─┐┌─┐┬─┐┌─┐┌┬┐┌─┐┬─┐  ╔╦╗┌─┐┌┐┌┬ ┬  ╔═╗┌─┐┌┬┐┬┌─┐┌┐┌┌─┐  ║
                ║  ║ ║├─┘├┤ ├┬┘├─┤ │ │ │├┬┘  ║║║├┤ ││││ │  ║ ║├─┘ │ ││ ││││└─┐  ║
                ║  ╚═╝┴  └─┘┴└─┴ ┴ ┴ └─┘┴└─  ╩ ╩└─┘┘└┘└─┘  ╚═╝┴   ┴ ┴└─┘┘└┘└─┘  ║""");
        System.out.println("╠═══════════════════════════════════════════════════════════════╣");
        System.out.println("║ > Press '1' to Show a Test Run                                ║");
        System.out.println("║ > Press '2' to Continue in CLI                                ║");
        System.out.println("║ > Press '3' to Continue in GUI                                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        boolean done = false;
        int option;
        while (!done) {
            System.out.println();
            System.out.print("Enter your choice here: ");  //Getting inputs
            try {
                option = input.nextInt();
                System.out.println();
                if(option < 4 && option >= 0){
                    done = true;
                    choice(option);
                }else{
                    System.out.println("Option does not exists. Please try again!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Please only enter Integer inputs!");
                System.out.println();
                input.next();
            }
        }
    }

    /*
     * Choice method
     * Allows the user to chose an option
     */
    static void choice(int option){
        switch (option) {
            case 3 -> {
                GUI newGUI = new GUI();
                newGUI.startGUI();
            }
            case 1 -> {
                //examples/maze10_1.txt
                System.out.println("Reading the test file examples/maze10_1.txt...");
                System.out.println();
                String filepath = "examples/maze10_1.txt";
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(filepath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                SlidingPuzzle puzzle = new SlidingPuzzle(bufferedReader, filepath, "examples");
                System.out.println();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println();
                System.out.println("Solving the puzzle...");
                System.out.println();
                System.out.println("Start at [" + puzzle.beginNode[0] + "," + puzzle.beginNode[1] + "]");
                long startTime = System.nanoTime();
                puzzle.breadthFirstSearch();
                long endTime = System.nanoTime();
                // get the difference between the two nano time values
                long timeElapsed = endTime - startTime;
                System.out.println("Execution time in nanoseconds: " + timeElapsed);
                System.out.println("Execution time in milliseconds: " + TimeUnit.MILLISECONDS.convert(timeElapsed, TimeUnit.NANOSECONDS));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
            case 2 -> {
                boolean done = false;
                while (!done) {
                    try {
                        System.out.print("Please enter the name of the folder where the file exists: ");
                        String folder = input.next();
                        System.out.println();
                        System.out.print("Please enter the name of the file: ");
                        String file2 = input.next();
                        String filepath = folder + "/" + file2 + ".txt";
                        System.out.println();
                        System.out.println("Reading the file " + filepath + "...");
                        System.out.println();
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
                        SlidingPuzzle puzzle2 = new SlidingPuzzle(bufferedReader,filepath, folder);
                        System.out.println();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println();
                        System.out.println("Solving the puzzle...");
                        System.out.println();
                        System.out.println("Start at [" + puzzle2.beginNode[0] + "," + puzzle2.beginNode[1] + "]");
                        long startTime = System.nanoTime();
                        puzzle2.breadthFirstSearch();
                        long endTime = System.nanoTime();
                        // get the difference between the two nano time values
                        long timeElapsed = endTime - startTime;
                        System.out.println("Execution time in nanoseconds: " + timeElapsed);
                        System.out.println("Execution time in milliseconds: " + TimeUnit.MILLISECONDS.convert(timeElapsed, TimeUnit.NANOSECONDS));
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        done = true;

                    } catch (FileNotFoundException e) {
                        System.out.println("File not found! Please try again");
                        System.out.println();
                    }
                }
            }
            default -> System.out.println("Input does not match! Please try again!");
        }
    }
}