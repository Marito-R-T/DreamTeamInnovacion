package org.example;

import org.example.view.Principalview;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Principalview pv = new Principalview();
        pv.setContentPane(pv.PrincipalPanel);
        pv.setTitle("Best Authors");
        pv.setSize(600, 500);
        pv.setVisible(true);
        pv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bootcamp";
            String user = "root";
            String password = "password";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}