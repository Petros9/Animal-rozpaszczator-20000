import org.junit.Test;
import projekt_1.Animal;
import projekt_1.AnimalsAtOnePosition;
import projekt_1.Planet;
import projekt_1.Vector2d;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AnimalAtOnePositionTest {

    @Test
    public void animalsAtOnePosition()
    {
        Planet map = new Planet(4,4,4,0,20);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(0,0),map,100);
        Animal third = new Animal(new Vector2d(0,0),map,90);
        Animal fourth = new Animal(new Vector2d(0,1),map,100);
        map.place(first);
        map.place(third);
        map.place(second);
        map.place(fourth);
        AnimalsAtOnePosition animalsAtOnePosition = new AnimalsAtOnePosition(map.getMap(),new Vector2d(0,0),map.getListOfAnimals(),20);
        assertTrue(animalsAtOnePosition.getAnimalList().size() == 3);
        assertEquals(animalsAtOnePosition.getAnimalList().get(0),first);
        assertEquals(animalsAtOnePosition.getAnimalList().get(2),third);
    }

    @Test
    public void feed()
    {
        Planet map = new Planet(4,4,4,0,20);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(0,0),map,100);
        Animal third = new Animal(new Vector2d(0,0),map,90);
        Animal fourth = new Animal(new Vector2d(0,0),map,100);
        map.place(first);
        map.place(second);
        map.place(third);
        map.place(fourth);
        AnimalsAtOnePosition animalsAtOnePosition = new AnimalsAtOnePosition(map.getMap(),new Vector2d(0,0),map.getListOfAnimals(),30);
        animalsAtOnePosition.feed();
        assertEquals(animalsAtOnePosition.getAnimalList().get(0),first);
        assertEquals(animalsAtOnePosition.getTopAnimalsNumber(),3);
        assertEquals(first.getEnergy(),110);
        assertEquals(second.getEnergy(),110);
        assertEquals(third.getEnergy(),90);
        assertEquals(fourth.getEnergy(),110);
    }

    @Test
    public void bread()
    {
        Planet map = new Planet(4,4,4,0,20);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(0,0),map,100);
        Animal third = new Animal(new Vector2d(0,0),map,90);
        Animal fourth = new Animal(new Vector2d(0,0),map,100);
        map.place(first);
        map.place(second);
        map.place(third);
        map.place(fourth);
        AnimalsAtOnePosition animalsAtOnePosition = new AnimalsAtOnePosition(map.getMap(),new Vector2d(0,0),map.getListOfAnimals(),30);
        animalsAtOnePosition.bread();
        assertEquals(animalsAtOnePosition.getTopAnimalsNumber(),3);
        assertEquals(map.getListOfAnimals().size(),5);
        assertEquals(map.getListOfAnimals().get(0).getChildrenNumber(),1);
        assertEquals(map.getListOfAnimals().get(1).getChildrenNumber(),1);
        assertEquals(map.getListOfAnimals().get(3).getChildrenNumber(),0);
    }

}
