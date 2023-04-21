package org.example;

import com.google.gson.JsonObject;
import org.example.controller.GoogleSearch;
import org.example.controller.SerpApiSearchException;
import org.example.view.Principalview;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Principalview pv = new Principalview();
        pv.setContentPane(pv.PrincipalPanel);
        pv.setTitle("Best Authors");
        pv.setSize(600, 500);
        pv.setVisible(true);
        pv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}