package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface CreatureCreationInterface
{
    // values for arm and leg length
    double getMinArmLength();
    double getMaxArmLength();
    double getAverageArmLength();
    double getRandomArmLength();

    // values for weight factor (body parts have weight of length * weightfactor)
    double getMinWeightFactor();
    double getMaxWeightFactor();
    double getAverageWeightFactor();
    double getRandomWeightFactor();

    // values for back and chest
    double getMinBackLength();
    double getMaxBackLength();
    double getAverageBackLength();
    double getRandomBackLength();

    double getMinEarLength();
    double getMaxEarLength();
    double getAverageEarLength();
    double getRandomEarLength();

    double getMinEyeLength();
    double getMaxEyeLength();
    double getAverageEyeLength();
    double getRandomEyeLength();

    // values for fingers and toes
    double getMinFingerLength();
    double getMaxFingerLength();
    double getAverageFingerLength();
    double getRandomFingerLength();

    int getFingerCount();
    int getToeCount();
    boolean isOpposable();

    BodyPartGenerator.Stance getStance();

    double getMinHandLength();
    double getMaxHandLength();
    double getAverageHandLength();
    double getRandomHandLength();

    double getMinHornLength();
    double getMaxHornLength();
    double getAverageHornLength();
    double getRandomHornLength();

    boolean isHorned();
    BodyHorn.HornStyle getHornStyle();

    double getMinMouthLength();
    double getMaxMouthLength();
    double getAverageMouthLength();
    double getRandomMouthLength();

    BodyMouth.MouthStyle getMouthStyle();
    BodyMouth.TeethStyle getTeethStyle();

    double getMinMuzzleLength();
    double getMaxMuzzleLength();
    double getAverageMuzzleLength();
    double getRandomMuzzleLength();

    boolean isMuzzled();

    double getMinNailLength();
    double getMaxNailLength();
    double getAverageNailLength();
    double getRandomNailLength();

    double getMinNeckLength();
    double getMaxNeckLength();
    double getAverageNeckLength();
    double getRandomNeckLength();

    double getMinNoseLength();
    double getMaxNoseLength();
    double getAverageNoseLength();
    double getRandomNoseLength();

    double getMinTailLength();
    double getMaxTailLength();
    double getAverageTailLength();
    double getRandomTailLength();

    boolean isTailed();

    double getMinTongueLength();
    double getMaxTongueLength();
    double getAverageTongueLength();
    double getRandomTongueLength();

    double getMinWingLength();
    double getMaxWingLength();
    double getAverageWingLength();
    double getRandomWingLength();

    boolean isWinged();

    double getMinBodyLength();
    double getMaxBodyLength();
    double getAverageBodyLength();
    double getRandomBodyLength();

    // used to set Maximum health for character
    double getMinMaxHealth();
    double getMaxMaxHealth();
    double getAverageMaxHealth();
    double getRandomMaxHealth();

    BodyPartGenerator.Gender getRandomGender();

    BodyPartGenerator.Texture getBodyTexture();

    // one of possible colors for the body
    ArrayList<Color> getColors();
    Color getRandomColor();

    // still thinking about if these are possible items, choose one, or if they pertain to all the body
    ArrayList<String> getBodyFeatures();
    ArrayList<String> getBodyItemsWorn();    // change to item object later
    ArrayList<String> getBodyResistances();
    ArrayList<String> getBodySkills();
    ArrayList<Color> getEyeColors();
    Color getRandomEyeColor();

    // drops random items when a creature is killed
    ArrayList<String> randomItemDrop();


    /* this method makes all the body adjustments that cannot be made by other methods
     (i.e. changes unique features, adjusts color patterns, gives unique skills, etc)
     it should be called after the body is updated
    */
    void bodyAdjustments(Body body);

    Body generateRandomBody();

}
