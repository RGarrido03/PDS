package ex3;

import javax.swing.*;

import ex3.startypes.StarType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sky extends JFrame {
    private List<StarType> stars = new ArrayList<>();

    public void placeStar(StarType star) {
        stars.add(star);
    }

    @Override
    public void paint(Graphics graphics) {
        for (StarType star : stars) {
            star.draw(graphics);
        }
    }
}