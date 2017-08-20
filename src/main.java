/**
 * Created by sb823249 on 11/03/2015.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    static String[] bits = new String[0];
    static String[] tableHeadings = new String[0];
    static String fileName = "";
    static String lineToAdd = "";

    public static void main(String[] args) throws IOException{
        int fileNumber = 0;
        for (int c = 1; c < 4; c++){
            fileNumber = c;
            fileName = "C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\" + fileNumber + ".txt";
            readDescription();
        }
        writeFile();
        fileName = "";
    }

    public static void readDescription() throws IOException{
        //List<String> descriptiveInput = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\1.txt"), StandardCharsets.UTF_8);
        List<String> descriptiveInput = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        for (int c = 0; c < descriptiveInput.size(); c++){
            if (descriptiveInput.size() > 1){
                System.out.print("your file did a bad");
            }
            String[] preBitsOne = descriptiveInput.get(c).split(";");
            ArrayList<String> bitsAdder = new ArrayList<String>();
            for (int b = 0; b < preBitsOne.length; b++){
                Pattern speciesName = Pattern.compile("\\. [A-Z]\\. [a-z]");
                Matcher speciesPattern = speciesName.matcher(preBitsOne[b]);
                if (speciesPattern.find()){
                    bitsAdder.add(preBitsOne[b]);
                }
                else {
                    String[] addToAdder = preBitsOne[b].split("\\.");
                    for (int r = 0; r < addToAdder.length; r++){
                        String addingToAdder = addToAdder[r];
                        //System.out.print(addingToAdder);
                        bitsAdder.add(addingToAdder);
                    }
                }
            }
            bits = bitsAdder.toArray(bits);
            for (int q = 0; q < bits.length; q++){
                System.out.print(bits[q] + "\n");
            }
            //System.out.print(Arrays.toString(bits) + "\n");
        }
        readHeadings();
    }

    public static void readHeadings() throws IOException{
        List<String> tableReader = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"), StandardCharsets.UTF_8);
        for (int c = 0; c < tableReader.size(); c++){
            tableHeadings = tableReader.get(c).split(",");
        }
        compareEntries();
    }

    public static void compareEntries() throws IOException {
        Boolean firstTime = true;
        int get = 0;
        int sizeOne = Array.getLength(tableHeadings);
        int sizeTwo = Array.getLength(bits);
        int sizeThree = 0;
        String adder = "";
        ArrayList<String> buildingLine = new ArrayList<String>();
        String[] line = new String[tableHeadings.length];
        if (sizeOne < sizeTwo) {
            sizeThree = sizeTwo;
        } else if (sizeOne >= sizeTwo) {
            sizeThree = sizeOne;
        }
        for (int c = 0; c < tableHeadings.length; c++){
            for (int b = 0; b < sizeThree; b++){
                Pattern speciesName = Pattern.compile("\\. [A-Z]\\. [a-z]");
                Matcher speciesPattern = speciesName.matcher(bits[b]);
                if (firstTime && speciesPattern.find()){
                    line[c] = bits[b];
                    firstTime = false;
                }
                else if (bits[b].contains(" ") && tableHeadings[c].contains(" ")){
                    boolean added = false;
                    //add replace charictor with blank here to get rid of punctuation which'll confuse the reader
                    //It all needs to be gone if its joined onto a letter. EG "linear," or "trimporphic- "
                    bits[b] = bits[b].replace("- ", " ");
                    bits[b] = bits[b].replace(",", "");
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
                    if (Arrays.asList(bitsBits).containsAll(Arrays.asList(headingsBits))){
                        line[c] = "yes";
                        added = true;
                    }
                    else {
                        for (int a = 0; a < headingsBits.length; a++){
                            if (Arrays.asList(bitsBits).contains(headingsBits[a])){
                                System.out.print("collumn missing for " + bits[b] + "\n");
                                line[c] = "no";
                            }
                        }
                    }
                    if (added = false){
                        if (line[c] != "yes"){
                            line[c] = "no";
                        }
                    }
                }
                else if (bits[b].contains(tableHeadings[c])){
                    line[c] = "yes";
                }

                else {
                    //if (line[c] != "yes"){
                    if (line[c] == null){
                        line[c] = "no";
                    }
                }
            }
        }
        /*
        System.out.print(Arrays.toString(line));
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
        System.out.print("\n" + Arrays.toString(tableHeadings) + "\n");
        System.out.print(Arrays.toString(line));
        */
        firstTime = true;
        lineAdder(line);
        //argTest(line);
    }

    public static void lineAdder(String[] line) throws IOException{
        String data = Arrays.toString(line);
        String dataA = data.replace("[", "");
        String dataB = dataA.replace("]", "");
        lineToAdd = lineToAdd + dataB + "\n";
        bits = new String[0];
    }

    public static void writeFile() throws IOException{
        FileWriter writer = new FileWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\output.csv");
        String headders = Arrays.toString(tableHeadings);
        String headint = headders.replace("[", "");
        String headetete = headint.replace(", ", ",");
        String headed = headetete.replace("]", "");
        writer.append(headed + "\n");
        writer.append(lineToAdd + "\n");
        writer.close();
    }

    /*
    public static void argTest(String [] line) throws IOException{
        System.out.print(Arrays.toString(line));
    }
    */


}

