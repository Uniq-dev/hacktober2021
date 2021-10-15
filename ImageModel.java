package com.example.imagelover.retrofit;
// to recive the only particulars as after requesting  from url multiple fields will be returned
public class ImageModel {
    private ImagesResponse urls;

    public ImageModel(ImagesResponse urls) {
        this.urls = urls;
    }

    public ImagesResponse getUrls() {
        return urls;
    }

    public void setUrls(ImagesResponse urls) {
        this.urls = urls;
    }
}
