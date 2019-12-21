package projekt_1;

public class Jungle {
    private Vector2d loweLeft;
    private Vector2d upperRight;
    public Jungle(double jungleRatio, int width, int height)
    {
        if(jungleRatio > 1) {
            jungleRatio=1;
        }
        if(jungleRatio < 0)
        {
            jungleRatio = 0;
        }
        double denominator = Math.sqrt(jungleRatio);
        double jungleWidth = denominator*width;
        double jungleHeight = denominator*height;
        if(width % 2 ==0)
        {
            if(height % 2 ==0)
            {
                Vector2d mapMiddle = new Vector2d(width / 2-1 , height / 2-1 );
                Vector2d upperRight = mapMiddle.add(new Vector2d((int)jungleWidth/2,(int)jungleHeight/2));
                this.upperRight = upperRight;
                Vector2d lowerLeft = mapMiddle.add(new Vector2d((int) ((-1)*jungleWidth/2+1),(int)((-1)*jungleHeight/2+1)));
                this.loweLeft = lowerLeft;
            }
            else
            {
                Vector2d mapMiddle = new Vector2d(width / 2-1 , height / 2 );
                Vector2d upperRight = mapMiddle.add(new Vector2d((int)jungleWidth/2,(int)jungleHeight/2));
                this.upperRight = upperRight;
                Vector2d lowerLeft = mapMiddle.add(new Vector2d((int) ((-1)*jungleWidth/2+1),(int)((-1)*jungleHeight/2+1)));
                this.loweLeft = lowerLeft;
            }
        }
        else
        {
            if(height % 2 ==0)
            {
                Vector2d mapMiddle = new Vector2d(width / 2 , height / 2-1 );
                Vector2d upperRight = mapMiddle.add(new Vector2d((int)jungleWidth/2,(int)jungleHeight/2));
                this.upperRight = upperRight;
                Vector2d lowerLeft = mapMiddle.add(new Vector2d((int) ((-1)*jungleWidth/2+1),(int)((-1)*jungleHeight/2+1)));
                this.loweLeft = lowerLeft;
            }
            else
            {
                Vector2d mapMiddle = new Vector2d(width / 2 , height / 2 );
                Vector2d upperRight = mapMiddle.add(new Vector2d((int)jungleWidth/2,(int)jungleHeight/2));
                this.upperRight = upperRight;
                Vector2d lowerLeft = mapMiddle.add(new Vector2d((int) ((-1)*jungleWidth/2+1),(int)((-1)*jungleHeight/2+1)));
                this.loweLeft = lowerLeft;
            }
        }//wszystko się dzieje w zależności od tego, który wymiar planety jest parzysty lub nieparzysty

    }
    public Vector2d getUpperRight()
    {
        return this.upperRight;
    }
    public boolean isInJungle(Vector2d vector2d)
    {
        return vector2d.follows(upperRight) && vector2d.precedes(loweLeft);
    }
}