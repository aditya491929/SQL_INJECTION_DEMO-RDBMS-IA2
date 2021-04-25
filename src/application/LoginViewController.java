package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import myProj.*;

public class LoginViewController implements Initializable{

    @FXML
    private Button adminLoginBtn;

    @FXML
    private Button studentLoginBtn;

    @FXML
    private Button newStudentBtn;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private TextField userId;

    @FXML
    private TextField userPass;

    @FXML
    private TextField studentRoll;

    @FXML
    private TextField studentPass;

    @FXML
    private TextField countF;


    public void init()
    {
        countF.setText(Integer.toString(Datasource.getTotalEntries()));
    }

    public void adminLogin(ActionEvent event)
    {
        String id,pass;
        if(userId.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incomplete credentials");
            alert.setHeaderText("Enter valid Login credentials!");
            alert.showAndWait();
            return;
        }
        else
            id = userId.getText();
        if(userPass.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incomplete credentials");
            alert.setHeaderText("Enter valid Login credentials!");
            alert.showAndWait();
            return;
        }
        else
            pass = userPass.getText();
        Boolean result = false;

        if (option1.isSelected()){
            System.out.println("in option1");
            result = Datasource.checkIfAdminExists(id,pass);
        }
        else{
            System.out.println("in option2");
            result = Datasource.checkIfAdminExists1(id,pass);
        }

        if (result){
            try {
                Stage current = (Stage)adminLoginBtn.getScene().getWindow();
                current.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root,908,600);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Admin");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Admin!");
            alert.setHeaderText("Incorrect EmailId or Password!");
            alert.setContentText("Verify your details once again!");
            alert.showAndWait();
            System.out.println("Incorrect password");
        }
//        if(id.equals(Admin.getEmail()))
//            if(pass.equals(Admin.getPassword())) {
//                try {
//                    Stage current = (Stage)adminLoginBtn.getScene().getWindow();
//                    current.close();
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
//                    Parent root = loader.load();
//                    Scene scene = new Scene(root,908,600);
//                    Stage primaryStage = new Stage();
//                    primaryStage.setTitle("Admin");
//                    primaryStage.setScene(scene);
//                    primaryStage.show();
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                System.out.println("Incorrect password");
//            }
//        else
//            System.out.println("Incorrect username");

    }

    public void studentLogin(ActionEvent event)
    {
        int roll;String pass;
        if(studentRoll.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incomplete credentials");
            alert.setHeaderText("Enter valid Login credentials!");
            alert.showAndWait();
            return;
        }
        else
            roll = Integer.parseInt(studentRoll.getText());
        if(studentRoll.getText().length()==0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incomplete credentials");
            alert.setHeaderText("Enter valid Login credentials!");
            alert.showAndWait();
            return;
        }
        else
            pass = studentPass.getText();
        Student student = Datasource.checkIfExists(roll,pass);
        if(student!=null){
            try {
                Stage current = (Stage)studentLoginBtn.getScene().getWindow();
                current.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
                Parent root = loader.load();
                SampleController studentC = loader.getController();

                studentC.init(student);

                Scene scene = new Scene(root,908,600);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Student Details");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Student!");
            alert.setHeaderText("Incorrect Roll Number or Password!");
            alert.setContentText("Verify your details once again!");
            alert.showAndWait();
        }
    }


    public void newStudentLogin(ActionEvent event)
    {
        try {
            Stage current = (Stage)studentLoginBtn.getScene().getWindow();
            current.close();
            System.out.println(getClass());
            System.out.println(getClass().getResource("Sample.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,908,600);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Student Details");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        init();
    }

}
