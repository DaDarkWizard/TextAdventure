package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BodyPartGenerator
{

    enum BodyPartType{ NA, FINGER, HAND, ARM, WING, HEAD, MUZZLE, NAIL, HORN, TAIL, TONGUE, EYE, EAR, NOSE, MOUTH,
                        NECK, CHEST, BACK, STOMACH, HEART, BRAIN, GLAND, SPECIAL;
        // enum methods to allow for retreiving type from integers
        private static final BodyPartType[] allValues = values();
        public static BodyPartType fromOrdinal(int n) {return allValues[n];}}

    enum Texture{ NA, SKIN, SCALED, FEATHERED, LEATHER, HAIRY, NAIL, SHARPNAIL, BONE, SPIKY, ROUGH, EYE, MUSCLE, SPECIAL;
        // enum methods to allow for retreiving type from integers
        private static final Texture[] allValues = values();
        public static Texture fromOrdinal(int n) {return allValues[n];}}

    enum AnimalType{ NA, MIXED, HUMAN, DRAGON, AIRIAN, GUINEAPIG, WYVERN, EAGLE, WOLF, SNAKE, BAT, RAT, OTHER;

        // enum methods to allow for retreiving type from integers
        private static final AnimalType[] allValues = values();
        public static AnimalType fromOrdinal(int n) {return allValues[n];}}

    enum Gender{NA, MALE, FEMALE, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final Gender[] allValues = values();
        public static Gender fromOrdinal(int n) {return allValues[n];}}


    enum HandType{NA, HAND, FRONTCLAW, FRONTPAW, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final HandType[] allValues = values();
        public static HandType fromOrdinal(int n) {return allValues[n];}}

    enum FootType{NA, FOOT, BACKCLAW, BACKPAW, GRASPINGFOOT, NONE, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final FootType[] allValues = values();
        public static FootType fromOrdinal(int n) {return allValues[n];}}

    enum EarShape{NA, ROUND, HIDDEN, FRILLED, POINTED, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final EarShape[] allValues = values();
        public static EarShape fromOrdinal(int n) {return allValues[n];}}

    enum PupilShape{NA, ROUND, SLITTED, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final PupilShape[] allValues = values();
        public static PupilShape fromOrdinal(int n) {return allValues[n];}}

    enum HornStyle {NA, SHORT, STRAIGHT, CURVED, WAVE, ANTLER, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final HornStyle[] allValues = values();
        public static HornStyle fromOrdinal(int n) {return allValues[n];}}

    enum TeethStyle{NA, HUMAN, GNAWING, SHARP, FANGED, SHARPRAGGED, OTHER;
        // enum methods to allow for retreiving type from integers
        private static final TeethStyle[] allValues = values();
        public static TeethStyle fromOrdinal(int n) {return allValues[n];}}


    enum MouthStyle{NA, FRONT, SMALL, WIDE, OTHER;// enum methods to allow for retreiving type from integers
        private static final MouthStyle[] allValues = values();
        public static MouthStyle fromOrdinal(int n) {return allValues[n];}}

    enum NoseShape{NA, ROUND, HIDDEN, SPLITLIP, POINTED, OTHER;
        private static final NoseShape[] allValues = values();
        public static NoseShape fromOrdinal(int n) {return allValues[n];}}

    enum WingType {NA, BIRD, BAT, DRAGON, OTHER;
        private static final WingType[] allValues = values();
        public static WingType fromOrdinal(int n) {return allValues[n];}}

    enum TailType {NA, BIRD, CAT, GATOR, FIN, SCREW, SICKLE, OTHER;
        private static final TailType[] allValues = values();
        public static TailType fromOrdinal(int n) {return allValues[n];}}

    enum LimbType {NA, ARMS2LEGS2, LEGS4, ARMS2LEGS2ARMWINGS2, LEGS4ARMWINGS2, LEGS2WINGS2, NOLIMBS, OTHER;
        private static final LimbType[] allValues = values();
        public static LimbType fromOrdinal(int n) {return allValues[n];}}

    enum GlandType {NA, INJECT, BREATH, GAS, OTHER;
        private static final GlandType[] allValues = values();
        public static GlandType fromOrdinal(int n) {return allValues[n];}}

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
