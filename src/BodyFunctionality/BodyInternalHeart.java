package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyInternalHeart extends BodyPart
{
    int maxEnergy, energy;

    /**
     * No argument constructor, sets default values
     */
    public BodyInternalHeart()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.HEART );
        this.setTexture( BodyPartEnums.Texture.SPECIAL );

        maxEnergy = 1;
        energy = 0;

    }

    /**
     * Constructor with a buffer containing the BodyInternalHeart data
     *
     * @param buffer ByteBuffer: The buffer containing the BodyInternalHeart data
     */
    public BodyInternalHeart( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyInternalHeart(Buffer) constructor" );

        maxEnergy = buffer.getInt();
        System.out.println( "Setting maxEnergy to " + maxEnergy );

        energy = buffer.getInt();
        System.out.println( "Setting maxEnergy to " + energy );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyInternalHeart:  The original part to copy
     */
    public BodyInternalHeart(BodyInternalHeart oldPart)
    {
        super( oldPart );
        maxEnergy= oldPart.getMaxEnergy();
        energy = oldPart.getEnergy();

    }

    public void setMaxEnergy( int maxEnergy )
    {
        this.maxEnergy = maxEnergy;
    }

    public void setEnergy( int energy )
    {
        this.energy = energy;
    }

    public int getMaxEnergy()
    {
        return maxEnergy;
    }

    public int getEnergy()
    {
        return energy;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.HEART;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.setTexture( BodyPartEnums.Texture.SPECIAL );


        maxEnergy = 1000;
        energy = 1000;

        /* todo update when implemented
        maxEnergy = creatureData.getRandomMaxEnergy();
        energy = maxEnergy;
        */

    }

    /**
     * Method that creates a ByteBuffer containing the BodyInternalHeart's data.
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

            buffer.putInt( maxEnergy );
            buffer.putInt( energy );

            isValid = true;
        }
        return isValid;
    }
}
