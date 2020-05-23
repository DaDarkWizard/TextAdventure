package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyChest extends BodyPart
{
    public BodyChest()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.FRONT );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.FRONT;
    }


    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();

        BodyArm leftArm = new BodyArm();
        leftArm.create( "Arm", "left", animalType, color );
        leftArm.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftArm );

        BodyArm rightArm = new BodyArm();
        rightArm.create( "Arm", "right", animalType, color );
        rightArm.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightArm );

        BodyArm leftLeg = new BodyArm();
        leftLeg.create( "Leg", "left", animalType, color );
        leftLeg.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftLeg );

        BodyArm rightLeg = new BodyArm();
        rightLeg.create( "Leg", "right", animalType, color );
        rightLeg.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightLeg );

    }
}
