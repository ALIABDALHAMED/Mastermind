
package com.example.demo.lib;
public class Guess {
    protected Color[] colors;
    protected static final Color[] COLORS = {
            Color.red, Color.green, Color.yellow, Color.blue, Color.orange, Color.purple
    };

    public Guess(int size) {
        colors = new Color[size];
    }
    public Guess(String str){
        colors = new Color[str.length()];
        for(int i = 0; i < str.length(); i++){
            setColor(i, str.charAt(i));
        }
    }

    protected Color charToColor(char c) {
        switch (c) {
            case 'r':
                return Color.red;
            case 'g':
                return Color.green;
            case 'b':
                return Color.blue;
            case 'o':
                return Color.orange;
            case 'p':
                return Color.purple;
            case 'y':
                return Color.yellow;

            default:
                throw new IllegalArgumentException("invalid char" + c);
        }
    }

    public static boolean isValid(String input, int size) {
        if (input.length() != size) return false;
        for (char c : input.toCharArray()) {
            if ("rgbopy".indexOf(c) == -1) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Color color : colors) {
            sb.append(color.symbol);
        }
        return sb.toString();
    }

    public char getChar(int n) {
        if (n >= 0 && n < colors.length) {
            return colors[n].symbol;
        }
        throw new IndexOutOfBoundsException("Invalid index color");
    }

    public int getSize() {
        return colors.length;
    }

    public Color getColor(int n) {
        if (n >= 0 && n < colors.length) {
            return colors[n];
        }
        throw new IndexOutOfBoundsException("Invalid index color");
    }

    public void setColor(int index, char colorChar) {
        Color color = charToColor(colorChar);
        colors[index] = color;
    }

    Color[] getColors() {
        return colors;
    }
}
