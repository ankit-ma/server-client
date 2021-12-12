package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Signup {
    @FXML
    public Button homebtn;
    public AnchorPane pane;
    public TextField usernameText;
    public TextField passwordText;
    public TextField checkPasswordText;
    public Button signupbtn;
    public static String userDashboardName;

    public static String setUserDashboardName(){
        return userDashboardName;
    }

    public void homebtncontroll(ActionEvent actionEvent) throws IOException {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        pane.getChildren().setAll(pane1);
    }
    // when clicked first check username pre-exited or not if exited pop-up message that you this username existed
    // if this username is not exited in database then check the password and re-enter password is matching or not
    // if matched go to an empty dashboard for filling personal details and add the username and password to  credentials database
    // if not matched pop -up message both pass is wrong
    public void signupcontrol(ActionEvent actionEvent) {
        String user = usernameText.getText().trim();
        String pass = passwordText.getText().trim();
        String repass = checkPasswordText.getText().trim();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "Ankitkumar1@");
            Statement smt = con.createStatement();
            String query = "select * from usercredential where roll_number='"+Integer.parseInt(user)+"'";
            ResultSet rs =smt.executeQuery(query);
            if(rs.next()){
                // give alert account is already created go login

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Existed Account");
                alert.setContentText("Go login to your account");
                alert.showAndWait();
                con.close();
            }
            else if(!pass.equals(repass)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("password not matching");
                alert.setContentText("Check password");
                alert.showAndWait();
                con.close();

            }
            else{
                // open user dashboardad
               // usrname=user;
                userDashboardName = user;
                String insertQuery = "insert into usercredential values('"+Integer.parseInt(user)+"','"+pass+"')";
                smt.execute(insertQuery);
                con.close();
                AnchorPane pane1 = FXMLLoader.load(getClass().getResource("userdashboard.fxml"));
               // Userdashboard uss = (Userdashboard) pane1.getChildren();
                //uss.displayuserId(user);

                pane.getChildren().setAll(pane1);

                // add user to database


            }
        }
        catch(Exception e){
            System.out.println("ERROR: "+ e);
        }


    }

}
