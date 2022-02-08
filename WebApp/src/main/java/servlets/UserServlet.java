package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import User.UserObject;
import User.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
       // we want to reach the database using our user object
        UserObject newUser = UserStore.getUserObject();
        ObjectMapper mapper = new ObjectMapper();
        String Json = mapper.writeValueAsString(newUser);
        resp.getWriter().print(Json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserObject payload = mapper.readValue(req.getInputStream(), UserObject.class);
        UserStore.setUserObject(payload);
        resp.setStatus(203);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
