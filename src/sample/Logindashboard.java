package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Logindashboard implements Initializable {
    public AnchorPane dashboardPane;
    public Button homebtn;
    public TextField name;
    public TextField emailId;
    public TextField courseSession;
    public TextField fatherName;
    public TextField motherName;
    public TextField rollNumber;
    public TextField regNo;


    public void homeControl(ActionEvent actionEvent) throws IOException {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        dashboardPane.getChildren().setAll(pane1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "Ankitkumar1@");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM student_portal.Students_detail WHERE `Roll no`="+Login.getUserId());
            if(rs.next()) {
                name.setText(rs.getString(1));
                int tem = rs.getInt(2);
                System.out.println(tem);
                //rollNumber.setText(String.valueOf((rs.getInt(2))));
                rollNumber.setText(String.valueOf(tem));
                regNo.setText(rs.getString(3));
                emailId.setText(rs.getString(4));
                fatherName.setText(rs.getString(5));
                motherName.setText(rs.getString(6));
                courseSession.setText(rs.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
