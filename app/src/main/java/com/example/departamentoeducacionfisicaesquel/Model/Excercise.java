package com.example.departamentoeducacionfisicaesquel.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Excercise {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;

    public Excercise() {
    }

    public Excercise(String name, String shortDescription, String description, String imageName, String category, String imageURL) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.imageName = imageName;
        this.category = category;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

