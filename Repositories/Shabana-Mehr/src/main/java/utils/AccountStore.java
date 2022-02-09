package Account;

public class AccountStore {
    private static AccountObject accountObj;
    private static AccountObject accountObj2;

    public AccountStore(){

    }

    public static AccountObject getAccountObj() {
        return accountObj;
    }

    public static void setAccountObj(AccountObject accountObj) {
        AccountStore.accountObj = accountObj;
    }

    public static AccountObject getAccountObj2() {
        return accountObj2;
    }

    public static void setAccountObj2(AccountObject accountObj2) {
        AccountStore.accountObj2 = accountObj2;
    }
}
