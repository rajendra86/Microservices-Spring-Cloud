package com.raju.microservices.springsecurityoauth.dto;

/**
 *
 */
public class User {
    private String userName;
    private char[] crdDtls;
    private String activeInd;

    public String getUserName() {
        return userName;
    }

    public char[] getCrdDtls() {
        return crdDtls;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCrdDtls(char[] crdDtls) {
        this.crdDtls = crdDtls;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }
}
