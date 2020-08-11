/**
 * This class holds data about the differences between two body objects
 *
 * Created by Michael Clinesmith 7/6/2020
 * Last edited 7/20/2020
 */
package Transformation;


import BodyFunctionality.*;
import BodyFunctionality.BodyPartEnums;

import java.util.ArrayList;

public class TransformationDifferences {

    double lengthDifferences;
    int colorDifferences, bodyPartDifferences, animalTypeDifferences, missingParts, extraParts,
            featureDifferences, resistanceTypeDifferences, skillTypeDifferences, injuryTypeDifferences,
            textureDifferences, valueDifferences;

    public TransformationDifferences()
    {
        lengthDifferences = 0.0;
        colorDifferences = 0;
        bodyPartDifferences = 0;
        animalTypeDifferences = 0;
        missingParts = 0;
        extraParts = 0;
        featureDifferences = 0;
        resistanceTypeDifferences = 0;
        skillTypeDifferences = 0;
        injuryTypeDifferences = 0;
        textureDifferences = 0;
        valueDifferences = 0;
    }

    /**
     * Method that calculates the differences between two bodies
     * It will compare the differences in values between the two bodies and in all its body parts
     *
     * Different factors to note due to difference stance types:
     * Actual Stances: NA, ARMS2LEGS2, LEGS4, ARMS2LEGS2ARMWINGS2, LEGS4ARMWINGS2, LEGS2WINGS2, NOLIMBS, OTHER
     *
     * any usage of NA, NOLIMBS and OTHER match straight up
     * if same stance type, match straight up
     *
     * ARMS2LEGS2 TO LEGS4 or LEGS4ARMWINGS2 match arms to front legs and legs to back legs
     * ARMS2LEGS2 TO ARMS2LEGS2ARMWING2, match straight up
     * ARMS2LEGS2 TO LEGS2WINGS2, match arms to wings
     *
     * LEGS4 TO ARMS2LEGS2 or ARMS2LEGS2ARMWINGS2 match front legs to arms and back legs to legs
     * LEGS4 TO LEGS4ARMWINGS2 match straight up
     * LEGS4 TO LEGS2WINGS2 match front legs to wings and back legs to legs
     *
     * ARMS2LEGS2ARMWINGS2 TO ARMSLEGS2 or LEGS2WINGS2 match straight up
     * ARMS2LEGS2ARMWINGS2 TO LEGS4 or LEGS4ARMWINGS2 match arms to front legs and legs to back legs
     *
     * LEGS2WINGS2 TO ARMS2LEGS2 match wings to legs
     * LEGS2WINGS2 TO LEGS4 match wings to front legs and legs to back legs
     * LEGS2WINGS2 TO ARMS2LEGS2ARMWINGS2 match straight up
     * LEGS2WINGS2 TO LEGS4ARMWINGS2 match legs to back legs
     *
     *
     * Different factors to not due to opposable fingers/toes
     * if the same opposability, match straight up
     * from opposable to nonopposable, match thumb to first finger (toe) and nth finger to (n+1)th finger
     * from nonopposable to opposable, match first finger to thumb and (n+1)th finger to nth finger (toe)
     *
     * @param body1 Body: The first Body object
     * @param body2 Body: The second Body object
     */
    public TransformationDifferences(Body body1, Body body2)
    {
        lengthDifferences = 0.0;
        colorDifferences = 0;
        bodyPartDifferences = 0;
        animalTypeDifferences = 0;
        missingParts = 0;
        extraParts = 0;
        featureDifferences = 0;
        resistanceTypeDifferences = 0;
        skillTypeDifferences = 0;
        injuryTypeDifferences = 0;
        textureDifferences = 0;
        valueDifferences = 0;

        System.out.println("Comparing differences between " + body1.getDescription() + " and " + body2.getDescription());


        ArrayList<BodyPart> body1AllParts = body1.getAllBodyParts();
        ArrayList<BodyPart> body2AllParts = body2.getAllBodyParts();
        BodyPart currentPart1, currentPart2;
        String nextPartNameMatch;
        TransformationDifferences differencesBetweenBodyParts;
        BodyPartEnums.OpposableChange handChange;
        BodyPartEnums.BodyLimbTypeChange  limbChange;


        if (true) //todo adjust for different limb types body1.getLimbType() == body2.getLimbType())
        {
            for(int i=0; i<body1AllParts.size(); i++)
            {
                currentPart1 = body1AllParts.get(i);

                handChange = CheckOpposableChange(body1, body2);
                limbChange = CheckLimbChange(body1, body2);

                nextPartNameMatch = MatchingName(currentPart1, handChange, limbChange);
                currentPart2 = body2.getBodyPart(currentPart1.getName());
                if (currentPart2==null)
                {
                    extraParts++;
                    System.out.println("Part " + currentPart1.getDescription() + " only exists on first body");
                }
                else
                {
                    differencesBetweenBodyParts = currentPart1.differences(currentPart2);
                    System.out.println("Differences between body part " + currentPart1.getDescription()
                        + " and " + currentPart2.getDescription() + "\n" + differencesBetweenBodyParts + "\n");

                    this.addDifferences(differencesBetweenBodyParts);
                }
            }

            for(int i=0; i<body2AllParts.size(); i++)
            {
                currentPart2 = body2AllParts.get(i);
                currentPart1 = body1.getBodyPart(currentPart2.getName());
                if (currentPart1==null)
                {
                    System.out.println("Part " + currentPart2.getDescription() + " only exists on second body");


                    missingParts++;
                }

            }

            System.out.println("Total differences between the two bodies\n" + this.toString() + "\n");

        }

    }



