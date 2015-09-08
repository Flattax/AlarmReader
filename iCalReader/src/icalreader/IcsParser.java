/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icalreader;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Nils
 */
public class IcsParser {
    
    private String[] splitLine, lines;
    String word;
    
    public IcsParser(){
        splitLine = null;
        lines = null;
        word = null;
    }
    
    //Gets information about the line and how to handle it
    public String getNextDate(String line){
        splitLine = line.split(":");
        return "";
    }
    
}
