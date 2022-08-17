package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GUI {

    static JTextField t = new JTextField("Enter file path here or Click SELECT button...");
    static JTextArea t2 = new JTextArea("Selected File...");
    static JTextArea t3 = new JTextArea("Resolved Path...");
    static SlidingPuzzle puzzle;

    public void startGUI(){

        JFrame f = new JFrame("Sliding Puzzle");

        // create a label to display text
        JLabel l = new JLabel("File Path:");

        // create a new button
        JButton b = new JButton("SELECT");
        b.setPreferredSize(new Dimension(80,30));

        // create a object of JTextField with 16 columns

        t.setPreferredSize(new Dimension(620,30));

        b.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int option = fileChooser.showOpenDialog(f);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getAbsolutePath();
                String folderName = file.getParent().substring(file.getParent().lastIndexOf(File.separator) + 1);
                t.setText(filePath);
                System.out.println("Reading the demo file examples/maze10_1.txt...");
                System.out.println();
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(filePath));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                puzzle = new SlidingPuzzle(bufferedReader,filePath, folderName);
            }else{
                t.setText("Open command canceled");
            }
        });

        JScrollPane st1 = new JScrollPane(t2);
        st1.setPreferredSize(new Dimension(340,400));

        JButton b2 = new JButton("<html>FIND<br>PATH</html>");
        b2.setPreferredSize(new Dimension(70,400));


        JScrollPane st2 = new JScrollPane(t3);
        st2.setPreferredSize(new Dimension(340,400));

        st1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        st1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        st2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        st2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        b2.addActionListener(e -> {
            System.out.println();
            System.out.println("Solving the puzzle...");
            System.out.println();
            t3.setText("Start at [" + puzzle.beginNode[0] + "," + puzzle.beginNode[1] + "]\n");
            puzzle.breadthFirstSearch();
        });

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();

        // add buttons and textfield to panel
        p.add(l);
        p.add(t);
        p.add(b);

        p.add(new JSeparator());

        p.add(st1);
        p.add(b2);
        p.add(st2);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(800, 490);
        f.setResizable(false);

        f.show();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
