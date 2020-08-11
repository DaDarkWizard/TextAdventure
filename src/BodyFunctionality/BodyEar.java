package BodyFunctionality;

import Skills.Skill;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyEar extends BodyPart
{
    protected BodyPartEnums.EarShape earShape;

    public BodyEar()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.EAR );
        earShape = BodyPartEnums.EarShape.POINTED;
    }

    /**
     * Constructor with a buffer containing the BodyEar data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyEar data
     */
    public BodyEar( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyEar(Buffer) constructor" );

        earShape = BodyPartEnums.EarShape.fromOrdinal( buffer.getInt());
        System.out.println( "Setting ear shape to " + earShape );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyEar:  The original part to copy
     */
    public BodyEar(BodyEar oldPart)
    {
        super(oldPart);
        earShape = oldPart.getEarShape();
    }



    public void setEarShape( BodyPartEnums.EarShape earShape )
    {
        this.earShape = earShape;
    }

    public BodyPartEnums.EarShape getEarShape()
    {
        return earShape;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.EAR;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        earShape = creatureData.getEarShape();

        this.addSkill( new Skill("Hearing: 5") );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyEar's data.
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
            buffer.putInt( earShape.ordinal() );
            isValid = true;
        }
        return isValid;
    }


}
