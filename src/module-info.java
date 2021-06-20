module team2.cng457.desktopapp {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens desktopapp.Controllers;
    opens desktopapp.Models;
    opens desktopapp.Views;

    opens desktopapp;
}