package com.mycompany.javadomo.Configuration;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Cache {

    public String userFirstName;
    public String userLastName;
    public String userRole;
    public String userHome;
    public String clock;

    /**
     * Method to create default configuration
     */
    public Cache() throws IOException {

        Ini cache = getCache();

        this.userFirstName = cache.get("user", "userFirstName");
        this.userLastName = cache.get("user", "userLastName");
        this.userRole = cache.get("user", "userRole");
        this.userHome = cache.get("user", "userHome");
        this.clock = cache.get("time","clock");
    }

    public static void generateCache(){

        try {
            FileWriter myWriter = new FileWriter("users.cache");
            myWriter.write("[user]\n");
            myWriter.write("userFirstName = \n");
            myWriter.write("userLastName = \n");
            myWriter.write("userRole = \n");
            myWriter.write("userHome = 0\n");
            myWriter.write("[time]\n");
            myWriter.write("clock = \n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred to create init file.");
            e.printStackTrace();
        }
    }
    public void updateClock(String time) throws IOException {
        /*try {
            FileWriter myWriter = new FileWriter("users.cache");
            myWriter.write("[time]\n");
            myWriter.write("clock = "+ time + "");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred to create init file.");
            e.printStackTrace();
    }*/

        Ini cache = getCache();
        try {
            this.clock = cache.put("time", "clock", time);
            cache.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCache() throws IOException {
        Ini cache = getCache();
        cache.getFile().deleteOnExit();
    }

    public void setUserFirstName(String userFirstName) throws IOException {
        Ini cache = getCache();
        try {
            this.userFirstName = cache.put("user", "userFirstName", userFirstName);
            cache.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserLastName(String userLastName) throws IOException {
        Ini cache = getCache();
        try {
            this.userLastName = cache.put("user", "userLastName", userLastName);
            cache.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserRole(String userRole) throws IOException {
        Ini cache = getCache();
        try {
            this.userRole = cache.put("user", "userRole", userRole);
            cache.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserHome(int userHome) throws IOException {
        Ini cache = getCache();
        try {
            this.userHome = cache.put("user", "userHome", Integer.toString(userHome));
            cache.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ini getCache() throws IOException {
        File cache = new File("users.cache");
        if (!cache.equals("users.cache")) {
            if (!cache.exists()) {
                throw new FileNotFoundException("File " + cache + " not found !");
            }
        }



        return new Ini(cache);
    }

}
