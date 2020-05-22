package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyTongue extends BodyPart implements BodyPartInterface
{
    public BodyTongue()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.TONGUE );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.TONGUE;
    }


    @Override
    public BodyTongue create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyTongue thisPart = (BodyTongue) super.create( name, "", animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        thisPart.addSkill( "Basic Taste" );

        return thisPart;
    }

}
