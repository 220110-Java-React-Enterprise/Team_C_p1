public class Main {

    public static void main(String ...args){

        User user = new User();
        user.setUserId(11);
        user.setAccountId(100);
        user.setFirstName("donnie");
        user.setLastname("blah");

        SQLScriptor sql = new SQLScriptor();

        System.out.println(sql.createSQLTable(user));
        System.out.println("This is working!!!!!!!!!!");
    }

}
