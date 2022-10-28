package com.foodproject;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class DBcontent implements Serializable {


    @Exclude
    private String key;
    private String name;
    private String thumnail;
    //private String price;
    private String content;
    private String location;
    private String sellar;
    private String primary_key;

    public DBcontent(String name, String content)
        {
            this.name = name;
            //this.price = price;
            this.content = content;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        /*
        public String getPrice ()
        {
            return price;
        }

        public void setPrice (String price)
        {
            this.price = price;
        }
         */

        public String getContent () { return content; }

        public void setContent (String content) { this.content = content; }

        public String getKey ()
        {
            return key;
        }

        public void setKey (String key)
        {
            this.key = key;
        }
}