package com.example.pertemuan10_readjson;

import com.google.gson.annotations.SerializedName

public class ServerResponse {

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    String getMessage() {
        return message;
    }
    boolean getSuccess() {
        return success;
    }
}