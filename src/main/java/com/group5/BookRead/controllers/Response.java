package com.group5.BookRead.controllers;

public class Response {
    boolean success = false;

    /**
     * getter
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * setter
     * @param success
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "{"
                + "success=" + success
                + "}";
    }
}
