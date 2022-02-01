package utils;

public class CommentObject {
    private Integer commentId;
    private String message;
    private Integer postId;//(FK)


    public CommentObject() {
    }

    public CommentObject(Integer commentId, String message,Integer postId) {
        this.commentId = commentId;
        this.message = message;
        this.postId = postId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
