package servlets;

import Persistence.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import User.UserObject;
import exceptions.CustomException;
import utils.FileLogger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    UserRepo repo = new UserRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
//        super.doGet(req, resp);
        // we want to reach the database using our user object

        try {
            List<UserObject> users = repo.read(new UserObject());
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(users);
            resp.getWriter().print(Json);
            resp.setStatus(200);
        } catch (JsonProcessingException e) {
            throw new CustomException("This user does not seem to exist!");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        //UserStore.createUser(payload); ================> keeping this maybe use a parent class(dao)
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserObject payload = mapper.readValue(req.getInputStream(), UserObject.class);
            repo.insert(payload);
            resp.setStatus(203);
            resp.getWriter().print("User has been created.");
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
        //UserStore.updateUser(payload); ================> keeping this maybe use a parent class(dao)
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserObject payload = mapper.readValue(req.getInputStream(), UserObject.class);
            repo.update(payload);
            resp.setStatus(203);
            resp.getWriter().print("There has been a change to the users information.");
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
            UserObject payload = mapper.readValue(req.getInputStream(), UserObject.class);
            repo.delete(payload);
            resp.setStatus(203);
            resp.getWriter().print("User has been deleted.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This request cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
