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
package PE.JavaFX;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author BlackMoon
 */
public class Form {

    protected Stage stage = new Stage();
    protected String p = "";
    protected Parent parent;
    private FXMLLoader loader;

    public Form(String path) {
        this.parent = null;
        try {

            this.loader = new FXMLLoader();
            this.parent = loader.load(getClass().getResource(path));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setPath(String Path) {
        this.p = Path;
    }

    public String getPath() {
        return this.p;
    }

    public Parent getParent() {
        return this.parent;
    }

    public Object getCnt() {
        return this.loader.getController();
    }

    public void open() {

        Scene s = new Scene(this.parent);

        this.stage.initStyle(StageStyle.DECORATED);
        this.stage.setScene(s);
        this.stage.show();
    }

    public void open(StageStyle stageSytle) {

        Scene s = new Scene(this.parent);

        this.stage.initStyle(stageSytle);
        this.stage.setScene(s);
        this.stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public void open(StageStyle stageSytle, Boolean isFullScreen) {

        Scene s = new Scene(this.parent);

        if (isFullScreen) {
            //this.stage.setFullScreen(true);
            this.stage.setMaximized(true);
            this.stage.fullScreenExitKeyProperty().setValue(KeyCombination.NO_MATCH);
            this.stage.setAlwaysOnTop(false);
        }

        this.stage.initStyle(stageSytle);
        this.stage.setScene(s);
        this.stage.show();
    }

    public static void close(Stage target) {
        target.close();
    }

    public static void close(Window w) {
        ((Stage) w).close();
    }

    public static void close(Node n) {
        ((Stage) n.getScene().getWindow()).close();
    }

    public FXMLLoader getLoader() {
        return loader;
    }

}
