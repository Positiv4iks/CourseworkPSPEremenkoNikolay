package ITassetsEremenko.server;

public class DatabaseOptions {
    protected static final String
            DATABASE_NAME = "amort",
            DATABASE_USERNAME = "root",
            DATABASE_PASSWORD = "root",
            DATABASE_CONNECTION_PORT = "3306",
          DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:" + DATABASE_CONNECTION_PORT + "/" + DATABASE_NAME + "?useUnicode=true&serverTimezone=UTC",
    // DATABASE_CONNECTION_URL = "jdbc:mysql://127.0.0.1" + DATABASE_CONNECTION_PORT + "/" + DATABASE_NAME + "?useUnicode=true&serverTimezone=UTC",
            DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static final String
            ADMIN_TABLE = "admins",
            USER_TABLE = "users";
    protected static final String
            LOGIN = "Login",
            PASSWORD = "Password";
    protected static final String
            ID = "id",
            NAME = "name",
            YEAR_PERCENT = "yearPercent",
            MONTH_PRICE = "monthPrice",
            MONTH_PERCENT = "monthPercent",
            YEAR_PRICE = "yearPrice",
            TERM_OF_USE = "termOfUse",

            INCOMEMONEY = "incomeMoney",

            PAYBACK = "payBack",
            RENTABILITY= "rentability",
            TOTAL_BENEFIT = "totalBenefit",



            NAMEEMPLOYEE = "nameEmployee",
            SURNAMEEMPLOYEE = "surname",
            DEPARTMENTEMPLOYEE = "department",

            PRICE = "price";
    protected static final String
            ASSETS_TABLE = "assets";
    protected static final String
            DEPRECIATION_TABLE = "depreciation";
    protected static final String
            EMPLOYEE_TABLE = "employees";

}
