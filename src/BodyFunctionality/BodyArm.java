package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyArm extends BodyPart
{
    public BodyArm()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.ARM );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.ARM;
    }

    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        createUpperArm( name, side, animalType, color );
    }

    public void createUpperArm (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyArm lowerArm = new BodyArm();
        lowerArm.createLowerArm("lower arm", side, animalType, color);
        lowerArm.setAboveBodyPart( this );
        this.attachedBodyParts.add( lowerArm );
    }

    public void createLowerArm (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyHand hand = new BodyHand();
        if (BodyPartGenerator.handType( animalType )== BodyPartGenerator.HandType.HAND)
        {
            hand.createHand("hand", side, animalType, color);
        }
        else
        {
            hand.createClaw(BodyPartGenerator.handType( animalType ).toString().toLowerCase(), side, animalType, color);
        }
        hand.setAboveBodyPart( this );
        this.attachedBodyParts.add( hand );
    }

    public void createUpperLeg (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyArm lowerLeg = new BodyArm();
        lowerLeg.createLowerLeg("upper leg", side, animalType, color);
        lowerLeg.setAboveBodyPart( this );
        this.attachedBodyParts.add( lowerLeg );
    }

    public void createLowerLeg (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyHand hand = new BodyHand();
        if (BodyPartGenerator.footType( animalType )== BodyPartGenerator.FootType.GRASPINGFOOT)
        {
            hand.createGraspingFoot("grasping foot", side, animalType, color);
        }
        else if (BodyPartGenerator.footType( animalType )== BodyPartGenerator.FootType.FOOT)
        {
            hand.createFoot(name, side, animalType, color);
        }
        else
        {
            hand.createFoot(BodyPartGenerator.handType( animalType ).toString().toLowerCase(), side, animalType, color);
        }

        hand.setAboveBodyPart( this );
        this.attachedBodyParts.add( hand );

    }






}
