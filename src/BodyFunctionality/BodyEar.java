package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyEar extends BodyPart
{
    enum EarShape{NA, ROUND, HIDDEN, FRILLED, POINTED}

    protected EarShape earShape;

    public BodyEar()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.EAR );
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
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        this.addSkill( "Hearing: 5" );

    }


}
