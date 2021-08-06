/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;


public class User {
    
    private String name;
    private String surname;
    private String email;
    private char[] password;
    private int cellNumber;
    private int userID;
    private String courseID;
    private ArrayList<User> friendList;
    private boolean isAdmin;
    
    public User(String name, String surname, String email, char[] password, int cellNumber, int userID,String courseID, ArrayList<User> friendList, boolean isAdmin ){
        this.setCellNumber(cellNumber);
        this.setCourseID(courseID);
        this.setEmail(email);
        this.setFriendList(friendList);
        this.setIsAdmin(isAdmin);
        this.setName(name);
        this.setPassword(password);
        this.setSurname(surname);
        this.setUserID(userID);
    }

    public User(String name, String surname, String email, char[] password, int userID) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public User(String name, String surname, String email, char[] password, int cellNumber, int userID) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.cellNumber = cellNumber;
        this.userID = userID;
    }

    public User() {
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public String toString(){
        return "Name: " +  this.name + " Surname: " + this.surname + " Email: " + this.email + " Cell number: " + this.cellNumber + 
                 " CourseID: " + this.courseID + " FriensList: " + this.friendList + " is Admin: " + this.isAdmin;
    }
}
