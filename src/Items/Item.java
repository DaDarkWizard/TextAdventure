/**
 * Class that contains data related to a Item in the game
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 8/1/2020
 * Last edited 8/1/2020
 */
package Items;

public class Item {

    protected String description;
    protected String name;
    protected double weight;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public Item()
    {
        name = "";
        description = "";
        weight = 0.0;

    }

    /**
     * Constructor with given name, description, and weight arguments
     * @param name String: The name of an Item object
     * @param description String: The description of an Item
     * @param weight int: The value of an Item object
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Copy Constructor
     * @param oldItem Item: Item object to make a copy of
     */
    public Item(Item oldItem)
    {
        description = oldItem.getDescription();
        name = oldItem.getName();
        weight = oldItem.getWeight();
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Accessor method to get the Item's description
     * @return String: The description of the Item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accessor method to get the Item's name
     * @return String: The name of the Item
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to get the Item's weight
     * @return double: The weight of the Item
     */
    public double getWeight() {
        return weight;
    }


    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the description of the Item
     * @param description String: The description of the Item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mutator method to set the name of the Item
     * @param name String: The name of the Item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to set the weight of the Item
     * @param weight double: The weight of the Item
     */
    public void setWeight(double weight) {
        this.weight = weight;
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
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}
