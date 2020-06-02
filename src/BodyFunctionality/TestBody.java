package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;



public class TestBody
{
    public static void main(String[] args)
    {
        CreatureData creatureData = new CreatureData();
        CreatureDataObject humanObject = creatureData.getCreatureDataObject( BodyPartGenerator.AnimalType.HUMAN );

        Body body = humanObject.generateRandomBody();
        System.out.println( body.toString() );
        System.out.println( "Total body parts: " + body.countParts() );

        ByteBuffer buffer = body.toBuffer();
        buffer.flip();
        // from BodyPart class

        Body body1 = new Body(buffer);

        System.out.println( body1.toString() );
        System.out.println( "Total body parts: " + body1.countParts() );
        System.out.println(  "Attached body parts to body: " + body1.attachedBodyParts);



    }





}
