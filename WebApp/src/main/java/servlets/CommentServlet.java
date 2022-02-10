package servlets;

import Persistence.CommentRepo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Comment.CommentObject;

import exceptions.CustomException;
import utils.FileLogger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//Let's try to combine all the servlets methods to one parent class
public class CommentServlet extends HttpServlet {

    CommentRepo repo = new CommentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try{
            List<CommentObject> comments = repo.read(new CommentObject());
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(comments);
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
            repo.insert(payload);
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
            repo.insert(payload);
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
            ObjectMapper mapper = new ObjectMapper();
            CommentObject payload = mapper.readValue(req.getInputStream(), CommentObject.class);
            repo.insert(payload);
        } catch (JsonProcessingException e) {
            throw new CustomException("This request cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
