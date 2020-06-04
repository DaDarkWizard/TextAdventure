package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyTail extends BodyPart
{
    protected BodyPartGenerator.TailType tailType;

    public BodyTail()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.TAIL );
        tailType = BodyPartGenerator.TailType.SICKLE;
    }


    /**
     * Constructor with a buffer containing the BodyTail data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyTail data
     */
    public BodyTail( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyTail(Buffer) constructor" );

        tailType = BodyPartGenerator.TailType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting tail shape to " + tailType );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyTail:  The original part to copy
     */
    public BodyTail(BodyTail oldPart)
    {
        super(oldPart);
        tailType = oldPart.getTailType();
    }


    public void setTailType( BodyPartGenerator.TailType tailType )
    {
        this.tailType = tailType;
    }

    public BodyPartGenerator.TailType getTailType()
    {
        return tailType;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.TAIL;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        length = creatureData.getRandomTailLength();
        weight = creatureData.getRandomWeightFactor() * length;
        tailType = creatureData.getTailType();

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
            buffer.putInt( tailType.ordinal() );
            isValid = true;
        }
        return isValid;
    }

}
