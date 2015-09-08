/*
 * Creates object loading and reading a given ics file, finding earliest appointment
 * of every day with events in ics file.
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
    
    private BufferedReader bufferedICS;
    private FileReader inRead;
    private String line;
    private String[] lines;
    private ArrayList<String> arLines;
    TreeMap<String, String> map;
    
    /**
     * Constructor for ICalReader. Reads chosen .ics file, name given in code.
     * Successful reading creates ArrayList containing data from .ics file,
     * each object containing a row from .ics file.
     */
    public ICalReader(){
        map = new TreeMap<String, String>();
        arLines = new ArrayList<String>();
        try{
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
    
    /**
     * Finds every date which has a booked event, choosing earliest possible 
     * event. Date and time for event is stored in TreeMap, only one earliest
     * event allowed per day.
     */
    public void findDates(){
        String[] date;
        for(String line: arLines){
            date = line.split(":");
            if(date[0].equals("DTSTART") && !map.containsKey(date[1].substring(0, 8))){
                map.put(date[1].substring(0,8), date[1].substring(9, 13));
            }
        }
    }
    /**
     * Overrides toString, using TreeMap built in toString
     * @return String representation of TreeMap containing date and time for event.
     */
    @Override
    public String toString(){
        return map.toString();
    }
    
    /**
     * Returns a TreeMap mapping every date with a planned event and earliest
     * time for a event in case of there being a event.
     * @return TreeMap containing date and time pair as strings.
     */
    public TreeMap<String, String> getTimeDate(){
        return map;
    }
    
    public static void main(String[] args) {
        ICalReader read = new ICalReader();
        read.findDates();
        System.out.println(read.toString());
    }
    
}
