package utils;

public class PostStore {
    private static PostObject postObj;

    public PostStore(){

    }

    public static PostObject getPostObj() {
        return postObj;
    }

    public static void setAccountObj(PostObject postObj) {
        PostStore.postObj = postObj;
    }
}
