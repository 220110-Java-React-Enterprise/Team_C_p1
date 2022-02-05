import java.lang.reflect.Field;

public class SQLScriptor implements CRUD<Object>{
    StringBuilder sb = new StringBuilder();
    @Override
    public String createSQLTable(Object o) {
        sb.append("CREATE TABLE ");
        String className = o.getClass().getCanonicalName();
        sb.append(className);
        sb.append(" (");

        Field[] fields = o.getClass().getDeclaredFields();
        for( int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSimpleName().equals("Integer")) {
                String intString;
                if (i == fields.length - 1) {
                    intString = fields[i].getName() + " int";
                } else {
                    intString = fields[i].getName() + " int, ";
                }
                sb.append(intString);
            }
            if (fields[i].getType().getSimpleName().equals("String")) {
                String varString;
                if (i == fields.length - 1) {
                    varString = fields[i].getName() + " varchar(30)";
                } else {
                    varString = fields[i].getName() + " varchar(30), ";
                }
                sb.append(varString);
            }
        }
        sb.append(");");
        return sb.toString();
    }

    @Override
    public String insertSQLByTableId(Object o) {
        sb.append("INSERT INTO ");
        String tableName = o.getClass().getCanonicalName();
        sb.append(tableName);
        sb.append(" (");
        Field[] fields = o.getClass().getDeclaredFields();
        for( int i = 0; i < fields.length; i++) {
                if (i == fields.length - 1) {
                    sb.append(fields[i].getName());
                } else {
                    sb.append(fields[i].getName() + ", ");

                }
            }
        sb.append(") VALUES (");
        for(int i = 0; i < fields.length;i++) {
            if (i == (fields.length - 1)) {
                sb.append(" ?");
            } else {
                sb.append(" ?,");
            }
        }
        String lastPart = ");";
        sb.append(lastPart);
        return sb.toString();
    }

    @Override
    public String readSQLTable(Object o) {
        sb.append("SELECT * FROM ");
        String tableName = o.getClass().getCanonicalName();
        sb.append(tableName);
        sb.append(" WHERE ");
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field: fields){
            if(field.isAnnotationPresent(UserPK.class)){
                sb.append(field.getName());
            }
        }
        sb.append(" = ?");
        return sb.toString();
    }

    @Override
    public String updateSQLTable(Object o) {
        return null;
    }

    @Override
    public String deleteSQLTable(Object o) {
        return null;
    }
}
