/**
 * This class holds data about the differences between two body objects
 *
 * Created by Michael Clinesmith 7/6/2020
 */
package Transformation;


import BodyFunctionality.*;

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
        TransformationDifferences differencesBetweenBodyParts;


        if (true) //todo adjust for different limb types body1.getLimbType() == body2.getLimbType())
        {
            for(int i=0; i<body1AllParts.size(); i++)
            {
                currentPart1 = body1AllParts.get(i);
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
