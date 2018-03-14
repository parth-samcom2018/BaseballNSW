package com.nsw.baseballnsw.models;

import com.nsw.baseballnsw.DM;

import java.util.Date;

public class MediaComment {
    public int mediaId;
    public int mediaCommentId;
    public String comment;
    public int memberId;
    public String member;
    public String memberAvatar;
    public Date commentDate;

    public String getTimeAgo()
    {
        return DM.getTimeAgo(commentDate);
    }
}
