package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyArm extends BodyPart implements BodyPartInterface
{
    public BodyArm()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.ARM );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.ARM;
    }

    @Override
    public BodyArm create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        return createUpperArm( name, side, animalType, color );
    }

    public BodyArm createUpperArm (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {

        BodyArm thisPart = (BodyArm) super.create( name, side, animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyArm lowerArm = new BodyArm();
        lowerArm.createLowerArm("lower arm", side, animalType, color);
        lowerArm.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( lowerArm );

        return thisPart;
    }

    public BodyArm createLowerArm (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {

        BodyArm thisPart = (BodyArm) super.create( name, side, animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyHand hand = new BodyHand();
        if (BodyPartGenerator.handType( animalType )== BodyPartGenerator.HandType.HAND)
        {
            hand.createHand("hand", side, animalType, color);
        }
        else
        {
            hand.createClaw(BodyPartGenerator.handType( animalType ).toString().toLowerCase(), side, animalType, color);
        }
        hand.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( hand );

        return thisPart;
    }

    public BodyArm createUpperLeg (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {

        BodyArm thisPart = (BodyArm) super.create( name, side, animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyArm lowerLeg = new BodyArm();
        lowerLeg.createLowerLeg("upper leg", side, animalType, color);
        lowerLeg.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( lowerLeg );

        return thisPart;
    }

    public BodyArm createLowerLeg (String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {

        BodyArm thisPart = (BodyArm) super.create( name, side, animalType, color );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

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

        hand.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( hand );

        return thisPart;
    }






}
