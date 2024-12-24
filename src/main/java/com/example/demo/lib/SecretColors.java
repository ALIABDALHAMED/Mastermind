package com.example.demo.lib;
import java.util.Random;

class SecretColors extends Guess {
    public SecretColors(int size) {
        super(size);
        generateRandom();
    }

    private void generateRandom() {
        Random random = new Random();

        for (int i = 0; i < colors.length; i++) {
            colors[i] = COLORS[random.nextInt(COLORS.length)];
        }
    }
}
