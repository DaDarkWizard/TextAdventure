package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyFinger extends BodyPart implements BodyPartInterface
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
    public BodyFinger create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyFinger thisPart = (BodyFinger) super.create( name, side, animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyPart nail = new BodyNail();
        nail.create( name + "nail", "", animalType, Color.WHITE);
        nail.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( nail );

        return thisPart;
    }

}
