package servlets;

import Account.AccountStore;
import User.UserObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Post.PostObject;
import Post.PostStore;
import exceptions.CustomException;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try{
            PostObject postObj = PostStore.getPostObj();
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(postObj);
            resp.getWriter().print(Json);
            resp.setStatus(200);
        } catch (JsonProcessingException e) {
            throw new CustomException("This post does not seem to exist!");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            PostObject payload = mapper.readValue(req.getInputStream(), PostObject.class);
            PostStore.setPostObj(payload);
            resp.setStatus(203);
            resp.getWriter().print("User has added a new post.");
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
        try{
            ObjectMapper mapper = new ObjectMapper();
            PostObject payload = mapper.readValue(req.getInputStream(), PostObject.class);
            PostStore.setPostObj(payload);
            //PostStore.updatePost(payload);
            resp.setStatus(203);
            resp.getWriter().print("The user has updated their post.");
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
            PostStore.setPostObj(null);
            resp.setStatus(203);
            resp.getWriter().print("Post has been deleted.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This request cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
