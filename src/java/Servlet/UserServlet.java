package Servlet;

import ChatServerVzap.service.UserServiceImp;
import ChatServerVzap.service.UserServiceInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    UserServiceInterface usi = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("action")!=null)
        {
            switch(request.getParameter("action"))
            {
                case"login":
                    usi.login(request, response);
                    //usi.getFriendsList(request, response);
                    break;
                case"loginAdmin":
                    usi.loginAdmin(request, response);
                    break;
                case"updateUser":
                    usi.updateUser(request, response);
                    break;
                case"registerRequestResponse":
                    usi.registerRequestResponse(request, response);
                    break;
                case"registrationRequestCreation":
                    usi.registrationRequestCreation(request, response);
                    break;
                case"makeAdmin":
                    usi.makeAdmin(request, response);
                    break;
                case"banUser":
                    usi.banUser(request, response);
                    break;
                case"restoreUser":
                    usi.restoreUser(request, response);
                    break;
                case"getFriendsList":
                    usi.getFriendsList(request, response);
                    break;
                case"sendFriendRequest":
                    usi.sendFriendRequest(request, response);
                    break;
                case"friendRequestResponse":
                    usi.friendRequestResponse(request, response);
                    break;
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("action")!=null)
        {
            switch(request.getParameter("action"))
            {
                case"login":
                    usi.login(request, response);
                    //usi.getFriendsList(request, response);
                    break;
                case"loginAdmin":
                    usi.loginAdmin(request, response);
                    break;
                case"updateUser":
                    usi.updateUser(request, response);
                    break;
                case"registerRequestResponse":
                    usi.registerRequestResponse(request, response);
                    break;
                case"registrationRequestCreation":
                    usi.registrationRequestCreation(request, response);
                    break;
                case"makeAdmin":
                    usi.makeAdmin(request, response);
                    break;
                case"banUser":
                    usi.banUser(request, response);
                    break;
                case"restoreUser":
                    usi.restoreUser(request, response);
                    break;
                case"getFriendsList":
                    usi.getFriendsList(request, response);
                    break;
                case"sendFriendRequest":
                    usi.sendFriendRequest(request, response);
                    break;
                case"friendRequestResponse":
                    usi.friendRequestResponse(request, response);
                    break;
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
