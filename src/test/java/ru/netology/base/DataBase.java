package ru.netology.base;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;

import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {

    @SneakyThrows
    void drop() {

        String s1 = "DROP TABLE auth_codes";
        String s2 = "DROP TABLE card_transactions";
        String s3 = "DROP TABLE cards";
        String s4 = "DROP TABLE users";

        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
                Statement s= conn.createStatement()) {
            s.addBatch(s1);
            s.addBatch(s2);
            s.addBatch(s3);
            s.addBatch(s4);
            s.executeBatch();
        }
    }

}
