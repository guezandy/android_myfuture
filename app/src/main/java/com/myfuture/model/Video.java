package com.myfuture.model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseFile;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given shot
 */

@ParseClassName("Video")
public class Video extends ParseObject {

    public Video() {
        // A default constructor is required.
    }

    public static ParseQuery<Video> getQuery() {
        return ParseQuery.getQuery(Video.class);
    }

    public ParseUser getAuthor() {
        return getParseUser("user");
    }

    public void setAuthor(ParseUser user) {
        put("user", user);
    }

    public void setTitle(String points) {
        put("title", points);
    }

    public String getTitle() {
        return this.getString("title");
    }

    //for gps
    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint value) {
        put("location", value);
    }

    public String getDescription() {
        return this.getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String url) {
        put("url", url);
    }

    public void setImage(ParseFile file) {
        put("image", file);
    }

    public ParseFile getImage() {
        return this.getParseFile("image");
    }


    //moethods to remove
    public Boolean isAlwaysFavorite() {
        return false;
    }
}