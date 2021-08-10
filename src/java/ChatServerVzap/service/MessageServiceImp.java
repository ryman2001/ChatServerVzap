package ChatServerVzap.service;

import DAO.DAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Group;
import models.Message;

public class MessageServiceImp implements MessageServiceInterface
{
    DAO dao;

    public MessageServiceImp() {
        dao = new DAO();
    }

    @Override
    public void composeMessage(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Message message = new Message();
        try {
            message = new Message(Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("receiverID")),format.parse(request.getParameter("Date")),request.getParameter("message"));
        } catch (ParseException ex) {
            Logger.getLogger(MessageServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession();
            if(dao.composeMessage(message))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(dao.getMessage(Integer.parseInt(request.getParameter("messageID"))).getMessage()!=null)
        {
            if(dao.deleteMessage(Integer.parseInt(request.getParameter("messageID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void findMessage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        request.setAttribute("message",dao.getMessage(Integer.parseInt(request.getParameter("messageID"))));
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void flagMessage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(dao.getMessage(Integer.parseInt(request.getParameter("messageID"))).getMessage()!=null)
        {
            if(dao.flagMessage(Integer.parseInt(request.getParameter("messageID")),Integer.parseInt(request.getParameter("userID")),request.getParameter("reason")))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createGroup(HttpServletRequest request, HttpServletResponse response) {
        if(dao.createGroup(new Group(request.getParameter("groupName"),Integer.parseInt(request.getParameter("userID")),request.getParameter("groupDes"))))
        {
            request.setAttribute("Successful", "Successful");
        }
        else
        {
            request.setAttribute("Successful", "failed");
        }
        request.setAttribute("user", dao.getUser(Integer.parseInt(request.getParameter("userID"))));
        try {
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!=null)
        {
            if(dao.deleteGroup(Integer.parseInt(request.getParameter("groupID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateGroup(HttpServletRequest request, HttpServletResponse response) {
        Group group = new Group(request.getParameter("name"),Integer.parseInt(request.getParameter("groupID")),request.getParameter("description"));
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.updateGroup(group))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inviteToGroup(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.inviteToGroup(Integer.parseInt(request.getParameter("groupID")),Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("receiverID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeFromGroup(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.removeFromGroup(Integer.parseInt(request.getParameter("groupID")),Integer.parseInt(request.getParameter("userID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }else
        {
            request.setAttribute("Successful", "failed");
        }
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void getGroup(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("group", dao.getGroup(Integer.parseInt(request.getParameter("groupID"))));
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unreadMessages(HttpServletRequest request, HttpServletResponse response) {
        int unreadNumber = dao.showUnread(Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("otherID")));
        request.setAttribute("unread", unreadNumber);
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
