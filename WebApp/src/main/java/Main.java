import utils.FileLogger;

import java.sql.SQLException;
import User.UserObject;

public class Main {
    public static void main(String ...args) {

//        System.out.println("This is working!!!!!!!!!!");
//        logMessage("This is our message");
//        methodOne();
       SQLScriptor sqlScript = new SQLScriptor();

    UserObject user = new UserObject();
//        System.out.println(sqlScript.readSQLTable(user));

        System.out.println(sqlScript.updateSQLTable(user,"userId"));
    }


    public static void logMessage(String msg){
        FileLogger.getFileLogger().log(msg);
    }

    public static void logException(Exception e){
        FileLogger.getFileLogger().log(e);
    }


}