public class User {

    //@CustomFieldAnnotation(fieldName = "pk")
    private Integer userId;
    private String firstName;
    private String lastname;
    private Integer accountId;

    public User(){

    }

    public User(Integer userId, String firstName, String lastname, Integer accountId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String toString(){
        return (this.firstName + " " +
                this.lastname + " " +
                this.userId + " " +
                this.accountId);
    }
}