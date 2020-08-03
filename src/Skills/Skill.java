/**
 * Class that contains data related to a Skill in the game
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 8/3/2020
 * Last edited 8/3/2020
 */
package Skills;

public class Skill {

    protected String name;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public Skill()
    {
        name = "";

    }

    /**
     * Constructor with given name and value arguments
     * @param name String: The name of a Skill object
     */
    public Skill(String name)
    {
        this.name = name;
    }

    /**
     * Copy Constructor
     * @param oldSkill Skill: Skill object to make a copy of
     */
    public Skill(Skill oldSkill)
    {
        name = oldSkill.getName();
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

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the name of the Skill
     * @param name String: The name of the Skill
     */
    public void setName(String name) {
        this.name = name;
    }


    /*-------------------------------------------------------------------------------------
     *  Other Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method to get the data stored in the Skill object
     * @return String: Data saved in the Skill object
     */
    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}