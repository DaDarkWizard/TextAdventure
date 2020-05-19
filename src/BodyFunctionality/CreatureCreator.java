package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CreatureCreator
{

    public static Body createAverageHuman()
    {
        Body newBody = new Body();

        newBody.setFirstName( "Human" + Integer.toString( newBody.getIndentifier()) );
        newBody.setColor( Color.PEACHPUFF );
        newBody.setTexture(BodyPartGenerator.Texture.SKIN);
        newBody.setAnimalType( BodyPartGenerator.AnimalType.HUMAN );
        newBody.setGender( BodyPartGenerator.Gender.MALE );
        newBody.setDescription( "A male human");
        newBody.setStance( BodyPartGenerator.Stance.UPRIGHT );
        newBody.setLength( 70 );
        newBody.setWidth( 20 );
        newBody.setDepth( 12 );

        newBody.setMaxHealth( 1000 );
        newBody.setHealth( 1000 );

        BodyPart neck, leftLeg, rightLeg, leftArm, rightArm;

        neck = BodyPartGenerator.createNeck( BodyPartGenerator.AnimalType.HUMAN, Color.PEACHPUFF );
        leftLeg = BodyPartGenerator.createArm( "Leg", "Left", "Upper", BodyPartGenerator.AnimalType.HUMAN, Color.PEACHPUFF);
        rightLeg = BodyPartGenerator.createArm( "Leg", "Right", "Upper", BodyPartGenerator.AnimalType.HUMAN, Color.PEACHPUFF);
        leftArm = BodyPartGenerator.createArm( "Arm", "Left", "Upper", BodyPartGenerator.AnimalType.HUMAN, Color.PEACHPUFF);
        rightArm = BodyPartGenerator.createArm( "Arm", "Right", "Upper", BodyPartGenerator.AnimalType.HUMAN, Color.PEACHPUFF);

        neck.setAllBody( newBody );
        leftLeg.setAllBody( newBody );
        rightLeg.setAllBody( newBody );
        leftArm.setAllBody( newBody );
        rightArm.setAllBody( newBody );

        return newBody;
    }




}
