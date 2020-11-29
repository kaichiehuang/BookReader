package com.group5.BookRead.controllers;

public class Response {
    boolean success = false;
    String msg = "";

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
     * get message
     * @return
     */
    public String getMsg() {
        return msg;
    }


    /**
     * set message
     * @param msg
     */
    public void setMsg(final String msg) {
        this.msg = msg;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Response{"
                + "success=" + success
                + ", msg='" + msg + '\'' + '}';
    }
}
