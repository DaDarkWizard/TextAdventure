package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyMuzzle extends BodyPart
{

    public BodyMuzzle()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.MUZZLE );
    }

    /**
     * Constructor with a buffer containing the BodyMuzzle data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyMuzzle data
     */
    public BodyMuzzle( ByteBuffer buffer)
    {
        super(buffer);

    }

    /**
     * Copy Constructor
     * @param oldPart BodyMuzzle:  The original part to copy
     */
    public BodyMuzzle(BodyMuzzle oldPart)
    {
        super(oldPart);
    }



    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.MUZZLE;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        double noseSize = creatureData.getRandomNoseLength();
        double noseWeight = creatureData.getRandomWeightFactor() * noseSize;

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", creatureData, color );
        nose.setNoseShape( creatureData.getNoseShape());
        nose.setLength( noseSize );
        nose.setWeight( noseWeight );
        nose.setAboveBodyPart( this );
        this.attachedBodyParts.add( nose );

        double mouthSize = creatureData.getRandomMouthLength();
        double mouthWeight = creatureData.getRandomWeightFactor() * mouthSize;

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", creatureData, Color.RED );
        mouth.setLength( mouthSize );
        mouth.setWeight( mouthWeight );
        mouth.setMouthStyle( creatureData.getMouthStyle() );
        mouth.setTeethStyle( creatureData.getTeethStyle() );
        mouth.setNumOfTeeth( creatureData.getTeethCount() );
        mouth.setAboveBodyPart( this );
        this.attachedBodyParts.add( mouth );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyMuzzle's data.
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
