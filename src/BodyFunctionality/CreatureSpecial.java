package BodyFunctionality;

import Skills.Skill;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CreatureSpecial
{

    static void createBody( CreatureDataObject creatureDataObject, Body body )
    {
        BodyPartEnums.AnimalType animalType = body.getAnimalType();

        switch (animalType)
        {
            case HUMAN:
                createHuman( creatureDataObject, body );
                break;
            case DRAGON:
                createDragon( creatureDataObject, body );
                break;
        }


    }

    /**
     * Method to add random special features when creating a human
     * @param creatureDataObject CreatureDataObject: Data related to human creation
     * @param body Body: A human Body object
     */
    static void createHuman(CreatureDataObject creatureDataObject, Body body)
    {
        //add head hair
        ArrayList<Color> colorList = new ArrayList<Color>();
        colorList.add( Color.WHITE );
        colorList.add( Color.BROWN );
        colorList.add( Color.TAN );
        colorList.add( Color.BLACK );

        Random randomNumbers = new Random();

        Color hairColor = colorList.get( randomNumbers.nextInt( colorList.size() ) );

        body.getBodyPart( "head" ).addFeature( new BodyFeature("Hair color: " + hairColor.toString().toLowerCase(), 0 ));

    }

    /**
     * Method to add random special features when creating a dragon
     * @param creatureDataObject CreatureDataObject: Data related to dragon creation
     * @param body Body: A dragon Body object
     */
    static void createDragon(CreatureDataObject creatureDataObject, Body body)
    {

        // place head horns todo modify later for more variety in horns
        double hornLength = creatureDataObject.getRandomHornLength();
        double hornWeight = creatureDataObject.getRandomWeightFactor() * hornLength;

        BodyHorn leftHeadHorn, rightHeadHorn;
        BodyHead head;
        BodyInternalGland fireGland;
        Color hornColor = Color.GAINSBORO;

        head = (BodyHead) body.getBodyPart( "head" );

        leftHeadHorn = new BodyHorn(  );
        rightHeadHorn = new BodyHorn(  );


        leftHeadHorn.create( "forehead horn", "left", creatureDataObject, hornColor);
        leftHeadHorn.setAboveBodyPart( head );
        leftHeadHorn.setLength( hornLength );
        leftHeadHorn.setWeight( hornWeight );
        leftHeadHorn.setThisBody( body );

        rightHeadHorn.create( "forehead horn", "right", creatureDataObject, hornColor);
        rightHeadHorn.setAboveBodyPart( head );
        rightHeadHorn.setLength( hornLength );
        rightHeadHorn.setWeight( hornWeight );
        rightHeadHorn.setThisBody( body );

        head.attachedBodyParts.add(leftHeadHorn);
        head.attachedBodyParts.add(rightHeadHorn);

        fireGland = new BodyInternalGland(  );
        fireGland.create( "firebreath gland" , "", creatureDataObject, hornColor);
        fireGland.addSkill( new Skill("FireBreath: 10") );
        fireGland.setAboveBodyPart( body );
        fireGland.setThisBody( body );
        body.internalBodyParts.add(fireGland);


    }

}


