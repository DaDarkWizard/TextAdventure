/**
 * This class holds Body Part Enums that are used in other Body Part Classes
 *
 *
 *
 * Created by Michael Clinesmith
 * Last update edit 8/11/2020
 * -added comment blocks
 * -rearranged order of method
 * -changed name of class
 */
package BodyFunctionality;

public class BodyPartEnums
{

    /**
     * The enum AnimalType holds the different kinds of creatures that can be created in the game
     */
    public enum AnimalType{ NA, MIXED, HUMAN, DRAGON, AIRIAN, GUINEAPIG, WYVERN, EAGLE, WOLF, SNAKE, BAT, RAT, OTHER;

        // enum methods to allow for retrieving type from integers
        private static final AnimalType[] allValues = values();
        public static AnimalType fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum BodyLimbTypeChange is used when transforming creatures from one kind to another, related to the body part
     * structure of arms/legs/wings to note if a change of arms, legs or wings would need to occur
     */
    public enum BodyLimbTypeChange {NONE, ARMSTOFRONTLEGS, ARMSTOWINGS, LEGSTOARMS, LEGSTOWINGS, WINGSTOLEGS,
        WINGSTOFRONTLEGS, LEGSTOBACKLEGS, OTHER;
        private static final BodyLimbTypeChange[] allValues = values();
        public static BodyLimbTypeChange fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum BodyPartType is used to note the kind of Body Part is being accessed
     */
    public enum BodyPartType{ NA, FINGER, HAND, ARM, WING, HEAD, MUZZLE, NAIL, HORN, TAIL, TONGUE, EYE, EAR, NOSE, MOUTH,
        NECK, CHEST, BACK, STOMACH, HEART, BRAIN, GLAND, BODY, SPECIAL;
        // enum methods to allow for retrieving type from integers
        private static final BodyPartType[] allValues = values();
        public static BodyPartType fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum EarShape is used to note the kind of ear shape a creature has
     */
    public enum EarShape{NA, ROUND, HIDDEN, FRILLED, POINTED, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final EarShape[] allValues = values();
        public static EarShape fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum FootType is used to note the kind of foot a creature has
     * todo will this be used?
     */
    public enum FootType{NA, FOOT, BACKCLAW, BACKPAW, GRASPINGFOOT, NONE, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final FootType[] allValues = values();
        public static FootType fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum Gender is used to note the gender of the creature
     */
    public enum Gender{NA, MALE, FEMALE, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final Gender[] allValues = values();
        public static Gender fromOrdinal(int n) {return allValues[n];}}

    /**
     * The enum GlandType is used to note the way a creature uses its "elemental" weapon,
     * e.g. by biting, a stream of breath, a diffuse gas
     */
    public enum GlandType {NA, INJECT, BREATH, GAS, OTHER;
        private static final GlandType[] allValues = values();
        public static GlandType fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used to note the type of hand a creature has
     * todo is this being used
     */
    public enum HandType{NA, HAND, FRONTCLAW, FRONTPAW, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final HandType[] allValues = values();
        public static HandType fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used to note the kind of horn a creature has
     */
    public enum HornStyle {NA, SHORT, STRAIGHT, CURVED, WAVE, ANTLER, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final HornStyle[] allValues = values();
        public static HornStyle fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used to note the kind of stance/body structure a creature has regarding
     * arms, legs, and wings
     */
    public enum LimbType {NA, ARMS2LEGS2, LEGS4, ARMS2LEGS2ARMWINGS2, LEGS4ARMWINGS2, LEGS2WINGS2, NOLIMBS, OTHER;
        private static final LimbType[] allValues = values();
        public static LimbType fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used to note the type of mouth a creature has
     */
    public enum MouthStyle{NA, FRONT, SMALL, WIDE, OTHER;// enum methods to allow for retrieving type from integers
        private static final MouthStyle[] allValues = values();
        public static MouthStyle fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used to note the shape of a creature's nose
     */
    public enum NoseShape{NA, ROUND, HIDDEN, SPLITLIP, POINTED, OTHER;
        private static final NoseShape[] allValues = values();
        public static NoseShape fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding transformations to check if the creature's transformation would involve
     * changing opposable thumbs and toes
     */
    public enum OpposableChange {NONE, ARMYESTONO, ARMNOTOYES, FOOTYESTONO, FOOTNOTOYES, OTHER;
        private static final OpposableChange[] allValues = values();
        public static OpposableChange fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding transformations to check if hands or feet are being transformed
     */
    public enum OpposableType {NONE, HANDS, FEET, HANDSFEET, OTHER;
        private static final OpposableType[] allValues = values();
        public static OpposableType fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the shape of a creature's eye pupil
     */
    public enum PupilShape{NA, ROUND, SLITTED, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final PupilShape[] allValues = values();
        public static PupilShape fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the which side of the Body a body part is on
     */
    public enum Side {NA, LEFT, RIGHT, MIDDLE, OTHER;
        private static final Side[] allValues = values();
        public static Side fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the shape of a creature's tail
     */
    public enum TailType {NA, BIRD, CAT, GATOR, FIN, SCREW, SICKLE, OTHER;
        private static final TailType[] allValues = values();
        public static TailType fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the shape of a creature's teeth
     */
    public enum TeethStyle{NA, HUMAN, GNAWING, SHARP, FANGED, SHARPRAGGED, OTHER;
        // enum methods to allow for retrieving type from integers
        private static final TeethStyle[] allValues = values();
        public static TeethStyle fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the texture of a body part - is if is skin, or covered with scales or feathers, etc.
     */
    public enum Texture{ NA, SKIN, SCALED, FEATHERED, LEATHER, HAIRY, NAIL, SHARPNAIL, BONE, SPIKY, ROUGH, EYE,
        MUSCLE, SPECIAL;
        // enum methods to allow for retrieving type from integers
        private static final Texture[] allValues = values();
        public static Texture fromOrdinal(int n) {return allValues[n];}}

    /**
     * This enum is used regarding the style of a creature's wing
     */
    public enum WingType {NA, BIRD, BAT, DRAGON, OTHER;
        private static final WingType[] allValues = values();
        public static WingType fromOrdinal(int n) {return allValues[n];}}

    /**
     * Method that returns the general type of texture for a creature
     * todo When a new creature is added, list it under the appropriate switch statement
     *
     * @param animalType AnimalType: The general type of creature
     * @return Texture: The texture that that general type of creature has
     */

    /*
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

    static HandType handType(AnimalType animalType)
    {
        HandType handType = HandType.FRONTCLAW;

        switch (animalType) {
            case HUMAN:
            case UPRIGHTDRAGON:

                handType = HandType.HAND;
                break;
            case DRAGON:
                handType = HandType.FRONTCLAW;
                break;
            case GUINEAPIG:
                handType = HandType.FRONTPAW;
                break;
            default:
                handType = HandType.NA;
        }

        return handType;

    }


    static FootType footType(AnimalType animalType)
    {
        FootType footType = FootType.BACKCLAW;

        switch (animalType) {
            case HUMAN:
            case UPRIGHTDRAGON:
                footType = FootType.FOOT;
                break;
            case DRAGON:
                footType = FootType.BACKCLAW;
                break;
            case GUINEAPIG:
                footType = FootType.BACKPAW;
                break;
            default:
                footType = FootType.NA;
        }

        return footType;

    }

    */
}
