package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//sign in user dashboard
public class Userdashboard implements Initializable {
    public AnchorPane pane;
    public Button homeBtn;
    public TextField userrID;
    public TextField studentName;
    //public TextField rollNumber;
    public TextField regNumber;
    public TextField fatherName;
    public TextField motherName;
    public TextField emailId;
    public TextField courseSession;
    public Button updateButton;
    // String userNameid = Signup.userName();

    public void displayuserId(String name){
        userrID.setText(name);
    }
    public void homeControl(ActionEvent actionEvent) throws IOException {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        pane.getChildren().setAll(pane1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userrID.setText(Signup.setUserDashboardName());
    }

    public void updatebuttonControl(ActionEvent actionEvent) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "Ankitkumar1@");
        Statement smt = con.createStatement();
        String query = "INSERT INTO Students_detail VALUES('"+studentName.getText()+"',"+Integer.parseInt(Signup.setUserDashboardName())+",'"+regNumber.getText()+ "','"+emailId.getText()+"','"+fatherName.getText()+"','"+motherName.getText()+"','"+courseSession.getText()+"')";
        // try to insert if not insert and prompt error go to catch block and prompt error
        try{
            smt.execute(query);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Successful");
           // alert.setHeaderText("Account updates");
            alert.setContentText("Account Updated go login");
            alert.showAndWait();
        }
        catch(Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            //alert.setHeaderText("Account updates");
            alert.setContentText("Fill all fields with correct info");
            alert.showAndWait();


        }
    }
}
