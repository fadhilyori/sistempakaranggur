package dev.anggur.sistempakaranggur.models;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ResponseUser {
    private boolean isSuccess;
    private String user;

    public ResponseUser(boolean isSuccess, String user) {
        this.isSuccess = isSuccess;
        this.user = user;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getUser() {
        return user;
    }
}