    public void setAnimalTypeDifferences(int animalTypeDifferences) {
        this.animalTypeDifferences = animalTypeDifferences;
    }

    public void setBodyPartDifferences(int bodyPartDifferences) {
        this.bodyPartDifferences = bodyPartDifferences;
    }

    public void setColorDifferences(int colorDifferences) {
        this.colorDifferences = colorDifferences;
    }

    public void setExtraParts(int extraParts) {
        this.extraParts = extraParts;
    }

    public void setFeatureDifferences(int featureDifferences) {
        this.featureDifferences = featureDifferences;
    }

    public void setInjuryTypeDifferences(int injuryTypeDifferences) {
        this.injuryTypeDifferences = injuryTypeDifferences;
    }

    public void setLengthDifferences(double lengthDifferences) {
        this.lengthDifferences = lengthDifferences;
    }

    public void setMissingParts(int missingParts) {
        this.missingParts = missingParts;
    }

    public void setResistanceTypeDifferences(int resistanceTypeDifferences) {
        this.resistanceTypeDifferences = resistanceTypeDifferences;
    }

    public void setSkillTypeDifferences(int skillTypeDifferences) {
        this.skillTypeDifferences = skillTypeDifferences;
    }

    public void setTextureDifferences(int textureDifferences) {
        this.textureDifferences = textureDifferences;
    }

    public void setValueDifferences(int valueDifferences) {
        this.valueDifferences = valueDifferences;
    }

    public int getAnimalTypeDifferences() {
        return animalTypeDifferences;
    }

    public int getBodyPartDifferences() {
        return bodyPartDifferences;
    }

    public int getColorDifferences() {
        return colorDifferences;
    }

    public int getExtraParts() {
        return extraParts;
    }

    public int getFeatureDifferences() {
        return featureDifferences;
    }

    public int getInjuryTypeDifferences() {
        return injuryTypeDifferences;
    }

    public double getLengthDifferences() {
        return lengthDifferences;
    }

    public int getMissingParts() {
        return missingParts;
    }

    public int getResistanceTypeDifferences() {
        return resistanceTypeDifferences;
    }

    public int getSkillTypeDifferences() {
        return skillTypeDifferences;
    }

    public int getTextureDifferences() {
        return textureDifferences;
    }

    public int getValueDifferences() {
        return valueDifferences;
    }

    /**
     * Method to increment the animalTypeDifferences field by one
     */
    public void addAnimalTypeDifference()
    {
        animalTypeDifferences++;
    }

    /**
     * Method to increment the bodyPartDifferences field by one
     */
    public void addBodyPartDifference()
    {
        bodyPartDifferences++;
    }

    /**
     * Method to increment the addColorDifferences field by one
     */
    public void addColorDifference()
    {
        colorDifferences++;
    }


    /**
     * Method to increment the extraParts field by one
     */
    public void addExtraPart() {
        this.extraParts += 1;
    }

    /**
     * Method to add the given amount to the feature differences variable
     * @param addedDifferences int: The amount to add to the featureDifferences field
     */
    public void addFeatureDifferences(int addedDifferences) {
        this.featureDifferences += addedDifferences;
    }

    /**
     * Method to add the given amount to the injury differences variable
     * @param addedDifferences int: The amount to add to the injuryTypeDifferences field
     */
    public void addInjuriesDifferences(int addedDifferences)
    {
        this.injuryTypeDifferences += addedDifferences;
    }

    /**
     * Method that adds to lengthDifferences the given amount
     * @param addedDifferences double: Amount to add to the lengthDifferences field
     */
    public void addLengthDifferences(double addedDifferences) {
        this.lengthDifferences += addedDifferences;
    }

    /**
     * Method to increment the missing part variable by one
     */
    public void addMissingPart() {
        this.missingParts += 1;
    }

    /**
     * Method to add the given amount to the resistance differences variable
     * @param addedDifferences int: The amount to add to the resistanceTypeDifferences field
     */
    public void addResistanceDifferences(int addedDifferences)
    {
        this.resistanceTypeDifferences += addedDifferences;
    }

