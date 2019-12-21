package projekt_1;

import java.util.Random;

public class Genome {
    private int[] genome;
    public Genome()
    {
        int []firstGenome = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
        this.genome = firstGenome;
    }

    public int getNumber()
    {
        Random generator = new Random();
        return this.genome[generator.nextInt(31)];
    }

    public Genome makeNewGenome(Genome first, Genome second)
    {
        Genome newGenome = new Genome();
        Random generator = new Random();
        int firstGroupBorder = generator.nextInt(29-8)+8;
        int secondGroupBorder = generator.nextInt(30 - firstGroupBorder) + firstGroupBorder;
        int whichParent = generator.nextInt(1);
        for(int i = 0; i<8; i++)
        {
            newGenome.genome[i]=i;
        }
        for (int i = 8; i <= firstGroupBorder; i++) {
            if (whichParent == 1) {
                newGenome.genome[i] = first.genome[i];
            } else {
                newGenome.genome[i] = second.genome[i];
            }
        }
        whichParent = generator.nextInt(1);
        for (int i = firstGroupBorder + 1; i <= secondGroupBorder; i++) {
            if (whichParent == 1) {
                newGenome.genome[i] = first.genome[i];
            } else {
                newGenome.genome[i] = second.genome[i];
            }
        }
        if (whichParent == 0) {
            whichParent = 1;
        } else {
            whichParent = 0;
        }
        for (int i = secondGroupBorder + 1; i < 31; i++) {
            if (whichParent == 1) {
                newGenome.genome[i] = first.genome[i];
            } else {
                newGenome.genome[i] = second.genome[i];
            }
        }
        return newGenome;
    }
}
