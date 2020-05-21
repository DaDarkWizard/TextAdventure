package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNose extends BodyPart implements BodyPartInterface
{

    enum NoseShape{NA, ROUND, HIDDEN, SPLITLIP, POINTED}

    protected NoseShape noseShape;

    public BodyNose()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.NOSE );
        noseShape = NoseShape.HIDDEN;
    }

    public void setNoseShape( NoseShape noseShape )
    {
        this.noseShape = noseShape;
    }

    public NoseShape getNoseShape()
    {
        return noseShape;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NOSE;
    }


    @Override
    public BodyNose create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyNose thisPart = (BodyNose) super.create( name, "", animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        thisPart.addSkill( "Smell: 5" );

        return thisPart;
    }


}
