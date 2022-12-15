package org.example.sedin.model.listUsersResponse;

import com.google.gson.annotations.SerializedName;

public class Support {

    @SerializedName("text")
    private String text;

    @SerializedName("url")
    private String url;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return
                "Support{" +
                        "text = '" + text + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}