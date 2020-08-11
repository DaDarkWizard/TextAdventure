package BodyFunctionality;

import Skills.Skill;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyEye extends BodyPart
{


    protected BodyPartEnums.PupilShape pupilShape;
    protected Color pupilColor, scleraColor;

    public BodyEye()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.EYE );
        pupilShape = BodyPartEnums.PupilShape.ROUND;
        pupilColor = Color.BLACK;
        scleraColor = Color.WHITE;
    }

    /**
     * Constructor with a buffer containing the BodyEye data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyEye data
     */
    public BodyEye( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyEye(Buffer) constructor" );

        pupilShape = BodyPartEnums.PupilShape.fromOrdinal( buffer.getInt());
        System.out.println( "Setting eye shape to " + pupilShape );

        pupilColor = ByteBufferIO.getColor( buffer );
        System.out.println( "Setting pupil color to " + pupilColor );

        scleraColor = ByteBufferIO.getColor( buffer );
        System.out.println( "Setting sclera color to " + scleraColor );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyEye:  The original part to copy
     */
    public BodyEye(BodyEye oldPart)
    {
        super(oldPart);
        pupilShape = oldPart.getPupilShape();
        pupilColor = oldPart.getPupilColor();
        scleraColor = oldPart.getScleraColor();
    }


    public void setPupilShape( BodyPartEnums.PupilShape pupilShape )
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

    public BodyPartEnums.PupilShape getPupilShape()
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
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.EYE;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.texture = BodyPartEnums.Texture.EYE;
        pupilColor = Color.BLACK;
        scleraColor = Color.WHITE;
        this.addSkill( new Skill("Vision: 5") );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyEye's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+22)
        {
            buffer.putInt( pupilShape.ordinal() );
            System.out.println( "Buffering pupil shape as " + pupilShape.ordinal() );
            ByteBufferIO.putColor( buffer, pupilColor );
            ByteBufferIO.putColor( buffer, scleraColor );
            isValid = true;
        }
        return isValid;
    }


}
