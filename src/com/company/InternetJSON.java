package com.company;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Created by John Drake on 2/5/2017.
 */
public class InternetJSON {
    public static String json = "";
    public static void toFile(){
        try {
            File storedJson = new File("C:\\Users\\John Drake\\Desktop\\Code\\CS 126 Projects\\MovieParser\\Test\\com\\company\\DownloadedJSON");
            if (storedJson.exists()) {
                //delete if exists
                storedJson.delete();
            }
            FileWriter out = new FileWriter(storedJson);
            out.write(json);
            out.close();
        } catch (FileNotFoundException e){
            System.out.print("No FIle Found");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
