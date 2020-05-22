package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyEye extends BodyPart implements BodyPartInterface
{
    enum PupilShape{NA, ROUND, SLITTED, OTHER}

    protected PupilShape pupilShape;
    protected Color pupilColor, scleraColor;

    public BodyEye()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.EYE );
        pupilShape = PupilShape.ROUND;
        pupilColor = Color.BLACK;
        scleraColor = Color.WHITE;
    }

    public void setPupilShape( PupilShape pupilShape )
    {
        this.pupilShape = pupilShape;
    }

    public void setPupilColor( Color pupilColor )
    {
        this.pupilColor = pupilColor;
    }

    public void setScleraColor( Color scleraColor )
    {
        this.scleraColor = scleraColor;
    }

    public PupilShape getPupilShape()
    {
        return pupilShape;
    }

    public Color getPupilColor()
    {
        return pupilColor;
    }

    public Color getScleraColor()
    {
        return scleraColor;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.EYE;
    }


    @Override
    public BodyEye create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyEye thisPart = (BodyEye) super.create( name, "", animalType, Color.WHITE );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        thisPart.setTexture( BodyPartGenerator.Texture.SPECIAL );

        thisPart.addSkill( "Vision: 5" );

        return thisPart;
    }



}
