/**
 * Created by sb823249 on 19/03/2015.
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
    static boolean familyCount = true;
    static boolean genusCount = true;
    static boolean speciesCount = true;

    public static void main(String[] args) throws IOException {

        int fileNumber = 0;
        for (int c = 1; c < 40; c++) {
            fileNumber = c;
            //fileName = "" + System.getProperty("user.dir") + "\\text post adaptations\\" + fileNumber + ".txt";
            fileName = "" + System.getProperty("user.dir") + "\\stace text mk1.txt";
            readDescription();
        }
        writeFile();
        fileName = "";

        //firstStep();
    }

    /*public static void firstStep() throws IOException{
        int fileNumber = 0;
        for (int c = 1; c < 40; c++) {
            fileNumber = c;
            //fileName = "" + System.getProperty("user.dir") + "\\text post adaptations\\" + fileNumber + ".txt";
            fileName = "" + System.getProperty("user.dir") + "\\stace text mk1.txt";
            //readDescription();
        }
        writeFile();
        fileName = "";
    }*/

    public static void readDescription() throws IOException {
        //List<String> descriptiveInput = Files.readAllLines(Paths.get(fileName), StandardCharsets.ISO_8859_1);
        List<String> descriptiveInput = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        String[] input = new String[descriptiveInput.size()];
        input = descriptiveInput.toArray(input);
        for (int c = 0; c < input.length; c++) {
            String[] preBitsOne = descriptiveInput.get(c).split(";");
            ArrayList<String> bitsAdder = new ArrayList<String>();
            for (int b = 0; b < preBitsOne.length; b++) {
                Pattern familyName = Pattern.compile("\\. \\.*ACEAE");
                Pattern genusName = Pattern.compile("\\. [A-Z][A-Z][A-Z][A-Z][A-Z]");
                Pattern speciesName = Pattern.compile("\\. [A-Z]\\. [a-z]");
                Matcher familyPattern = familyName.matcher(preBitsOne[b]);
                Matcher genusPattern = genusName.matcher(preBitsOne[b]);
                Matcher speciesPattern = speciesName.matcher(preBitsOne[b]);
                if (familyPattern.find()) {
                    bitsAdder.add(preBitsOne[b]);
                } else if (genusPattern.find()) {
                    bitsAdder.add(preBitsOne[b]);
                } else if (speciesPattern.find()) {
                    bitsAdder.add(preBitsOne[b]);
                } else {
                    String[] addToAdder = preBitsOne[b].split("\\.");
                    for (int r = 0; r < addToAdder.length; r++) {
                        String addingToAdder = addToAdder[r];
                        bitsAdder.add(addingToAdder);
                    }
                }
            }
            bits = bitsAdder.toArray(bits);
            for (int q = 0; q < bits.length; q++) {
                //System.out.print(bits[q] + "\n");
            }
        }
        readHeadings();
    }

    public static void readHeadings() throws IOException {
        List<String> tableReader = Files.readAllLines(Paths.get("" + System.getProperty("user.dir") + "\\database.csv"), StandardCharsets.UTF_8);
        for (int c = 0; c < tableReader.size(); c++) {
            tableHeadings = tableReader.get(c).split(",");
        }
        compareEntries();
    }

    public static void compareEntries() throws IOException {
        Boolean speciesFirst = true;
        Boolean genusFirst = true;
        Boolean familyFirst = true;
        String adder = "";
        ArrayList<String> buildingLine = new ArrayList<String>();
        String[] line = new String[tableHeadings.length];
        for (int c = 0; c < tableHeadings.length; c++) {
            boolean familyHeading = false;
            boolean genusHeading = false;
            boolean speciesHeading = false;
            String family = "Family";
            String genus = "Genus";
            String species = "Name";
            if (tableHeadings[c].equals(family)) {
                familyHeading = true;
            }
            if (tableHeadings[c].equals(genus)) {
                genusHeading = true;
            }
            if (tableHeadings[c].equals(species)) {
                speciesHeading = true;
            }
            bitsChecker:
            for (int b = 0; b < bits.length; b++) {
                bits[b] = bits[b].replace(",", "");
                Boolean familyFound = false;
                Boolean genusFound = false;
                Boolean speciesFound = false;
                String[] bitsOne = bits[b].split(" - ");
                nameFinder:
                for (int n = 0; n < bitsOne.length; n++) {
                    String part = bitsOne[n];
                    Pattern genuSs = Pattern.compile("[A-Z][A-Z][A-Z]");
                    Matcher genusMatcher = genuSs.matcher(part);
                    Pattern specieSs = Pattern.compile("\\. [A-Z]\\. [a-z]");
                    Matcher speciesMatcher = specieSs.matcher(part);
                    if (part.endsWith("ACEAE")) {
                        familyFound = true;
                        break nameFinder;
                    } else if (genusMatcher.find()) {
                        genusFound = true;
                        break nameFinder;
                    } else if (speciesMatcher.find()) {
                        speciesFound = true;
                        break nameFinder;
                    }
                }
                if ((familyFound) && familyHeading) {
                    line[c] = bits[b];
                    speciesFirst = false;
                    break bitsChecker;
                } else if (genusFound && genusHeading) {
                    line[c] = bits[b];
                    genusFirst = false;
                    genusFound = false;
                    break bitsChecker;
                } else if (speciesFound && speciesHeading) {
                    line[c] = bits[b];
                    speciesFirst = false;
                    speciesFound = false;
                    break bitsChecker;
                } else if (bits[b].contains(" ") && tableHeadings[c].contains(" ")) {
                    boolean added = false;
                    bits[b] = bits[b].toLowerCase();
                    tableHeadings[c] = tableHeadings[c].toLowerCase();
                    bits[b] = bits[b].replace("- ", " ");
                    bits[b] = bits[b].replace(",", "");
                    String[] bitsBits = bits[b].split(" ");
                    String[] headingsBits = tableHeadings[c].split(" ");
                    if (Arrays.asList(bitsBits).containsAll(Arrays.asList(headingsBits))) {
                        line[c] = "yes";
                        added = true;
                        break bitsChecker;
                    } else {
                        line[c] = "no";
                        System.out.print("Column missing for " + bits[b] + "\n");
                    }
                    if (added = false) {
                        if (line[c] != "yes") {
                            line[c] = "no";
                        }
                    }
                } else if (bits[b].contains(tableHeadings[c])) {
                    line[c] = "yes";
                    break bitsChecker;
                } else {
                    if (line[c] == null) {
                        System.out.print("collumn missing for " + bits[b] + "\n");
                        line[c] = "no";
                    }
                }
            }
        }
        lineAdder(line);
    }

    public static void lineAdder(String[] line) throws IOException {
        String data = Arrays.toString(line);
        String dataA = data.replace("[", "");
        String dataB = dataA.replace("]", "");
        lineToAdd = lineToAdd + dataB + "\n";
        bits = new String[0];
    }

    public static void writeFile() throws IOException {
        FileWriter writer = new FileWriter("" + System.getProperty("user.dir") + "\\output.csv");
        String headders = Arrays.toString(tableHeadings);
        String headint = headders.replace("[", "");
        String headetete = headint.replace(", ", ",");
        String headed = headetete.replace("]", "");
        writer.append(headed + "\n");
        writer.append(lineToAdd + "\n");
        writer.close();
    }
}
