public class Main {

    public static void main(String ...args){

        User user = new User();

        SQLScriptor sql = new SQLScriptor();

        System.out.println(sql.createSQLTable(user));
        System.out.println(sql.insertSQLByTableId(user));
        System.out.println(sql.readSQLTable(user));

    }

}
