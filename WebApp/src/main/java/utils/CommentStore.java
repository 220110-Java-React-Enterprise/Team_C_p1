package utils;

public class CommentStore {
    private static CommentObject commentObj;

    public CommentStore() {
    }

    public static CommentObject getCommentObj() {
        return commentObj;
    }

    public static void setCommentObj(CommentObject commentObj) {
        CommentStore.commentObj = commentObj;
    }
}
