package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CreatureCreator
{

    protected CreatureData creatureData;

    public CreatureCreator()
    {
        creatureData = new CreatureData();
    }


    /*
    public static Body createAverageHuman()
    {
        CreatureDataObject human = creatureData.getCreatureDataObject( BodyPartGenerator.AnimalType.HUMAN );
        Body body = human.generateRandomBody();
        return body;
    }
    */
}
