/**
 * Class that contains data related to an StatusEffect in the game
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 8/3/2020
 * Last edited 8/3/2020
 */
package Effects;

public class StatusEffect{

    protected String name;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public StatusEffect()
    {
        name = "";
    }

    /**
     * Constructor with given name and value arguments
     * @param name String: The name of a StatusEffect object
     */
    public StatusEffect(String name)
    {

        this.name = name;
    }

    /**
     * Copy Constructor
     * @param oldEffect StatusEffect: StatusEffect object to make a copy of
     */
    public StatusEffect(StatusEffect oldEffect)
    {
        name = oldEffect.getName();
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/


    /**
     * Accessor method to get the StatusEffect's name
     * @return String: The name of the StatusEffect
     */
    public String getName() {
        return name;
    }

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the name of the StatusEffect
     * @param name String: The name of the StatusEffect
     */
    public void setName(String name) {
        this.name = name;
    }


    /*-------------------------------------------------------------------------------------
     *  Other Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method to get the data stored in the StatusEffect object
     * @return String: Data saved in the StatusEffect object
     */
    @Override
    public String toString() {
        return "StatusEffect{" +
                "name='" + name + '\'' +
                '}';
    }
}