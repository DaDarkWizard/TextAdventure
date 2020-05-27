package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyTongue extends BodyPart
{
    public BodyTongue()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.TONGUE );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.TONGUE;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        this.addSkill( "Basic Taste: 5" );

    }

}
