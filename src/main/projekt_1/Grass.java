package projekt_1;

public class Grass implements MapElement {
    private Vector2d position;


    public Grass(Vector2d position) {

        this.position = position;
    }

    @Override
    public Vector2d getPosition()
    {
        return this.position;
    }

}
