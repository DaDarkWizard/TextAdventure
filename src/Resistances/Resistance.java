/**
 * Class that contains data related to a Resistance in the game
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 8/1/2020
 * Last edited 8/1/2020
 */
package Resistances;

public class Resistance {

    protected String name;
    protected double value;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public Resistance()
    {
        name = "";
        value = 0.0;

    }

    /**
     * Constructor with given name and value arguments
     * @param name String: The name of a Resistance object
     * @param value double: The value of a Resistance object
     */
    public Resistance(String name, double value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Copy Constructor
     * @param oldResistance Resistance: Resistance object to make a copy of
     */
    public Resistance(Resistance oldResistance)
    {
        name = oldResistance.getName();
        value = oldResistance.getValue();
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Accessor method to get the Resistance's name
     * @return String: The name of the Resistance
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to get the Resistance's value
     * @return double: The value of the Resistance
     */
    public double getValue() {
        return value;
    }


    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the name of the Resistance
     * @param name String: The name of the Resistance
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to set the value of the Resistance
     * @param value double: The value of the Resistance
     */
    public void setValue(double value) {
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
        return "Resistance{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}