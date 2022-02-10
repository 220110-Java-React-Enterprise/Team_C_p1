package Persistence;

import Account.AccountObject;
//import Comment.CommentObject;
import ORM.Repository;
//import Post.PostObject;

import java.util.ArrayList;
import java.util.List;

public class AccountRepo {

    private final Repository repo = new Repository();

    public void insert(AccountObject account){

        repo.insertIntoTableOnDatabase(account);

    }

    public List<AccountObject> read(AccountObject account){
        List<Object> objects = repo.readFromTableOnDatabase(account);
        List<AccountObject> accounts = new ArrayList<>();

        for(Object o: objects){
            accounts.add((AccountObject)o);
        }
        return accounts;
    }

    public void update(AccountObject account){

        repo.updateTableOnDatabase(account);
    }

    public void delete(AccountObject account){
        repo.deleteFromTableOnDatabase(account);
    }
}
