package servlets;

import User.UserObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Comment.CommentObject;
import Comment.CommentStore;
import exceptions.CustomException;
import utils.FileLogger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//Let's try to combine all the servlets methods to one parent class
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try{
            CommentObject commentObj = CommentStore.getCommentObj();
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(commentObj);
            resp.getWriter().print(Json);
            resp.setStatus(200);
        } catch (JsonProcessingException e) {
            throw new CustomException("This comment does not seem to exist!");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CommentObject payload = mapper.readValue(req.getInputStream(), CommentObject.class);
            CommentStore.setCommentObj(payload);
            resp.setStatus(203);
            resp.getWriter().print("New comment added to post.");
        } catch (JsonProcessingException e) {
            throw new CustomException("Unable to process this request.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CommentObject payload = mapper.readValue(req.getInputStream(), CommentObject.class);
            CommentStore.setCommentObj(payload);
            //CommentStore.updateComment(payload);
            resp.setStatus(203);
            resp.getWriter().print("There has been a change to the comment.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This change cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try {
            CommentStore.setCommentObj(null);
            resp.setStatus(203);
            resp.getWriter().print("Comment has been deleted.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This request cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
