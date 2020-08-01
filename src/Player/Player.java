/**
 * Class that contains data related to a Player.Player/user in the game
 *
 * Currently a dummy class
 *
 * Created by Michael Clinesmith 7/28/2020
 * Last edited 7/28/2020
 */

package Player;

public class Player {

    protected String playerName;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor
     */
    public Player()
    {
        playerName = "";
    }

    /**
     * Copy Constructor
     * @param oldPlayer Player.Player: Player.Player object to make a copy of
     */
    public Player(Player oldPlayer)
    {
        playerName = oldPlayer.getPlayerName();
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Accessor method to get the playerName
     * @return String: The playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method to set the playerName
     * @param playerName String: The playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        return "Player.Player{" +
                "playerName='" + playerName + '\'' +
                '}';
    }
}
