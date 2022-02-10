import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private Connection connection;
    private SQLScriptor sqlScriptor;

    public Repository(){
        connection = ConnectionManager.getConnection();
        sqlScriptor = new SQLScriptor();
    }

    public boolean createTableOnDatabase(Object o){

        try{

            PreparedStatement pstmt = connection.prepareStatement(sqlScriptor.createSQLTable(o));

            pstmt.executeUpdate();
//            System.out.println(sqlSting);
        } catch (Exception e){
            // here we can use out file logger
            e.printStackTrace();
            return false;
        }

            return true;
    }

    public boolean insertIntoTableOnDatabase(Object o) {

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlScriptor.insertSQLByTableId(o));

            Field[] fields = o.getClass().getDeclaredFields();
            for(int i = 0; i<fields.length;i++){
                fields[i].setAccessible(true);
                if(!fields[i].isAnnotationPresent(CustomFieldAnnotation.class)){
                    if(fields[i].getType().equals(Integer.class)){
                        pstmt.setInt((i),(Integer)fields[i].get(o));
                    } else {
                        if(fields[i].getType().equals(String.class)){
                            pstmt.setString((i),(String) fields[i].get(o));
                        }
                    }

                }
                fields[i].setAccessible(false);
            }

            pstmt.executeUpdate();
        } catch(Exception e){
            //file logger
            e.printStackTrace();
        }
        return true;
    }

    public List<Object> readFromTableOnDatabase(Object o){

        List<Object> objects = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlScriptor.readSQLTable(o));

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
               Object obj = o.getClass().getConstructor().newInstance();

               Field[] fields = o.getClass().getDeclaredFields();

               for(Field field: fields){
                   field.setAccessible(true);
                   field.set(obj,rs.getObject(field.getName()));
                   field.setAccessible(false);
               }

                objects.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return objects;
    }

    public boolean updateTableOnDatabase(Object o){

        try {

            PreparedStatement pstmt = connection.prepareStatement(sqlScriptor.updateSQLTable(o));
            Field[] fields = o.getClass().getDeclaredFields();
            int i;
            for(i = 0; i<fields.length;i++){
                fields[i].setAccessible(true);
                if(!fields[i].isAnnotationPresent(CustomFieldAnnotation.class)){
                    if(fields[i].getType().equals(Integer.class)){
                        pstmt.setInt((i),(Integer)fields[i].get(o));
                    } else {
                        if(fields[i].getType().equals(String.class)){
                            pstmt.setString((i),(String) fields[i].get(o));
                        }
                    }
                }
                fields[i].setAccessible(false);
            }
            fields[0].setAccessible(true);
                pstmt.setInt(i, (Integer) fields[0].get(o));
            fields[0].setAccessible(false);
            pstmt.executeUpdate();


        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean deleteFromTableOnDatabase(Object o){
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlScriptor.deleteSQLTable(o));
            Field[] fields = o.getClass().getDeclaredFields();
            fields[0].setAccessible(true);
            pstmt.setInt(1, (Integer) fields[0].get(o));
            fields[0].setAccessible(false);
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


}

