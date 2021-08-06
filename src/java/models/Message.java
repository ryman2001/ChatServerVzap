/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;


public class Message {
    
    private int messageID;
    private int ownerID;
    private int receiverID;
    private Date time;
    private String message;
    private boolean isViewed;
    private boolean isDeleted;
    
    public Message(int messageID, int ownerID, int receiverID, Date time, String message, boolean isViewed, boolean isDeleted){
       this.setIsDeleted(isDeleted);
       this.setIsViewed(isViewed);
       this.setMessage(message);
       this.setMessageID(messageID);
       this.setOwnerID(ownerID);
       this.setReceiverID(receiverID);
       this.setTime(time);
    }

    public Message(int ownerID, int receiverID, Date time, String message) {
        this.ownerID = ownerID;
        this.receiverID = receiverID;
        this.time = time;
        this.message = message;
    }

    public Message() {
    }

    
    
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsViewed() {
        return isViewed;
    }

    public void setIsViewed(boolean isViewed) {
        this.isViewed = isViewed;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    @Override
    public String toString(){
        return "MessageID : " + this.messageID + " ownerID: " + this.ownerID + " ReceiverId: " + this.receiverID +
                " Time: " + this.time + "Message:" + this.message;
    }
    
}
