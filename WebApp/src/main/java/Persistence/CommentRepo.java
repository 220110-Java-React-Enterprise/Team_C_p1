package Persistence;

import Comment.CommentObject;
import ORM.Repository;

import java.util.ArrayList;
import java.util.List;

public class CommentRepo {

    private Repository repo = new Repository();

    public void create(CommentObject comment){

        repo.createTableOnDatabase(comment);

    }

    public void insert(CommentObject comment){

        repo.insertIntoTableOnDatabase(comment);

    }

    public List<CommentObject> read(CommentObject comment){
        List<Object> objects = repo.readFromTableOnDatabase(comment);
        List<CommentObject> posts = new ArrayList<>();

        for(Object o: objects){
            posts.add((CommentObject)o);
        }
        return posts;
    }

    public void update(CommentObject comment){

        repo.updateTableOnDatabase(comment);
    }

    public void delete(CommentObject comment){
        repo.deleteFromTableOnDatabase(comment);
    }


}
