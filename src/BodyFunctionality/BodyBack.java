package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyBack extends BodyPart
{
    public BodyBack()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.BACK );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.BACK;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();

    }
}