    /**
     * Method to add the given amount to the skills differences variable
     * @param addedDifferences int: The amount to add to the skillsTypeDifferences field
     */
    public void addSkillsDifferences(int addedDifferences)
    {
        this.skillTypeDifferences += addedDifferences;
    }

    /**
     * Method to increment the given amount of textureDifference by one
     */
    public void addTextureDifference() {
        this.textureDifferences += 1;
    }

    /**
     * Method to add the given amount to the valueDifference
     * @param addedDifferences int: The amount to add to the valueDifferences field
     */
    public void addValueDifferences(int addedDifferences) {
        this.valueDifferences += addedDifferences;
    }

    /**
     * Method to add the values in the parameter to this object's field values
     * @param obj2 TransformationDifferences: Hold data on differences between two BodyPart objects
     */
    public void addDifferences(TransformationDifferences obj2)
    {
        lengthDifferences += obj2.getLengthDifferences();
        colorDifferences += obj2.getColorDifferences();
        bodyPartDifferences += obj2.getBodyPartDifferences();
        animalTypeDifferences += obj2.getAnimalTypeDifferences();
        missingParts += obj2.getMissingParts();
        extraParts += obj2.getExtraParts();
        featureDifferences += obj2.getFeatureDifferences();
        resistanceTypeDifferences += obj2.getResistanceTypeDifferences();
        skillTypeDifferences += obj2.getSkillTypeDifferences();
        injuryTypeDifferences += obj2.getInjuryTypeDifferences();
        textureDifferences += obj2.getTextureDifferences();
        valueDifferences += obj2.getValueDifferences();

    }

    /**
     * Method to determine the linkage between the hand parts in body1 and body2
     * @param body1 Body: The first Body object
     * @param body2 Body: The second Body object
     * @return OpposableChange: The enum value that maps Body Part objects from body1 to body2
     */
    public BodyPartEnums.OpposableChange CheckOpposableChange(Body body1, Body body2)
    {

        // Limb types: NA, ARMS2LEGS2, LEGS4, ARMS2LEGS2ARMWINGS2, LEGS4ARMWINGS2, LEGS2WINGS2, NOLIMBS, OTHER

        boolean frontOpposable = false;
        boolean backOpposable = false;
        BodyHand body1Hand;

        if (body1.getLimbType() == BodyPartEnums.LimbType.ARMS2LEGS2
                || body1.getLimbType() == BodyPartEnums.LimbType.ARMS2LEGS2ARMWINGS2)
        {
            body1Hand = (BodyHand) body1.getBodyPart("right hand");
            frontOpposable = body1Hand.isOpposable();
        }
        else if (body1.getLimbType() == BodyPartEnums.LimbType.LEGS4
          ||  body1.getLimbType() == BodyPartEnums.LimbType.LEGS4ARMWINGS2)
        {
            body1Hand = (BodyHand) body1.getBodyPart("front ");
        }

        return BodyPartEnums.OpposableChange.NONE;
    }

    /**
     * Method to determine the linkage between the limb parts in body1 and body2
     * @param body1 Body: The first Body object
     * @param body2 Body: The second Body object
     * @return BodyLimbTypeChange: The enum value that maps Body Part objects from body1 to body2
     */
    public BodyPartEnums.BodyLimbTypeChange CheckLimbChange(Body body1, Body body2)
    {
        return BodyPartEnums.BodyLimbTypeChange.NONE;
    }


    /**
     * Method to get the name of the part that should match to the part bodypart
     * @param bodypart BodyPart:    The BodyPart object used in matching
     * @param handChange OpposableChange:   The enum value indicating how fingers and toes are mapped
     * @param limbChange BodyLimbTypeChange:    The enum value indicating how body limbs are mapped
     * @return String:  The String name that the BodyPart object should map to
     */
    public String MatchingName(BodyPart bodypart, BodyPartEnums.OpposableChange handChange,
                               BodyPartEnums.BodyLimbTypeChange limbChange)
    {
        return bodypart.getName();
    }


    /**
     * Method that saves the information regarding the class to a String
     * @return String: identifiers and field data saved in the class
     */
    @Override
    public String toString() {
        return "TransformationDifferences{" +
                "lengthDifferences=" + lengthDifferences +
                ", colorDifferences=" + colorDifferences +
                ", bodyPartDifferences=" + bodyPartDifferences +
                ", animalTypeDifferences=" + animalTypeDifferences +
                ", missingParts=" + missingParts +
                ", extraParts=" + extraParts +
                ", featureDifferences=" + featureDifferences +
                ", resistanceTypeDifferences=" + resistanceTypeDifferences +
                ", skillTypeDifferences=" + skillTypeDifferences +
                ", injuryTypeDifferences=" + injuryTypeDifferences +
                ", textureDifferences=" + textureDifferences +
                ", valueDifferences=" + valueDifferences +
                '}';
    }
}
