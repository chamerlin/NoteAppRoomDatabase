package com.example.noteappdatabase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    public Notes(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }
}