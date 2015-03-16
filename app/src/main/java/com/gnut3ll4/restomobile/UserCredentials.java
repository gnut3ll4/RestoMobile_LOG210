package com.gnut3ll4.restomobile;

public class UserCredentials {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private String password = "";
    private String username = "";

    public UserCredentials(final SecurePreferences prefs) {
        if (prefs != null) {
            password = prefs.getString(UserCredentials.PASSWORD, "");
            username = prefs.getString(UserCredentials.USERNAME, "");
        }
    }

    public UserCredentials(final String codeU, final String codeP) {
        username = codeU;
        password = codeP;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    public void setUsername(final String username) {
        this.username = username;
    }
    public boolean isLoggedIn() {
        return !"".equals(password) && !"".equals(username);
    }

}