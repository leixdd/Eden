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
package PE.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BlackMoon
 */
public class CFG_Class_Extenstions {

    protected static enum DB_Driver {
        MYSQL, SQLSERVER
    }

    protected static Map<String, String> configuration(DB_Driver Driver, String host, String port, String DB, String username, String password) {
        return new HashMap<String, String>() {
            {
                put("host", host);
                put("port", port);
                put("database", DB);
                put("username", username);
                put("password", password);
                put("driver", String.valueOf(Driver));

                if (Driver.equals(DB_Driver.SQLSERVER)) {
                    put("schema", "dbo");
                }

            }
        };
    }

    protected static Map<String, String> configuration(DB_Driver Driver, String host, String port, String DB, String username, String password, String schema) {
        return new HashMap<String, String>() {
            {
                put("host", host);
                put("port", port);
                put("database", DB);
                put("username", username);
                put("password", password);
                put("driver", String.valueOf(Driver));

                if (Driver.equals(DB_Driver.SQLSERVER)) {
                    put("schema", schema);
                }
            }
        };
    }

}
