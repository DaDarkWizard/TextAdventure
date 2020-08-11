package BodyFunctionality;

import Skills.Skill;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyNose extends BodyPart
{

    protected BodyPartEnums.NoseShape noseShape;

    public BodyNose()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.NOSE );
        noseShape = BodyPartEnums.NoseShape.HIDDEN;
    }

    /**
     * Constructor with a buffer containing the BodyNose data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyNose data
     */
    public BodyNose( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyNose(Buffer) constructor" );

        noseShape = BodyPartEnums.NoseShape.fromOrdinal( buffer.getInt());
        System.out.println( "Setting nose shape to " + noseShape );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyNose:  The original part to copy
     */
    public BodyNose(BodyNose oldPart)
    {
        super(oldPart);
        noseShape = oldPart.getNoseShape();
    }

    public void setNoseShape( BodyPartEnums.NoseShape noseShape )
    {
        this.noseShape = noseShape;
    }

    public BodyPartEnums.NoseShape getNoseShape()
    {
        return noseShape;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.NOSE;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.addSkill( new Skill("Smell: 5") );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyNose's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+2)
        {
            buffer.putInt( noseShape.ordinal() );
            isValid = true;
        }
        return isValid;
    }

}
