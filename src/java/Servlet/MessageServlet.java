package Servlet;

import ChatServerVzap.service.MessageServiceImp;
import ChatServerVzap.service.MessageServiceInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {
    
    MessageServiceInterface msi = new MessageServiceImp();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("action"))
        {
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("action"))
        {
            case"composeMessage":
                msi.composeMessage(request, response);
                break;
            case"deleteMessage":
                msi.deleteMessage(request, response);
                break;
            case"findMessage":
                msi.findMessage(request, response);
                break;
            case"flagMessage":
                msi.flagMessage(request, response);
                break;
            case"createGroup":
                msi.createGroup(request, response);
                break;
            case"deleteGroup":
                msi.deleteGroup(request, response);
                break;
            case"updateGroup":
                msi.updateGroup(request, response);
                break;
            case"getGroup":
                msi.getGroup(request, response);
                break;
            case"inviteToGroup":
                msi.inviteToGroup(request, response);
                break;
                case"removeFromGroup":
                msi.removeFromGroup(request, response);
                break;
            case"unreadMessages":
                msi.unreadMessages(request, response);
                break;
                
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
