public interface CRUD<T> {
    public String createSQLTable(T t);
    public String insertSQLByTableId(T t);
    public String readSQLTable(T t);
    public String updateSQLTable(T t);
    public String deleteSQLTable(T t);
}
