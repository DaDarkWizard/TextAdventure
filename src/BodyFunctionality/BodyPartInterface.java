package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public interface BodyPartInterface
{

    // method that updates a BodyPart object
    void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color);


    String toString();

    BodyPartGenerator.BodyPartType bodyPartType();

    ByteBuffer toBuffer();
}
