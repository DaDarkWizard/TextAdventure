package BodyFunctionality;

import javafx.scene.paint.Color;
import java.nio.ByteBuffer;

public class BodyBack extends BodyPart
{
    public BodyBack()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.BACK );
    }

    /**
     * Constructor with a buffer containing the BodyBack data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyBack data
     */
    public BodyBack( ByteBuffer buffer)
    {
        super(buffer);
    }

    /**
     * Copy Constructor
     * @param oldPart BodyBack:  The original part to copy
     */
    public BodyBack(BodyBack oldPart)
    {
        super(oldPart);
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.BACK;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        switch(creatureData.getLimbStyle())
        {
            // create bird like wings with no arms
            case LEGS2WINGS2:
                BodyWing leftWing = new BodyWing();
                leftWing.create( "wing", "left", creatureData, color );
                leftWing.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftWing );

                BodyWing rightWing = new BodyWing();
                rightWing.create( "wing", "right", creatureData, color );
                rightWing.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightWing );

                // create dragon like wings with arms
            case LEGS4ARMWINGS2:
            case ARMS2LEGS2ARMWINGS2:
                BodyArm leftWingArm = new BodyArm();
                leftWingArm.createUpperWingArm( "wing arm", "left", creatureData, color );
                leftWingArm.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftWingArm );

                BodyArm rightWingArm = new BodyArm();
                rightWingArm.createUpperWingArm( "wing arm", "right", creatureData, color );
                rightWingArm.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightWingArm );

                break;
        }

        if (creatureData.isTailed())
        {
            BodyTail lefttail = new BodyTail();
            lefttail.create( "tail", "", creatureData, color );
            lefttail.setAboveBodyPart( this );
            this.attachedBodyParts.add( lefttail );

        }

    }

    /**
     * Method that creates a ByteBuffer containing the BodyBack's data.
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
