package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyHorn extends BodyPart
{
    enum HornStyle {NA, SHORT, STRAIGHT, CURVED, WAVE, ANTLER, OTHER}
    protected HornStyle hornStyle;

    public BodyHorn()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.HORN );
        hornStyle = HornStyle.STRAIGHT;

    }

    public void setHornStyle( HornStyle hornStyle )
    {
        this.hornStyle = hornStyle;
    }

    public HornStyle getHornStyle()
    {
        return hornStyle;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.HORN;
    }

    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        this.texture = BodyPartGenerator.Texture.BONE;

    }

}
