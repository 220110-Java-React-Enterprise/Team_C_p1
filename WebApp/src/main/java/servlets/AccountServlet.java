package servlets;

import Comment.CommentObject;
import Persistence.AccountRepo;
import User.UserObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Account.AccountObject;
import exceptions.CustomException;
import utils.FileLogger;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {

    AccountRepo repo = new AccountRepo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try {
            List<AccountObject> accounts = repo.read(new AccountObject());
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(accounts);
            resp.getWriter().print(Json);
            resp.setStatus(200);
        } catch (JsonProcessingException e) {
            throw new CustomException("This account does not seem to exist!");
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
            AccountObject payload = mapper.readValue(req.getInputStream(), AccountObject.class);
            repo.insert(payload);
            resp.setStatus(203);
            resp.getWriter().print("Account successfully created.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This account does not seem to exist!");
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
            AccountObject payload = mapper.readValue(req.getInputStream(), AccountObject.class);
            repo.insert(payload);
            resp.setStatus(203);
            resp.getWriter().print("There has been a change to the accounts information.");
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
        try{
            ObjectMapper mapper = new ObjectMapper();
            AccountObject payload = mapper.readValue(req.getInputStream(), AccountObject.class);
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
