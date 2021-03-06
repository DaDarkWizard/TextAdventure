package BodyFunctionality;

import javafx.scene.paint.Color;
import java.nio.ByteBuffer;

public class BodyArm extends BodyPart
{
    public BodyArm()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.ARM );

    }

    /**
     * Constructor with a buffer containing the BodyArm data including it's children
     * This method does nothing but call the BodyPart class method constructor
     *
     * @param buffer ByteBuffer: The buffer containing the BodyArm data
     */
    public BodyArm( ByteBuffer buffer)
    {
        super(buffer);
    }

    /**
     * Copy Constructor
     * @param oldPart BodyArm:  The original part to copy
     */
    public BodyArm(BodyArm oldPart)
    {
        super(oldPart);
    }


    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.ARM;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        createUpperArm( name, side, creatureData, color );
    }

    public void createUpperArm (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyArm lowerArm = new BodyArm();
        lowerArm.createLowerArm("lower arm", side, creatureData, color);
        lowerArm.setAboveBodyPart( this );
        this.attachedBodyParts.add( lowerArm );
    }

    public void createLowerArm (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyHand hand = new BodyHand();
        if ( creatureData.isOpposable())
        {
            hand.createHand("hand", side, creatureData, color);
        }
        else
        {
            hand.createClaw("claw", side, creatureData, color);
        }
        hand.setAboveBodyPart( this );
        this.attachedBodyParts.add( hand );
    }

    public void createUpperLeg (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyArm lowerLeg = new BodyArm();
        lowerLeg.createLowerLeg("upper leg", side, creatureData, color);
        lowerLeg.setAboveBodyPart( this );
        this.attachedBodyParts.add( lowerLeg );
    }

    public void createLowerLeg (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyHand hand = new BodyHand();
        BodyPartGenerator.LimbType limbType = creatureData.getLimbStyle();
        if (creatureData.isOpposable() && limbType == BodyPartGenerator.LimbType.LEGS2WINGS2)
        {
            hand.createGraspingFoot("grasping foot", side, creatureData, color);
        }
        else if ( limbType == BodyPartGenerator.LimbType.ARMS2LEGS2
                || limbType == BodyPartGenerator.LimbType.ARMS2LEGS2ARMWINGS2
                || limbType == BodyPartGenerator.LimbType.LEGS2WINGS2)
        {
            hand.createFoot("foot", side, creatureData, color);
        }
        else
        {
            hand.createFoot("claw", side, creatureData, color);
        }

        hand.setAboveBodyPart( this );
        this.attachedBodyParts.add( hand );

    }

    public void createUpperWingArm (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyArm lowerArm = new BodyArm();
        lowerArm.createLowerArm("lower wing arm", side, creatureData, color);
        lowerArm.setAboveBodyPart( this );
        this.attachedBodyParts.add( lowerArm );
    }

    public void createLowerWingArm (String name, String side, CreatureDataObject creatureData, Color color)
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        BodyHand hand = new BodyHand();

            hand.createHand("wing", side, creatureData, color);


        hand.setAboveBodyPart( this );
        this.attachedBodyParts.add( hand );
    }



    /**
     * Method that creates a ByteBuffer containing the BodyArm's data.
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
