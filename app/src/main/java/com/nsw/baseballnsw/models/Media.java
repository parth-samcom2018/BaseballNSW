package com.nsw.baseballnsw.models;

import java.util.Date;
import java.util.List;

public class Media {

    public String url;

    public String thumbnail;
    public String caption;
    public Date dateAdded;
    public List<MediaComment> comments;
    public int mediaId;
}
