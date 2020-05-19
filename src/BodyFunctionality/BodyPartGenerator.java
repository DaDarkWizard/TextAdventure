package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BodyPartGenerator
{

    enum BodyPartType{ NA, FINGER, HAND, ARM, WING, HEAD, MUZZLE, NAIL, HORN, TAIL, TONGUE, EYE, EAR, NOSE, MOUTH,
                        STOMACH, HEART, MIND, SPECIAL}

    enum Texture{ NA, SKIN, SCALED, LEATHER, HAIRY, NAIL, BONE, SPIKY, ROUGH, SPECIAL }

    enum AnimalType{ NA, MIXED, HUMAN, DRAGON, UPRIGHTDRAGON, GUINEAPIG, OTHER }

    enum Stance{NA, UPRIGHT, ONFEET, ONGROUND, OTHER}

    enum Gender{NA, MALE, FEMALE, OTHER}

    static BodyPart createNail(String name, AnimalType animalType)
    {
        BodyPart nail = new BodyPart();

        nail.setName( name );
        nail.setType( BodyPartType.NAIL );
        nail.setColor( Color.WHITE);
        nail.setAnimalType( animalType );
        nail.setDescription( "A " + animalType.toString() + " " + name );

        return nail;
    }

    static BodyPart createFinger(String name, AnimalType animalType, Color color)
    {
        BodyPart finger = new BodyPart();
        finger.setName( name );
        finger.setType( BodyPartType.FINGER );
        finger.setColor( color );
        finger.setTexture(getAnimalTexture(animalType));
        finger.setAnimalType( animalType );
        finger.setDescription( "A " + animalType.toString() + " " + name );

        BodyPart nail = createNail( name + "nail", animalType);
        nail.setAboveBodyPart( finger );
        finger.attachedBodyParts.add( nail );

        return finger;
    }

    static BodyPart createHand(String name, String side, AnimalType animalType, Color color)
    {
        BodyPart hand = new BodyPart();
        hand.setName( side + " " + name );
        hand.setType( BodyPartType.HAND );
        hand.setColor( color );
        hand.setTexture(getAnimalTexture(animalType));
        hand.setAnimalType( animalType );
        hand.setDescription( "A " + animalType.toString() + " " + name );

        hand.attachedBodyParts.add( createFinger( side + " thumb", animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " first finger", animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " second finger", animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " third finger", animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " fourth finger", animalType, color ) );

        hand.addSkill( "Write" );
        hand.addSkill( "Wield" );

        attachBodyParts(hand.attachedBodyParts, hand);

        return hand;
    }

    static BodyPart createClaw(String name, String side, AnimalType animalType, Color color)
    {
        BodyPart hand = new BodyPart();
        hand.setName( side + " " + name );
        hand.setType( BodyPartType.HAND );
        hand.setColor( color );
        hand.setTexture(getAnimalTexture(animalType));
        hand.setAnimalType( animalType );
        hand.setDescription( "A " + animalType.toString() + " " + name );

        String digit = "finger";
        if (name.contains( "leg" ) || name.contains( "back" ))
        {
            digit = "toe";
        }


        hand.attachedBodyParts.add( createFinger( side + " first " + digit, animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " second " + digit, animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " third " + digit, animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " fourth " + digit, animalType, color ) );
        hand.attachedBodyParts.add( createFinger( side + " fifth " + digit, animalType, color ) );

        attachBodyParts(hand.attachedBodyParts, hand);

        return hand;
    }


    static BodyPart createArm(String name, String side, String location, AnimalType animalType, Color color)
    {
        BodyPart arm = new BodyPart();
        arm.setName( side + " " + location + " " + name );
        arm.setType( BodyPartType.ARM );
        arm.setColor( color );
        arm.setTexture(getAnimalTexture(animalType));
        arm.setAnimalType( animalType );

        if (location.equals( "upper" ))
        {
            arm.setDescription( "An " + animalType.toString() + " " + name );
            arm.attachedBodyParts.add( createArm( name, side, "lower" , animalType, color ) );
        }
        else
        {
            arm.setDescription( "A " + animalType.toString() + " " + name );
            arm.attachedBodyParts.add( createArm( name, side, "lower" , animalType, color ) );
        }

        attachBodyParts(arm.attachedBodyParts, arm);


        return arm;
    }

    static BodyPart createNeck(AnimalType animalType, Color color)
    {
        BodyPart neck = new BodyPart();
        neck.setName( "Neck" );
        neck.setType( BodyPartType.FINGER );
        neck.setColor( color );
        neck.setTexture(getAnimalTexture(animalType));
        neck.setAnimalType( animalType );

        if (animalType==AnimalType.HUMAN)
        {
            neck.attachedBodyParts.add( createHumanHead(animalType, color) );
        }
        else
        {
            neck.attachedBodyParts.add( createHead( animalType, color ) );
        }

        attachBodyParts(neck.attachedBodyParts, neck);

        return neck;
    }

    static BodyPart createHead(AnimalType animalType, Color color)
    {
        BodyPart head = new BodyPart();
        head.setName( "Head" );
        head.setType( BodyPartType.FINGER );
        head.setColor( color );
        head.setTexture(getAnimalTexture(animalType));
        head.setAnimalType( animalType );

        head.attachedBodyParts.add( createEye("Left", animalType, Color.GREEN));
        head.attachedBodyParts.add( createEye("Right", animalType, Color.GREEN));
        head.attachedBodyParts.add( createMuzzle(animalType, color));
        head.attachedBodyParts.add ( createEar("Left", animalType, color));
        head.attachedBodyParts.add ( createEar("Right", animalType, color));

        attachBodyParts(head.attachedBodyParts, head);

        return head;
    }

    static BodyPart createHumanHead(AnimalType animalType, Color color)
    {
        BodyPart head = new BodyPart();
        head.setName( "Head" );
        head.setType( BodyPartType.FINGER );
        head.setColor( color );
        head.setTexture(getAnimalTexture(animalType));
        head.setAnimalType( animalType );

        head.attachedBodyParts.add( createEye("Left", animalType, Color.BLUE));
        head.attachedBodyParts.add( createEye("Right", animalType, Color.BLUE));
        head.attachedBodyParts.add( createNose(animalType, color));
        head.attachedBodyParts.add( createMouth(animalType));
        head.attachedBodyParts.add( createEar("Left", animalType, color));
        head.attachedBodyParts.add( createEar("Right", animalType, color));

        attachBodyParts(head.attachedBodyParts, head);

        return head;
    }

    static BodyPart createEye(String side, AnimalType animalType, Color color)
    {
        BodyPart eye = new BodyPart();
        eye.setName( side + " Eye" );
        eye.setType( BodyPartType.EYE );
        eye.setColor( color );
        eye.setTexture(getAnimalTexture(animalType));
        eye.setAnimalType( animalType );
        eye.addFeature( "Sclera: WHITE" );
        eye.addFeature( "Pupil: BLACK" );

        return eye;
    }

    static BodyPart createEar(String side, AnimalType animalType, Color color)
    {
        BodyPart ear = new BodyPart();
        ear.setName( side + " Ear" );
        ear.setType( BodyPartType.EAR );
        ear.setColor( color );
        ear.setTexture(getAnimalTexture(animalType));
        ear.setAnimalType( animalType );
        ear.addFeature( "Shape: Round" );

        return ear;
    }

    static BodyPart createMuzzle(AnimalType animalType, Color color)
    {
        BodyPart muzzle = new BodyPart(  );
        muzzle.setName( "Muzzle" );
        muzzle.setType( BodyPartType.MUZZLE );
        muzzle.setColor( color );
        muzzle.setTexture(getAnimalTexture(animalType));
        muzzle.setAnimalType( animalType );

        muzzle.attachedBodyParts.add( createNose(animalType, color));
        muzzle.attachedBodyParts.add( createMouth(animalType));

        attachBodyParts(muzzle.attachedBodyParts, muzzle);

        return muzzle;
    }

    static BodyPart createNose(AnimalType animalType, Color color)
    {
        BodyPart nose = new BodyPart(  );
        nose.setName( "Nose" );
        nose.setType( BodyPartType.NOSE );
        nose.setColor( color );
        nose.setTexture(getAnimalTexture(animalType));
        nose.setAnimalType( animalType );
        nose.addFeature( "Shape: Round" );

        return nose;
    }

    static BodyPart createMouth(AnimalType animalType)
    {
        BodyPart mouth = new BodyPart(  );
        mouth.setName( "Mouth" );
        mouth.setType( BodyPartType.MOUTH );
        mouth.setColor( Color.RED );
        mouth.setTexture(getAnimalTexture(animalType));
        mouth.setAnimalType( animalType );

        mouth.attachedBodyParts.add( createTongue(animalType));

        attachBodyParts(mouth.attachedBodyParts, mouth);

        return mouth;
    }

    static BodyPart createTongue(AnimalType animalType)
    {
        BodyPart tongue = new BodyPart(  );
        tongue.setName( "Tongue" );
        tongue.setType( BodyPartType.TONGUE );
        tongue.setColor( Color.RED );
        tongue.setTexture(getAnimalTexture(animalType));
        tongue.setAnimalType( animalType );

        return tongue;
    }


    static void attachBodyParts(ArrayList<BodyPart> parts, BodyPart above)
    {
        for (int i=0; i<parts.size(); i++)
        {
            parts.get( i ).setAboveBodyPart( above );
        }
    }



    /**
     * Method that returns the general type of texture for a creature
     * todo When a new creature is added, list it under the appropriate switch statement
     *
     * @param animalType AnimalType: The general type of creature
     * @return Texture: The texture that that general type of creature has
     */
    static Texture getAnimalTexture(AnimalType animalType)
    {
        Texture texture = Texture.NA;

        switch (animalType) {
            case HUMAN:
                texture = Texture.SKIN;
                break;
            case DRAGON:
            case UPRIGHTDRAGON:
                texture = Texture.SCALED;
                break;
            case GUINEAPIG:
                texture = Texture.HAIRY;
                break;
            default:
                texture = Texture.NA;
        }

        return texture;
    }

    static String handType(AnimalType animalType)
    {
        String handType = "front claw";

        switch (animalType) {
            case HUMAN:
            case UPRIGHTDRAGON:
                handType = "hand";
                break;
            case DRAGON:
                handType = "front claw";
                break;
            case GUINEAPIG:
                handType = "front paw";
                break;
            default:
                handType = "front claw";
        }

        return handType;

    }

    static String footType(AnimalType animalType)
    {
        String handType = "back claw";

        switch (animalType) {
            case HUMAN:
            case UPRIGHTDRAGON:
                handType = "foot";
                break;
            case DRAGON:
                handType = "back claw";
                break;
            case GUINEAPIG:
                handType = "back paw";
                break;
            default:
                handType = "back claw";
        }

        return handType;

    }





}
