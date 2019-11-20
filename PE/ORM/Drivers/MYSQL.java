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
package PE.ORM.Drivers;

import PE.ORM.Interfaces.DB_Driver_Interface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BlackMoon
 */
public class MYSQL implements DB_Driver_Interface{

    private Connection CONNECTION = null;
    private String Host = "jdbc:mysql://";
    private String IP;
    private String PORT;
    private String DATABASE;
    private String USER;
    private String PASSWORD;

    public MYSQL() {
        this.IP = PE.Core.getCoreInstance().getDB_Host();
        this.PORT = PE.Core.getCoreInstance().getDB_Port();
        this.DATABASE = PE.Core.getCoreInstance().getDB_Database();
        this.USER = PE.Core.getCoreInstance().getDB_Username();
        this.PASSWORD = PE.Core.getCoreInstance().getDB_Password();
    }

    @Override
    public DB_Driver_Interface startConnection() {
        try {

            Connection connection = (Connection) DriverManager.getConnection(this.Host + this.IP + ":" + this.PORT + "/" + this.DATABASE, this.USER, this.PASSWORD);
            this.CONNECTION = connection;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return this;

    }

    @Override
    public Boolean hasConnection() {
        try {
            return this.CONNECTION != null && !this.CONNECTION.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return this.CONNECTION;
    }

    @Override
    public Boolean closeConnection() {
        try {
            this.CONNECTION.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
