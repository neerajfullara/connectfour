package com.project.connectfour;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Controller implements Initializable {
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int CIRCLE_DIAMETER = 80;
    private static final String discColor1 = "#631C94";
    private static final String discColor2 = "#C89CE6";

    private static String PLAYER_ONE = "Player One";
    private static String PLAYER_TWO = "Player Two";

    private boolean isPlayerOneTrun = true;

    private Disc[][] insertedDiscsArray = new Disc[ROWS][COLUMNS];

    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane insertedDiscsPane;

    @FXML
    public Label playerNameLabel;

    @FXML
    public TextField playerOneTextfield, playerTwoTextfield;

    @FXML
    public Button setNameButton;

    private boolean isAllowedToInsert = true;   // Flag to avoid same color disc being added.

    // Give the design to Playground and adding some Action.
    public void createPlayground() {

        Shape rectangleWithHoles = matrixShape();
        rootGridPane.add(rectangleWithHoles, 0, 1);

        List<Rectangle> rectangleList = createClickableColumns();
        for (Rectangle rectangle : rectangleList) {
            rootGridPane.add(rectangle, 0, 1);

        }

        // Setting Action to the Set Action button.
        setNameButton.setOnAction(event -> {
            PLAYER_ONE = playerOneTextfield.getText();
            PLAYER_TWO = playerTwoTextfield.getText();
            playerNameLabel.setText(isPlayerOneTrun? PLAYER_ONE:PLAYER_TWO);
        });
    }

    // Creating the Circles for the Holes.
    private Shape matrixShape() {
        Shape rectangleWithHoles = new Rectangle((COLUMNS + 1) * CIRCLE_DIAMETER, (ROWS + 1) * CIRCLE_DIAMETER);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle circle = new Circle();
                circle.setRadius(CIRCLE_DIAMETER / 2);
                circle.setCenterX(CIRCLE_DIAMETER / 2);
                circle.setCenterY(CIRCLE_DIAMETER / 2);
                circle.setSmooth(true);
                circle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
                circle.setTranslateY(row * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);

                rectangleWithHoles = Shape.subtract(rectangleWithHoles, circle);
            }
        }

        rectangleWithHoles.setFill(Color.WHITE);
        return rectangleWithHoles;
    }

    // To make a fade out effect to the Colomn on which mouse is hovered.
    private List<Rectangle> createClickableColumns() {
        List<Rectangle> rectangleList = new ArrayList<>();

        for (int col = 0; col < COLUMNS; col++) {
            Rectangle rectangle = new Rectangle(CIRCLE_DIAMETER, (ROWS + 1) * CIRCLE_DIAMETER);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);

            rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.valueOf("#eeeeee26")));
            rectangle.setOnMouseExited(event -> rectangle.setFill(Color.TRANSPARENT));

            final int column = col;
            rectangle.setOnMouseClicked(event -> {

                if (isAllowedToInsert) {
                    isAllowedToInsert = false;  // When disc is being dropped then no more disc will be inserted
                    insertDisc(new Disc(isPlayerOneTrun), column);
                }
            });

            rectangleList.add(rectangle);
        }

        return rectangleList;
    }

    // Insering the Discs or Circle.
    private void insertDisc(Disc disc, int column) {

        int row = ROWS - 1;
        while (row >= 0) {
            if (getDiscIfPresent(row, column) == null) {
                break;
            }
            row--;
        }
        if (row < 0) { // If it is full, we cannot insert anymore disc
            return;
        }

        insertedDiscsArray[row][column] = disc; // For structural Changes: For developers
        insertedDiscsPane.getChildren().add(disc); // For Visual changes : For Player

        disc.setTranslateX(column * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);

        int currentRow = row;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), disc);
        translateTransition.setToY(row * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
        translateTransition.setOnFinished(event -> {

            isAllowedToInsert = true;
            if (gameEnded(currentRow, column)) {
                gameOver();
                return;
            }
            isPlayerOneTrun = !isPlayerOneTrun;
            playerNameLabel.setText(isPlayerOneTrun ? PLAYER_ONE : PLAYER_TWO);
        });
        translateTransition.play();
    }

    // Ending the game when Player one or two is completed the Criteria.
    private boolean gameEnded(int row, int column) {

        // vertical points
        List<Point2D> verticalPoints = IntStream.rangeClosed(row - 3, row + 3) // rnage of row values = 0,1,2,3,4,5
                .mapToObj(r -> new Point2D(r, column)) // 0,3 1,3 2,3, 3,3 4,3 5,3 --> Point2D x,y
                .collect(Collectors.toList());

        // Horizontal Points
        List<Point2D> horizontalPoints = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(col -> new Point2D(row, col))
                .collect(Collectors.toList());

        // Diagonal 1
        Point2D startPoint1 = new Point2D(row - 3, column + 3);
        List<Point2D> diagonal1Points = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> startPoint1.add(i, -i))
                .collect(Collectors.toList());

        // Diagonal 2
        Point2D startPoint2 = new Point2D(row - 3, column - 3);
        List<Point2D> diagonal2Points = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> startPoint2.add(i, i))
                .collect(Collectors.toList());

        boolean isEnded = checkCombination(verticalPoints) || checkCombination(horizontalPoints)
                || checkCombination(diagonal1Points) || checkCombination(diagonal2Points);

        return isEnded;
    }

    // Checking the combination is player complete the combination.
    private boolean checkCombination(List<Point2D> points) {

        int chain = 0;

        for (Point2D point : points) {

            int rowIndexForArray = (int) point.getX();
            int columnIndexForArray = (int) point.getY();

            Disc disc = getDiscIfPresent(rowIndexForArray, columnIndexForArray);
            if (disc != null && disc.isPlayerOneMove == isPlayerOneTrun) { // If the last inserted Disc belongs to the
                // current player
                chain++;
                if (chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }
        return false;
    }

    private Disc getDiscIfPresent(int row, int column) { // To prevent ArrayIndexOutofBoundException
        if (row >= ROWS || row < 0 || column >= COLUMNS || column < 0) { // If row or column index is invalid
            return null;
        }

        return insertedDiscsArray[row][column];
    }

    // Alert what to do after completing game.
    private void gameOver() {
        String winner = isPlayerOneTrun ? PLAYER_ONE : PLAYER_TWO;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Connect 4");
        alert.setHeaderText("Winner is : " + winner);
        alert.setContentText("Do you  want to continue with new game ?");

        ButtonType yesBtn = new ButtonType("Yes");
        ButtonType noBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(yesBtn, noBtn);

        Platform.runLater(() -> {   // Help us to resolve IllegalStateException.

            Optional<ButtonType> btnClicked = alert.showAndWait();
            if (btnClicked.isPresent() && btnClicked.get() == yesBtn) {
                // User yes, then reset
                resetGame();
            } else {
                // User no, exit the game.
                Platform.exit();
                System.exit(0);
            }
        });
    }

    // Resetting the game.
    public void resetGame() {
        insertedDiscsPane.getChildren().clear();    // Remove all Inserted Disc from Pane

        for (int row = 0; row < insertedDiscsArray.length; row++) { // Structurally, Make all elements of insertedDiscArray[][] to null
            for (int col = 0; col < insertedDiscsArray[row].length; col++) {
                insertedDiscsArray[row][col] = null;
            }
        }
        isPlayerOneTrun = true; // Let Player start the game
        playerNameLabel.setText(PLAYER_ONE);

        createPlayground(); // Prepare a fresh playGound
    }

    private static class Disc extends Circle {
        private final boolean isPlayerOneMove;

        public Disc(boolean isPlayerOneMove) {
            this.isPlayerOneMove = isPlayerOneMove;
            setRadius(CIRCLE_DIAMETER / 2);
            setFill(isPlayerOneMove ? Color.valueOf(discColor1) : Color.valueOf(discColor2));
            setCenterX(CIRCLE_DIAMETER / 2);
            setCenterY(CIRCLE_DIAMETER / 2);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}