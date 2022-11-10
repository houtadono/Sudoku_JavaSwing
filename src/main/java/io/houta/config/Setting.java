package io.houta.config;

import java.util.*;

import io.houta.model.Setting1;
import io.houta.model.Setting3;

import java.io.*;

public class Setting {
    public static Properties properties = null;
    //set1

    //set2

    //set3
    public static void readFileSetting(){
        FileReader reader ;
        try {
            reader = new FileReader(new File("userSetting.properties") );
            properties = new Properties();
            try {
                properties.load(reader);
            }catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            try {
                reader = new FileReader( System.getProperty("user.dir") + "/sudoku/src/main/java/io/houta/config/defaultSetting.properties" );
                properties = new Properties();
                try {
                    properties.load(reader);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Setting1 getSetting1(){
        return new Setting1(
            Boolean.parseBoolean(properties.getProperty( "stopwatch")),
            Boolean.parseBoolean(properties.getProperty( "errorChecking")),
            Boolean.parseBoolean(properties.getProperty( "markDuplicateNumbers")),
            Boolean.parseBoolean(properties.getProperty( "markZone"))
        );
    }

    public static Setting3 getSetting3(){
        return new Setting3(properties.getProperty("colorBackgroundDefaultCell"),
                            properties.getProperty("colorBackgroundClickCell"),
                            properties.getProperty("colorBackgroundSelectZone"),
                            properties.getProperty("colorBackgroundSameValue"),
                            properties.getProperty("colorForegroundDefaultCell"),
                            properties.getProperty("colorForegroundAddCell"),
                            properties.getProperty("colorForegroundErrorCell"),
                            properties.getProperty("colorForegroundSolutionCell")
        );
    }

    public static void saveSetting1( ){
           
    }

    public static void saveChange(){
        try {
            properties.store(new FileWriter("userSetting.properties"), "New");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Setting.readFileSetting();
        // Setting.saveChange();
    }
}
