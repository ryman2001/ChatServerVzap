package DAO;

import java.io.FileReader;
import models.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO implements DAOInterface{

    private Connection con;
    private Properties prop;
    private InputStream is;
    private PreparedStatement ps,ps2,ps3;
    private ResultSet rs,rs2;
    
    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           System.out.println(ex.getMessage());
            System.out.println("Driver Not Found");
            System.exit(0);
        }
        
       
        try {
           prop = new Properties();
           prop.load(new FileReader("C:\\Users\\ryanl\\OneDrive\\Desktop\\Java\\module2Projects\\ChatServerVzap\\src\\java\\DAO\\DAOProperties.properties"));
           con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("un"),prop.getProperty("pw"));
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //USERS 
    
    @Override
    public boolean addUser(int requestID) {
       int rowsAffected =0;
        if(con!=null){
            try {
                
                ps3 = con.prepareStatement("INSERT INTO registrationrequesttbl(Accepted,RegistrationDate)"+
                                           " VALUES('Y',curdate()) WHERE RequestID = ? ");
                ps3.setInt(1,requestID);
                
                ps = con.prepareStatement("SELECT FROM pendingusertbl Name,Surname,UserID,Email,"+
                                          "ContactNumber,CourseNumber,Password WHERE RequestID = ?");
                ps.setInt(1,requestID);
                rs = ps.executeQuery();
                
                ps2 = con.prepareStatement("INSERT INTO usertbl(Name,Surname,UserID,Email,"+
                                          "ContactNumber,CourseNumber,Password)"+
                                          "VALUES (?,?,?,?,?,?,?)");
              while(rs.next()){
                   
                ps2.setString(1, rs.getString("Name"));
                ps2.setString(2, rs.getString("Surname"));
                ps2.setInt(3, rs.getInt("UserID"));
                ps2.setString(4, rs.getString("Email"));
                ps2.setInt(5,rs.getInt("ContactNumber"));
                ps2.setString(6,rs.getString("CourseNumber"));
                ps2.setString(7, rs.getString("Password"));
                }
                
                rowsAffected = ps2.executeUpdate() + ps3.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 8;
    }

    @Override
    public boolean makeAdmin(int id) {
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE usertbl SET Admin = '1' WHERE UserID = ?");
                ps.setInt(1, id);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean updateUser(User user){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE usertbl SET Name = ?,Surname = ?, Email = ?,"+
                                          "ContactNumber = ?, CourseNumber = ?, Password = ? "+
                                          "WHERE UserID = ?");
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getEmail());
                ps.setInt(4,user.getCellNumber());
                ps.setString(5, user.getCourseID());
                ps.setString(6, String.valueOf(user.getPassword()));
                ps.setInt(7,user.getUserID());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean removeUser(int id){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE usertbl SET Removed = 'Y' WHERE UserID = ?");
                ps.setInt(1, id);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
 
    @Override
    public boolean registrationRequest(User user){
        int rowsAffected = 0;
        String pw;
        if(con!=null){
            try {
                ps2 = con.prepareStatement("INSERT INTO pendingusertbl(Name,Surname,UserID,Email,"+
                                          "ContactNumber,CourseNumber,Password)"+
                                          "VALUES (?,?,?,?,?,?,?)");
                ps2.setString(1, user.getName());
                ps2.setString(2, user.getSurname());
                ps2.setInt(3, user.getUserID());
                ps2.setString(4, user.getEmail());
                ps2.setInt(5,user.getCellNumber());
                ps2.setString(6,user.getCourseID());
                ps2.setString(7, pw = String.valueOf(user.getPassword()));
                ps2.executeUpdate();
                
                ps3 = con.prepareStatement("SELECT RequestID FROM pendingusertbl WHERE Email = ?");
                ps3.setString(1,user.getEmail());
                rs = ps3.executeQuery();
                rs.next();
                int id = rs.getInt("RequestID");
                
                ps = con.prepareStatement("INSERT INTO registrationrequesttbl(Accepted,RequestDate,RequestID)"+
                                          "VALUES('N',curdate(),?)");
                ps.setInt(1,id);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    if(ps!=null)
                        ps.close();
                    if(ps2!=null)
                        ps2.close();
                    if(ps3!=null)
                        ps3.close();
                    if(rs!=null)
                        rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public  boolean denyRegistrationRequest(int requestID){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE registrationrequesttbl SET Accept = 'D' WHERE RequestId = ?");
                ps.setInt(1,requestID);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected ==1;
    }
    
    @Override
    public User login(String email, char[] password){
        User user = new User();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT* FROM usertbl WHERE Email = ? AND Password = ?");
                ps.setString(1,email);
                ps.setString(2,String.valueOf(password));
                rs = ps.executeQuery();
                while(rs.next()){
                    user.setName(rs.getString("Name"));
                    user.setSurname(rs.getString("Surname"));
                    user.setCellNumber(rs.getInt("ContactNumber"));
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setCourseID(rs.getString("CourseNumber"));
                    user.setUserID(rs.getInt("UserID"));
                }
                                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }
    
    @Override
    public User adminLogin(String email, char [] password){
         User user = new User();
        if(con!=null){
            try {
                ps2 = con.prepareStatement("SELECT Admin FROM usertbl WHERE Email = ? AND Password = ?");
                rs2 = ps2.executeQuery();          
                while(rs.next()){
                    user.setEmail(email);
                    user.setPassword(password);
                }
                if(rs2.getBoolean("Admin")== true){
                ps = con.prepareStatement("SELECT* FROM usertbl WHERE Email = ? AND Password = ?");
                ps.setString(1,email);
                ps.setString(2,String.valueOf(password));
                rs = ps.executeQuery();
                while(rs.next()){
                    user.setName(rs.getString("Name"));
                    user.setSurname(rs.getString("Surname"));
                    user.setCellNumber(rs.getInt("ContactNumber"));
                    user.setCourseID(rs.getString("CourseNumber"));
                }
                }else{
                    user = null;
                }
                                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    ps2.close();
                    rs.close();
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }
    
    @Override
    public boolean restoreUser(int id){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE usertbl SET Removed = 'N' WHERE UserID = ?");
                ps.setInt(1, id);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override 
    public User getUser(int userId){
        User user = new User();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Email,Name,Surname,ContactNumber,CourseNumber,password FROM usertbl WHERE UserID = ?");
                ps.setInt(1,userId);
                
                rs = ps.executeQuery();
                while(rs.next()){
                    user.setUserID(userId);
                    user.setName(rs.getString("Name"));
                    user.setSurname(rs.getString("Surname"));
                    user.setCellNumber(rs.getInt("ContactNumber"));
                    user.setCourseID(rs.getString("CourseNumber"));
                    user.setEmail(rs.getString("Email"));
                    user.setPassword(rs.getString("password").toCharArray());
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }
    
  //GROUPS
    
    @Override
    public boolean createGroup(Group group){
        int rowsAffected = 0;
       if(con!=null){
           try {
               ps = con.prepareStatement("INSERT INTO grouptbl VALUES(?,?,?,?,curdate())");
               ps.setInt(1, group.getGroupID());
               ps.setString(2, group.getName());
               ps.setInt(3, group.getOwnerID());
               ps.setString(4, group.getDescription());
               
               rowsAffected = ps.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
           }finally{
               try {
                   ps.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return rowsAffected == 5;
    }

    @Override
    public boolean updateGroup(Group group){
             int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE grouptbl SET groupName = ?, ownerId = ?, description = ? WHERE groupId = ?");
                ps.setString(1,group.getName());
                ps.setInt(2, group.getOwnerID());
                ps.setString(3, group.getDescription());
                ps.setInt(4, group.getGroupID());
                
                rowsAffected = ps.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 4;
    }

    @Override
    public boolean deleteGroup(int groupId) {
       int rowsAffected = 0;
       if(con!= null){
           try {
               ps = con.prepareStatement("DELETE FROM grouptbl WHERE groupId = ?");
               ps.setInt(1, groupId);
               
               rowsAffected = ps.executeUpdate();
               
           } catch (SQLException ex) {
               Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
           }finally{
               try {
                   ps.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return rowsAffected == 1;
    }
    
    @Override
    public boolean inviteToGroup(int groupId,int senderId, int recipientId){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO grouprequesttbl(GroupId,SenderId,RecipientId) VALUES(?,?,?)");
                ps.setInt(1, groupId);
                ps.setInt(2, senderId);
                ps.setInt(3,recipientId);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected ==1;        
    }
    
    @Override
    public boolean acceptGroupInvite(int groupId, int recipientId){
        int rowsAffected =0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE grouprequesttbl SET Accepted ='Y'");
                ps2 = con.prepareStatement("INSERT INTO linkgrouptbl VALUES(?,?)");
                ps2.setInt(1, groupId);
                ps2.setInt(2, recipientId);
                
                rowsAffected = ps.executeUpdate() + ps2.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 3;
    }
    
    @Override
    public boolean denyGroupInvite(int groupId, int recipientId){
        int rowsAffected =0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE grouprequesttbl SET Accepted ='D'");
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean removeFromGroup(int groupId,int userId){
        int rowsAffected = 0;
        if(con!= null){
            try {
                ps = con.prepareStatement("DELETE FROM linkgrouptbl WHERE groupID = ? AND userID = ?");
                ps.setInt(1, groupId);
                ps.setInt(2, userId);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public Group getGroup(int groupId){
        Group group = new Group();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT groupName,ownerID,description,dateCreated "+
                                          "FROM grouptbl WHERE groupID = ?");
                ps.setInt(1, groupId);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    group.setGroupID(groupId);
                    group.setName(rs.getString("groupName"));
                    group.setOwnerID(rs.getInt("ownerID"));
                    group.setDescription(rs.getString("description"));
                    group.setDateCreated(rs.getDate("dateCreated"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return group;
    }
    
  //FRIENDS
    
    @Override
    public ArrayList<User> getFriends(int id){
        ArrayList<User> arr = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Name,Surname,usertbl.UserID,ContactNumber,Email,courseNumber FROM usertbl,friendstbl WHERE"+
                                         " usertbl.UserID = friendstbl.FriendID OR"+
                                         " usertbl.UserID = friendstbl.UserID AND"+
                                         " friendstbl.UserID = ? OR friendstbl.FriendID = ?");
                ps.setInt(1,id);
                ps.setInt(2,id);
                rs = ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt("UserID")!=id)
                    {
                       User user = new User();
                       user.setName(rs.getString("Name"));
                       user.setSurname(rs.getString("Surname"));
                       user.setUserID(rs.getInt("UserID"));
                       user.setCourseID(rs.getString("courseNumber"));
                       user.setCellNumber(rs.getInt("contactNumber"));
                       user.setEmail(rs.getString("Email"));
                       arr.add(user);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    if(rs!=null)
                        rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arr;
    }
    
    @Override
    public ArrayList<User> getAllUsers(){
        ArrayList<User> arr = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Name,Surname FROM usertbl");
               
                rs = ps.executeQuery();
                while(rs.next()){
                   User user = new User();
                   user.setName(rs.getString("Name"));
                   user.setSurname(rs.getString("Surname"));
                   arr.add(user);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arr;
    }
    
    @Override
    public boolean sendFriendRequest(int senderID,int recipientID){
        int rowsAffected = 0;
        try {
            ps = con.prepareStatement("INSERT INTO friendrequesttbl values(?,?)");
            ps.setInt(1,senderID);
            ps.setInt(2, recipientID);
            
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean acceptFriendRequest(int senderID,int recipientID){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE friendrequesttbl SET Accepted = 'Y' "+
                                          "WHERE SenderID = ? AND RecipientID = ?");
                ps.setInt(1, senderID);
                ps.setInt(2, recipientID);
                
                ps2 = con.prepareStatement("INSERT INTO friendstbl(UserID,FriendID) VALUES(??)");
                ps2.setInt(1, senderID);
                ps2.setInt(2,recipientID);
                
                ps3 = con.prepareStatement("INSERT INTO friendstbl(UserID,FriendID) VALUES(??)");
                ps3.setInt(1, recipientID);
                ps3.setInt(2,senderID);
                
                rowsAffected = ps.executeUpdate() + ps2.executeUpdate() +ps3.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    ps2.close();
                    ps3.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 3;
    }
    
    @Override
    public  boolean denyFriendRequest(int senderID,int recipientID){
        int rowsAffected = 0;
        if(con!=null){
            try {
               ps = con.prepareStatement("UPDATE friendrequesttbl SET Accepted = 'D' "+
                                          "WHERE SenderID = ? AND RecipientID = ?");
                ps.setInt(1, senderID);
                ps.setInt(2, recipientID);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public ArrayList<User> getReceivedRequests(int userId){
        ArrayList<User> allRequests = new ArrayList();
        User sender = new User();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT* FROM friendrequesttbl WHERE RecipientID = ?");
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while(rs.next()){
                    ps2 = con.prepareStatement("SELECT Name,Surname FROM usertbl WHERE UserID = ?");
                    ps2.setInt(1, rs.getInt("SenderID"));
                    rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        sender.setName(rs2.getString("Name"));
                        sender.setSurname(rs.getString("Surname"));
                        allRequests.add(sender);
                    }
                
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    if(ps2!=null)
                        ps2.close();
                    rs.close();
                    if(rs2!=null)
                        rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return allRequests;
    }
    
    @Override 
    public ArrayList<User> getSentRequests(int senderId){
        ArrayList<User> allSent = new ArrayList();
         User recipient = new User();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT* FROM friendrequesttbl WHERE SenderID = ?");
                ps.setInt(1, senderId);
                rs = ps.executeQuery();
                while(rs.next()){
                    ps2 = con.prepareStatement("SELECT Name,Surname FROM usertbl WHERE UserID = ?");
                    ps2.setInt(1, rs.getInt("RecipientID"));
                    rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        recipient.setName(rs2.getString("Name"));
                        recipient.setSurname(rs.getString("Surname"));
                        allSent.add(recipient);
                    }
                
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    ps2.close();
                    rs.close();
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return allSent;
    }
    
 ///MESSAGES
    @Override
    public boolean composeMessage(Message message){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO messagetbl(OwnerID,RecipientID,Message,TimeSent)"+
                                          " VALUES(?,?,?,now())");
                ps.setInt(1, message.getOwnerID());
                ps.setInt(2,message.getReceiverID());
                ps.setString(3,message.getMessage());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected ==1;
    }
  
    @Override
    public boolean deleteMessage(int messageId){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE messagetbl SET Deleted ='Y' WHERE MessageID = ?");
                ps.setInt(1, messageId);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected ==1;
    }
    
    @Override
    public boolean flagMessage(int messageId,int flaggerId, String reason){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO flagdedtbl(MessageID,FlaggerID,Reason,DateFlagged) VALUES(?,?,?,curdate())");
                ps.setInt(1, messageId);
                ps.setInt(2,flaggerId);
                ps.setString(3,reason);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 3;
    }
    
    @Override
    public boolean readMessage(int messageId){
        int rowsAffected =0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE messagetbl SET Viewed ='Y' WHERE MessageID = ?");
                ps.setInt(1,messageId);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public int showUnread(int userID,int recipientID){
        int unread = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Message FROM messagetbl WHERE "+
                                          "Viewed ='N' AND OwnerID = ? AND RecipientID = ?");
                ps.setInt(1, userID);
                ps.setInt(2, recipientID);
                
                rs = ps.executeQuery();
                while(rs.next()){
                    unread++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return unread;
    }
    
    @Override
    public Message getMessage(int messageID){
        Message msg = new Message();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Message,OwnerID,TimeSent FROM messagetbl WHERE MessageID = ?");
                ps.setInt(1,messageID);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    msg.setMessageID(messageID);
                    msg.setOwnerID(rs.getInt("OwnerID"));
                    msg.setMessage(rs.getString("Message"));
                    msg.setTime(rs.getDate("TimeSent"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return msg;
    }
    
    @Override
    public ArrayList<Message> getAllGroupMessages(int groupId){
        ArrayList<Message> arr = new ArrayList();
        Message msg = new Message();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Message,OwnerID,TimeSent FROM messagetbl WHERE RecipientID = ?");
                ps.setInt(1,groupId);
                rs = ps.executeQuery();
                while(rs.next()){
                    msg.setMessage(rs.getString("Message"));
                    msg.setOwnerID(rs.getInt("OwnerID"));
                    msg.setTime(rs.getDate("TimeSent"));
                    
                    arr.add(msg);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
        return arr;
    }
    
//POST
    @Override
    public boolean createArticlePost(Post post){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO posttbl(OwnerID,Infomation,Title,Date,Type) VALUES(?,?,?,curdate(),'Article')");
                ps.setInt(1,post.getOwnerID());
                ps.setString(2,post.getInformation());
                ps.setString(3,post.getTitle());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return rowsAffected == 4;
        
    }
   
    @Override
    public boolean createCodePost(Post post){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO posttbl(OwnerID,Infomation,Title,Date,Type) VALUES(?,?,?,curdate(),'Code')");
                ps.setInt(1,post.getOwnerID());
                ps.setString(2,post.getInformation());
                ps.setString(4,post.getTitle());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return rowsAffected == 4;
        
    }
    
    @Override
    public boolean updatePost(Post post){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE posttbl SET Infomation = ? , Title = ? WHERE PostID = ?");
                ps.setString(1, post.getInformation());
                ps.setString(2, post.getTitle());
                ps.setInt(3, post.getPostID());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected ==2;
    }
    
    @Override
    public boolean deletePost(int postID){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE posttbl SET Deleted = 'Y' WHERE PostID = ?");
                ps.setInt(1, postID);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean readPost(int postID){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE posttbl SET Read ='Y' WHERE PostID = ?");
                ps.setInt(1,postID);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public ArrayList<Post> getAllPosts(){
        ArrayList<Post> allPosts = new ArrayList();
        Post post = new Post();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT PostID,OwnerID,Title,Infomation,TimePosted FROM posttbl");
                rs = ps.executeQuery();
                while(rs.next()){
                    post.setPostID(rs.getInt("PostID"));
                    post.setOwnerID(rs.getInt("OwnerID"));
                    post.setTitle(rs.getString("Title"));
                    post.setUploadTime(rs.getDate("TimePosted"));
                    post.setInformation(rs.getString("Infomation"));
                    allPosts.add(post);
                    post = new Post();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return allPosts;
    }
    
    @Override
    public Post getPost(int postID){
        Post post = new Post();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Infomation,Title,OwnerID,TimePosted"+
                                          " FROM posttbl WHERE PostID = ?");
                ps.setInt(1, postID);
                rs = ps.executeQuery();
                while(rs.next()){
                    post.setOwnerID(rs.getInt("OwnerID"));
                    post.setInformation(rs.getString("Infomation"));
                    post.setTitle(rs.getString("Title"));
                    post.setUploadTime(rs.getDate("TimePosted"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return post;
    }
    
//COMMENT
    @Override
    public boolean createComment(Comment comment){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("INSERT INTO commenttbl(PostID,OwnerID,Comment,Date) VALUES(?,?,?,now())");
                ps.setInt(1, comment.getPostID());
                ps.setInt(2,comment.getOwnerID());
                ps.setString(3,comment.getComment());
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 3;
    }
    
    @Override 
    public boolean deleteComment(int commentId){
        int rowsAffected = 0;
        if(con!=null){
            try {
                ps = con.prepareStatement("UPDATE commenttbl SET Deleted ='Y' WHERE CommentID = ?");
                ps.setInt(1,commentId);
                
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected==1;
    }
    
    @Override
    public Comment getComment(int commentId){
        Comment comment = new Comment();
        if(con!=null){
            try {
                ps = con.prepareStatement("SELECT Comment,PostID,OwnerID,Date FROM"+
                                          " commenttbl WHERE CommentID = ?");
                ps.setInt(1, commentId);
                rs = ps.executeQuery();
                while(rs.next()){
                    comment.setCommentID(commentId);
                    comment.setComment(rs.getString("Comment"));
                    comment.setOwnerID(rs.getInt("OwnerID"));
                    comment.setPostID(rs.getInt("PostID"));
                    comment.setUploadTime(rs.getDate("Date"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return comment;
    }
    
    @Override
    public ArrayList<Comment> getCommentsForPost(int postId) {
       ArrayList<Comment> arr = new ArrayList();
       Comment com = new Comment();
       if(con!=null){
           try {
               ps = con.prepareStatement("SELECT CommentID,OwnerID,Comment,Date "+
                                         "FROM commenttbl WHERE PostID = ?");
               ps.setInt(1, postId);
               
               rs = ps.executeQuery();
               
               while(rs.next()){
                   com.setCommentID(rs.getInt("CommentID"));
                   com.setOwnerID(rs.getInt("OwnerID"));
                   com.setPostID(postId);
                   com.setComment(rs.getString("Comment"));
                   com.setUploadTime(rs.getDate("Date"));
                   
                   arr.add(com);
               }
           } catch (SQLException ex) {
               Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
           }finally{
               try {
                   ps.close();
                   rs.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return arr;
    }
}
