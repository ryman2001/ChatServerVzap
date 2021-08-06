package DAO;
import models.*;
import java.util.ArrayList;

public interface DAOInterface {
//USERS
    boolean addUser(int requestID); //accept registration request and add user to db
    boolean makeAdmin(int id);//allows a user to make another user an admin
    boolean updateUser(User user);
    boolean removeUser(int id);
    boolean registrationRequest(User user);//creates a new request in the registration requesttable, adds user info to pendingusertbl
    boolean denyRegistrationRequest(int requestID);//declines a registration request
    User login(String email,char[] password);//
    User adminLogin(String email, char [] password);
    boolean restoreUser(int id);
    User getUser(int userID);
    
//GROUPS
    boolean createGroup(Group group);
    boolean updateGroup(Group group);
    boolean deleteGroup(int groupId);
    boolean inviteToGroup(int groupId, int senderId, int recipientId);
    boolean acceptGroupInvite(int groupId, int recipientId);
    boolean denyGroupInvite(int groupId, int recipientId);
    boolean removeFromGroup(int groupId,int userId);
    Group getGroup(int groupId);
    
//FRIENDS
    ArrayList<User> getFriends(int id);
    ArrayList<User> getAllUsers();
    boolean sendFriendRequest(int senderID,int recipientID);
    boolean acceptFriendRequest(int senderID,int recipientID);
    boolean denyFriendRequest(int senderID,int recipientID);
    ArrayList<User> getReceivedRequests(int userId);
    ArrayList<User> getSentRequests(int userId);
    
//Messages
    boolean composeMessage(Message message);
    boolean readMessage(int messageId);
    boolean deleteMessage(int messageId);
    boolean flagMessage(int messageId,int flaggerId,String reason);
    int showUnread(int userID,int recipientID);//gets number of unread messages by a certain user
    Message getMessage(int messageID);
    ArrayList<Message> getAllGroupMessages(int groupId);
    
//POST
    boolean createArticlePost(Post post);
    boolean createCodePost(Post post);
    boolean updatePost(Post post);
    boolean deletePost(int postID);
    boolean readPost(int postID);
    Post getPost(int postID);
    ArrayList<Post> getAllPosts();
    
//COMMENT
    boolean createComment(Comment comment);
    boolean deleteComment(int commentId);
    Comment getComment(int commentId);
    ArrayList<Comment> getCommentsForPost(int postId);
}
