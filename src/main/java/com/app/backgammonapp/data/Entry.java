package com.app.backgammonapp.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.HashMap;

@Document(indexName = "entryindex")
public class Entry {

    @Id
    private String id;

    @Field(type = FieldType.Date, name = "@timestamp")
    private Date timestamp;

    @Field(type = FieldType.Text, name = "game")
    private String game;

    @Field(type = FieldType.Auto, name = "points")
    private HashMap<String, Float> points;

    public HashMap<String, Float> getPoints() {
        return points;
    }

    public void setPoints(HashMap<String, Float> points) {
        this.points = points;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getId() {return id; }

    public void setId(String id) {this.id = id; }
}
