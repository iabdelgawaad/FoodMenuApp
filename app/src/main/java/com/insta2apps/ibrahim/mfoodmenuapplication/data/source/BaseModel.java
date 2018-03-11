package com.insta2apps.ibrahim.mfoodmenuapplication.data.source;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class BaseModel implements Serializable {
    private String errorCode;
    private String error;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return error;
    }

    public void setMessage(String message) {
        this.error = message;
    }
}
