package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class Login {
    public Button homeBtn;
    public AnchorPane loginPane;
    public TextField userText;
    public TextField userPass;
    public Button LoginBtn;
    public static int userId;

    public static int getUserId(){
        return userId;
    }

    public void homepageControl(ActionEvent actionEvent) throws IOException {
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        loginPane.getChildren().setAll(pane1);
    }
    // query in databse for given username if username doesnot exit prompt no user with this name signup now
    // if username and password doesnot match promt worng credentials
    // if matches credentials go to dashboard
    public void LoginControl(ActionEvent actionEvent) throws SQLException {
        String user = userText.getText().trim();
        String pass = userPass.getText().trim();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "Ankitkumar1@");
            Statement smt = con.createStatement();
            String query = "select * from usercredential where roll_number='"+Integer.parseInt(user)+"'";
            ResultSet rs =smt.executeQuery(query);

            if(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2)+user +" "+pass);
                if(Integer.toString(rs.getInt(1)).equals(user) && rs.getString(2).equals(pass)) {
                    userId = rs.getInt(1);
                    con.close();
                    AnchorPane pane1 = FXMLLoader.load(getClass().getResource("logindashboard.fxml"));

                    loginPane.getChildren().setAll(pane1);

                }
                else
                {
                    userText.setText("");
                    userPass.setText("");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Credentials not match");
                    alert.setContentText("Password doesnot match");
                    alert.showAndWait();
                }
            }
            else{
                userText.setText("");
                userPass.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("User not found");
                alert.setContentText("Create an account");
                alert.showAndWait();
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }

    }
}
