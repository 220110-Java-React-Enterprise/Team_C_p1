public class Main {

    public static void main(String ...args) {

        User user = new User();

        SQLScriptor sql = new SQLScriptor();

        sql.createSQLTable(user);
        System.out.println("/n");
        String insert = sql.insertSQLByTableId(user);
        System.out.println(insert);
        String read = sql.readSQLTable(user);
       // System.out.println(read);
        String update = sql.updateSQLTable(user, " whatever");
       // System.out.println(update);
        String delete = sql.deleteSQLTable(user);
       // System.out.println(delete);
    }
}
