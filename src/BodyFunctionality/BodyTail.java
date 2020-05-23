package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyTail extends BodyPart
{
    public BodyTail()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.TAIL );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.TAIL;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();

    }

}
