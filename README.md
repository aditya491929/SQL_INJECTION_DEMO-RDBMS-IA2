# SQL_INJECTION_DEMO-RDBMS-IA2
#####  Name: Aditya Malwade #####
##### RollNo: 1911091 #####
##### Batch: B2 #####

# ABSTRACT #
The project is a javafx application where in there are two login fields to counter sql injection attack the use of prepared statements has been done, for demo purpose the Admin 
login field has a radio button that enables and disables use of prepared statements to demonstrate how sql injection attacks work.
![Capture2](https://user-images.githubusercontent.com/69159108/115992618-367da180-a5ec-11eb-85fa-03a29a0c6920.PNG)

# SET UP # 
* Start a JavaFXApplication
* ADD mysql-connector-java jar files to Project Structure
* ADD javafx-sdk jar files 
![Capture](https://user-images.githubusercontent.com/69159108/115992231-38466580-a5ea-11eb-8272-cee65427e3e8.PNG)
* ADD VM options 
![Capture1](https://user-images.githubusercontent.com/69159108/115992330-c6225080-a5ea-11eb-88ad-66dacb9e504b.PNG)
``` bash
--module-path
D:\javafx-sdk-15.0.1\lib
--add-modules
javafx.controls,javafx.fxml,javafx.media
--add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
```
* Start Xampp server
* Set application.Main as Main Java file
