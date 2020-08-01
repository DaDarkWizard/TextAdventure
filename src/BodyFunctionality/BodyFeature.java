/**
 * Class that contains data related to a created bodyPart's feature (description)
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 8/1/2020
 * Last edited 8/1/2020
 */
package BodyFunctionality;

public class BodyFeature {

    protected String name;
    protected int value;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public BodyFeature()
    {
        name = "";
        value = 0;
    }

    /**
     * Constructor with given name and value arguments
     * @param name String: The name of a Feature object
     * @param value int: The value of a Feature object
     */
    public BodyFeature(String name, int value)
    {
        this.name = name;
        this.value = value;
    }


    /**
     * Copy Constructor
     * @param oldFeature BodyFeature: BodyFeature object to make a copy of
     */
    public BodyFeature(BodyFeature oldFeature)
    {
        name = oldFeature.getName();
        value = oldFeature.getValue();
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Accessor method to get the Feature name
     * @return String: The Feature name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to get the value of a Feature
     * @return int: The feature's value
     */
    public int getValue() {
        return value;
    }

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the Feature name
     * @param playerName String: The Feature name
     */
    public void setName(String playerName) {
        this.name = name;
    }

    /**
     * Mutator method to set the value of a Feature
     * @param value int: The Feature's value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /*-------------------------------------------------------------------------------------
     *  Other Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method to get the data stored in the Player.Player object
     * @return String: Data saved in the Player.Player object
     */
    @Override
    public String toString() {
        return "BodyFeature{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
