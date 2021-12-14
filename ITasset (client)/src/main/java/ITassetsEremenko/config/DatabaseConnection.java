package ITassetsEremenko.config;

import ITassetsEremenko.model.DataObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection extends DatabaseOptions {
    public void createDatabase() throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "create database if not exists " + this.DATABASE_NAME;
            statement.executeUpdate(sqlQuery);
            System.out.println("Запрос " + sqlQuery + " был обработан!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DataObject getAuthorization(DataObject obj, String table) throws ClassNotFoundException {
        ResultSet set = null;
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {

            String sqlQuery = "select * from " + table +
                    " where " + this.LOGIN + "=? and " + this.PASSWORD + "=?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, obj.getLogin());
            ps.setString(2, obj.getPassword());
            set = ps.executeQuery();
            try {
                if (set.next()) {
                    obj.setResult(true);
                } else {
                    obj.setResult(false);
                }
            } catch (NullPointerException n) {
                n.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return obj;
        }
    }

    public DataObject getRegistration(DataObject obj, String table) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "insert into " + table + "(" + this.LOGIN + "," + this.PASSWORD + ")" + " values(?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getLogin());
                ps.setString(2, obj.getPassword());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return obj;
        }
    }

    public void deleteUser(DataObject obj, String table) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "delete from " + table +
                    " where " + this.LOGIN + "=? and " + this.PASSWORD + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getLogin());
                ps.setString(2, obj.getPassword());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DataObject getAsset(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "insert into " + this.ASSETS_TABLE +
                    "(" + this.NAME + "," + this.PRICE + "," + this.TERM_OF_USE + "," + this.INCOMEMONEY + ") values (?,?,?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getName());
                ps.setDouble(2, obj.getPrice());
                ps.setInt(3, obj.getTermOfUse());
                ps.setInt(4, obj.getIncomeMoney()); // прибыль incomeMoney
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return obj;
        }
    }

    public ArrayList<DataObject> getAssets(ArrayList<DataObject> objects) throws ClassNotFoundException {
        ArrayList<DataObject> objectArrayList = new ArrayList<DataObject>();
        ResultSet resultSet = null;
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "select * from " + this.ASSETS_TABLE;
            try {
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    objectArrayList.add(new DataObject(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5) // Прибыль incomeMoney
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return objectArrayList;
        }
    }

    public void filewriter() throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        ResultSet resultSet = null;
        List<String> data = new ArrayList<String>();
        List<String> head = new ArrayList<String>();
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "select * from " + this.DEPRECIATION_TABLE;
            try {
                resultSet = statement.executeQuery(sqlQuery);
               while (resultSet.next()) {
                String id = String.valueOf(resultSet.getInt(1));
                String name = resultSet.getString(2);
                String payBack = String.valueOf(resultSet.getDouble(7));
                String rentability = String.valueOf(resultSet.getDouble(8));
                String totalBenefit = String.valueOf(resultSet.getDouble(9));
                data.add(id + "\t\t" + name + "\t\t\t" +payBack + "\t\t\t" + rentability + "\t\t\t\t" + totalBenefit);
                }
               head.add("ID\t\tАктив\t\tОкупаемость\t\tРентабельность\t\tСуммарная выгода\n--------------------------------------------------------------------------------------------------------------------------------------------------");
                writeToFile(head, "report.txt");
                writeToFile(data, "report.txt");
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void writeToFile(java.util.List<String> list, String path) {
        BufferedWriter out = null;
        try {
            File file = new File(path);
            out = new BufferedWriter(new FileWriter(file, true));
            for (String s : list) {
                out.write(s);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
        }
    }

    public void editAssets(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "update " + this.ASSETS_TABLE + " set " + this.NAME + "=?, " +
                    this.PRICE + "=?, "+ this.TERM_OF_USE + "=?, " + this.INCOMEMONEY + "=?" + " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getName());
                ps.setDouble(2, obj.getPrice());
                ps.setInt(3, obj.getTermOfUse());
                ps.setInt(4, obj.getIncomeMoney());
                ps.setInt(5, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAssets(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "delete from " + this.ASSETS_TABLE +
                    " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DataObject getDepreciation(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "insert into " + this.DEPRECIATION_TABLE + "(" +
                    this.NAME + "," + this.YEAR_PERCENT + "," +
                    this.YEAR_PRICE + "," + this.MONTH_PERCENT + "," + this.MONTH_PRICE + "," + this.PAYBACK
                    + "," + this.RENTABILITY + "," + this.TOTAL_BENEFIT + ")" + " values (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getName());
                ps.setDouble(2, obj.getYearPercent());
                ps.setDouble(3, obj.getYearPrice());
                ps.setDouble(4, obj.getMonthPercent());
                ps.setDouble(5, obj.getMonthPrice());
                ps.setDouble(6, obj.getPayBack());
                ps.setDouble(7, obj.getRentability());
                ps.setDouble(8, obj.getTotalBenefit());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return obj;
        }
    }

    public ArrayList<DataObject> getDepreciation(ArrayList<DataObject> objects) throws ClassNotFoundException {
        ArrayList<DataObject> objectArrayList = new ArrayList<DataObject>();
        ResultSet resultSet = null;
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "select * from " + this.DEPRECIATION_TABLE;
            try {
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    objectArrayList.add(new DataObject(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(4),
                            resultSet.getDouble(3),
                            resultSet.getDouble(6),
                            resultSet.getDouble(5),
                            resultSet.getDouble(7),
                            resultSet.getDouble(8),
                            resultSet.getDouble(9)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return objectArrayList;
        }
    }

    public void deleteDepreciation(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "delete from " + this.DEPRECIATION_TABLE +
                    " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editDepreciation(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "update " + this.DEPRECIATION_TABLE + " set " + this.NAME + "=?, " +
                    this.YEAR_PRICE + "=?, " + this.YEAR_PERCENT + "=?, " + this.MONTH_PRICE
                    + "=?, " + this.MONTH_PERCENT + "=?, " + this.PAYBACK + "=?, " + this.RENTABILITY + "=?, " +this.TOTAL_BENEFIT + "=?" + " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getName());
                ps.setDouble(2, obj.getYearPercent());
                ps.setDouble(3, obj.getYearPrice());
                ps.setDouble(4, obj.getMonthPercent());
                ps.setDouble(5, obj.getMonthPrice());

                ps.setDouble(6, obj.getPayBack());
                ps.setDouble(7, obj.getRentability());
                ps.setDouble(8, obj.getTotalBenefit());
                ps.setInt(9, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





// Сотрудники // getEmployee из кейса в Handler --> при отправке addEmployeeData из контроллера

    public DataObject getEmployee(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "insert into " + this.EMPLOYEE_TABLE +
                    "(" + this.NAMEEMPLOYEE + "," + this.SURNAMEEMPLOYEE + "," + this.DEPARTMENTEMPLOYEE  + ") values (?,?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getNameEmployee());
                ps.setString(2, obj.getSurname());
                ps.setString(3, obj.getDepartment());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return obj;
        }
    }


    public ArrayList<DataObject> getEmployee(ArrayList<DataObject> objects) throws ClassNotFoundException {
        ArrayList<DataObject> objectArrayList = new ArrayList<DataObject>();
        ResultSet resultSet = null;
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "select * from " + this.EMPLOYEE_TABLE;
            try {
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    objectArrayList.add(new DataObject(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return objectArrayList;
        }
    }

    public void deleteEmployee(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (java.sql.Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "delete from " + this.EMPLOYEE_TABLE +
                    " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editEmployee(DataObject obj) throws ClassNotFoundException {
        Class.forName(this.DATABASE_DRIVER);
        try (Connection connection = DriverManager.getConnection(this.DATABASE_CONNECTION_URL, this.DATABASE_USERNAME, this.DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "update " + this.EMPLOYEE_TABLE + " set " + this.NAMEEMPLOYEE + "=?, " +
                    this.SURNAMEEMPLOYEE + "=?, " + this.DEPARTMENTEMPLOYEE + "=?" + " where " + this.ID + "=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, obj.getNameEmployee());
                ps.setString(2, obj.getSurname());
                ps.setString(3, obj.getDepartment());
                ps.setInt(4, obj.getId());
                ps.executeUpdate();
                obj.setResult(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Запрос " + sqlQuery + " был обработан!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
