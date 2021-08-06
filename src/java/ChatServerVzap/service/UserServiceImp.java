package ChatServerVzap.service;

import DAO.DAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class UserServiceImp  implements UserServiceInterface
{
    DAO dao;

    public UserServiceImp() 
    {
        dao = new DAO();
    }

    @Override
    public void registerRequestResponse(HttpServletRequest request, HttpServletResponse response) {
        
        if(Boolean.getBoolean(request.getParameter("isAccept")))
        {
            if(dao.addUser(Integer.parseInt(request.getParameter("requestID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }
        else
        {
            if(dao.denyRegistrationRequest(Integer.parseInt(request.getParameter("requestID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
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
    public void login(HttpServletRequest request, HttpServletResponse response) {
        if((dao.login(request.getParameter("email"),request.getParameter("password").toCharArray()).getName()) != null)
        {
            User user = dao.login(request.getParameter("email"),request.getParameter("password").toCharArray());
            request.setAttribute("user", user);
            request.setAttribute("userID",user.getUserID());
            try {
                request.getRequestDispatcher("HomePage.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                request.getRequestDispatcher("AlumniLogin.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void loginAdmin(HttpServletRequest request, HttpServletResponse response) {
        if(dao.adminLogin(request.getParameter("email"),request.getParameter("password").toCharArray()).getName() != null)
        {
            User user = dao.adminLogin(request.getParameter("email"),request.getParameter("password").toCharArray());
            request.setAttribute("user", user);
            try {
                request.getRequestDispatcher("AlumniLogin.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void registrationRequestCreation(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("name"),request.getParameter("surname"),request.getParameter("email"),request.getParameter("password").toCharArray(),Integer.parseInt(request.getParameter("userID")));
        if(!request.getParameter("cellNum").equalsIgnoreCase(""))
        {
            user.setCellNumber(Integer.parseInt(request.getParameter("cellNum")));
        }
        if(request.getParameter("courseID").isEmpty())
        {
            user.setCourseID(request.getParameter("courseID")   );
        }
        
            if(dao.registrationRequest(user))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        try {
            request.getRequestDispatcher("AlumniLogin.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void makeAdmin(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getUser(Integer.parseInt(request.getParameter("userID"))) != null)
        {
            if(dao.makeAdmin(Integer.parseInt(request.getParameter("userID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
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
    public void banUser(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getUser(Integer.parseInt(request.getParameter("userID"))) != null)
        {
            if(dao.removeUser(Integer.parseInt(request.getParameter("userID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
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
    public void restoreUser(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getUser(Integer.parseInt(request.getParameter("userID"))) != null)
        {
            if(dao.restoreUser(Integer.parseInt(request.getParameter("userID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
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
    public void getFriendsList(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<User> friends = new ArrayList<>();
        friends = dao.getFriends(Integer.parseInt(request.getParameter("userID")));
        
        request.setAttribute("friendList", friends);
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendFriendRequest(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getUser(Integer.parseInt(request.getParameter("userID"))) != null && dao.getUser(Integer.parseInt(request.getParameter("friendID"))) != null)
        {
            if(dao.sendFriendRequest(Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("friendID"))))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
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
    public void friendRequestResponse(HttpServletRequest request, HttpServletResponse response) {
        if(dao.getUser(Integer.parseInt(request.getParameter("friendID"))) != null)
        {
            if(Boolean.getBoolean(request.getParameter("isAccept")))
            {
                if(dao.acceptFriendRequest(Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("friendID"))))
                {
                    request.setAttribute("Successful", "Successful");
                }
                else
                {
                    request.setAttribute("Successful", "failed");
                }
            }
            else
            {
                if(dao.denyFriendRequest(Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("friendID"))))
                {
                    request.setAttribute("Successful", "Successful");
                }
                else
                {
                    request.setAttribute("Successful", "failed");
                }
            }
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
    public void updateUser(HttpServletRequest request, HttpServletResponse response) {
        User user;
        if((user = dao.getUser(Integer.parseInt(request.getParameter("userID")))).getName() != null)
        {
            String type = request.getParameter("type");
            if(type.equalsIgnoreCase("email"))
            {
                user.setEmail(request.getParameter("email"));
            }
            else if(type.equalsIgnoreCase("cellNum"))
            {
                user.setCellNumber(Integer.parseInt(request.getParameter("cellNum")));
            }
            else if(type.equalsIgnoreCase("password"))
            {
                user.setPassword(request.getParameter("password").toCharArray());
            }
            if(dao.updateUser(user))
            {
                request.setAttribute("Successful", "Successful");
            }
            else
            {
                request.setAttribute("Successful", "failed");
            }
        }
        else
        {
            request.setAttribute("Successful", "failed");  
        }
        request.setAttribute("user", user);
        try {
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
