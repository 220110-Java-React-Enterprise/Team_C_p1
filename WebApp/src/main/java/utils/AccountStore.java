package utils;

public class AccountStore {
    private static AccountObject accountObj;
    public AccountStore(){

    }

    public static AccountObject getAccountObj() {
        return accountObj;
    }

    public static void setAccountObj(AccountObject accountObj) {
        AccountStore.accountObj = accountObj;
    }
}
