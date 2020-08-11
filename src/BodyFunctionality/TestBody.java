package BodyFunctionality;

import Transformation.TransformationCompare;
import Transformation.TransformationDifferences;

import java.nio.ByteBuffer;


public class TestBody
{
    public static void main(String[] args)
    {
        CreatureData creatureData = new CreatureData();
        CreatureDataObject humanObject = creatureData.getCreatureDataObject( BodyPartEnums.AnimalType.HUMAN );
        CreatureDataObject dragonObject = creatureData.getCreatureDataObject( BodyPartEnums.AnimalType.DRAGON );
        CreatureDataObject guineaPigObject = creatureData.getCreatureDataObject( BodyPartEnums.AnimalType.GUINEAPIG );


        Body bodyHuman = humanObject.generateRandomBody();
        Body bodyHuman1 = humanObject.generateRandomBody();
        Body bodyGuinea = guineaPigObject.generateRandomBody();

        Body bodyDragon = dragonObject.generateRandomBody();
        System.out.println( bodyGuinea.toString() );
        System.out.println( "Total body parts: " + bodyGuinea.countParts() );

        ByteBuffer buffer = bodyGuinea.toBuffer();
        buffer.flip();
        // from BodyPart class

        Body body1 = new Body(buffer);

        System.out.println( body1.toString() );
        System.out.println( "Total body parts: " + body1.countParts() );
        System.out.println(  "Attached body parts to body: " + body1);

        TransformationDifferences bodyDifferences;
        bodyDifferences = TransformationCompare.compare(bodyHuman, bodyGuinea);


    }





}
