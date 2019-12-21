import org.junit.Test;
import projekt_1.Animal;
import projekt_1.Planet;
import projekt_1.Vector2d;

import static junit.framework.TestCase.assertTrue;

public class AnimalTest {
    @Test
    public void isAlive()
    {
        Planet map = new Planet(4,4,4,100,0);
        Animal animal = new Animal(new Vector2d(0,0),map,99);
        map.place(animal);
        map.run();
        assertTrue(map.getListOfDeadAnimals().size() == 1);
        map.run();
        assertTrue(map.getListOfAnimals().size() == 0);
    }

    @Test
    public void pocreate()
    {
        Planet map = new Planet(4,4,4,0,0);
        Animal first = new Animal(new Vector2d(0,0),map,100);
        Animal second = new Animal(new Vector2d(0,0),map,100);
        Animal firstBarrier = new Animal(new Vector2d(0,1),map, 100);
        Animal secondBarrier = new Animal(new Vector2d(1,1),map,100);
        Animal thirdBarrier = new Animal(new Vector2d(1,0),map,100);
        Animal fourthBarrier = new Animal(new Vector2d(3,0),map,100);
        Animal fifthBarrier = new Animal(new Vector2d(0,3),map,100);
        Animal sixthBarrier = new Animal(new Vector2d(3,1),map,100);
        Animal seventhBarrier = new Animal(new Vector2d(1,3),map, 100);
        map.place(first);
        map.place(second);
        map.place(firstBarrier);
        map.place(secondBarrier);
        map.place(thirdBarrier);
        map.place(fourthBarrier);
        map.place(fifthBarrier);
        map.place(sixthBarrier);
        map.place(seventhBarrier);
        first.procreate(second);
        assertTrue(map.isOccupied(new Vector2d(3,3)));
        assertTrue(first.getEnergy() == 75 && second.getEnergy() == 75);
        assertTrue(first.getChildrenNumber() == 1 && second.getChildrenNumber() ==1);
    }
}
