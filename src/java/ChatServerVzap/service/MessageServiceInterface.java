package ChatServerVzap.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MessageServiceInterface 
{
    void composeMessage(HttpServletRequest request,HttpServletResponse response);
    void deleteMessage(HttpServletRequest request,HttpServletResponse response);
    void findMessage(HttpServletRequest request,HttpServletResponse response);
    void flagMessage(HttpServletRequest request,HttpServletResponse response);
    
    void createGroup(HttpServletRequest request,HttpServletResponse response);
    void deleteGroup(HttpServletRequest request,HttpServletResponse response);
    void updateGroup(HttpServletRequest request,HttpServletResponse response);
    void getGroup(HttpServletRequest request,HttpServletResponse response);
    
    void inviteToGroup(HttpServletRequest request,HttpServletResponse response);
    void removeFromGroup(HttpServletRequest request,HttpServletResponse response);
    
    void unreadMessages(HttpServletRequest request,HttpServletResponse response);
}
