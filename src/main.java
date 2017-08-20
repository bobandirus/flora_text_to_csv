/**
 * Created by sb823249 on 19/02/2015.
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.Pack200;

public class main {
    public static void main(String[] args) throws IOException{
        readData();
        //PrintWriter writer = new PrintWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\test2.txt", "UTF-8");
    }

    public static void readData() throws IOException{
        //|// Reads the file, and puts it into a list of strings
        List<String> input = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\test.txt"), StandardCharsets.UTF_8);
        List<String> database = Files.readAllLines(Paths.get("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv"), StandardCharsets.UTF_8);
        String addOn = String.valueOf("2");
        //System.out.print(addOn);
        //System.out.println(input);
        //|// for an increasing c thats less than the size of the list
        for (int c = 0; c < input.size(); c++){
            //System.out.println(input);
            //String[] bits = input.get(c).split(";");
            //|// split input on ';' and '.', putting the results into a string array
            String[] bits = input.get(c).split(";|\\.");
            //|// for an increasing b thats less than bits length
            //System.out.println(bits);
            for (int b = 0; b < bits.length; b++){
                //print the bits of bits
                //System.out.print(bits[b] + "\n");
                if (bits[b] == String.valueOf("Mediterranean")){
                    System.out.print("hurrah!");
                }
            }


      /*      //
            //
            // THIS IS A MASSIVE MESS. IT MIGHT HAVE THE RIGHT IDEA IN IT,
            // BUT NOT YET. maybe re-write while actually thinking about whats going on?
            //
            //
            //
            //for (int h = 0; h < bits.length; h++){
                for (int f = 0; f < database.size(); f++){
                    String[] dataBits = database.get(f).split(",");
                    System.out.print(Arrays.toString(dataBits));
                    for (int q = 0; q < dataBits.length; q++){
                        if (bits[f] == dataBits[q]){
                            //System.out.print(dataBits[f]);
                            String yerp = "222";// + addOn + bits[f] + ",";
                            System.out.print(yerp);
                            database.add(yerp);
                            database.add("3");
                            PrintWriter dataWriter = new PrintWriter("C:\\Users\\sb823249\\Google Drive\\PhD!\\OCR thoughts\\ocrd text to database\\test one\\database.csv", "UTF-8");
                            dataWriter.print(database);
                            dataWriter.close();
                        }
                    }
                }
            //}

            */

            for (int z = 0; z < database.size(); z++){
                String[]dataBits = database.get(z).split(",");
                for (int h = 0; h < dataBits.length; h++){
                    System.out.print(dataBits[z]);
                    if (bits[h].contains(dataBits[z])){
                        System.out.print(dataBits[z]);
                        System.out.print("hello");
                    }
                }
            }
        }
    }
}
