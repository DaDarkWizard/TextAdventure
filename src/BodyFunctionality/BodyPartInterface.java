package BodyFunctionality;

import Transformation.TransformationDifferences;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public interface BodyPartInterface
{

    // method that updates a BodyPart object
    void create(String name, String side, BodyPartEnums.AnimalType animalType, Color color);


    String toString();

    BodyPartEnums.BodyPartType bodyPartType();

    ByteBuffer toBuffer();

    TransformationDifferences differences( BodyPart anotherPart);
}
