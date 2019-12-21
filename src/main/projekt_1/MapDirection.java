package projekt_1;

public enum MapDirection {
    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;

    public MapDirection next()
    {
        switch(this)
        {
            case NORTH: return NORTH_EAST;
            case NORTH_EAST: return EAST;
            case EAST: return SOUTH_EAST;
            case SOUTH_EAST: return SOUTH;
            case SOUTH: return SOUTH_WEST;
            case SOUTH_WEST: return WEST;
            case WEST: return NORTH_WEST;
            case NORTH_WEST: return NORTH;
        }
        return null;
    }

    // składowe y w wektorach wiodących na północ i południ są odwrotne niżwskazywała by intuicja (np. układ współrzędnych kartezjańskich)
    //ponieważ uwzględniłem to, że mapa rysowana jest odbiciem lustrzanym względem osi y układu kartezjańskiego,
    //gdy zwierzak będzie poruszał się na północ, to na mapie będzie to widoczne, jako ruch zwierzaka do góry
    public Vector2d toUnitVector()
    {
        switch(this)
        {
            case NORTH:
                Vector2d northDir = new Vector2d(0,-1);
                return northDir;
            case NORTH_EAST:
                Vector2d northEastDir = new Vector2d(1,-1);
                return northEastDir;
            case EAST:
                Vector2d eastDir = new Vector2d(1,0);
                return eastDir;
            case SOUTH_EAST:
                Vector2d southEastDir = new Vector2d(1,1);
                return southEastDir;
            case SOUTH:
                Vector2d southDir = new Vector2d(0,1);
                return southDir;
            case SOUTH_WEST:
                Vector2d southWestDir = new Vector2d(-1,1);
                return southWestDir;
            case WEST:
                Vector2d westDir = new Vector2d(-1,0);
                return westDir;
            case NORTH_WEST:
                Vector2d northWestDir = new Vector2d(-1,-1);
                return northWestDir;
        }
    return null;
    }
}
