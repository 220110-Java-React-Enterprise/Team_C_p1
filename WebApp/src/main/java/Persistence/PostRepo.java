package Persistence;

import ORM.Repository;
import Post.PostObject;


import java.util.ArrayList;
import java.util.List;

public class PostRepo {
    private Repository repo = new Repository();

    public void create(PostObject post){

        repo.createTableOnDatabase(post);

    }

    public void insert(PostObject post){

        repo.insertIntoTableOnDatabase(post);

    }

    public List<PostObject> read(PostObject post){
        List<Object> objects = repo.readFromTableOnDatabase(post);
        List<PostObject> posts = new ArrayList<>();

        for(Object o: objects){
            posts.add((PostObject)o);
        }
        return posts;
    }

    public void update(PostObject post){

        repo.updateTableOnDatabase(post);
    }

    public void delete(PostObject post){
        repo.deleteFromTableOnDatabase(post);
    }



}