/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Date;


public class Group {
    private String name;
    private int groupID;
    private Date dateCreated;
    private int ownerID;
    private ArrayList<User> listUser;
    private String Description;
    
    public Group(){
        
    }
    
    public Group(String name, int groupID, Date dateCreated, int ownerID, ArrayList<User> listUser,String Description){
        this.setDateCreated(dateCreated);
        this.setGroupID(groupID);
        this.setListUser(listUser);
        this.setName(name);
        this.setOwnerID(ownerID);
        this.setDescription(Description);
        
    }

    public Group(String name, Date dateCreated, int ownerID,String description) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.ownerID = ownerID;
        this.Description = description;
    }

    public Group(String name, int ownerID, String Description) {
        this.name = name;
        this.ownerID = ownerID;
        this.Description = Description;
    }
   
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
    
    @Override
    public String toString(){
        return "Name: " + this.name + " GroupID: " + this.groupID + " Date Created: " + this.dateCreated + 
                " OwnerID: " + this.ownerID;
    }
}
