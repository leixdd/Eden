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
package PE.Modules.SplashScreenModule;

import PE.Modules.Auth.AuthModule;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class SplashScreenController implements Initializable {

    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblPercent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.tick();
    }

    public void tick() {
        new Thread(() -> {
            Double prog = .01;
            while (progressBar.progressProperty().floatValue() != 1f) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {

                }

//                initRoutingTable();
//                initSession();
//                initConnection();
                progressBar.setProgress(prog += .01);
                ControlPercent(String.valueOf((Math.round(prog * 100))) + " %");

            }

            PassedLogin();
        }).start();
    }

    public void PassedLogin() {
        Platform.runLater(() -> {
            PE.JavaFX.Form.close(progressBar);
            new AuthModule().open();
        });
    }

    public void ControlText(String text) {
        Platform.runLater(() -> {
            this.lblStatus.setText(text);
        });
    }

    public void ControlPercent(String text) {
        Platform.runLater(() -> {
            this.lblPercent.setText(text);
        });
    }

}
