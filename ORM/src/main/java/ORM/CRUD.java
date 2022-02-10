package ORM;

public interface CRUD<T> {
    public String createSQLTable(T t);

    public String insertSQLByTableId(T t);

    public String readSQLTable(T t);

    public String updateSQLTable(Object o, String userId);

    public String deleteSQLTable(T t);


}
