package projekt_1;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y =y;
    }

    public Vector2d add(Vector2d other)
    {
        Vector2d vector2d = new Vector2d(this.x + other.x, this.y + other.y);
        return vector2d;
    }

    public boolean equals(Object other)
    {
        if(this == other) return true;
        if(!(other instanceof Vector2d)) return false;
        Vector2d that = (Vector2d) other;
        if (that.x == this.x && that.y == this.y) return true;
        return false;
    }

    public boolean follows(Vector2d other)
    {
        if(this.x <= other.x)
        {
            if(this.y <= other.y) return true;
            return false;
        }
        return false;
    }
    public boolean precedes(Vector2d other)
    {
        if(this.x >= other.x)
        {
            if(this.y >= other.y)return true;
            return false;
        }
        return false;
    }

    public Vector2d adjustToPlanet(Vector2d vector2d, int width, int height)
    {
        if(vector2d.x < 0) {vector2d = vector2d.add(new Vector2d(width+1,0));}
        if(vector2d.y < 0) {vector2d = vector2d.add(new Vector2d(0,height+1));}
        if(vector2d.x > width) {vector2d = vector2d.add( new Vector2d((-1)*width-1,0));}
        if(vector2d.y > height) {vector2d = vector2d.add( new Vector2d(0,(-1)*height-1));}
        return  vector2d;
    }
    public int hashCode() {

        return Objects.hash(x, y);
    }

    public String toString()
    {
        int newx = this.x;
        int newy = this.y;
        String stringx = Integer.toString(newx);
        String stringy = Integer.toString(newy);
        return  ("(" + stringx + "," + stringy + ")");
    }
}