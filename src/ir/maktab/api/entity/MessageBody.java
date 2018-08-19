package ir.maktab.api.entity;

import com.google.gson.Gson;

public class MessageBody {

    private int statusCode;
    private String message;

    public MessageBody() {
    }

    public MessageBody(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
