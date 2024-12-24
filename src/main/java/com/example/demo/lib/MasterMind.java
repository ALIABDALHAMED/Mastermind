package com.example.demo.lib;
public class MasterMind {
    SecretColors secretCode;
    public int attempts = 10;
    public static boolean DEBUG = false;

    public MasterMind() {
        int size = 4;
        secretCode = new SecretColors(size);
        if(DEBUG){
            System.out.println("You are now in Debugging mode, and your secret code is: " + secretCode.toString());
        }
    }
    public GuessResult checkGuess(Guess guess) {
        GuessResult result = new GuessResult(0, 0);
        result.blackBalls = numberOfPositionMatch(guess);
        result.whiteBalls = numberOfColorMatch(guess);
        return result;
    }
    public int numberOfPositionMatch(Guess guess) {
        String guessStr = guess.toString();
        String secretStr = secretCode.toString();

        int count = 0;
        for (int i = 0; i < secretStr.length(); i++) {
            if (guessStr.charAt(i) == secretStr.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public int numberOfColorMatch(Guess guess) {
        int[] guessColorsCount = new int[Color.colorsCount];
        int[] secretColorsCount = new int[Color.colorsCount];
        for (int i = 0; i < guess.getSize(); i++) {
            if(guess.getColor(i).id != secretCode.getColor(i).id) {
                guessColorsCount[guess.getColor(i).id]++;
                secretColorsCount[secretCode.getColor(i).id]++;
            }
        }
        int count = 0;
        for (int i = 0; i < guessColorsCount.length; i++) {
            count += Math.min(guessColorsCount[i], secretColorsCount[i]);
        }
        return count;
    }
}