package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNail extends BodyPart
{
    public BodyNail()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.NAIL );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NAIL;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        this.texture = BodyPartGenerator.Texture.NAIL;

    }


}
