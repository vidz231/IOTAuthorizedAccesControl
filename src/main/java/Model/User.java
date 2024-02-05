package Model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String firstname;
    private boolean isFingerprintRegistered;

    public User() {
    }

    public User(int id, String username, String email, String firstname, boolean isFingerprintRegistered) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.isFingerprintRegistered = isFingerprintRegistered;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isFingerprintRegistered() {
        return isFingerprintRegistered;
    }

    public void setFingerprintRegistered(boolean fingerprintRegistered) {
        isFingerprintRegistered = fingerprintRegistered;
    }
}
