package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import static BodyFunctionality.CreatureCreator.createAverageHuman;

public class TestBody
{
    public static void main(String[] args)
    {
        Body body = createAverageHuman();
        System.out.println( body.toString() );
        System.out.println( "Total body parts: " + body.countParts() );

        ByteBuffer buffer = body.toBuffer();
        buffer.flip();
        // from BodyPart class

        System.out.println( "Buffer contents:\n" + buffer );

        String name = ByteBufferIO.getString( buffer );
        System.out.println( "Body Name: " + name );

        String description = ByteBufferIO.getString( buffer );
        System.out.println( "Description Type: " + description);

        BodyPartGenerator.BodyPartType bodyPartType = BodyPartGenerator.BodyPartType.fromOrdinal( buffer.getInt());
        System.out.println( "Body Part Type: " + bodyPartType);

        Color color = ByteBufferIO.getColor( buffer );
        System.out.println( "Color: " + color);

        BodyPartGenerator.Texture texture = BodyPartGenerator.Texture.fromOrdinal( buffer.getInt());
        BodyPartGenerator.AnimalType animalType = BodyPartGenerator.AnimalType.fromOrdinal( buffer.getInt());
        ArrayList<String> features = ByteBufferIO.getFeatures( buffer );
        ArrayList<String> itemsWorn = ByteBufferIO.getItemsWorn( buffer );
        ArrayList<String> resistances = ByteBufferIO.getResistances( buffer );
        ArrayList<String> skills = ByteBufferIO.getSkills( buffer );
        ArrayList<String> injuries = ByteBufferIO.getInjuries( buffer );
        double length = buffer.getDouble();
        double weight = buffer.getDouble();
        int maxHealth = buffer.getInt();
        int health = buffer.getInt();
        ArrayList<BodyPart> bodyParts = ByteBufferIO.getAttachedBodyParts( buffer );




        System.out.println( "Texture Type: " + texture);
        System.out.println( "Animal Type: " + animalType);
        System.out.println( "Number of features: " + features.size() );
        System.out.println( "Number of items worn: " + itemsWorn.size() );
        System.out.println( "Number of resistances: " + resistances.size() );
        System.out.println( "Number of skills: " + skills.size() );
        System.out.println( "Number of injuries: " + injuries.size() );
        System.out.println( "Length: " + length );
        System.out.println( "Weight: " + weight );
        System.out.println( "Max Health: " + maxHealth );
        System.out.println( "Health: " + health );
        //System.out.println( "Number of Attached Body Parts: " + bodyParts.size() );

    // from Body class
        String firstName = ByteBufferIO.getString( buffer );

        System.out.println( "Body First Name: " + firstName );


    }





}
