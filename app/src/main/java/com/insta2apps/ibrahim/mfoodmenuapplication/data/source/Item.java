package com.insta2apps.ibrahim.mfoodmenuapplication.data.source;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class Item extends BaseModel{
    private Integer id;
    private String name;
    private String photoUrl;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
