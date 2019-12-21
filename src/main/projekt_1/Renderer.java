package projekt_1;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    private Planet map;
    private int width;
    private int height;
    public Renderer(int width, int height, Planet map){
        this.width = width;
        this.height = height;
        this.map = map;
        setPreferredSize(new Dimension(850,850));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Color jungleColor = new Color(33, 124, 15);
        Color savannaColor = new Color(195, 215, 63);
        Color grassColor = new Color(37, 95, 37);
        Color animalColor = new Color(100, 79, 10);
        int proportionX = 800 / width;
        int proportionY = 800 / height;
        int noProportionX = 0;

        for (int x = 0; x < 800; x = x + proportionX) {

            int noProportionY = 0;

            for (int y = 0; y < 800; y = y + proportionY) {

                if (noProportionX < width && noProportionY < height) {

                    Vector2d vector2d = new Vector2d(noProportionX, noProportionY);

                    if (map.jungle.isInJungle(vector2d)) {

                        MapElement element = map.getMapElement(vector2d);

                        if (element instanceof Grass) {

                            graphics.setColor(grassColor);
                            graphics.fillRect(x, y, proportionX, proportionY);

                        }
                        else {

                            graphics.setColor(jungleColor);
                            graphics.fillRect(x, y, proportionX, proportionY);

                            if (element instanceof Animal) {

                                graphics.setColor(animalColor);
                                graphics.fillOval(x, y, proportionX, proportionY);
                            }
                        }
                    }
                    else {

                        MapElement element = map.getMapElement(vector2d);

                        if (element instanceof Grass) {

                            graphics.setColor(grassColor);
                            graphics.fillRect(x, y, proportionX, proportionY);

                        } else {

                            graphics.setColor(savannaColor);
                            graphics.fillRect(x, y, proportionX, proportionY);

                            if (element instanceof Animal) {

                                graphics.setColor(animalColor);
                                graphics.fillOval(x, y, proportionX, proportionY);

                            }
                        }
                    }
                }
                noProportionY++;
            }
            noProportionX++;
        }
    }
}