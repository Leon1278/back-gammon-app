package com.app.backgammonapp.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.HashMap;

@Document(indexName = "metadata")
public class Aggregation {

    @Id
    private Integer id;

    @Field(type = FieldType.Date, name = "@timestamp")
    private Date timestamp;

    @Field(type = FieldType.Text, name = "game")
    private String game;

    @Field(type = FieldType.Auto, name = "players")
    private HashMap<String, Float> playerCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Float> getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(HashMap<String, Float> playerCount) {
        this.playerCount = playerCount;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
