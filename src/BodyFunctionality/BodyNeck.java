package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNeck extends BodyPart
{
    public BodyNeck()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.NECK );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NECK;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyHead head = new BodyHead();
        if (animalType== BodyPartGenerator.AnimalType.HUMAN)
        {
            head.createHumanHead( "head", "", animalType, color );
        }
        else
        {
            head.createAnimalHead( "head", "", animalType, color );
        }
        head.setAboveBodyPart( this );
        this.attachedBodyParts.add( head );
    }

}
