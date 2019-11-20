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

import javafx.stage.StageStyle;

/**
 *
 * @author BlackMoon
 */
public class Module {

    private String mod_name;
    private String fxml_path;

    public Module(String mod_name, String fxml_path) {
        this.mod_name = mod_name;
        this.fxml_path = fxml_path;
    }

    
    protected String getFXMLPath() {
        return this.fxml_path;
    }

    public void setFxml_path(String fxml_path) {
        this.fxml_path = fxml_path;
    }

    public void setMod_name(String mod_name) {
        this.mod_name = mod_name;
    }

    public void open() {
        PE.JavaFX.Form frm = new PE.JavaFX.Form(this.getFXMLPath() + ".fxml");
        frm.open(StageStyle.UNDECORATED, false);
    }
}
