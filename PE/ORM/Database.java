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
package PE.ORM;

import PE.ORM.Interfaces.DB_Driver_Interface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BlackMoon
 */
public class Database {

    private DB_Driver_Interface DB_Connection;

    public Database(String Driver) {
        try {
            PE.Core.getCoreInstance().setDBConfig(Driver);
            this.DB_Connection = ((DB_Driver_Interface) Class.forName("PE.ORM.Drivers." + Driver.toUpperCase()).newInstance()).startConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DB_Driver_Interface getDB() {
        return DB_Connection;
    }

}
