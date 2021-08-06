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
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
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
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        session.setAttribute("message",dao.getMessage(Integer.parseInt(request.getParameter("messageID"))));
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
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        HttpSession session = request.getSession();
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        try {
            if(dao.createGroup(new Group(request.getParameter("name"),format.parse(request.getParameter("date")),Integer.parseInt(request.getParameter("userID")),request.getParameter("description"))))
            {
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        } catch (ParseException ex) {
            Logger.getLogger(MessageServiceImp.class.getName()).log(Level.SEVERE, null, ex);
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
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!=null)
        {
            if(dao.deleteGroup(Integer.parseInt(request.getParameter("groupID"))))
            {
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        HttpSession session = request.getSession();
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.updateGroup(group))
            {
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        HttpSession session = request.getSession();
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.inviteToGroup(Integer.parseInt(request.getParameter("groupID")),Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("receiverID"))))
            {
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        HttpSession session = request.getSession();
        if(dao.getGroup(Integer.parseInt(request.getParameter("groupID"))).getDescription()!= null)
        {
            if(dao.removeFromGroup(Integer.parseInt(request.getParameter("groupID")),Integer.parseInt(request.getParameter("userID"))))
            {
                session.setAttribute("Successful", "Successful");
            }
            else
            {
                session.setAttribute("Successful", "failed");
            }
        }else
        {
            session.setAttribute("Successful", "failed");
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
        HttpSession session = request.getSession();
        session.setAttribute("group", dao.getGroup(Integer.parseInt(request.getParameter("groupID"))));
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
        HttpSession session = request.getSession();
        session.setAttribute("unread", unreadNumber);
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
