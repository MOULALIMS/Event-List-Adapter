package com.example.revatrail2;

public class Item {
    private String name;
    private String description;
    private String link;
    private long timestamp;

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String name, String description, String link, long timestamp) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
