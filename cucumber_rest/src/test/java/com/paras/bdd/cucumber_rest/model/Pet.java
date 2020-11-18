package com.paras.bdd.cucumber_rest.model;

import java.util.Arrays;

public class Pet {
    private String id;
    private String name;
    private String[] photoUrls;
    private TagComponents[] tags;
    private String status;
    private Category category;

    public Pet(){}

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Pet(String id, String name, String[] photoUrls, TagComponents[] tags, String status, Category category) {
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
        this.category = category;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhotoUrls(String[] photoUrls){
        this.photoUrls = photoUrls;
    }

    public void setTags(TagComponents[] tags){
        this.tags = tags;
    }

    @Override
    public String toString() {
        return id + "_" + name + "_" + Arrays.toString(photoUrls) + "_" + Arrays.toString(tags) + "_" + status + "_" + category;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String[] getPhotoUrls(){
        return photoUrls;
    }
    public TagComponents[] getTags() {
        return tags;
    }
    public String getStatus() {
        return status;
    }


    public String getInfo(){
        return this.id + "_" + this.name;
    }
}