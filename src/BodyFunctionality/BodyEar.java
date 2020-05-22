package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyEar extends BodyPart implements BodyPartInterface
{
    enum EarShape{NA, ROUND, HIDDEN, FRILLED, POINTED}

    protected EarShape earShape;

    public BodyEar()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.EAR );
        earShape = EarShape.POINTED;
    }

    public void setEarShape( EarShape earShape )
    {
        this.earShape = earShape;
    }

    public EarShape getEarShape()
    {
        return earShape;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.EAR;
    }

    @Override
    public BodyEar create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyEar thisPart = (BodyEar) super.create( name, side, animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        thisPart.addSkill( "Hearing: 5" );

        return thisPart;
    }


}
