package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyNail extends BodyPart implements BodyPartInterface
{
    public BodyNail()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.NAIL );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NAIL;
    }


    @Override
    public BodyNail create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyNail thisPart = (BodyNail) super.create( name, "", animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        thisPart.setTexture( BodyPartGenerator.Texture.NAIL );

        return thisPart;
    }


}
