package projekt_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame implements ActionListener {

    private JSpinner setWidthField, setHeightField, setJungleRatioField, setStartEnergyField, setMoveEnergyField, setPlantEnergyField, setNumberOfAnimalsField;
    private int width, height, startEnergy, moveEnergy, plantEnergy, numberOfAnimals;
    private double jungleRatio;


    public StartWindow() {
        setSize(450, 550);
        setTitle("Animal rozpaszczator 2000 (ustawienia)");
        setLayout(null);
        JButton startButton = new JButton("Start");
        startButton.setBounds(175, 400, 100, 40);
        add(startButton);
        startButton.addActionListener(this);
        JLabel setWidthLabel = new JLabel("Szerokość: ");
        this.setWidthField = new JSpinner();
        this.setWidthField.setValue(8);
        JLabel setHeightLabel = new JLabel("Wysokość: ");
        this.setHeightField = new JSpinner();
        this.setHeightField.setValue(8);
        JLabel setJungleRatioLabel = new JLabel("Pole mapy/pole dżungli:  ");
        this.setJungleRatioField = new JSpinner();
        this.setJungleRatioField.setValue(4);
        JLabel setStartEnergyLabel = new JLabel("Energia startowa zwierząt: ");
        this.setStartEnergyField = new JSpinner();
        this.setStartEnergyField.setValue(100);
        JLabel setMoveEnergyLabel = new JLabel("Energia ruchu: ");
        this.setMoveEnergyField = new JSpinner();
        this.setMoveEnergyField.setValue(5);
        JLabel setPlantEnergyLabel = new JLabel("Energia zyskana po pożywieniu się: ");
        this.setPlantEnergyField = new JSpinner();
        this.setPlantEnergyField.setValue(50);
        JLabel setNumberOfAnimalsLabel = new JLabel("Początkowa liczba zwierząt: ");
        this.setNumberOfAnimalsField = new JSpinner();
        this.setNumberOfAnimalsField.setValue(10);
        setWidthLabel.setBounds(100, 50, 100, 20);
        setWidthField.setBounds(310, 50, 50, 20);
        add(setWidthLabel);
        add(setWidthField);
        setHeightLabel.setBounds(100, 100, 200, 20);
        setHeightField.setBounds(310, 100, 50, 20);
        add(setHeightLabel);
        add(setHeightField);
        setJungleRatioLabel.setBounds(100, 150, 200, 20);
        setJungleRatioField.setBounds(310, 150, 50, 20);
        add(setJungleRatioLabel);
        add(setJungleRatioField);
        setStartEnergyLabel.setBounds(100, 200, 200, 20);
        setStartEnergyField.setBounds(310, 200, 50, 20);
        add(setStartEnergyLabel);
        add(setStartEnergyField);
        setMoveEnergyLabel.setBounds(100, 250, 100, 20);
        setMoveEnergyField.setBounds(310, 250, 50, 20);
        add(setMoveEnergyField);
        add(setMoveEnergyLabel);
        setPlantEnergyLabel.setBounds(100, 300, 300, 20);
        setPlantEnergyField.setBounds(310, 300, 50, 20);
        add(setPlantEnergyLabel);
        add(setPlantEnergyField);
        setNumberOfAnimalsLabel.setBounds(100, 350, 200, 20);
        setNumberOfAnimalsField.setBounds(310, 350, 50, 20);
        add(setNumberOfAnimalsLabel);
        add(setNumberOfAnimalsField);
    }

    @Override
    public void actionPerformed(ActionEvent event){
            dispose();
            this.width = Integer.parseInt(setWidthField.getValue().toString());
            this.height = Integer.parseInt(setHeightField.getValue().toString());
            this.jungleRatio = Double.parseDouble(setJungleRatioField.getValue().toString());
            this.startEnergy = Integer.parseInt(setStartEnergyField.getValue().toString());
            this.moveEnergy = Integer.parseInt(setMoveEnergyField.getValue().toString());
            this.plantEnergy = Integer.parseInt(setPlantEnergyField.getValue().toString());
            this.numberOfAnimals = Integer.parseInt(setNumberOfAnimalsField.getValue().toString());
            Planet map = new Planet(height, width, jungleRatio, moveEnergy, plantEnergy);
            ProgrammeWindow window = new ProgrammeWindow(width, height, map, startEnergy, numberOfAnimals);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.run();
    }

    public static void main(String[] args) {
        StartWindow window = new StartWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
