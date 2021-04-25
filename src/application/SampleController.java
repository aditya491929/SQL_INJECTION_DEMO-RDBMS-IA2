package application;
import myProj.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.event.*;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SampleController {

    Student temp = new Student();

    @FXML
    private Button browse1;
    @FXML
    private Button browse2;
    @FXML
    private Button browse3;

    @FXML
    private TextField textF1;
    @FXML
    private TextField textF2;
    @FXML
    private TextField textF3;

    boolean newStudent = false;

    public void BrowseAction1(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected!=null) {
            textF1.setText(selected.getName());
            temp.setDoc1(selected.getAbsolutePath());
            temp.setD1n(selected.getAbsolutePath());
            //System.out.println(temp.getDoc1());
        }
        else
        {
            textF1.setText("");
        }
    }

    public void BrowseAction2(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected!=null) {
            textF2.setText(selected.getName());
            temp.setDoc2(selected.getAbsolutePath());
            temp.setD2n(selected.getAbsolutePath());
            System.out.println(temp.getDoc2());
        }
        else
        {
            textF2.setText("");
        }
    }

    public void BrowseAction3(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected!=null) {
            textF3.setText(selected.getName());
            temp.setDoc3(selected.getAbsolutePath());
            temp.setD3n(selected.getAbsolutePath());
            System.out.println(temp.getDoc3());
        }
        else
        {
            textF3.setText("");
        }
    }

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField mail;
    @FXML
    private TextField rnum;
    @FXML
    private TextField pass;
    @FXML
    private CheckBox check;
    @FXML
    private RadioButton comp;
    @FXML
    private RadioButton it;
    @FXML
    private RadioButton mech;
    @FXML
    private RadioButton etrx;
    @FXML
    private RadioButton extc;
    @FXML
    private ToggleGroup Dept;
    @FXML
    private Button saveB;

    public RadioButton getRadioButton(String s)
    {
        if(comp.getText().equals(s))
            return comp;
        else if(it.getText().equals(s))
            return it;
        else if(mech.getText().equals(s))
            return mech;
        else if(etrx.getText().equals(s))
            return etrx;
        return extc;
    }

    public void init(Student t)
    {
        temp = new Student();
        //System.out.println("INIT : "+t.getFirstname());
        fname.setText(t.getFirstname());
        lname.setText(t.getLastname());
        mail.setText(t.getEmail());
        rnum.setText(Integer.toString(t.getId()));
        Dept.selectToggle(getRadioButton(t.getDepartment()));
        textF1.setText(t.getD1n());
        textF2.setText(t.getD2n());
        textF3.setText(t.getD3n());
        //System.out.println("Student t in init");
        //System.out.println(t.getD1n());
        //System.out.println(t.getD2n());
        //System.out.println(t.getD3n());
        temp.setDoc1(t.getD1n());
        temp.setDoc2(t.getD2n());
        temp.setDoc3(t.getD3n());
        temp.setD1n(t.getD1n());
        temp.setD2n(t.getD2n());
        temp.setD3n(t.getD3n());
    }

    public void save()
    {
        if(check.isSelected()) {

            if(fname.getText().length()==0)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incomplete Details");
                alert.setHeaderText("Enter complete details!");
                alert.showAndWait();
                return;
            }
            else
                temp.setFirstname(fname.getText());
            if(lname.getText().length()==0)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incomplete Details");
                alert.setHeaderText("Enter complete details!");
                alert.showAndWait();
                return;
            }
            else
                temp.setLastname(lname.getText());
            if(mail.getText().length()==0)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incomplete Details");
                alert.setHeaderText("Enter complete details!");
                alert.showAndWait();
                return;
            }
            else
                temp.setEmail(mail.getText());
            if(rnum.getText().length()==0)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incomplete Details");
                alert.setHeaderText("Enter complete details!");
                alert.showAndWait();
                return;
            }
            else
                temp.setId(Integer.parseInt(rnum.getText()));
            RadioButton selectedRadio = (RadioButton)Dept.getSelectedToggle();
            temp.setDepartment(selectedRadio.getText());
            if(pass.getText().length()==0)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Incomplete Details");
                alert.setHeaderText("Enter complete details!");
                alert.showAndWait();
                return;
            }
            else
                temp.setPassword(pass.getText());


            Datasource.insertstudent(temp);
            view();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Upload Successful!");
            alert.setHeaderText("Details have been submitted successfully!");
            alert.showAndWait();
            try {
                Stage current = (Stage)saveB.getScene().getWindow();
                current.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
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
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Verify your details once again!");
            alert.setContentText("Once verified, check for confirmation!");
            alert.showAndWait();
        }
    }

    @FXML
    private Button updateB;

    public void update()
    {
        if(pass.getText()!="" && rnum.getText()!="")
        {
            if(check.isSelected()) {

                Student temp_s = Datasource.checkIfExists(Integer.parseInt(rnum.getText()),pass.getText());

                if(temp_s!=null)
                {
                    if(fname.getText().length()==0)
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Incomplete Details");
                        alert.setHeaderText("Enter complete details!");
                        alert.showAndWait();
                        return;
                    }
                    else
                        temp_s.setFirstname(fname.getText());
                    if(lname.getText().length()==0)
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Incomplete Details");
                        alert.setHeaderText("Enter complete details!");
                        alert.showAndWait();
                        return;
                    }
                    else
                        temp_s.setLastname(lname.getText());
                    if(mail.getText().length()==0)
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Incomplete Details");
                        alert.setHeaderText("Enter complete details!");
                        alert.showAndWait();
                        return;
                    }
                    else
                        temp_s.setEmail(mail.getText());
                    if(rnum.getText().length()==0)
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Incomplete Details");
                        alert.setHeaderText("Enter complete details!");
                        alert.showAndWait();
                        return;
                    }
                    else
                        temp_s.setId(Integer.parseInt(rnum.getText()));
                    RadioButton selectedRadio = (RadioButton)Dept.getSelectedToggle();
                    temp_s.setDepartment(selectedRadio.getText());
                    if(pass.getText().length()==0)
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Incomplete Details");
                        alert.setHeaderText("Enter complete details!");
                        alert.showAndWait();
                        return;
                    }
                    else
                        temp_s.setPassword(pass.getText());

                    temp_s.setD1n(temp.getD1n());
                    temp_s.setD2n(temp.getD2n());
                    temp_s.setD3n(temp.getD3n());
                    temp_s.setDoc1(temp.getDoc1());
                    temp_s.setDoc2(temp.getDoc2());
                    temp_s.setDoc3(temp.getDoc3());

                    Datasource.updateStudent(temp_s);


                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Updated");
                    alert.setHeaderText("Update successfull!");
                    alert.showAndWait();
                    try {
                        Stage current = (Stage)saveB.getScene().getWindow();
                        current.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
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
                else
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Incorrect Roll Number OR Password");
                    alert.setHeaderText("Verify your details once again!");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Verify your details once again!");
                alert.setContentText("Once verified, check for confirmation!");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Enter RollNumber and Password!");
            alert.setContentText("In order to update your record, fill in correct roll number and password!");
            alert.showAndWait();
        }
    }

    @FXML
    private Button viewBtn;

    public void view()
    {
        temp.setFirstname(fname.getText());
        temp.setLastname(lname.getText());
        temp.setEmail(mail.getText());
        temp.setId(Integer.parseInt(rnum.getText()));
        temp.setEmail(mail.getText());
        RadioButton selectedRadio = (RadioButton)Dept.getSelectedToggle();
        temp.setDepartment(selectedRadio.getText());
        temp.setD1n(textF1.getText());
        temp.setD2n(textF2.getText());
        temp.setD3n(textF3.getText());

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Uploaded Details");
        alert.setHeaderText("You have uploaded the following details!");
        String d1 = temp.getD1n()==null ? "Not submitted" : temp.getD1n();
        String d2 = temp.getD2n()==null ? "Not submitted" : temp.getD2n();
        String d3 = temp.getD3n()==null ? "Not submitted" : temp.getD3n();

        alert.setContentText("Name             : "+temp.getFirstname()+" "+temp.getLastname()+"\nRoll number   : "+temp.getId()+"\nDepartment   : "+temp.getDepartment()
                +"\nEmail              : "+temp.getEmail()+"\nDocument 1  : "+d1+"\nDocument 2  : "+d2+"\nDocument 3  : "+d3);
        alert.showAndWait();

    }
}
