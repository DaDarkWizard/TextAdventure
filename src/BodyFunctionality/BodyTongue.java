package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyTongue extends BodyPart
{
    public BodyTongue()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.TONGUE );
    }

    /**
     * Constructor with a buffer containing the BodyTongue data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyTongue data
     */
    public BodyTongue( ByteBuffer buffer)
    {
        super(buffer);

    }

    /**
     * Copy Constructor
     * @param oldPart BodyTongue:  The original part to copy
     */
    public BodyTongue (BodyTongue oldPart)
    {
        super(oldPart);
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.TONGUE;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.addSkill( "Basic Taste: 5" );
    }

    /**
     * Method that creates a ByteBuffer containing the BodyTongue's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=true;
        return isValid;
    }



}
