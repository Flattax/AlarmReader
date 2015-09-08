/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icalreader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Nils
 */
public class ICalReader {

    /**
     * @param args the command line arguments
     */
    
    BufferedReader bufferedICS;
    FileReader inRead;
    private String line;
    private String[] lines;
    private ArrayList<String> arLines;
    TreeMap<String, String> map;
    
    public ICalReader(){
        map = new TreeMap<String, String>();
        arLines = new ArrayList<String>();
        try{
            FileInputStream in = new FileInputStream("mycalendar.ics");
            inRead = new FileReader("mycalendar.ics");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        bufferedICS = new BufferedReader(inRead);
        if(bufferedICS != null){
            try{
                while((line = bufferedICS.readLine()) != null){
                    arLines.add(line);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void findDates(){
        String[] date;
        for(String line: arLines){
            date = line.split(":");
            if(date[0].equals("DTSTART") && !map.containsKey(date[1].substring(0, 8))){
                map.put(date[1].substring(0,8), date[1].substring(9, 13));
            }
        }
    }
    
    public String toString(){
        return map.toString();
    }
    
    public static void main(String[] args) {
        ICalReader read = new ICalReader();
        read.findDates();
        System.out.println(read.toString());
    }
    
}
