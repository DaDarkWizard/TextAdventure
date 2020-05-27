package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyWing extends BodyPart
{
    enum WingType {NA, BIRD, BAT, OTHER}

    protected WingType wingType;

    public BodyWing()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.WING );
        wingType = WingType.BIRD;
    }

    public void setWingType( WingType wingType )
    {
        this.wingType = wingType;
    }

    public WingType getWingType()
    {
        return wingType;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.WING;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        this.texture = BodyPartGenerator.Texture.FEATHERED;

    }

}
