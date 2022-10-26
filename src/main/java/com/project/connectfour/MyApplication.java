package com.project.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class MyApplication extends Application {

    private Controller controller;

    // Starting the Stage which holds the Scene
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
        controller.createPlayground();

        // Menu Bar
        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);

        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Four");
        Image img = new Image("C:\\Users\\neeraj\\Desktop\\Projects Java\\connectfour\\src\\main\\resources\\com\\project\\connectfour\\connect4.png");
        primaryStage.getIcons().add(img);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Creating Menu
    private MenuBar createMenu() {

        // File Menu Bar
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(event -> controller.resetGame());

        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(event -> controller.resetGame());

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(event -> exitGame());

        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutConnect = new MenuItem("About Connect4");
        aboutConnect.setOnAction(event -> aboutGame());
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> aboutMeAct());
        helpMenu.getItems().addAll(aboutConnect, separator, aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }

    // About Me
    private void aboutMeAct() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Neeraj Fullara");
        alert.setContentText("Hello, My name is Neeraj Fullara." + "I build this Little App."
                + "It's an Game application." + "The name of this Application is Connect 4."
                + "If see any flow in this app please contact me at neerajfullara@gmail.com");
        alert.show();
    }

    // About Game
    private void aboutGame() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect 4");
        alert.setHeaderText("How to Play");
        alert.setContentText("Connect Four is a two-player connection game in which the "
                + "players first choose a color and then take turns dropping colored discs "
                + "from the top into a seven-column, six-row vertically suspended grid. "
                + "The pieces fall straight down, occupying the next available space within the column. "
                + "The objective of the game is to be the first to form a horizontal, vertical, "
                + "or diagonal line of four of one's own discs. Connect Four is a solved game. "
                + "The first player can always win by playing the right moves.");
        alert.show();
    }

    // Action on clicking Exit Game in manu bar
    private void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    // Main Method
    public static void main(String[] args) {
        launch(args);
    }

}