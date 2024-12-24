package com.example.demo.lib;

public class Color {
    public static Color red, green, yellow, blue, orange, purple;
    public static int colorsCount = 6;
    public static char[] gameColors = {'r', 'g', 'y', 'b', 'o', 'p'};
    public int id;
    public String name;
    public char symbol;

    Color(int id, String name) {
        this.id = id;
        this.name = name;
        this.symbol = gameColors[id];
    }
    public String toString() {
        return name;
    }

    static {
        red = new Color(0, "Red");
        green = new Color(1, "Green");
        yellow = new Color(2, "Yellow");
        blue = new Color(3, "Blue");
        orange = new Color(4, "orange");
        purple = new Color(5, "Purple");
    }
}
