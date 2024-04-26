package com.example.revatrail2;

public class HelperClass {

    String name,description,link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description =description;
    }

    public String getlink() {
        return link;
    }

    public void setlink(String link)  {
        this.link = link;
    }



    public HelperClass(String name, String description,String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public HelperClass() {
    }
}
