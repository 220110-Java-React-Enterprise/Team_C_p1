package servlets;

import User.UserObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import Comment.CommentObject;
import Comment.CommentStore;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentObject commentObj = CommentStore.getCommentObj();
        ObjectMapper mapper = new ObjectMapper();
        String Json = mapper.writeValueAsString(commentObj);
        resp.getWriter().print(Json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        CommentObject payload = mapper.readValue(req.getInputStream(), CommentObject.class);
        CommentStore.setCommentObj(payload);
        resp.setStatus(203);
        resp.getWriter().print("New comment added to post.");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        CommentObject payload = mapper.readValue(req.getInputStream(), CommentObject.class);
        //CommentStore.updateComment(payload);
        resp.setStatus(203);
        resp.getWriter().print("There has been a change to the comment.");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentStore.setCommentObj(null);
        resp.setStatus(203);
        resp.getWriter().print("Comment has been deleted.");
    }
}
