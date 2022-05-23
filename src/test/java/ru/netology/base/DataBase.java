package ru.netology.base;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {

    @SneakyThrows
    public static void cleanDatabase() {
        var statement = getStatement();
        statement.executeUpdate("DELETE FROM  auth_codes;");
        statement.executeUpdate("DELETE FROM  cards;");
        statement.executeUpdate("DELETE FROM  users;");
        statement.executeUpdate("DELETE FROM  card_transactions;");
    }

    @SneakyThrows
    public static Statement getStatement() {
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        var statement = conn.createStatement();
        return statement;
    }

    @SneakyThrows
    public static String verificationCode() {
        var statement = getStatement();
        {
            var reseltSet = statement.executeQuery("SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1;");
            {
                if (reseltSet.next()) {
                    var code = reseltSet.getString("code");
                    return code;
                }
            }
        }
        return null;
    }

    static Faker faker = new Faker();

    public static String getInvalidPassword() {
        return faker.internet().password();
    }

}
