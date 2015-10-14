package com.loukou.auth.web.config;

public class SubTab {
    private String text;
    private String path;
    
    public SubTab(String text, String path) {
        super();
        this.text = text;
        this.path = path;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    

    

}
