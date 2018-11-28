package jav;


public class User {

    private String firstName;
    private String lastName;
    private String login;
    private String password;


    public User(String firstName, String lastName, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }


    public User() { }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
