
package Servlet;

import ChatServerVzap.service.PostService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Post;


@WebServlet(name = "PostControllerServlet", urlPatterns = {"/PostControllerServlet"})
public class PostControllerServlet extends HttpServlet {
    
    PostService service = new PostService();
  
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("action")){
            case "getPost":
                service.getPost(request, response);            
                break;
            case "updatePost":
                service.updatePost(request, response);
                break;
            default:
                break;  
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("action")){
            case "createPost":
                service.createPost(request, response);
                break;
            case "addComment":
                service.addComment(request, response);
                break; 
            default:
                break;      
        }
    }
      @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        switch(request.getParameter("delete")){
            case "delete post":
                service.deletePost(request, response);
                break;
            case "detete comment":
                service.deleteComment(request, response);
            default:
                break;
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
