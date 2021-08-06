/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatServerVzap.service;

import models.Post;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IPostService {
    
   void createPost(HttpServletRequest request, HttpServletResponse response);
    
    void getPost(HttpServletRequest request, HttpServletResponse response);
    
    void updatePost(HttpServletRequest request, HttpServletResponse response);
    
    void deletePost(HttpServletRequest request, HttpServletResponse response);
    
    void addComment(HttpServletRequest request, HttpServletResponse response);
    
    void deleteComment(HttpServletRequest request, HttpServletResponse response);
}
