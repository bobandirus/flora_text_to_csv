/**
 * Created by sb823249 on 02/03/2015.
 */

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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

    public static void readDescription() throws IOException {
        List<String> descriptiveInput = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\test.txt"), StandardCharsets.UTF_8);
        for (int c = 0; c < descriptiveInput.size(); c++) {
            bits = descriptiveInput.get(c).split(";|\\.");
            System.out.print("Descriptive input is");
            for (int b = 0; b < bits.length; b++) {
                System.out.print("\n" + bits[b]);
            }
        }
        System.out.print("\n  \n");
        readHeadings();
    }

    public static void readHeadings() throws IOException{
        List<String> tableReader = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"), StandardCharsets.UTF_8);
        for (int c = 0; c < tableReader.size(); c++){
            tableHeadings = tableReader.get(c).split(",");
            System.out.print("headings are");
            for (int b = 0; b < tableHeadings.length; b++){
                System.out.print("\n" + tableHeadings[b]);
            }
        }
        System.out.print("\n \n");
        compareEntries();
    }

    /*public static void compareEntries() throws IOException{
        System.out.print("1");
        for (int c = 0; c < Math.max(tableHeadings.length, bits.length); c++){
            System.out.print("2");
            if (bits[c].contains(tableHeadings[c])){
                System.out.print("3");
                System.out.print("yay");
            }
        }
    }*/

    public static void compareEntries() throws IOException{
        int get= 0;
        int sizeOne = Array.getLength(tableHeadings);
        int sizeTwo = Array.getLength(bits);
        int sizeThree = 0;
        String adder = "";
        if (sizeOne < sizeTwo){
            sizeThree = sizeTwo;
        }
        else if (sizeOne > sizeTwo){
            sizeThree = sizeOne;
        }
        String[] line = new String[tableHeadings.length];
        for (int c = 0; c < tableHeadings.length; c++){
            for (int b = 0; b < sizeThree; b++){
                int noColumn = 0;
                //for (int c = 0; c < sizeThree; c++){
                //    for (int b = 0; b < tableHeadings.length; b++){
             /*   if (bits[b].contains(" ")){
                    //System.out.print(bits[b]);
                    String[] eachSection = new String[bits[b].length()];
                    eachSection = bits[b].split(" ");
                    System.out.print(Arrays.toString(eachSection));
                    if (tableHeadings[c].contains(" ")){
                        //String[] specificHeading = new String[tableHeadings[c].split(" ")];
                        String breaker = tableHeadings[c];
                        String[] headingSpecific = breaker.split(" ");
                    }
                    for (int t = 0; t < eachSection.length; t++){
                        if (eachSection[t].contains(tableHeadings[c])){
                            //System.out.print(eachSection[t]);
                            for (int w = 0; w < eachSection.length; w++){
                                if (eachSection[t].contains(tableHeadings[c]) && eachSection[w].contains(tableHeadings[c])){
                                    System.out.print(eachSection[t] + eachSection[c]);
                                }
                            }
                        }
                    }
                }

             */
                if (bits[b].contains(" ") && tableHeadings[c].contains(" ")){
                    boolean added = false;
                    bits[b] = bits[b].replace("- ", " ");
                    String[] bitsBits = bits[b].split(" ");
                    String[] headingsBits = tableHeadings[c].split(" ");
                    //bitsBits = bitsBits.re
                    int stepperOne = Array.getLength(bitsBits);
                    int stepperTwo = Array.getLength(headingsBits);
                    int stepperThree = 0;
                    String addMore = "";
                    if (stepperOne > stepperTwo){
                        stepperThree = stepperOne;
                    }
                    if (stepperOne < stepperTwo){
                        stepperThree = stepperTwo;
                    }
                    ////System.out.print(Arrays.toString(bitsBits) + Arrays.toString(headingsBits));
                    if (Arrays.asList(bitsBits).containsAll(Arrays.asList(headingsBits))){
                        line[c] = "yes";
                        //System.out.print("          got to a              ");
                        added = true;

                    }
                    else {
                        for (int hello = 0; hello < headingsBits.length; hello++){
                            if (Arrays.asList(bitsBits).contains(headingsBits[hello])){
                                get++;
                            }
                            else{
                            }
                        }
                        if (get == 1){
                            System.out.print("\nmissing column for " + bits[b]);
                            get = 0;
                        }
                        if (get == 0){
                            //System.out.print("\nno column for " + bits[b] + " where headings = " + tableHeadings[c]);
                            noColumn++;
                        }
                    }
                    if (noColumn != 0){
                        System.out.print("\nthere is no column even close for " + bits[b]);
                        noColumn = 0;
                    }
                    //else if (Arrays.asList(bitsBits)!containsAll(Arra)
                  /*

                  //THIS WAS ATTEMPT ONE AT THE LOOP DESCRIBED BELOW (I THINK)

                    else if (for (int h = 0; h < bitsBits.length; h++)){
                        if (Arrays.asList(headingsBits).contains(bitsBits[h])){
                            get++;
                        }
                        if (get == 1){
                            System.out.print("Column missing where " + bitsBits[h]);
                        }
                        else {
                            get = 0;
                        }
                    }

                  */
              /*
                 //
                 //
                 //THIS WAS ATTEMPT TWO AT THIS PART. THE HOPE WAS TO OUTPUT WHERE THE HEADING HAD
                 //ONE OF THE WORDS IN SECTION OF DESCRIPTION, BUT NOT THE OTHER. THIS WAS SO YOU COULD
                 //SEE WHERE THERE WERE MISSING HEADINGS IS A COHERENT MANNER
                 //
                 //
                 //
                    else {
                        for (int h = 0; h < bitsBits.length; h++) {
                            if (Arrays.asList(headingsBits).contains(bitsBits[h])) {
                                get++;
                                added = true;
                            }
                            if (get == 1) {
                                String helpImLost = "";
                                //System.out.print("\n\n\n\n\n\nColumn missing where " + bitsBits[h] + " is " + "\n\n\n\n\n");
                                for (int e = 0; e < bitsBits.length; e++){
                                    //if (Arrays.asList(headingsBits).contains(bitsBits[e])){
                                    //    System.out.print("\n\n\n\n" + bitsBits[e] + "\n\n\n");
                                    //}
                                   // for (int y = 0; y < headingsBits.length; y++){
                                   //     if
                                    if (Arrays.asList(headingsBits).contains(bitsBits[e])){
                                        helpImLost = helpImLost + bitsBits[e];
                                        for (int z = 0; z < bitsBits.length; z++){
                                            if (z != e){
                                                if (Arrays.asList(headingsBits).contains(bitsBits[z])){
                                                    helpImLost = helpImLost + bitsBits[z];
                                                    System.out.print("\n\n its where " + bitsBits[z]);
                                                }
                                            }
                                        }
                                    }
                                    System.out.print("\n\n error with " + bitsBits[e]);
                                }
                                System.out.print("\n\n\n\n\n" + helpImLost +  "\n\n\n\n\n\nreached here\n\n\n");
                                get = 0;
                            } else {
                                get = 0;
                            }
                        }
                    }
             */

                    if (added = false){
                        if (line[c] != "yes"){
                            line[c] = "no";
                        }
                    }
                  /*  else ({
                        //line[c] = "no";
                        //System.out.print("got to b");
                        if (line[c] != "yes"){
                            line[c] = "no";
                        }
                    }
                  */
                    //for (int s = 0; s < headingsBits.length; s++){
                    //    for (int m = 0; m < stepperThree; m++){
                    //    }
                    //}
                  /*  for (int s = 0; s < stepperThree; s++){
                        for (int d = 0; d < headingsBits.length; d++){
                            for (int n = 0; n < stepperThree; n++){
                                if (bitsBits[n].contains()
                            }
                        }
                    }
                  */
                }
                if (bits[b].contains(tableHeadings[c])){
                    System.out.print("\n   state 1 reached where " + bits[b] + " = " + tableHeadings[c] + "   \n");
                    //adder = adder + "yes,";
                    line[c] = "yes";
                }
                else {
                    //adder = adder + "no,";
                    //line[c] = "no";
                    if (line[c] != "yes"){
                        line[c] = "no";
                    }
                }
                /*else if (bits[b] == "0"){
                    System.out.print("bits[b] is 0 for some reason");
                }
                else {
                    System.out.print("um");
                }*/
            }
            //FileReader original = new FileReader("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv");
            //String headders = String.valueOf(original);
            //String headders = original.getText().toString();
            //String headders = new File("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv").toString();
            //String headders = File("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv").getText.toString();
            //String headders = String.valueOf(original.getEncoding());
            //String headders = readFile("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv", StandardCharsets.UTF_8);
            //List<String> tableReader = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"), StandardCharsets.UTF_8);
            //String headders = Arrays.toString(tableReader);
            String headders = Arrays.toString(tableHeadings);
            String headint = headders.replace("[", "");
            String headetete = headint.replace(", ", ",");
            String headed = headetete.replace("]", "");
            //System.out.print(headed);
            FileWriter writer = new FileWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\output.csv");
            //writer.append(Files.copy("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"));
            //writer.append(headders + "\n");
            writer.append(headed + "\n");
            String data = Arrays.toString(line);
            String dataA = data.replace("[", "");
            String dataB = dataA.replace("]", "");
            //String dataC = dataB.replace(", ", "");
            writer.append(dataB + "\n");
            //writer.append(adder + "\n");
            writer.close();
        }
        System.out.print(Arrays.toString(line));
    }
    /*FileWriter writer = new FileWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv");
    writer.append("," + bits[b]);
    writer.close();*/
}