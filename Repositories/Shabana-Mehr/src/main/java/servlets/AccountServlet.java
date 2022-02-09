package servlets;

/*import Comment.CommentStore;
import Post.PostObject;
import User.UserObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Account.AccountObject;
import Account.AccountStore;
import exceptions.CustomException;
import utils.FileLogger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws CustomException {
        try {
            AccountObject accountObj = AccountStore.getAccountObj();
            ObjectMapper mapper = new ObjectMapper();
            String Json = mapper.writeValueAsString(accountObj);
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
            AccountStore.setAccountObj(payload);
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
            //AccountStore.updateAccount(payload);
            AccountStore.setAccountObj(payload);
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
            AccountStore.setAccountObj(null);
            resp.setStatus(203);
            resp.getWriter().print("Account has been deleted.");
        } catch (JsonProcessingException e) {
            throw new CustomException("This request cannot be made.");
        } catch (IOException e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
*/