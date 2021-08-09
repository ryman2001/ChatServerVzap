package ChatServerVzap.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserServiceInterface 
{
    void registerRequestResponse(HttpServletRequest request,HttpServletResponse response);
    void registrationRequestCreation(HttpServletRequest request,HttpServletResponse response);
    void login(HttpServletRequest request,HttpServletResponse response);
    void loginAdmin(HttpServletRequest request,HttpServletResponse response);
    void makeAdmin(HttpServletRequest request,HttpServletResponse response);
    void banUser(HttpServletRequest request,HttpServletResponse response);
    void restoreUser(HttpServletRequest request,HttpServletResponse response);
    void getFriendsList(HttpServletRequest request,HttpServletResponse response);
    void sendFriendRequest(HttpServletRequest request,HttpServletResponse response);
    void updateUser(HttpServletRequest request,HttpServletResponse response);
    void removeFriend(HttpServletRequest request,HttpServletResponse response);
    void friendRequestAccept(HttpServletRequest request,HttpServletResponse response);
    void friendRequestDecline(HttpServletRequest request,HttpServletResponse response);
}
