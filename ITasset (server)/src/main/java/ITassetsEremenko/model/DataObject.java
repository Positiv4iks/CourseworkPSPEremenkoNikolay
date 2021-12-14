package ITassetsEremenko.model;

import java.io.Serializable;

public class DataObject implements Serializable {
    private String command;
    private String login, password;
    private String name;
    private double price;
    private int termOfUse;
    private int incomeMoney;

    private String nameEmployee;
    private String surname;
    private String department;

    private int id;
    private double yearPercent, yearPrice, monthPercent, monthPrice, payBack, rentability, totalBenefit; // Нигде не вводим
    private boolean result;

    public DataObject() {

    }

    public DataObject(int id, String name, double price, int termOfUse, int incomeMoney ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.termOfUse = termOfUse;
        this.incomeMoney = incomeMoney;
    }

    public DataObject(int id, String name, double yearPrice, double yearPercent, double monthPrice, double monthPercent, double payBack,
                      double rentability, double totalBenefit ) {
        this.id = id;
        this.name = name;
        this.yearPrice = yearPrice;
        this.yearPercent = yearPercent;
        this.monthPrice = monthPrice;
        this.monthPercent = monthPercent;
        this.payBack = payBack; // Окупаемость
        this.rentability = rentability;
        this.totalBenefit = totalBenefit; // Выгода за всё время
    }


    public DataObject(int id, String nameEmployee, String surname, String department ) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.surname = surname;
        this.department = department;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setCommand(String command) {
        this.command = command;
    } // Команда

    public String getCommand() {
        return command;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResult(boolean res) {
        this.result = res;
    }

    public boolean getResult() {
        return result;
    }

    public double getMonthPercent() {
        return monthPercent;
    }

    public void setMonthPercent(double monthPercent) {
        this.monthPercent = monthPercent;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public void setYearPercent(double yearPercent) {
        this.yearPercent= yearPercent;
    }

    public double getYearPercent() {
        return yearPercent;
    }



    public void setPayBack(double payBack) {
        this.payBack= payBack;
    }

    public double getPayBack() {
        return payBack;
    }

    public void setRentability(double rentability) {
        this.rentability= rentability;
    }

    public double getRentability() {
        return rentability;
    }

    public void setTotalBenefit(double totalBenefit) {
        this.totalBenefit= totalBenefit;
    }

    public double getTotalBenefit() {
        return totalBenefit;
    }


    public String getName() {
        return name;
    }

    public double getMonthPrice() { return monthPrice; }

    public void setName(String name) {
        this.name = name;
    }

    public double getYearPrice() {
        return yearPrice;
    }

    public void setYearPrice(double yearPrice) {
        this.yearPrice = yearPrice;
    }

    public int getTermOfUse() { return termOfUse; }

    public void setTermOfUse(int termOfUse) { this.termOfUse = termOfUse; }


    public void setIncomeMoney(int incomeMoney) { this.incomeMoney = incomeMoney; } // Прибыль

    public int getIncomeMoney() { return incomeMoney; }



   // Сотрудники
    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }
    public String getNameEmployee() {
        return nameEmployee;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }


    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }



}
