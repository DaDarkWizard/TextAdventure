package BodyFunctionality;

import Transformation.TransformationCompare;
import Transformation.TransformationDifferences;
import javafx.scene.paint.Color;
import BodyFunctionality.CreatureData;
import java.nio.ByteBuffer;
import java.util.ArrayList;



public class TestBody
{
    public static void main(String[] args)
    {
        CreatureData creatureData = new CreatureData();
        CreatureDataObject humanObject = creatureData.getCreatureDataObject( BodyPartGenerator.AnimalType.HUMAN );
        CreatureDataObject dragonObject = creatureData.getCreatureDataObject( BodyPartGenerator.AnimalType.DRAGON );

        Body bodyHuman = humanObject.generateRandomBody();
        Body bodyHuman1 = humanObject.generateRandomBody();

        Body bodyDragon = dragonObject.generateRandomBody();
        System.out.println( bodyDragon.toString() );
        System.out.println( "Total body parts: " + bodyDragon.countParts() );

        ByteBuffer buffer = bodyDragon.toBuffer();
        buffer.flip();
        // from BodyPart class

        Body body1 = new Body(buffer);

        System.out.println( body1.toString() );
        System.out.println( "Total body parts: " + body1.countParts() );
        System.out.println(  "Attached body parts to body: " + body1);

        TransformationDifferences bodyDifferences;
        bodyDifferences = TransformationCompare.compare(bodyHuman, bodyHuman1);


    }





}
