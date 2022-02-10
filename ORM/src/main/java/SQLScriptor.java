import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Field;

public class SQLScriptor implements CRUD<Object>{

   // private Object o;
    //private String columnName;

    @Override
    public String createSQLTable(Object o) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ");
        String className = o.getClass().getSimpleName();
        sb.append(className);
        sb.append(" (");

        Field[] fields = o.getClass().getDeclaredFields();


        for( int i = 0; i < fields.length; i++) {
            if(fields[i].isAnnotationPresent(CustomFieldAnnotation.class)){

                if(fields[i].getAnnotation(CustomFieldAnnotation.class).fieldName().equals("pk")) {
                    String intString;
                    if (i == fields.length - 1) {
                        intString = fields[i].getName() + " int auto_increment not null,";
                        intString += "CONSTRAINT "+o.getClass().getSimpleName()+"_pk PRIMARY KEY ("+fields[i].getName()+")";
                    } else {
                        intString = fields[i].getName() + " int auto_increment not null, ";
                        intString += "CONSTRAINT "+o.getClass().getSimpleName()+"_pk PRIMARY KEY ("+fields[i].getName()+"),";
                    }

                    sb.append(intString);
                }
            } else {
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

        }
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String insertSQLByTableId(Object o) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        String tableName = o.getClass().getSimpleName();
        sb.append(tableName);
        sb.append(" (");
        Field[] fields = o.getClass().getDeclaredFields();
        for( int i = 0; i < fields.length; i++) {
            if(!fields[i].isAnnotationPresent(CustomFieldAnnotation.class)) {
                if (i == fields.length - 1) {
                    sb.append(fields[i].getName());
                } else {
                    sb.append(fields[i].getName() + ", ");
                }
            }
        }
        sb.append(") VALUES (");
        for(int i = 0; i < fields.length;i++) {
            if(!fields[i].isAnnotationPresent(CustomFieldAnnotation.class)) {
                if (i == (fields.length - 1)) {
                    sb.append("?");
                } else {
                    sb.append("?,");
                }
            }
        }
        String lastPart = ");";
        sb.append(lastPart);
        return sb.toString();
    }

    @Override
    public String readSQLTable(Object o) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM ");
        String tableName = o.getClass().getSimpleName();
        sb.append(tableName);

        return sb.toString();
    }


@Override
    public String updateSQLTable(Object o){
    StringBuilder sb = new StringBuilder();

//        "UPDATE customers SET account_id = ? WHERE customer_id = ?"

            sb.append("UPDATE ");
            String tableName  = o.getClass().getSimpleName();
            sb.append(tableName);
            sb.append(" SET ");

                String primaryKey = "";
        Field[] fields = o.getClass().getDeclaredFields();
    for( int i = 0; i < fields.length; i++) {
        if(!fields[i].isAnnotationPresent(CustomFieldAnnotation.class)) {
            if (i == fields.length - 1) {
                sb.append(fields[i].getName()+"= ? ");
            } else {
                sb.append(fields[i].getName() + " = ? , ");
            }
        } else {
            primaryKey = fields[i].getName();
        }
    }

    sb.append(" WHERE ");
    sb.append(primaryKey);

        sb.append(" = ?;");

        return sb.toString();
    }


//deleteSQL mehtod deletes a customer by user input. method is updated in CRUD. user input is updated in Main.
@Override
    public String deleteSQLTable(Object o) {
        //this.o = o;
       // this.columnName = columnName;
    StringBuilder sb = new StringBuilder();

       // DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste'
        sb.append("DELETE FROM ");
        String tableName  = o.getClass().getSimpleName();
        sb.append(tableName);
        sb.append(" WHERE ");

        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field: fields){
            if(field.isAnnotationPresent(CustomFieldAnnotation.class)){

                sb.append(field.getName());
            }
        }

        sb.append(" = ?;");

        return sb.toString();
    }


    }
