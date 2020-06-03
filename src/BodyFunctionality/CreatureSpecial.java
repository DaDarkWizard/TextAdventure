package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CreatureSpecial
{

    static void createBody( BodyPartGenerator.AnimalType animalType, Body body )
    {
        if (animalType== BodyPartGenerator.AnimalType.HUMAN)
        {
            createHuman(body);
        }



    }


    static void createHuman(Body body)
    {
        //add head hair
        ArrayList<Color> colorList = new ArrayList<Color>();
        colorList.add( Color.WHITE );
        colorList.add( Color.BROWN );
        colorList.add( Color.TAN );
        colorList.add( Color.BLACK );

        Random randomNumbers = new Random();

        Color hairColor = colorList.get( randomNumbers.nextInt( colorList.size() ) );

        body.getBodyPart( "head" ).addFeature( "Hair color: " + hairColor.toString().toLowerCase() );

    }


}


