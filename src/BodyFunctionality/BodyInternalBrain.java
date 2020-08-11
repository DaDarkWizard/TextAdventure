package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyInternalBrain extends BodyPart
{
    int maxBrainPower, brainPower;

    /**
     * No argument constructor, sets default values
     */
    public BodyInternalBrain()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.HEART );
        this.setTexture( BodyPartEnums.Texture.SPECIAL );

        maxBrainPower = 1;
        brainPower = 0;

    }

    /**
     * Constructor with a buffer containing the BodyInternalBrain data
     *
     * @param buffer ByteBuffer: The buffer containing the BodyInternalBrain data
     */
    public BodyInternalBrain( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyInternalBrain(Buffer) constructor" );

        maxBrainPower = buffer.getInt();
        System.out.println( "Setting maxBrainPower to " + maxBrainPower );

        brainPower = buffer.getInt();
        System.out.println( "Setting brainPower to " + brainPower );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyInternalBrain:  The original part to copy
     */
    public BodyInternalBrain(BodyInternalBrain oldPart)
    {
        super( oldPart );
        maxBrainPower = oldPart.getMaxBrainPower();
        brainPower = oldPart.getBrainPower();

    }

    public void setMaxBrainPower( int maxBrainPower )
    {
        this.maxBrainPower = maxBrainPower;
    }

    public void setBrainPower( int brainPower )
    {
        this.brainPower = brainPower;
    }

    public int getMaxBrainPower()
    {
        return maxBrainPower;
    }

    public int getBrainPower()
    {
        return brainPower;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.BRAIN;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.setTexture( BodyPartEnums.Texture.SPECIAL );

        maxBrainPower = 1000;
        brainPower = 1000;

        /* todo update when implemented
        maxBrainPower = creatureData.getRandomMaxBrainPower();
        brainPower = maxBrainPower;
        */

    }

    /**
     * Method that creates a ByteBuffer containing the BodyInternalBrain's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+8)
        {

            buffer.putInt( maxBrainPower );
            buffer.putInt( brainPower );

            isValid = true;
        }
        return isValid;
    }


}
