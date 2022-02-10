package Post;

import ORM.CustomFieldAnnotation;

public class PostObject {
    @CustomFieldAnnotation(fieldName = "pk")
    private Integer postId;

    private String title;
    private String description;
    private Integer accountId; //(FK)

    public PostObject() {
    }

    public PostObject(Integer postId, String title, String description, Integer accountId) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.accountId = accountId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
