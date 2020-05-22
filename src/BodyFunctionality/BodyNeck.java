package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNeck extends BodyPart implements BodyPartInterface
{
    public BodyNeck()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.NECK );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NECK;
    }


    @Override
    public BodyNeck create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyNeck thisPart = (BodyNeck) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyHead head = new BodyHead();
        head.create( "head", "", animalType, color);
        head.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( head );

        return thisPart;
    }


}
