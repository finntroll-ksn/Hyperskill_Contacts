package contacts;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String phone = "[no number]";
    private String creationDate;
    private String lastEditDate;

    public Contact() {
    }

    public void getAllFields() {
    }

    public ArrayList<String> getAllItemField() {
        return null;
    }

    public void changeField(String field, String volume) {
    }

    public String getField(String field) {
        switch (field) {
            case "phone":
                return this.getPhone();
            case "creationDate":
                return this.getCreationDate();
            case "lastEditDate":
                return this.getLastEditDate();
            default:
                return null;
        }
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (checkPhoneNumber(phone)) {
            this.phone = phone;
        } else {
            System.out.println("Wrong number format!");
            this.phone = "[no number]";
        }
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
    }
}
