package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyFinger extends BodyPart
{
    public BodyFinger()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.FINGER );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.FINGER;
    }

    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();

        BodyPart nail = new BodyNail();
        nail.create( name + "nail", "", animalType, Color.WHITE);
        nail.setAboveBodyPart( this );
        this.attachedBodyParts.add( nail );
    }

}
