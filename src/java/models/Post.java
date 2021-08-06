/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;


public class Post {
    
    private int postID;
    private String information;
    private Date uploadTime; 
    private String title;
    private String type;
    private int ownerID;

    public Post(){
        
    }
    
    public Post(int adminID, String tirle, String information) {
        this.setInformation(information);
        this.setTitle(title);
        this.setType(type);
    }
    public Post(int postID, String information, Date uploadTime, String title, String type, int ownerID){
        this.setInformation(information);
        this.setOwnerID(ownerID);
        this.setPostID(postID);
        this.setTitle(title);
        this.setType(type);
        this.setUploadTime(uploadTime);
        
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
    
    @Override
    public String toString(){
        return "PostID: " + this.postID + " Information: " + this.information + " UploadTime: " + this.uploadTime + " Title: " + this.title + " Type: " + this.type;
    }
    
    
}
