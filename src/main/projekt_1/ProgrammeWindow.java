package projekt_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ProgrammeWindow extends JFrame implements ActionListener {
    private Planet map;
    Renderer renderer;
    private int width, height;
    private int startEnergy;
    private int numberOfAnimalsAtStart;
    JLabel animalNumber,grassNumber,averageChildrenNumber,averageLifeDays,averageEnergy, currentAge;
    JButton pauseButton;
    Timer timer;
    boolean works;
    int age;

    public ProgrammeWindow(int width, int height, Planet map, int startEnergy, int numberOfAnimalsAtStart) {
        this.width = width;
        this.height = height;
        this.map = map;
        this.startEnergy = startEnergy;
        this.numberOfAnimalsAtStart = numberOfAnimalsAtStart;
        setSize(1200, 1000);
        setTitle("Animal rozpaszczator 2000");
        JPanel panel = new JPanel(null);
        renderer = new Renderer(width,height,map);
        renderer.setBounds(0,0,800,800);
        panel.add(renderer);
        panel.setPreferredSize(new Dimension(800,800));
        add(panel);
        timer = new Timer(500, this);
        age = 0;
    }

    public void run() {
        setLayout(new FlowLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());

        animalNumber = new JLabel("Zwierzęta: " + (map.getListOfAnimals().size()));
        grassNumber = new JLabel("Trawy: " + map.getGrassNumber());
        averageChildrenNumber = new JLabel("Przeciętna liczba dzieci: " + (map.getAvgChildrenNumber()));
        averageLifeDays = new JLabel("Przeciętna długość życia: " + (map.getAvgDaysOfLife()));
        averageEnergy = new JLabel("Przeciętna energia: " + (map.getAvgEnergy()));
        currentAge = new JLabel("Obecna era: "+ age);
        pauseButton = new JButton("||");
        pauseButton.addActionListener(this);

        infoPanel.add(animalNumber);
        infoPanel.add(grassNumber);
        infoPanel.add(averageChildrenNumber);
        infoPanel.add(averageLifeDays);
        infoPanel.add(averageEnergy);
        infoPanel.add(currentAge);
        infoPanel.add(pauseButton);

        infoPanel.setBounds(900, 100, 200, 200);
        add(infoPanel);

        Random generator = new Random();
        for (int i = 0; i < numberOfAnimalsAtStart; i++) {
            Vector2d vector2d = new Vector2d(generator.nextInt(width - 1), generator.nextInt(height - 1));
            Animal animal = new Animal(vector2d, map, startEnergy);
            map.place(animal);
        }

        updateLabels();
        timer.start();
        works = true;
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == pauseButton)
        {
            works = !works;
            if(!works)
            {
                timer.stop();
                pauseButton.setText("->");
            }
            else
            {
                timer.start();
                pauseButton.setText("||");
            }
        }
        else {
            age++;
            map.run();
            updateLabels();
            renderer.repaint();
        }
    }

    public void updateLabels()
    {
        animalNumber.setText("Zwierzęta: " + (map.getListOfAnimals().size()));
        grassNumber.setText("Trawy: " + (map.getGrassNumber()));
        averageChildrenNumber.setText("Przeciętna liczba dzieci: " + (map.getAvgChildrenNumber()));
        averageLifeDays.setText("Przeciętna długość życia: " + (map.getAvgDaysOfLife()));
        averageEnergy.setText("Przeciętna energia: " + (map.getAvgEnergy()));
        currentAge.setText("Obecna era: "+ age);
    }
}
