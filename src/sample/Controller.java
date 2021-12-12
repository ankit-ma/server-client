package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {

    public Button loginBtn;
    public Button signupBtn;
    public AnchorPane homePane;

    public void SignupControl(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("signup.fxml"));
        homePane.getChildren().setAll(pane);

    }

    public void loginBtnControll(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
        homePane.getChildren().setAll(pane);
    }
}
