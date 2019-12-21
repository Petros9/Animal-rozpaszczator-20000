package projekt_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalsAtOnePosition {
    private List<Animal> animalsAtOnePosition;
    private Vector2d position;
    private Map<Vector2d,MapElement> map;
    private int topAnimalsNumber;
    private List<Animal> listOfAnimals;
    private int supply;

    public AnimalsAtOnePosition(Map<Vector2d,MapElement> map, Vector2d position, List<Animal> listOfAnimals, int supply)
    {
        this.map = map;
        this.position = position;
        this.listOfAnimals = listOfAnimals;
        this.topAnimalsNumber = 0;
        this.animalsAtOnePosition = new ArrayList<>();
        this.supply = supply;
        for(int i = 0; i<listOfAnimals.size();i++)
        {
            Animal animal = listOfAnimals.get(i%listOfAnimals.size());
            if(animal.getPosition().equals(position))
            {
                if(animalsAtOnePosition.isEmpty())
                {
                    animalsAtOnePosition.add(animal);
                    topAnimalsNumber++;
                }
                else
                {
                    int positionInTheList = 0;
                    boolean inserted = false;
                    for(int j = 0; j<animalsAtOnePosition.size(); j++) {
                        if (animal.getEnergy() > animalsAtOnePosition.get(j).getEnergy()) {
                            positionInTheList = j;
                            animalsAtOnePosition.add(positionInTheList, animal);
                            inserted = true;
                            break;
                        }
                    }

                    if(!inserted)
                    {
                        animalsAtOnePosition.add(animal);
                        if(animal.getEnergy() == animalsAtOnePosition.get(topAnimalsNumber-1).getEnergy())
                        {
                            topAnimalsNumber++;
                        }
                    }
                    else {
                        if (positionInTheList < topAnimalsNumber) {
                            topAnimalsNumber = 1;
                        }
                        if(animal.getEnergy() == animalsAtOnePosition.get(topAnimalsNumber-1).getEnergy())
                        {
                            topAnimalsNumber++;
                        }
                    }
                }
            }
        }
    }//w kostruktorze tworzona jest lista zwierząt na każdej pozycji, przy wstawianiu zwierzaków do listy sortuje się je po energii
    public List getAnimalList(){return this.animalsAtOnePosition;}
    public int getTopAnimalsNumber(){return this.topAnimalsNumber;}
    public void bread()
    {
        if(animalsAtOnePosition.size() > 1)
        {
            Animal first = animalsAtOnePosition.get(0);
            Animal second = animalsAtOnePosition.get(1);
            first.procreate(second);
        }
    }

    public void feed()
    {
        int i = 0;
        while(i != topAnimalsNumber)
        {
            animalsAtOnePosition.get(i).feed(supply/topAnimalsNumber);
            i++;
        }
    }
}