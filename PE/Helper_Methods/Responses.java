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
package PE.Helper_Methods;

/**
 *
 * @author BlackMoon
 */
public class Responses {

    public static enum SYORM_Cause {
        ORM_WHERE,
        ORM_SELECT,
        ORM_INSERT,
        ORM_DELETE,
        ORM_UPDATE,
        ORM_JOIN,
        ORM_ORDERBY,
        ORM_GROUPBY
    }
    
    public static String ORM_ERR_01 = "Where values must 3 values.. \nExample \n { { \"column\", \"=\", \"'example'\"}, { \"column2\", \"=\", \"'example2'\"} }";
    public static String ORM_ERR_02 = "Insert values must 2 values.. \nExample \n { { \"column\", \"'example'\"}, { \"column2\", \"'example2'\"} }";

}
