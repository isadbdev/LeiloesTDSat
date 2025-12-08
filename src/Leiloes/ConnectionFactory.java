package Leiloes;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/leiloes";
    private static final String USER = "root";
    private static final String PASS = "@Isa&GB13";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}