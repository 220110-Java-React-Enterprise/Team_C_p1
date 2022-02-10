import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String ...args) {
        Connection conn = ConnectionManager.getConnection();

        User user = new User("Tiff","obi",12);
        Repository repo = new Repository();
//        repo.createTableOnDatabase(user);
//        repo.insertIntoTableOnDatabase(user);

     List<Object> objs = repo.readFromTableOnDatabase(user);
     for(int i = 0; i<objs.size();i++){
         System.out.println(objs.get(i));
     }

     user = (User)objs.get(2);

     repo.deleteFromTableOnDatabase(user);


    objs = repo.readFromTableOnDatabase(user);
        for(int i = 0; i<objs.size();i++){
            System.out.println(objs.get(i));
        }





    }
}
