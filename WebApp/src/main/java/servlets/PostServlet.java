package servlets;

import Persistence.PostRepo;
import User.UserObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Post.PostObject;
import exceptions.CustomException;
import utils.FileLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostServlet extends HttpServlet {

    PostRepo repo = new PostRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try{
            List<PostObject> posts = repo.read(new PostObject());
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(posts);
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
            repo.insert(payload);
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
            repo.update(payload);
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
            ObjectMapper mapper = new ObjectMapper();
            PostObject payload = mapper.readValue(req.getInputStream(), PostObject.class);
            repo.delete(payload);
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
