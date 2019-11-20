/*
 * The MIT License
 *
 * Copyright 2019 BlackMoon.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package PE;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BlackMoon
 */
public class Core {

    /**
     * Database Configurations
     */
    private String DB_Driver;
    private String DB_Host;
    private String DB_Port;
    private String DB_Username;
    private String DB_Password;
    private String DB_Database;
    private String DB_Schema;

    public String getDB_Driver() {
        return DB_Driver;
    }

    public String getDB_Host() {
        return DB_Host;
    }

    public String getDB_Password() {
        return DB_Password;
    }

    public String getDB_Port() {
        return DB_Port;
    }

    public String getDB_Username() {
        return DB_Username;
    }

    public String getDB_Database() {
        return DB_Database;
    }

    public String getDB_Schema() {
        return DB_Schema;
    }
    
    
    

    public void setDBConfig(String DDB) {

        
//        for (Map.Entry<String, Map<String, String>> entry : CFGS.db_Config.entrySet()) {
//            System.out.println(entry.getKey());
//            for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
//                System.out.printf("%s = %s\n", entry2.getKey(), entry2.getValue());
//            }
//        }

        //Get the Default DB
        Map<String, String> dbConfig = Config.DatabaseConfig.db_Config.get(DDB);
        this.DB_Driver = dbConfig.get("driver");
        this.DB_Host = dbConfig.get("host");
        this.DB_Password = dbConfig.get("password");
        this.DB_Username = dbConfig.get("username");
        this.DB_Port = dbConfig.get("port");
        this.DB_Database = dbConfig.get("database");
        this.DB_Schema = dbConfig.getOrDefault("schema", "");
        
    }

    private static class CoreInstance {

        private static final Core coreInstance = new Core();
    };

    private Core() {
    }

    public static Core getCoreInstance() {
        return CoreInstance.coreInstance;
    }

}
