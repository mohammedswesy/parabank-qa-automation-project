package utils;

public final class TestData {

    private TestData() {
    }

    // =========================
    // User Registration Data
    // =========================

    public static final String FIRST_NAME =
            "Mohammed";

    public static final String LAST_NAME =
            "AlSwesy";

    public static final String ADDRESS =
            "Gaza Palestine";

    public static final String CITY =
            "Gaza";

    public static final String STATE =
            "Palestine";

    public static final String ZIP_CODE =
            "00970";

    public static final String PHONE =
            "0598890011";

    public static final String SSN =
            "123456789";

    public static final String PASSWORD =
            "Test@12345";

    // =========================
    // Bill Payment Data
    // =========================

    public static final String PAYEE_NAME =
            "Gaza Electricity";

    public static final String PAYEE_ADDRESS =
            "Al Nasr Street";

    public static final String PAYEE_CITY =
            "Gaza";

    public static final String PAYEE_STATE =
            "Palestine";

    public static final String PAYEE_ZIP =
            "00970";

    public static final String PAYEE_PHONE =
            "0590000000";

    public static final String PAYEE_ACCOUNT =
            "123456";

    // =========================
    // Transaction Data
    // =========================

    public static final String TRANSFER_AMOUNT =
            "50";

    public static final String BILL_AMOUNT =
            "25";

    // =========================
    // Dynamic Username Generator
    // =========================

    public static String uniqueUsername() {

        return "qauser"
                + System.currentTimeMillis();
    }
}