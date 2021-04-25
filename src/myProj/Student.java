package myProj;


public class Student {

    private int id;
    private String firstName;
    private String lastName;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String email;
    private String department;
    private String password;
    private String Doc1;
    private String Doc2;
    private String Doc3;
    private String D1n;
    private String D2n;
    private String D3n;

    public String getD1n() {
        return D1n;
    }

    public void setD1n(String D1n) {
        this.D1n = D1n;
    }

    public String getD2n() {
        return D2n;
    }

    public void setD2n(String D2n) {
        this.D2n = D2n;
    }

    public String getD3n() {
        return D3n;
    }

    public void setD3n(String D3n) {
        this.D3n = D3n;
    }

    public Student()
    {

    }

    public Student(int id, String firstName, String lastName, String email, String department, String password,
                   String Doc1, String Doc2, String Doc3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.password = password;
        this.Doc1 = Doc1;
        this.Doc2 = Doc2;
        this.Doc3 = Doc3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoc1() {
        return Doc1;
    }

    public void setDoc1(String Doc1) {
        this.Doc1 = Doc1;
    }

    public String getDoc2() {
        return Doc2;
    }

    public void setDoc2(String Doc2) {
        this.Doc2 = Doc2;
    }

    public String getDoc3() {
        return Doc3;
    }

    public void setDoc3(String Doc3) {
        this.Doc3 = Doc3;
    }
}
