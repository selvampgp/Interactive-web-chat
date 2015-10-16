package com.ivannyrkov.chat.models;

/**
 * @author Ivan Nyrkov
 */
public enum Color {
    BLACK("Black"), GREEN("Green"), BLUE("Blue"), YELLOW("Yellow"), RED("Red");

    private final String text;

    Color(String color) {
        this.text = color;
    }

    @Override
    public String toString() {
        return text;
    }
}
