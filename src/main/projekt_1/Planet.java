package projekt_1;

import java.util.*;

public class Planet{

    private final Map<Vector2d, MapElement> map;
    private List<Animal> listOfAnimals;
    public final Jungle jungle;
    private List<Animal> listOfDeadAnimals;
    private Vector2d mapUpperRight;
    private List<Vector2d> freeSpaceInTheJungle;
    private List<Vector2d> freeSpaceOutOfTheJungle;
    private List<Vector2d> animalOccupiedPositions;
    private List<Vector2d> feedingPositions;
    private int moveEnergy;
    private int plantEnergy;
    private int grassNumber;

    public Planet(int height, int width, double jungleRatio, int moveEnergy, int plantEnergy) {
        this.mapUpperRight = new Vector2d(width-1,height-1);
        this.map = new HashMap<>();
        this.listOfAnimals = new ArrayList<>();
        this.jungle = new Jungle(1/jungleRatio, width,height);
        this.listOfDeadAnimals = new ArrayList<>();
        this.freeSpaceInTheJungle = new ArrayList<>();
        this.freeSpaceOutOfTheJungle = new ArrayList<>();
        fillFreePositionList(this.jungle);
        this.animalOccupiedPositions = new ArrayList<>();
        this.feedingPositions = new ArrayList<>();
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
    }

    public double getAvgDaysOfLife()
    {
        double avgDays = 0;
        for(int i = 0; i< listOfAnimals.size();i++)
        {
            avgDays += listOfAnimals.get(i).getDaysOfLife();
        }
        if(avgDays==0)return 0;
        avgDays = avgDays/listOfAnimals.size();
        avgDays*=100;
        avgDays = Math.round(avgDays);
        avgDays = avgDays/100;
        return avgDays;
    }

    public double getAvgEnergy()
    {
        double avgEnergy = 0;
        for(int i = 0; i< listOfAnimals.size();i++)
        {
            avgEnergy += listOfAnimals.get(i).getEnergy();
        }
        if(avgEnergy ==0)return 0;
        avgEnergy = avgEnergy/listOfAnimals.size();
        avgEnergy= avgEnergy*100;
        avgEnergy = Math.round(avgEnergy);
        avgEnergy = avgEnergy/100;
        return avgEnergy;
    }

    public double  getAvgChildrenNumber()
    {
        double avgChild = 0;
        for(int i = 0; i< listOfAnimals.size();i++)
        {
            avgChild += listOfAnimals.get(i).getChildrenNumber();
        }
        if(avgChild ==0)return 0;
        avgChild = avgChild/listOfAnimals.size();
        avgChild*=100;
        avgChild = Math.round(avgChild);
        avgChild = avgChild/100;
        return avgChild;
    }

    public int getGrassNumber() {
        return grassNumber;
    }

    public List<Animal> getListOfDeadAnimals() { return listOfDeadAnimals; }

    public List<Animal> getListOfAnimals() {
        return listOfAnimals;
    }

    public MapElement getMapElement(Vector2d position)
    {
        return this.map.get(position);
    }
    public int getMoveEnergy()
    {
        return this.moveEnergy;
    }

    public int getWidth()
    {
        return this.mapUpperRight.x;
    }

    public int getHeight()
    {
        return this.mapUpperRight.y;
    }

    public List<Vector2d> getFreeSpaceInTheJungle(){return freeSpaceInTheJungle;}

    public List<Vector2d> getFreeSpaceOutOfTheJungle(){return freeSpaceOutOfTheJungle;}

    public List <Vector2d> getAnimalOccupiedPositions(){return  animalOccupiedPositions;}

    public List<Vector2d> getFeedingPositions() {return  feedingPositions;}

    public Map getMap(){return this.map;}

    public void addDeadAnimal(Animal animal)
    {
        this.listOfDeadAnimals.add(animal);
    }

    private void fillFreePositionList(Jungle jungle)
    {
        for(int i = 0; i<=this.mapUpperRight.y; i++)
        {
            for(int j = 0; j<=this.mapUpperRight.y; j++)
            {
                Vector2d vector2d = new Vector2d(i,j);
                if(jungle.isInJungle(vector2d))
                {
                    freeSpaceInTheJungle.add(vector2d);
                }
                else
                {
                    freeSpaceOutOfTheJungle.add(vector2d);
                }
            }
        }
    } //wolne pozycje są po to, by dodawanie nowej trawy do mapy było całkowicie losowe

