package com.mycompany.javadomo.Configuration;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Conf {
    public String userpassword;
    public String usermail;
    public String ipAdress;
    public String port;
    public String dbName;
    public String username;
    public String password;

    /**
     * Constructor
     *
     * @throws FileNotFoundException return exception if file not exists
     */
    public Conf() throws IOException {

        Ini ini = getIni();

        this.ipAdress = ini.get("config", "ipAdress");
        this.port = ini.get("config", "port");
        this.dbName = ini.get("config", "dbName");
        this.username = ini.get("config", "username");
        this.password = ini.get("config", "password");
        this.usermail = ini.get("user", "usermail");
        this.userpassword = ini.get("user", "password");
    }

    public void setUserpassword(String userpassword) throws IOException {
        Ini ini = getIni();
        try {
            this.userpassword = ini.put("user", "password", userpassword);
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsermail(String usermail) throws IOException {
        Ini ini = getIni();
        try {
            this.usermail = ini.put("user", "usermail", usermail);
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ini getIni() throws IOException {
        File config = new File("config.ini");
        if (!config.equals("config.ini")) {
            if (!config.exists()) {
                throw new FileNotFoundException("File " + config + " not found !");
            }
        }
        return new Ini(config);
    }
}