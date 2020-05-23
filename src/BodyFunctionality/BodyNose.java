package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNose extends BodyPart
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
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();
        this.addSkill( "Smell: 5" );

    }


}
