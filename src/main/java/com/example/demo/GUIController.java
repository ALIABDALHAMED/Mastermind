package com.example.demo;

import com.example.demo.lib.Guess;
import com.example.demo.lib.GuessResult;
import com.example.demo.lib.MasterMind;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GUIController {

    public Label resultLabel;
    public Label attemptsLeftLabel;
    public Button btnNewGame;
    public Button btnSubmit;
    public Button btnReset;
    @FXML
    private Button btnRed, btnYellow, btnBlue, btnPurple, btnGreen, btnOrange;

    @FXML
    private Circle ball1, ball2, ball3, ball4;

    private Circle[] balls;

    private int currentBallIndex = 0;
    private MasterMind game;

    @FXML
    public void initialize() {
        game = new MasterMind();

        balls = new Circle[] { ball1, ball2, ball3, ball4 };
        for (Circle ball : balls) {
            ball.setFill(Color.WHITE);
        }
        btnRed.setOnAction(event -> colorBall(Color.RED));
        btnYellow.setOnAction(event -> colorBall(Color.YELLOW));
        btnBlue.setOnAction(event -> colorBall(Color.BLUE));
        btnPurple.setOnAction(event -> colorBall(Color.PURPLE));
        btnGreen.setOnAction(event -> colorBall(Color.GREEN));
        btnOrange.setOnAction(event -> colorBall(Color.ORANGE));
    }
    @FXML
    public void submit(){
        if(!Guess.isValid(getColorsInString(), 4)){
            setResultLabel("Please select colors.");
            return;
        }
        setAttemptLabel(--game.attempts);
        GuessResult result = game.checkGuess(new Guess(getColorsInString()));
        if(game.attempts == 0){
            handleLose();
        }else if(result.blackBalls == 4){
            handleWin();
        }else {
            setResultLabel("Black: " + result.blackBalls + "   White: " + result.whiteBalls);
        }
    }

    private String getColorsInString(){
        StringBuilder result = new StringBuilder();
        for(Circle ball : balls){
            char ballSymbol = colorToChar(ball.getFill().toString());
            result.append(ballSymbol);
        }
        return result.toString();
    }
    private void setResultLabel(String text){
        resultLabel.setText(text);
    }
    private char colorToChar(String x){
        switch(x){
            case "0xff0000ff":return 'r';
            case "0xffff00ff":return 'y';
            case "0x0000ffff":return 'b';
            case "0x800080ff":return 'p';
            case "0x008000ff":return 'g';
            case "0xffa500ff":return 'o';
            case "0xffffffff":return 'w';
            default:
                throw new IllegalArgumentException("invalid string"+ x);
        }
    }

    private void handleWin(){
        setResultLabel("You Won!!");
        endGame();
    }
    private void handleLose(){
        setResultLabel("You Lost.");
        endGame();
    }
    private void endGame(){
        btnNewGame.setManaged(true);
        btnNewGame.setVisible(true);
        btnSubmit.setManaged(false);
        btnSubmit.setVisible(false);
        btnReset.setManaged(false);
        btnReset.setVisible(false);
    }
    @FXML
    public void newGame(){
        btnNewGame.setManaged(false);
        btnNewGame.setVisible(false);
        btnSubmit.setManaged(true);
        btnSubmit.setVisible(true);
        btnReset.setManaged(true);
        btnReset.setVisible(true);
        resetGame();
    }

    private void colorBall(Color color) {
        if (currentBallIndex < balls.length) {
            balls[currentBallIndex].setFill(color);
            currentBallIndex++;
        } else {
            if(MasterMind.DEBUG) {
                System.out.println("All balls are filled!");
            }
        }
    }
    public void setAttemptLabel(int attempt){
        attemptsLeftLabel.setText("Attempts Left: " + attempt);
    }

    @FXML
    public void resetColors() {
        for (Circle ball : balls) {
            ball.setFill(Color.WHITE);
        }
        currentBallIndex = 0;
    }
    private void resetGame(){
        resetColors();
        game = new MasterMind();
        setAttemptLabel(game.attempts);
        setResultLabel("");
    }
}
