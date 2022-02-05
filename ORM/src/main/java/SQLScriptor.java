import java.lang.reflect.Field;

public class SQLScriptor implements CRUD<Object>{
    @Override
    public String createSQLTable(Object o) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("CREATE TABLE ");
        String className = o.getClass().getCanonicalName();
        sb.append(className);
        sb.append(" (");

        Field[] fields = o.getClass().getDeclaredFields();
        for( int i = 0; 1 < fields.length; i++) {
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
        return null;
    }

    @Override
    public String readSQLTable(Object o) {
        return null;
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