    public void removeDeadAnimal()
    {
        while(!this.listOfDeadAnimals.isEmpty())
        {
            map.remove(this.listOfDeadAnimals.get(0).getPosition());
            listOfAnimals.remove(this.listOfDeadAnimals.get(0));
            listOfDeadAnimals.remove(0);
        }
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return map.get(position);
    }


    public void place(MapElement element){
        Vector2d position = element.getPosition();
        map.put(position,element);
        if(jungle.isInJungle(position))
        {
            freeSpaceInTheJungle.remove(position);
        }
        else {
            freeSpaceOutOfTheJungle.remove(position);
        }
        if (element instanceof Animal) {
            Animal animal = (Animal) element;
            listOfAnimals.add(animal);
            if(!animalOccupiedPositions.contains(animal.getPosition()))
            {
                animalOccupiedPositions.add(animal.getPosition());
            }
        }
    }

    public void placeChild(Animal animal)
    {
        Vector2d position = animal.getPosition();
        if(isOccupied(position))
        {
            map.remove(position);
            grassNumber--;
        }
        map.put(position,animal);
        listOfAnimals.add(animal);
        animalOccupiedPositions.add(position);
        if(jungle.isInJungle(position))
        {
            freeSpaceInTheJungle.remove(position);
        }
        else {
               freeSpaceOutOfTheJungle.remove(position);
            }
    }//jest wydzielone, ponieważ dziecko może się urodzić na trawie


    public void run(){
        removeDeadAnimal();//usuwanie martwych zwierzaków z mapy

        int numberOfAnimals = listOfAnimals.size();
        for (int i = 0; i < numberOfAnimals; i++) {
            Animal animal = listOfAnimals.get(i % numberOfAnimals);
            Vector2d old_position = animal.getPosition();
            animal.moveDirection();
            this.map.remove(old_position, animal);
            MapElement element = map.get(animal.getPosition());
            if(element instanceof Grass)
            {
                map.remove(element);
                feedingPositions.add(animal.getPosition());
                grassNumber--;
            }
            this.map.put(animal.getPosition(), animal);
            if(!animalOccupiedPositions.contains(animal.getPosition()))
            {
                animalOccupiedPositions.add(animal.getPosition());
            }

            if(jungle.isInJungle(animal.getPosition()))
            {
                freeSpaceInTheJungle.remove(animal.getPosition());
            }
            else {
                freeSpaceOutOfTheJungle.remove(animal.getPosition());
            }
        }//przemieszczanie zwierzaków

        actualizeOccupation(this.jungle);
        feedAndProcreate();
        plantGrass();
    }

    public void actualizeOccupation(Jungle jungle)
    {
        int i = 0;
        while(i!=animalOccupiedPositions.size())
        {
            if(!isOccupied(animalOccupiedPositions.get(i)))
            {
                Vector2d vector = animalOccupiedPositions.get(i);
                animalOccupiedPositions.remove(vector);
                if(jungle.isInJungle(vector))
                {
                    freeSpaceInTheJungle.add(vector);
                }
                else {
                    freeSpaceOutOfTheJungle.add(vector);
                }
            }

            else {
                i++;
            }

        }
    }

    public void feedAndProcreate() {
        int numberOfPositions = animalOccupiedPositions.size();
        for (int l = 0; l < numberOfPositions; l++) {
            Vector2d position = animalOccupiedPositions.get(l % numberOfPositions);
            MapElement element = map.get(position);
            if (element instanceof Animal) {
                AnimalsAtOnePosition animalsAtOnePosition = new AnimalsAtOnePosition(map,position,listOfAnimals,plantEnergy);
                if(feedingPositions.contains(position))
                {
                    animalsAtOnePosition.feed();
                    feedingPositions.remove(position);
                }
                animalsAtOnePosition.bread();
            }
        }
    }


    public void plantGrass()
    {
        if(!freeSpaceInTheJungle.isEmpty()) {
            Random generator = new Random();
            Vector2d jungleVector = freeSpaceInTheJungle.get(generator.nextInt(freeSpaceInTheJungle.size()));
            Grass jungleGrass = new Grass(jungleVector);
            if(jungleVector.follows(mapUpperRight)&& jungleVector.x>-1 && jungleVector.y >-1) {
                place(jungleGrass);
                grassNumber++;
            }
        }
        if(!freeSpaceOutOfTheJungle.isEmpty()) {
                    Random generator = new Random();
                    Vector2d vector = freeSpaceOutOfTheJungle.get(generator.nextInt(freeSpaceOutOfTheJungle.size()));
                    Grass grass = new Grass(vector);
                if(vector.follows(mapUpperRight) &&vector.x>-1 && vector.y >-1) {
                    place(grass);
                    grassNumber++;
                }
        }
    }
}