package com.nsw.baseballnsw.models;

import com.nsw.baseballnsw.DM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {

    public int articleId;
    public String shortDescription;
    public String longDescription;
    public Date timeCreated;
    public int authorId;

    public String author;
    public List<ArticleComment> comments = new ArrayList<>();
    public String authorAvatar;

    public String getTimeAgo()
    {
        return DM.getTimeAgo(timeCreated);
    }


    public String getCommentsString()
    {
        if(comments.size() == 1)return "1 comment";
        else return this.comments.size()+" comments";
    }
}
