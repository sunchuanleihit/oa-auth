package com.loukou.auth.web.config;

import java.util.ArrayList;
import java.util.List;

public class ParentTab {
    private String text;
    private List<SubTab> subtabs = new ArrayList<SubTab>();
    private String moduleName;

    public ParentTab(String moduleName,String text, List<SubTab> subtabs) {
        super();
        this.moduleName = moduleName;
        this.text = text;
        this.subtabs = subtabs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SubTab> getSubtabs() {
        return subtabs;
    }

    public void setSubtabs(List<SubTab> subtabs) {
        this.subtabs = subtabs;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

 

}
