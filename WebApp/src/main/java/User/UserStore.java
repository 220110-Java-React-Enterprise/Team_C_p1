package User;

public class UserStore {
    private static UserObject userObject;
    private  static UserObject tempUser;

    public UserStore() {
    }

    public static UserObject getUserObject() {
        return userObject;
    }
    public static void setUserObject(UserObject userObject) {
        UserStore.userObject = userObject;
    }

    public static UserObject getTempUser() {
        return tempUser;
    }

    public static void setTempUser(UserObject tempUser) {
        UserStore.tempUser = tempUser;
    }

}
