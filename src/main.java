/**
 * Created by sb823249 on 05/03/2015.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class main {
    static String[] bits = new String[0];
    static String[] tableHeadings = new String[0];

    public static void main(String[] args) throws IOException {
        readDescription();
    }

    //
    //Reads the description, splitting it up into an arraylist of strings, each element of the array split on ; or .
    //if the element of the arraylist is "Capital letter, full stop, space, lowercase letter", then it keeps that
    //seperate because thats a species name (name bit not implimented yet)
    //
    public static void readDescription() throws IOException {
        // makes a list array from all lines of the specified file
        List<String> descriptiveInput = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\test.txt"), StandardCharsets.UTF_8);
        // for every element in the list array (only goes through once, as there's only one line of the list array. not
        // sure how nessasary this step is
        for (int c = 0; c < descriptiveInput.size(); c++) {
            //checks to ensure that descriptiveInput is only 1 element in size. or else bad things will happen
            if (descriptiveInput.size() > 1){
                System.out.print("your input file did a bad.");
            }
            // the string array called bits is filled with the elements of descriptiveInput, split on ; and .
            // descriptiveInput is only
            //cycled through once, as there's only one element every in descriptive input (hopefully). de
            bits = descriptiveInput.get(c).split(";|\\.");
            //descriptiveInput is then printed to ensure it all works
            System.out.print("Descriptive input is");
            for (int b = 0; b < bits.length; b++) {
                System.out.print("\n" + bits[b]);
            }
        }
        System.out.print("\n  \n");
        readHeadings();
    }

    //reads the database headings file and puts them in a seperate array. other than simply splitting on , only,
    //it uses the same method as before
    public static void readHeadings() throws IOException {
        List<String> tableReader = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"), StandardCharsets.UTF_8);
        for (int c = 0; c < tableReader.size(); c++) {
            tableHeadings = tableReader.get(c).split(",");
            System.out.print("headings are");
            for (int b = 0; b < tableHeadings.length; b++) {
                System.out.print("\n" + tableHeadings[b]);
            }
        }
        System.out.print("\n \n");
        compareEntries();
    }

    //compares the two arrays to see if elements of the description array would work under the headings in the
    //column heading array. a line of text is printed with 'yes' or 'no' for each column heading. A new document
    //'output' is then produced containing the headings, and the line of 'yes' or 'no'.
    public static void compareEntries() throws IOException {
        int get = 0;
        int sizeOne = Array.getLength(tableHeadings);
        int sizeTwo = Array.getLength(bits);
        int sizeThree = 0;
        String adder = "";
        if (sizeOne < sizeTwo) {
            sizeThree = sizeTwo;
        } else if (sizeOne > sizeTwo) {
            sizeThree = sizeOne;
        }
        //creates the string array containing the line containign either the species name, 'yes' or 'no'
        String[] line = new String[tableHeadings.length];
        for (int c = 0; c < tableHeadings.length; c++) {
            for (int b = 0; b < sizeThree; b++) {
                int noColumn = 0;
                if (bits[b].contains("^[A-Z]+$" + "\\. .")){
                    System.out.print("name is " + bits[b] + "!");
                }
                else if (bits[b].contains(" ") && tableHeadings[c].contains(" ")) {
                    boolean added = false;
                    bits[b] = bits[b].replace("- ", " ");
                    String[] bitsBits = bits[b].split(" ");
                    String[] headingsBits = tableHeadings[c].split(" ");
                    int stepperOne = Array.getLength(bitsBits);
                    int stepperTwo = Array.getLength(headingsBits);
                    int stepperThree = 0;
                    String addMore = "";
                    if (stepperOne > stepperTwo) {
                        stepperThree = stepperOne;
                    }
                    if (stepperOne < stepperTwo) {
                        stepperThree = stepperTwo;
                    }
                    if (Arrays.asList(bitsBits).containsAll(Arrays.asList(headingsBits))) {
                        line[c] = "yes";
                        added = true;

                    }
                    else {
                        for (int hello = 0; hello < headingsBits.length; hello++) {
                            if (Arrays.asList(bitsBits).contains(headingsBits[hello])) {
                                get++;
                            }
                            else {
                            }
                        }
                        if (get == 1) {
                            //System.out.print("\nmissing column for " + bits[b]);
                            get = 0;
                        }
                        if (get == 0) {
                            noColumn++;
                        }

                    }
                    if (noColumn != 0) {
                        //System.out.print("\nthere is no column even close for " + bits[b]);
                        noColumn = 0;
                    }
                    if (added = false) {
                        if (line[c] != "yes") {
                            line[c] = "no";
                        }
                    }
                }
                else if (bits[b].contains(tableHeadings[c])) {
                    //System.out.print("\n   state 1 reached where " + bits[b] + " = " + tableHeadings[c] + "   \n");


                    line[c] = "yes";
                }

                else {
                    if (line[c] != "yes") {
                        line[c] = "no";
                    }
                }
            }


            String headders = Arrays.toString(tableHeadings);
            String headint = headders.replace("[", "");
            String headetete = headint.replace(", ", ",");
            String headed = headetete.replace("]", "");
            FileWriter writer = new FileWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\output.csv");
            writer.append(headed + "\n");
            String data = Arrays.toString(line);
            String dataA = data.replace("[", "");
            String dataB = dataA.replace("]", "");
            writer.append(dataB + "\n");
            writer.close();
        }

        System.out.print(Arrays.toString(line));
    }
}