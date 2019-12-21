import org.junit.Test;
import projekt_1.*;

import static junit.framework.TestCase.assertTrue;

public class PlanetTest {
    @Test
    public void gettery()
    {
        Planet map = new Planet(10,10,4,60,0);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(9,9), map, 50);
        Animal third = new Animal(new Vector2d(0,0),map,75);
        map.place(first);
        map.place(second);
        map.place(third);
        double avg = 75;
        assertTrue(map.getAvgEnergy() == avg);
        assertTrue(map.getAvgChildrenNumber() == 0);
        assertTrue(map.getListOfAnimals().size() == 3);
        first.procreate(second);
        assertTrue(map.getListOfAnimals().size() == 4);
        avg = 56.0;
       assertTrue(map.getAvgEnergy() == avg);
       assertTrue(map.getAvgChildrenNumber() == 0.5);
       map.run();
       avg = avg - 60;
       assertTrue(map.getAvgEnergy() == avg);
       assertTrue(map.getGrassNumber() == 2);
    }
    @Test
    public void deadAnimals()
    {
        Planet map = new Planet(10,10,4,60,0);
        Animal first = new Animal(new Vector2d(0,0),map,10);
        map.place(first);
        assertTrue(map.getListOfAnimals().size() == 1);
        map.run();
        assertTrue(map.getListOfDeadAnimals().size() == 1);
        map.run();
        assertTrue(map.getListOfAnimals().size() == 0);
        assertTrue(map.getListOfDeadAnimals().size() == 0);
    }
    @Test
    public void fillFreePositions()
    {
        Planet map = new Planet(4,4,4,10,0);
        assertTrue(map.getFreeSpaceInTheJungle().size() == 4);
        assertTrue(map.getFreeSpaceOutOfTheJungle().size() == 12);
        Animal first = new Animal(new Vector2d(2,2),map,100);
        Animal second = new Animal(new Vector2d(3,3),map,100);
        map.place(first);
        map.place(second);
        assertTrue(map.getFreeSpaceInTheJungle().size() == 3);
        assertTrue(map.getFreeSpaceOutOfTheJungle().size() == 11);
    }

    @Test
    public void isOccupied()
    {
        Planet map = new Planet(4,4,4,10,0);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position,map,100);
        map.place(animal);
        assertTrue(map.isOccupied(position));
    }

    @Test
    public void place()
    {
        Planet map = new Planet(4,4,4,10,0);
        Animal animal = new Animal(new Vector2d(0,0),map,100);
        Grass grass = new Grass(new Vector2d(3,3));
        map.place(animal);
        map.place(grass);
        assertTrue(map.isOccupied(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(3,3)));
        assertTrue(map.getListOfAnimals().size() == 1);
    }

    @Test
    public void placeChild()
    {
        Planet map = new Planet(4,4,4,10,0);
        Grass grass = new Grass(new Vector2d(0,0));
        map.place(grass);
        Animal child = new Animal(new Vector2d(0,0),map,100);
        map.placeChild(child);
        assertTrue(map.getGrassNumber() == -1); // zakładam, że użytkownik nie może sam umieszczać traw na mapie
        assertTrue(map.getListOfAnimals().size() == 1);
    }

    @Test
    public void actualizeOccupation()
    {
        Planet map = new Planet(4,4,4,10,0);
        Animal animal1 = new Animal(new Vector2d(0,0),map,100);
        Animal anial2 = new Animal(new Vector2d(2,2),map,100);
        map.place(animal1);
        map.place(anial2);
        assertTrue(map.getAnimalOccupiedPositions().contains(new Vector2d(0,0)));
        assertTrue(map.getAnimalOccupiedPositions().contains(new Vector2d(2,2)));
        assertTrue(!map.getFreeSpaceInTheJungle().contains(new Vector2d(0,0)));
        assertTrue(!map.getFreeSpaceOutOfTheJungle().contains(new Vector2d(2,2)));
        map.run();
        assertTrue(!map.getAnimalOccupiedPositions().contains(new Vector2d(0,0)));
        assertTrue(!map.getAnimalOccupiedPositions().contains(new Vector2d(2,2)));
    }

    @Test
    public void feedAndProcreate()
    {
        Planet map = new Planet(4,4,4,20,0);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(0,0),map,90);
        Animal third = new Animal(new Vector2d(0,0),map,100);
        map.place(first);
        map.place(second);
        map.place(third);

        Animal fourth = new Animal(new Vector2d(2,2),map,90);
        Animal fifth = new Animal(new Vector2d(2,2),map,100);
        Animal sixth = new Animal(new Vector2d(2,2),map,95);
        map.place(fourth);
        map.place(fifth);
        map.place(sixth);

        AnimalsAtOnePosition animalsAtOnePositionFirst = new AnimalsAtOnePosition(map.getMap(),new Vector2d(0,0),map.getListOfAnimals(),20);
        animalsAtOnePositionFirst.feed();
        assertTrue(first.getEnergy()==110);
        assertTrue(second.getEnergy() == 90);
        assertTrue(third.getEnergy() == 110);
        animalsAtOnePositionFirst.bread();
        assertTrue(first.getChildrenNumber() == 1);
        assertTrue(third.getChildrenNumber() == 1);
        assertTrue(second.getChildrenNumber() == 0);
        AnimalsAtOnePosition animalsAtOnePositionSecond = new AnimalsAtOnePosition(map.getMap(),new Vector2d(2,2),map.getListOfAnimals(),20);
        animalsAtOnePositionSecond.feed();
        assertTrue(fourth.getEnergy() == 90);
        assertTrue(fifth.getEnergy() == 120);
        assertTrue(sixth.getEnergy() == 95);
        animalsAtOnePositionSecond.bread();
        assertTrue(fifth.getChildrenNumber() == 1);
        assertTrue(sixth.getChildrenNumber() == 1);
        assertTrue(second.getChildrenNumber() == 0);
        assertTrue(fourth.getEnergy() == 90);
        assertTrue(fifth.getEnergy() == 90);
        assertTrue(map.getListOfAnimals().size()==8);

    }

    @Test
    public void plantGrass()
    {
        Planet map = new Planet(4,4,4,20,0);
        map.run();
        assertTrue(map.getGrassNumber() == 2);
        assertTrue(map.getFreeSpaceOutOfTheJungle().size() == 11);
        assertTrue(map.getFreeSpaceInTheJungle().size() == 3);
    }
}
