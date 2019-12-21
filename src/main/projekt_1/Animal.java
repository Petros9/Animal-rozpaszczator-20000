package projekt_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static projekt_1.MapDirection.NORTH;

public class Animal implements MapElement {
    private Vector2d position;
    private int energy;
    private MapDirection orientation;
    private Planet map;
    private Genome genome;
    private int childrenNumber;
    private int daysOfLife;
    private int startEnergy;

    public Animal(Vector2d initialPosition, Planet map,  int startEnergy)
    {
        this.energy = startEnergy;
        this.position = initialPosition;
        this.orientation = NORTH;
        this.position  = initialPosition;
        this.map = map;
        this.genome= new Genome();
        this.childrenNumber = 0;
        this.daysOfLife = 0;
        this.startEnergy = startEnergy;
    }

    public Animal(Vector2d initialPosition, Planet map, Genome genome, int energy, int startEnergy)
    {
        this.energy = energy;
        this.position = initialPosition;
        this.orientation = NORTH;
        this.position  = initialPosition;
        this.map = map;
        this.genome= genome;
        this.daysOfLife=0;
        this.startEnergy = startEnergy;
    }

    public int getEnergy()

    {
        return this.energy;
    }

    @Override
    public Vector2d getPosition() {

        return this.position;
    }

    public int getDaysOfLife()
    {
        return this.daysOfLife;
    }

    public int getChildrenNumber()
    {
        return this.childrenNumber;
    }

    public void feed(int supply)
    {
        this.energy = this.energy + supply;
    }

    public boolean isAlive() {
        this.energy = this.energy - this.map.getMoveEnergy();
        return this.energy>0;
    }

    public void moveDirection() {
      if(this.isAlive()) {
          daysOfLife++;
          int i = this.genome.getNumber();
          while (i > 0) {
              this.orientation = this.orientation.next();
              i--;
          }
          this.position = this.position.add(this.orientation.toUnitVector());
          this.position = this.position.adjustToPlanet(this.position,map.getWidth(),map.getHeight());
      }
    else{
            map.addDeadAnimal(this);
        }
    }

    public void procreate(Animal other) {
        if(this.energy>= startEnergy/2 && other.energy >= startEnergy/2) {
            Random generator = new Random();
            int newEnergy = this.energy/4 + other.energy/4;
            this.energy = (this.energy * 3) / 4;
            other.energy = (other.energy * 3) / 4;
            List<Vector2d> listOfPositions = new ArrayList<>();
            MapDirection newOrientation = NORTH;

            for(int i = 0; i<8; i++)
            {
                Vector2d vector = new Vector2d(this.getPosition().x,this.getPosition().y);
                vector = vector.add(newOrientation.toUnitVector());
                vector = vector.adjustToPlanet(vector,map.getWidth(),map.getHeight());
                listOfPositions.add(vector);
                newOrientation = newOrientation.next();
            } //dodaje sąsiednie pozycje
            for(int i = 0; i < listOfPositions.size(); i++)
            {
                Vector2d vector2d = listOfPositions.get(i);
                if(map.isOccupied(vector2d))
                {
                    MapElement element = map.getMapElement(vector2d);
                    if(element instanceof Animal)
                    {
                        listOfPositions.remove(vector2d);
                        i--;
                    }
                }
            }
            //jeżeli potencjalne pozycje narodzin dziecka są okupowane przez ine zwierzęta, to jest ona usuwana

            if(!listOfPositions.isEmpty())
            {
                Vector2d childPosition = listOfPositions.get(generator.nextInt(listOfPositions.size()));

                Animal child = new Animal(childPosition,this.map,this.genome.makeNewGenome(this.genome,other.genome),newEnergy,startEnergy);
                for(int i = generator.nextInt(7); i>=0; i--) {
                    child.orientation = child.orientation.next();
                }
                this.map.placeChild(child);
                this.childrenNumber++;
                other.childrenNumber++;
            }
        }
    }
}
