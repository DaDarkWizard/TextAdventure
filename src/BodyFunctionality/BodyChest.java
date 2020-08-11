package BodyFunctionality;

import javafx.scene.paint.Color;
import java.nio.ByteBuffer;

public class BodyChest extends BodyPart
{
    protected BodyPartEnums.LimbType limbType;

    public BodyChest()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.CHEST );
    }

    /**
     * Constructor with a buffer containing the BodyChest data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyChest data
     */
    public BodyChest( ByteBuffer buffer)
    {
        super(buffer);
    }

    /**
     * Copy Constructor
     * @param oldPart BodyChest:  The original part to copy
     */
    public BodyChest(BodyChest oldPart)
    {
        super(oldPart);
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.CHEST;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        switch(creatureData.getLimbStyle())
        {
            //create 2 arms and 2 legs
            case ARMS2LEGS2:
            case ARMS2LEGS2ARMWINGS2:
                BodyUpperArm leftArm = new BodyUpperArm();
                leftArm.createUpperArm( "arm", "left", creatureData, color );
                leftArm.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftArm );

                BodyUpperArm rightArm = new BodyUpperArm();
                rightArm.createUpperArm( "arm", "right", creatureData, color );
                rightArm.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightArm );

                BodyUpperArm leftLeg = new BodyUpperArm();
                leftLeg.createUpperLeg( "leg", "left", creatureData, color );
                leftLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftLeg );

                BodyUpperArm rightLeg = new BodyUpperArm();
                rightLeg.createUpperLeg( "leg", "right", creatureData, color );
                rightLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightLeg );
                break;

                //create 4 legs
            case LEGS4:
            case LEGS4ARMWINGS2:
                BodyUpperArm leftFrontLeg = new BodyUpperArm();
                leftFrontLeg.createUpperLeg( "leg", "left front", creatureData, color );
                leftFrontLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftFrontLeg );

                BodyUpperArm rightFrontLeg = new BodyUpperArm();
                rightFrontLeg.createUpperLeg( "leg", "right front", creatureData, color );
                rightFrontLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightFrontLeg );

                BodyUpperArm leftBackLeg = new BodyUpperArm();
                leftBackLeg.createUpperLeg( "leg", "left back", creatureData, color );
                leftBackLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftBackLeg );

                BodyUpperArm rightBackLeg = new BodyUpperArm();
                rightBackLeg.createUpperLeg( "leg", "right back", creatureData, color );
                rightBackLeg.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightBackLeg );
                break;

                //create 2 legs
            case LEGS2WINGS2:
                BodyUpperArm leftLeg1 = new BodyUpperArm();
                leftLeg1.createUpperLeg( "leg", "left", creatureData, color );
                leftLeg1.setAboveBodyPart( this );
                this.attachedBodyParts.add( leftLeg1 );

                BodyUpperArm rightLeg1 = new BodyUpperArm();
                rightLeg1.createUpperLeg( "leg", "right", creatureData, color );
                rightLeg1.setAboveBodyPart( this );
                this.attachedBodyParts.add( rightLeg1 );
                break;

        }

    }

    /**
     * Method that creates a ByteBuffer containing the BodyChest's data.
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
