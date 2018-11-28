package jav;

public class Text {
    private String login;
    private String textFiles;
    private String date;

    Text(){}

    Text(String login, String  textFiles, String date){
        this.login = login;
        this.textFiles = textFiles;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTextFiles() {
        return textFiles;
    }

    public void setTextFiles(String textFiles) {
        this.textFiles = textFiles;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
