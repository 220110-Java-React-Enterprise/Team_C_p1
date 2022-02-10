package Persistence;

import ORM.Repository;
import User.UserObject;

import java.util.ArrayList;
import java.util.List;


public class UserRepo {


private Repository repo = new Repository();

    public void create(UserObject user){

        repo.createTableOnDatabase(user);

    }

    public void insert(UserObject user){

        repo.insertIntoTableOnDatabase(user);

    }

    public List<UserObject> read(UserObject user){
       List<Object> objects = repo.readFromTableOnDatabase(user);
       List<UserObject> users = new ArrayList<>();

       for(Object o: objects){
           users.add((UserObject)o);
       }
        return users;
    }

    public void update(UserObject user){

        repo.updateTableOnDatabase(user);
    }

    public void delete(UserObject user){
        repo.deleteFromTableOnDatabase(user);
    }


}
