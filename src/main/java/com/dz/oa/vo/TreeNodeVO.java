package com.dz.oa.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by daweizhuang on 9/7/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNodeVO {
    private String id;
    private String value;
    private String type;
    private String icon;
    private TreeNodeStateVO state;
    private Object children;
    @JsonProperty(value = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty(value = "text")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @JsonProperty(value = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonProperty(value = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    @JsonProperty(value = "state")
    public TreeNodeStateVO getState() {
        return state;
    }

    public void setState(TreeNodeStateVO state) {
        this.state = state;
    }

    @JsonProperty(value = "children")
    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}