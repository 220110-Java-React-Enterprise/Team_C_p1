import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    private Connection connection;

    public UserRepo(){
        connection = ConnectionManager.getConnection();
    }

    public String createTableOnDatabase(String sqlSting){

        try{

            PreparedStatement pstmt = connection.prepareStatement(sqlSting);
            pstmt.executeUpdate();
            System.out.println(sqlSting);
        } catch (Exception e){
            // here we can use out file logger
            e.printStackTrace();
        }

        return sqlSting;

    }

    public String insertIntoTableOnDatabase(String sqlString,User user) {

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1,user.getUserId());
            pstmt.setString(2,user.getFirstName());
            pstmt.setString(3,user.getLastname());
            pstmt.setInt(4,user.getAccountId());

            pstmt.executeUpdate();
        } catch(Exception e){
            //file logger
            e.printStackTrace();
        }
        return sqlString;
    }

    public User readFromTableOnDatabase(String sqlString,User user){

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1,user.getUserId());

            ResultSet rs = pstmt.executeQuery();

            User inComingUser = new User();

            while (rs.next()) {
                inComingUser.setUserId(rs.getInt("userId"));
                inComingUser.setFirstName(rs.getString("firstName"));
                inComingUser.setLastname(rs.getString("lastname"));
                inComingUser.setAccountId(rs.getInt("accountId"));
            }

            return inComingUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateTableOnDatabase(String sqlString, User user, String update){

        try {

            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(2,user.getUserId()); //second param is the exact row in the table we want to change
            pstmt.setString(1,update); //first param is the change we cant to make

            pstmt.executeUpdate();


        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean deleteFromTableOnDatabase(String sqlString, User user){
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1,user.getUserId());
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


}

