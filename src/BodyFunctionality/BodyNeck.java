package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyNeck extends BodyPart
{
    public BodyNeck()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.NECK );
    }

    /**
     * Constructor with a buffer containing the BodyNeck data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyNeck data
     */
    public BodyNeck( ByteBuffer buffer)
    {
        super(buffer);
    }

    /**
     * Copy Constructor
     * @param oldPart BodyNeck:  The original part to copy
     */
    public BodyNeck(BodyNeck oldPart)
    {
        super(oldPart);
    }


    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.NECK;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        length = creatureData.getRandomNeckLength();
        weight = creatureData.getRandomWeightFactor() * length;

        BodyHead head = new BodyHead();
        if (creatureData.isMuzzled())
        {
            head.createAnimalHead( "head", "", creatureData, color );
        }
        else
        {
            head.createHumanHead( "head", "", creatureData, color );
        }

        double headSize = creatureData.getRandomHeadLength();
        double headWeight = creatureData.getRandomWeightFactor() * headSize;

        head.setLength( headSize );
        head.setWeight( headWeight );
        head.setAboveBodyPart( this );
        this.attachedBodyParts.add( head );



    }

    /**
     * Method that creates a ByteBuffer containing the BodyNeck's data.
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
