package ChatServerVzap.service;

import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Comment;
import models.Post;

public class PostService implements IPostService {

    private final DAO dao;
    private Post post;
    private Comment comment;
    private boolean isPosted;

    public PostService() {//dependency injection
        dao = new DAO();
    }

    @Override
    public void createPost(HttpServletRequest request, HttpServletResponse response) {
        Date date;
        post = new Post();

        post.setInformation(request.getParameter("information"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            post.setUploadTime(format.parse(request.getParameter("uploadTime")));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        post.setTitle(request.getParameter("title"));
        post.setType(request.getParameter("type"));
        post.setOwnerID(Integer.parseInt(request.getParameter("ownerID")));
        String postResponse = "post failed to upload";
        if (dao.createArticlePost(post)) {
            postResponse = "succesfully posted";
        }
        request.setAttribute("postResponse", postResponse);
    }

    @Override
    public void getPost(HttpServletRequest request, HttpServletResponse response) {
 
        HttpSession session = request.getSession();
        request.setAttribute("getPost",dao.getPost(Integer.parseInt(request.getParameter("postId"))) );
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);//can redirect it to anywhere can change
        } catch (ServletException | IOException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void updatePost(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("updatePost", dao.updatePost(new Post(post.getPostID(), post.getInformation(), post.getUploadTime(), post.getTitle(), post.getType(), post.getOwnerID())) ? "Successfully updated post" : "failed to updated post");
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);//can redirect it to anywhere can change
        } catch (ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void deletePost(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("deletPostPost", dao.deletePost(post.getPostID()) ? "successfully deleted post" : "failed to delete post");
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void addComment(HttpServletRequest request, HttpServletResponse response) {
      
        try {             
            boolean added = dao.createComment(new Comment( Integer.parseInt(request.getParameter("userID")),Integer.parseInt(request.getParameter("postID")), request.getParameter("comment")));
            String commentResponse = "failed to add comment";
            if(added){
                commentResponse = "succesfully commented";
            }
             request.setAttribute("commentResponse", commentResponse);
             request.setAttribute("user", dao.getUser(Integer.parseInt(request.getParameter("userID"))));
             request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteComment(HttpServletRequest request, HttpServletResponse response) {
          request.setAttribute("deleteComment", dao.deleteComment(comment.getCommentID()) ? "successfully deleted comment" : "failed to delete comment");
        try {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

}
