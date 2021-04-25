package application;

import myProj.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminViewController implements Initializable{

    @FXML
    private RadioButton ascendingBtn;

    @FXML
    private RadioButton descendingBtn;

    @FXML
    private RadioButton defaultBtn;

    @FXML
    private ToggleGroup orderToggle;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField rnum;

    @FXML
    private Button download;

    @FXML
    private Button load;

    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student,Integer> id;
    @FXML
    private TableColumn<Student,String> fn;
    @FXML
    private TableColumn<Student,String> ln;
    @FXML
    private TableColumn<Student,String> dp;
    @FXML
    private TableColumn<Student,String> pw;
    @FXML
    private TableColumn<Student,String> em;
    @FXML
    private TableColumn<Student,String> f1;
    @FXML
    private TableColumn<Student,String> f2;
    @FXML
    private TableColumn<Student,String> f3;

//    String currentDir = System.getProperty("user.dir");
//    String musicPath = currentDir+"\\src\\application\\Scam1992.mp3";

    ObservableList<Student> list = FXCollections.observableArrayList();

//    Media musicF = new Media(new File(musicPath).toURI().toString());
//    MediaPlayer mediaplayer = new MediaPlayer(musicF);

    @Override
    public void initialize(URL url,ResourceBundle rb)
    {
        //System.out.println("INITIALISING");
//        mediaplayer.play();
//        mediaplayer.setVolume(0.1);
//        mediaplayer.setStartTime(Duration.seconds(8));
//        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaplayer.play();

        list = Datasource.queryStudents(1);

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        em.setCellValueFactory(new PropertyValueFactory<>("email"));
        pw.setCellValueFactory(new PropertyValueFactory<>("password"));
        dp.setCellValueFactory(new PropertyValueFactory<>("department"));
        f1.setCellValueFactory(new PropertyValueFactory<>("Doc1"));
        f2.setCellValueFactory(new PropertyValueFactory<>("Doc2"));
        f3.setCellValueFactory(new PropertyValueFactory<>("Doc3"));

        tableView.setItems(list);
    }

    public void load()
    {
        ObservableList<Student> list = FXCollections.observableArrayList();
        RadioButton selected = (RadioButton)orderToggle.getSelectedToggle();
        int order = 1;
        if(selected.getText().equals("Ascending Order"))
            order = 2;
        else if(selected.getText().equals("Descending Order"))
            order = 3;

        list = Datasource.queryStudents(order);
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        em.setCellValueFactory(new PropertyValueFactory<>("email"));
        pw.setCellValueFactory(new PropertyValueFactory<>("password"));
        dp.setCellValueFactory(new PropertyValueFactory<>("department"));
        f1.setCellValueFactory(new PropertyValueFactory<>("Doc1"));
        f2.setCellValueFactory(new PropertyValueFactory<>("Doc2"));
        f3.setCellValueFactory(new PropertyValueFactory<>("Doc3"));
        tableView.setItems(list);
    }

    public void logout()
    {
        Stage current = (Stage)logoutBtn.getScene().getWindow();
        current.close();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logging Out");
        alert.setHeaderText("Admin has been logged out!");
        alert.showAndWait();
//        mediaplayer.stop();
    }

    public void searchAndDownload()
    {
        //ObservableList<Student> list = FXCollections.observableArrayList();

        String rollNumber = rnum.getText();

        Path downloads = Paths.get(System.getProperty("user.home"),"Downloads");

        String path = downloads+"\\"+rollNumber+"\\";

        File file = new File(path);
        if(file.mkdir()) {
            System.out.println("Directory for "+rollNumber+" Created!");
        }
        else
        {
            System.out.println("Directory already exists!");
        }

        Student s = Datasource.queryparticularStudent(Integer.parseInt(rollNumber),path);

        if(s!=null) {
            boolean filesToDownload = true;
            if(s.getDoc1()=="\u274C" && s.getDoc2()=="\u274C" && s.getDoc3()=="\u274C") {
                filesToDownload = false;
            }
            list.add(s);
            id.setCellValueFactory(new PropertyValueFactory<>("Id"));
            fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            em.setCellValueFactory(new PropertyValueFactory<>("email"));
            pw.setCellValueFactory(new PropertyValueFactory<>("password"));
            dp.setCellValueFactory(new PropertyValueFactory<>("department"));
            f1.setCellValueFactory(new PropertyValueFactory<>("Doc1"));
            f2.setCellValueFactory(new PropertyValueFactory<>("Doc2"));
            f3.setCellValueFactory(new PropertyValueFactory<>("Doc3"));
            tableView.setItems(list);

            if(filesToDownload) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Details");
                alert.setHeaderText("Submitted documents have been downloaded!");
                alert.setContentText("Check in Downloads directory!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Details");
                alert.setHeaderText("No files to be downloaded!");
                alert.showAndWait();
            }

        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entry not found!");
            alert.showAndWait();
        }
    }
}
