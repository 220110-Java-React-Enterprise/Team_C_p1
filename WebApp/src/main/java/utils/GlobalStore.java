package utils;

public class GlobalStore {
    private static DataObject obj;
    private static DataObject obj2;

    public GlobalStore() {
    }

    public static DataObject getObj() {
        return obj;
    }

    public static void setObj(DataObject obj) {
        GlobalStore.obj = obj;
    }

    public static DataObject getObj2() {
        return obj2;
    }

    public static void setObj2(DataObject obj2) {
        GlobalStore.obj2 = obj2;
    }
}
