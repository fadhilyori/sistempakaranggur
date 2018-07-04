package dev.anggur.sistempakaranggur.models;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class ResponseUser {
    private boolean isSuccess;
    private String level;

    public ResponseUser(boolean isSuccess, String level) {
        this.isSuccess = isSuccess;
        this.level = level;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getLevel() {
        return level;
    }
}
