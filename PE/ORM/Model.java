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

import PE.Core;
import PE.Helper_Methods.*;
import PE.Helper_Methods.Responses.SYORM_Cause;
import PE.ORM.Interfaces.DB_Driver_Interface;
import PE.ORM.Interfaces.DB_METHODS;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author BlackMoon
 */
public abstract class Model implements DB_METHODS {

    protected String query = "SELECT ";

    protected String whereConstruct = "";
    protected String joinConstruct = "";

    protected String orderBy = "";
    protected String groupBy = "";

    protected Boolean isWhereSet = false;
    protected Boolean isJoinedSet = false;
    protected Boolean isSelectColumnSet = false;

    protected ArrayList<Object> finalValues = new ArrayList<>();
    protected ArrayList<Object> whereValues = new ArrayList<>();
    protected String[] SelectColumn;

    protected String current_table = "No Table";

    protected PreparedStatement pst;

    protected String connection = new Config.Env().getDefault_database();
    protected DB_Driver_Interface DB;

    public void startDBConnection() {
        this.DB = (new Database(this.connection)).getDB(); //setup the connection
        this.current_table = (Core.getCoreInstance().getDB_Driver().toUpperCase().equals("SQLSERVER") ? Core.getCoreInstance().getDB_Schema() + "." + this.current_table : this.current_table);
    }

    public void setDriver(String driver) {
        this.connection = driver;
    }

    public void setCurrent_table() {
        this.current_table = (Core.getCoreInstance().getDB_Driver().toUpperCase().equals("SQLSERVER") ? Core.getCoreInstance().getDB_Schema() + "." + this.current_table : this.current_table);
    }

    public String getCurrent_table() {
        return current_table;
    }

    public String getTable() {
        return this.getCurrent_table();
    }

    /**
     * ===========================================================
     *
     * @name: Get procedures
     * @desc: SELECT & JOINS
     * ===========================================================
     */
    /**
     * [SELECT]
     *
     * @param columns selecting certain columns
     * @return Will return the model class
     */
    public Model select(String... columns) {
        this.SelectColumn = columns;
        this.isSelectColumnSet = true;

        return this;
    }

    /**
     * [Get Method]
     *
     * <p>
     * Executes the Select query of SQL
     *
     * <p>
     * example: {@code new User().get(); }
     *
     * @return List of HashMaps that contains the result set of the query
     */
    @Override
    public List get() {

        this.startDBConnection();

        try {

            this.query += (isSelectColumnSet ? Utilities.combine(this.SelectColumn, ",") : "*") + " FROM " + getCurrent_table() + (this.isWhereSet ? " WHERE " + this.whereConstruct : "");

            if (this.DB.hasConnection()) {

                this.pst = this.DB.getConnection().prepareStatement(this.query);

                if (this.isWhereSet) {
                    //Binding all values to the prepared statement
                    for (int i = 1; i <= this.whereValues.size(); i++) {
                        System.out.println(this.whereValues.get(i - 1));
                        this.pst.setObject(i, this.whereValues.get(i - 1));
                    }
                }

                //Logging Query
                System.out.println("[SQL Query]: " + this.query);
                System.out.println("[Values]: " + Utilities.combine(this.whereValues.toArray(), ","));
                
                List rl = RS2L.convert(this.pst.executeQuery());
                this.clear();
                return rl;
                
            }
        } catch (SQLException ex) {
            System.err.println("[SQL Error] : " + ex.getMessage());
        }

        return new ArrayList(); //create empty List
    }

    /**
     * [Get Method]
     *
     * <p>
     * Executes the Select query of SQL
     *
     * <p>
     * example:
     * <pre>{@code
     * new User().get((data, success) ->
     * {
     *
     *  if(!success){
     *      System.err.println(data);
     *  }else {
     *      System.out.println(data); //List
     *  }
     *
     * } );}
     * </pre>
     *
     * @param cb A callback with two parameters (Data, Success)
     */
    @Override
    public void get(callback<List, Boolean> cb) {
        List list = this.get();

        if (list.isEmpty()) {
            cb._result(list, false);
            throw new SynapseException(SYORM_Cause.ORM_SELECT, "No Results due of an exception"); //will throw a runtime exception
        } else {
            cb._result(list, true); //access the private method of get
        }
    }

    /**
     * ===========================================================
     *
     * @name: Filter Procedures
     * @desc: WHERE, ORDER BY, GROUPBY,
     * ===========================================================
     */
    /**
     * <tt>Usage Example</tt>:
     *
     * <p>
     * User u = new User();
     *
     * <pre>{@code
     * ===========================
     * user.where(new Object[][]{
     * {"user_id", "=", "2"},
     * {"type", "=", "3"}
     * }).get();
     * ==========================
     * }</pre>
     * <p>
     * By breaking down the code, this method will create a form of query that
     * looks like this.
     *
     * <p>
     * {@code WHERE user_id = 2 AND type = 3}
     *
     * @param values Needs a 2 Dimensional Array of Objects -> Second Object
     * must follow the order of
     * <p>
     * { column, operator, value}
     * @return The Model Class able to chain to get() method
     */
    @Override
    public Model where(Object[][] values) {
        for (int i = 0; i < values.length; i++) {

            if (values[i].length < 3) {
                throw new SynapseException(SYORM_Cause.ORM_WHERE, Responses.ORM_ERR_01); //will throw a runtime exception
            }

            this.whereConstruct += values[i][0] + " " + values[i][1] + " ?";
            if (i != (values.length - 1)) {
                this.whereConstruct += " AND ";
            }

            this.whereValues.add(values[i][2]);

        }

        this.isWhereSet = true;

        return this;
    }

    /**
     * ===========================================================
     *
     * @name: Cleaning
     * ===========================================================
     */
    protected void clear() {
        this.whereValues = new ArrayList<>();
        this.whereConstruct = "";
        this.isWhereSet = false;
        this.groupBy = "";
        this.query = "SELECT ";
        this.joinConstruct = "";
        this.isJoinedSet = false;
        this.isSelectColumnSet = false;
        this.DB.closeConnection();
    }

}
