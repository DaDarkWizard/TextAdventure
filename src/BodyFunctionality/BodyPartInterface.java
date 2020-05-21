package BodyFunctionality;

import javafx.scene.paint.Color;

public interface BodyPartInterface
{

    BodyPart create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color);

    String toString();

    BodyPartGenerator.BodyPartType bodyPartType();
}
