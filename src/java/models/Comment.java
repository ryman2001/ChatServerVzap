/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Jason
 */
public class Comment {
    private int commentID;
    private int ownerID;
    private int postID;
    private String comment;
    private Date uploadTime;
    
    public Comment(){
        
    }
    
    public Comment(int commentID, int ownerID, String comment, Date uplaodTime){
        this.setComment(comment);
        this.setCommentID(commentID);
        this.setOwnerID(ownerID);
        this.setUploadTime(uploadTime);
    }

    public Comment(int commentID, int ownerID, int postID, String comment, Date uploadTime) {
        this.commentID = commentID;
        this.ownerID = ownerID;
        this.postID = postID;
        this.comment = comment;
        this.uploadTime = uploadTime;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
    
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    
    public String toString(){
        return "CommentID: " + this.commentID + " OwnerID: " + this.ownerID + " UploadTime: " + this.uploadTime + " Comment: " + this.comment;
    }
}
