package servlets;

import Comment.CommentStore;
import Post.PostObject;
import User.UserObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import Account.AccountObject;
import Account.AccountStore;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountObject accountObj = AccountStore.getAccountObj();
        ObjectMapper mapper = new ObjectMapper();
        String Json = mapper.writeValueAsString(accountObj);
        resp.getWriter().print(Json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        AccountObject payload = mapper.readValue(req.getInputStream(), AccountObject.class);
        AccountStore.setAccountObj(payload);
        resp.setStatus(203);
        resp.getWriter().print("Account successfully created.");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        AccountObject payload = mapper.readValue(req.getInputStream(), AccountObject.class);
        //AccountStore.updateAccount(payload);
        resp.setStatus(203);
        resp.getWriter().print("There has been a change to the accounts information.");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountStore.setAccountObj(null);
        resp.setStatus(203);
        resp.getWriter().print("Account has been deleted.");
    }
}
