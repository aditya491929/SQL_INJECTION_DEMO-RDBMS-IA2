package myProj;


import java.io.*;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Datasource {

    public static final String DB_NAME = "document";

    public static final String CONNECTION_URL ="jdbc:mysql://localhost:3306/"+DB_NAME;
    public static final String CONNECTION_USER ="root";
    public static final String CONNECTION_PASS ="";

    public static final String ADMIN ="admin";
    public static final String TABLE_DOCS ="documents";
    public static final String COLUMN_DOCS_ID="Id";
    public static final String COLUMN_DOCS_FirstName="firstName";
    public static final String COLUMN_DOCS_LastName="lastName";
    public static final String COLUMN_DOCS_EMAIL="email";
    public static final String COLUMN_DOCS_DEPARTMENT="department";
    public static final String COLUMN_DOCS_PASSWORD="password";
    public static final String COLUMN_DOCS_DOC1="Doc1";
    public static final String COLUMN_DOCS_DOC2="Doc2";
    public static final String COLUMN_DOCS_DOC3="Doc3";
    public static final String COLUMN_DOCS_NAME1 = "D1n";
    public static final String COLUMN_DOCS_NAME2 = "D2n";
    public static final String COLUMN_DOCS_NAME3 = "D3n";

    public static final int INDEX_DOCS_ID =1;
    public static final int INDEX_DOCS_FirstName =2;
    public static final int INDEX_DOCS_LastName =3;
    public static final int INDEX_DOCS_EMAIL =4;
    public static final int INDEX_DOCS_DEPARTMENT =5;
    public static final int INDEX_DOCS_PASSWORD =6;
    public static final int INDEX_DOCS_DOC1 =7;
    public static final int INDEX_DOCS_DOC2 =8;
    public static final int INDEX_DOCS_DOC3 =9;
    public static final int INDEX_DOCS_NAME1 = 10;
    public static final int INDEX_DOCS_NAME2 = 11;
    public static final int INDEX_DOCS_NAME3 = 12;

    public static final int ORDER_BY_NONE =1;
    public static final int ORDER_BY_ASC =2;
    public static final int ORDER_BY_DESC =3;

    public static final String QUERY_PARTICULAR_STUDENT = "SELECT * FROM "+TABLE_DOCS+" WHERE "+COLUMN_DOCS_ID + " = ? ";
    public static final String QUERY_GET_COUNT = "SELECT COUNT(*) AS entries FROM "+TABLE_DOCS;
    public static final String SEARCH_FOR_ADMIN = "SELECT * FROM " + DB_NAME+ "."+ADMIN+" WHERE email = ? AND password = ?";
    public static final String SEARCH_FOR_STUDENT = "SELECT * FROM " + DB_NAME+ "."+TABLE_DOCS+" WHERE Id = ? AND password = ?";
    public static final String INSERT_STUDENT = "INSERT INTO "+DB_NAME + "." + TABLE_DOCS+'('+COLUMN_DOCS_ID+","+COLUMN_DOCS_FirstName
            +","+COLUMN_DOCS_LastName+","+COLUMN_DOCS_EMAIL+","+COLUMN_DOCS_DEPARTMENT+","+COLUMN_DOCS_PASSWORD+","+
            COLUMN_DOCS_DOC1+","+COLUMN_DOCS_DOC2+","+COLUMN_DOCS_DOC3+","+COLUMN_DOCS_NAME1+","+COLUMN_DOCS_NAME2+","+COLUMN_DOCS_NAME3+") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_STUDENT = "UPDATE "+ DB_NAME + "." + TABLE_DOCS+" SET firstName = ? ,lastName = ? ,email = ? ," +
            "department = ? ,Doc1 = ? ,Doc2 = ? ,Doc3 = ? ,D1n = ? ,D2n = ? ,D3n = ? WHERE Id = ? AND password = ?";
    public static final String GET_ALL = "SELECT * FROM "+DB_NAME + "."+ "TABLE_DOCS";

    private static Connection conn;
    private static PreparedStatement queryParticularStudent;
    private static PreparedStatement insertStudent;
    private static PreparedStatement updateStudent;
    private static PreparedStatement searchAdmin;
    private static PreparedStatement searchStudent;
    private static PreparedStatement totalEntries;

    public static Connection getConnection()
    {
        try{
            conn = DriverManager.getConnection(CONNECTION_URL,CONNECTION_USER,CONNECTION_PASS);
            System.out.println("CONNECTION ESTABLISHED SUCCESSFULLY!!!");
        }
        catch(SQLException e)
        {
            conn = null;
            System.out.println("ERROR1 (Couldn't connect to DB) : "+e.getMessage());
        }
        return conn;
    }
    public static boolean open()   //#1  to open connection
    {
        try{
            conn = DriverManager.getConnection(CONNECTION_URL,CONNECTION_USER,CONNECTION_PASS);
            System.out.println("CONNECTION ESTABLISHED SUCCESSFULLY!!!");

            queryParticularStudent = conn.prepareStatement(QUERY_PARTICULAR_STUDENT);
            insertStudent = conn.prepareStatement(INSERT_STUDENT);
            updateStudent = conn.prepareStatement(UPDATE_STUDENT);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("ERROR1 (Couldn't connect to DB) : "+e.getMessage());
            return false;
        }
    }

    public static void close()         //#2 to close connection
    {
        try
        {
            if(queryParticularStudent != null)
            {
                queryParticularStudent.close();
            }

            if(insertStudent != null)
            {
                insertStudent.close();
            }

            if(updateStudent != null)
            {
                updateStudent.close();
            }

            if(conn!=null)
            {
                conn.close();
                System.out.println("CONNECTION CLOSED!");
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR2 (Couldn't close connection) : "+e.getMessage());
        }

    }

    public static ResultSet getAll()
    {
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(GET_ALL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int getTotalEntries()
    {
        Connection conn = getConnection();
        int count = 0 ;
        try {
            totalEntries = conn.prepareStatement(QUERY_GET_COUNT);
            ResultSet result = totalEntries.executeQuery();
            while(result.next())
                count = result.getInt("entries");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static Boolean checkIfAdminExists1(String mail, String pass)
    {
        Connection conn = getConnection();
        boolean found2 = false;
        try {
//            searchAdmin = conn.prepareStatement(SEARCH_FOR_ADMIN);
//            searchAdmin.setString(1,mail);
//            searchAdmin.setString(2,pass);
            String query1 = "SELECT * FROM document.admin WHERE email='" + mail + "' AND password = '" + pass + "'";
            Statement stmt = conn.createStatement();
            ResultSet results2 = stmt.executeQuery(query1);
            if (results2.next())
            {
                found2 = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(found2) {
            //System.out.println("STUDENT ALREADY EXISTS");
            System.out.println(found2);
            return true;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(found2);
        return false;
    }

    public static Boolean checkIfAdminExists(String mail, String pass)
    {
        Connection conn = getConnection();
        boolean found1 = false;
        try {
            searchAdmin = conn.prepareStatement(SEARCH_FOR_ADMIN);
            searchAdmin.setString(1,mail);
            searchAdmin.setString(2,pass);
            ResultSet results1 = searchAdmin.executeQuery();
            while(results1.next())
            {
//                System.out.println(mail +" "+ pass);
//                System.out.println(results1.getString(1)+" "+results1.getString(2));
                if (mail.equals(results1.getString(1)) && pass.equals(results1.getString(2)))
                {
                    found1 = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(found1) {
            //System.out.println("STUDENT ALREADY EXISTS");
            System.out.println(found1);
            return true;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(found1);
        return false;
    }

    public static Student checkIfExists(int roll, String pass)
    {
        Student temp = new Student();
        Connection conn = getConnection();
        boolean found = false;
        try {
            searchStudent = conn.prepareStatement(SEARCH_FOR_STUDENT);
            searchStudent.setInt(1,roll);
            searchStudent.setString(2,pass);
            ResultSet results = searchStudent.executeQuery();
            while(results.next())
            {
                found = true;
                temp.setId(results.getInt(INDEX_DOCS_ID));
                temp.setFirstname(results.getString(INDEX_DOCS_FirstName));
                //System.out.println(results.getString(INDEX_DOCS_FirstName));
                temp.setLastname(results.getString(INDEX_DOCS_LastName));
                //System.out.println(results.getString(INDEX_DOCS_LastName));
                temp.setEmail(results.getString(INDEX_DOCS_EMAIL));
                temp.setDepartment(results.getString(INDEX_DOCS_DEPARTMENT));
                temp.setPassword(results.getString(INDEX_DOCS_PASSWORD));
                temp.setDoc1(results.getString(INDEX_DOCS_NAME1));
                temp.setDoc2(results.getString(INDEX_DOCS_NAME2));
                temp.setDoc3(results.getString(INDEX_DOCS_NAME3));
                temp.setD1n(results.getString(INDEX_DOCS_NAME1));
                temp.setD2n(results.getString(INDEX_DOCS_NAME2));
                temp.setD3n(results.getString(INDEX_DOCS_NAME3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(found) {
            //System.out.println("STUDENT ALREADY EXISTS");
            return temp;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Student> queryStudents(int sortOrder)    //#3 query
    {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_DOCS);
        if(sortOrder != ORDER_BY_NONE)
        {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_DOCS_ID);
            if(sortOrder == ORDER_BY_DESC)
            {
                sb.append(" DESC");
            }
            else
            {
                sb.append(" ASC");
            }
        }
        Connection conn = getConnection();
        try
        {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString());
            ObservableList<Student> students = FXCollections.observableArrayList();
            while(results.next())
            {
                Student student = new Student();
                student.setId(results.getInt(INDEX_DOCS_ID));
                student.setFirstname(results.getString(INDEX_DOCS_FirstName));
                student.setLastname(results.getString(INDEX_DOCS_LastName));
                student.setEmail(results.getString(INDEX_DOCS_EMAIL));
                student.setDepartment(results.getString(INDEX_DOCS_DEPARTMENT));
                student.setPassword(results.getString(INDEX_DOCS_PASSWORD));
                if(results.getString(INDEX_DOCS_NAME1)!=null)
                    student.setDoc1("\u2713");//Tick
                else
                    student.setDoc1("\u274C");//Cross
                if(results.getString(INDEX_DOCS_NAME2)!=null)
                    student.setDoc2("\u2713");
                else
                    student.setDoc2("\u274C");
                if(results.getString(INDEX_DOCS_NAME3)!=null)
                    student.setDoc3("\u2713");
                else
                    student.setDoc3("\u274C");
                students.add(student);
            }
            conn.close();//EDIT1
            return students;
        }
        catch (SQLException e)
        {
            System.out.println("Query failed "+e.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Student queryparticularStudent(int id,String path) //#5
    {
        try
        {
            Connection conn = getConnection();

            queryParticularStudent = conn.prepareStatement(QUERY_PARTICULAR_STUDENT);
            queryParticularStudent.setInt(1,id);
            ResultSet results = queryParticularStudent.executeQuery();

            Student student = new Student();
            if(results.next())
            {
                //Systtut.println("EXISTS");
                student.setId(results.getInt(INDEX_DOCS_ID));
                student.setFirstname(results.getString(INDEX_DOCS_FirstName));
                student.setLastname(results.getString(INDEX_DOCS_LastName));
                student.setEmail(results.getString(INDEX_DOCS_EMAIL));
                student.setPassword(results.getString(INDEX_DOCS_PASSWORD));
                student.setDepartment(results.getString(INDEX_DOCS_DEPARTMENT));

                Statement statement1 = conn.createStatement();

                StringBuilder read1 = new StringBuilder("SELECT Doc1 FROM "+DB_NAME+"."+TABLE_DOCS+" WHERE Id = ");
                read1.append(id);

                ResultSet result1 = statement1.executeQuery(read1.toString());
                if(result1.next()){
                    if(result1.getBinaryStream(1)!=null) {
                        student.setDoc1("\u2713");
                        System.out.println("Lets Download DOC1 :");
                        File theFile1 = new File(path+results.getInt(INDEX_DOCS_ID)+"_1.pdf");
                        FileOutputStream output1 = new FileOutputStream(theFile1);
                        InputStream input1 = result1.getBinaryStream("Doc1");
                        byte[] buffer1 = new byte[1024];

                        while(input1.read(buffer1)>0){
                            output1.write(buffer1);
                        }
                        System.out.println("Saved to :"+theFile1.getAbsolutePath());
                        output1.close();
                    }
                    else
                        student.setDoc1("\u274C");
                }



                Statement statement2 = conn.createStatement();

                StringBuilder read2 = new StringBuilder("SELECT Doc2 FROM "+DB_NAME+"."+TABLE_DOCS+" WHERE Id = ");
                read2.append(id);
                ResultSet result2 = statement2.executeQuery(read2.toString());

                if(result2.next()){
                    if(result2.getBinaryStream(1)!=null) {
                        student.setDoc2("\u2713");
                        System.out.println("Lets Download DOC2 :");
                        File theFile2 = new File(path+results.getInt(INDEX_DOCS_ID)+"_2.pdf");
                        FileOutputStream output2 = new FileOutputStream(theFile2);
                        InputStream input2 = result2.getBinaryStream("Doc2");
                        byte[] buffer2 = new byte[1024];
                        while(input2.read(buffer2)>0){
                            output2.write(buffer2);
                        }
                        System.out.println("Saved to :"+theFile2.getAbsolutePath());
                        output2.close();
                    }
                    else
                        student.setDoc2("\u274C");
                }




                Statement statement3 = conn.createStatement();

                StringBuilder read3 = new StringBuilder("SELECT Doc3 FROM "+DB_NAME+"."+TABLE_DOCS+" WHERE Id = ");
                read3.append(id);
                ResultSet result3 = statement3.executeQuery(read3.toString());


                if(result3.next()){
                    if(result3.getBinaryStream(1)!=null) {
                        student.setDoc3("\u2713");
                        System.out.println("Lets Download DOC3 :");
                        File theFile3 = new File(path+results.getInt(INDEX_DOCS_ID)+"_3.pdf");
                        FileOutputStream output3 = new FileOutputStream(theFile3);
                        InputStream input3 = result3.getBinaryStream("Doc3");
                        byte[] buffer3 = new byte[1024];
                        while(input3.read(buffer3)>0){
                            output3.write(buffer3);
                        }
                        System.out.println("Saved to :"+theFile3.getAbsolutePath());
                        output3.close();
                    }
                    else
                        student.setDoc3("\u274C");
                }
            }
            else {
                student = null;
                System.out.println("Record not found for roll number "+id);
            }
            return student;
        }
        catch (Exception e)
        {
            System.out.println("QUERY FAILED1 : "+e.getMessage());

        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void insertstudent(Student s)
    {
        try {

            open();
            insertStudent.setInt(1, s.getId());
            insertStudent.setString(2, s.getFirstname());
            //Sysm.out.println("Datasource.insertstudent() : "+ s.getLastname());
            insertStudent.setString(3, s.getLastName());
            insertStudent.setString(4, s.getEmail());
            insertStudent.setString(5, s.getDepartment());
            insertStudent.setString(6, s.getPassword());

            FileInputStream input1 = null;
            FileInputStream input2 = null;
            FileInputStream input3 = null;

            if(s.getDoc1()!=null) {
                File file1 = new File(s.getDoc1());
                input1 = new FileInputStream(file1);
                System.out.println("Inserting");
                System.out.println(s.getD1n());
                insertStudent.setBinaryStream(7, input1);
                insertStudent.setString(10,s.getD1n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                //stem.out.println("DOC1 NOT UPLOADED");
                System.out.println(s.getD1n());
                insertStudent.setBinaryStream(7,null);
                insertStudent.setString(10,null);
            }

            if(s.getDoc2()!=null) {
                File file2 = new File(s.getDoc2());
                input2 = new FileInputStream(file2);
                System.out.println("Inserting");
                System.out.println(s.getD2n());
                insertStudent.setBinaryStream(8, input2);
                insertStudent.setString(11,s.getD2n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                System.out.println("DOC2 NOT UPLOADED");
                System.out.println(s.getD2n());
                insertStudent.setBinaryStream(8,null);
                insertStudent.setString(11,null);
            }
            if(s.getDoc3()!=null) {
                File file3 = new File(s.getDoc3());
                input3 = new FileInputStream(file3);
                System.out.println("Inserting");
                System.out.println(s.getD3n());
                insertStudent.setBinaryStream(9, input3);
                insertStudent.setString(12,s.getD3n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                System.out.println("DOC3 NOT UPLOADED");
                System.out.println(s.getD3n());
                insertStudent.setBinaryStream(9,null);
                insertStudent.setString(12,null);
            }

            insertStudent.executeUpdate();
            //stem.out.println("Files 1,2,3 Uploaded Successfully!");

        }
        catch(SQLException se)
        {
            System.out.println("ERROR CODE : "+se.getErrorCode());
            System.out.println("ERROR MESSAGE : "+se.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("ERROR INSERTING RECORD : "+e.getMessage());
        }
        close();
    }

    public static boolean updateStudent(Student s)
    {
        try {

            open();
            updateStudent.setInt(11, s.getId());
            updateStudent.setString(1, s.getFirstname());
            System.out.println("Datasource.insertstudent() : "+ s.getLastname());
            updateStudent.setString(2, s.getLastName());
            updateStudent.setString(3, s.getEmail());
            updateStudent.setString(4, s.getDepartment());
            updateStudent.setString(12, s.getPassword());

            FileInputStream input1 = null;
            FileInputStream input2 = null;
            FileInputStream input3 = null;
            if(s.getDoc1()!=null) {
                File file1 = new File(s.getDoc1());
                input1 = new FileInputStream(file1);
                System.out.println("Inserting");
                System.out.println(s.getD1n());
                updateStudent.setBinaryStream(5, input1);
                updateStudent.setString(8,s.getD1n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                System.out.println("DOC1 NOT UPLOADED");
                System.out.println(s.getD1n());
                updateStudent.setBinaryStream(5,null);
                updateStudent.setString(8,null);
            }

            if(s.getDoc2()!=null) {
                File file2 = new File(s.getDoc2());
                input2 = new FileInputStream(file2);
                System.out.println("Inserting");
                System.out.println(s.getD2n());
                updateStudent.setBinaryStream(6, input2);
                updateStudent.setString(9,s.getD2n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                System.out.println("DOC2 NOT UPLOADED");
                System.out.println(s.getD2n());
                updateStudent.setBinaryStream(6,null);
                updateStudent.setString(9,null);
            }
            if(s.getDoc3()!=null) {
                File file3 = new File(s.getDoc3());
                input3 = new FileInputStream(file3);
                System.out.println("Inserting");
                System.out.println(s.getD3n());
                updateStudent.setBinaryStream(7, input3);
                updateStudent.setString(10,s.getD3n());

                //System.out.println("\nReading file 1 : " + file1.getAbsolutePath());
            }
            else {
                System.out.println("DOC3 NOT UPLOADED");
                System.out.println(s.getD3n());
                updateStudent.setBinaryStream(7,null);
                updateStudent.setString(10,null);
            }

            System.out.println(updateStudent);
            updateStudent.executeUpdate();
            System.out.println("Files 1,2,3 Uploaded Successfully!");
            System.out.println("RECORD UPDATED SUCCESSFULLY!");
            close();
            return true;

        }
        catch(SQLException se)
        {
            System.out.println("ERROR CODE : "+se.getErrorCode());
            System.out.println("ERROR MESSAGE : "+se.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("ERROR INSERTING RECORD : "+e.getMessage());
        }
        close();
        return false;


    }

}
