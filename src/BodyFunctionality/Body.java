/**
 * This class holds data about the Body object of a Player/user
 *
 * This class inherits from the bodyPart class
 *
 * It holds extra information such as internal body parts and attached body parts,
 * names and identifiers
 *
 *
 * Created by Michael Clinesmith
 * Last update edit 8/1/2020
 * -added comment blocks
 * -rearranged order of methods
 * -updated Player methods
 * -added specific body part attachments and associated methods
 * -added field for all attached body parts
 * -updated ArrayList Classes
 */

package BodyFunctionality;

import Items.Item;
import Player.Player;
import javafx.scene.paint.Color;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Body extends BodyPart
{

    protected static int nextIdentifier = 1;

    protected String firstName, lastName, title;
    protected int identifier;
    protected Player player;       //to do attach to player object if controlled by a player

    protected BodyPartGenerator.Gender gender;

    protected BodyPartGenerator.LimbType limbType; // the different kinds of limbs

    // attached body parts
    protected BodyNeck attachedNeck;
    protected BodyChest attachedChest;
    protected BodyBack attachedBack;
    protected ArrayList<BodyArm> attachedLimbs;
    protected BodyTail attachedTail;
    protected ArrayList<BodyPart> internalBodyParts;
    protected ArrayList<BodyPart> bodyPartList;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No-argument constructor, sets default values
     */
    public Body()
    {
        super();
        firstName="";
        lastName="";
        title="";
        description="";
        identifier=nextIdentifier++;
        player = null;
        color = Color.BLACK;
        gender = BodyPartGenerator.Gender.NA;
        texture = BodyPartGenerator.Texture.NA;
        animalType = BodyPartGenerator.AnimalType.NA;
        limbType = BodyPartGenerator.LimbType.LEGS4;

        attachedNeck = null;
        attachedChest = null;
        attachedBack = null;
        attachedLimbs = new ArrayList<BodyArm>();
        attachedTail = null;

        features = new ArrayList<BodyFeature>();
        itemsWorn = new ArrayList<Item>();
        resistances = new ArrayList<String>();
        skills = new ArrayList<String>();
        injuries = new ArrayList<String>();
        length = 0;
        aboveBodyPart = null;
        attachedBodyParts = new ArrayList<BodyPart>();
        internalBodyParts = new ArrayList<BodyPart>();
        bodyPartList = new ArrayList<BodyPart>();

        maxHealth = 0;
        health = 0;

        this.setBodyPartType( BodyPartGenerator.BodyPartType.BODY);

    }

    /**
     * Constructor with a buffer containing the entire Body's data (including all BodyParts)
     *
     * This constructor will load the entire Body with its attached BodyParts in the buffer
     * @param buffer ByteBuffer: The buffer containing the Body data
     */
    public Body(ByteBuffer buffer)
    {
        super(buffer);
        firstName = ByteBufferIO.getString( buffer );
        System.out.println( "Setting Body First Name: " + firstName );

        lastName = ByteBufferIO.getString( buffer );
        System.out.println( "Setting Body Last Name: " + lastName );

        title = ByteBufferIO.getString( buffer );
        System.out.println( "Setting Body Title: " + title );

        identifier = buffer.getInt();
        System.out.println( "Setting Body Identifier: " + identifier );

        //todo update this method to get playerID and associate object with player
        //when Player class is implemented
        player = new Player();
        player.setPlayerName(ByteBufferIO.getString( buffer ));
        System.out.println( "Setting Body Player.Player: " + player );

        gender = BodyPartGenerator.Gender.fromOrdinal( buffer.getInt());
        System.out.println( "Setting Body Gender Type: " + gender);

        limbType = BodyPartGenerator.LimbType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting Body Stance Type: " + limbType);

        //todo update get attached body parts
        attachedNeck = null;
        attachedChest = null;
        attachedBack = null;
        attachedLimbs = new ArrayList<BodyArm>();
        attachedTail = null;


        internalBodyParts = ByteBufferIO.getAttachedBodyParts( buffer );
        System.out.println( "Setting Body Internal Body Parts: " + internalBodyParts);

        this.setAllBody( this );

    }

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Mutator method for setting BodyBack attached part
     * This method makes a deep copy of the BodyBack body part
     * @param attachedBack BodyBack: The BodyBack part to attach
     */
    public void setAttachedBack(BodyBack attachedBack) {
        this.attachedBack = new BodyBack(attachedBack);
    }

    /**
     * Mutator method for setting BodyChest attached part
     * This method makes a deep copy of the BodyChest body part
     * @param attachedChest BodyChest: The BodyChest part to attach
     */
    public void setAttachedChest(BodyChest attachedChest) {
        this.attachedChest = new BodyChest(attachedChest);
    }

    /**
     * Mutator method for setting BodyArm attached parts
     * This method makes a deep copy of the ArrayList of BodyArm body parts
     * @param attachedLimbs ArrayList<BodyArm>: The ArrayList of BodyArm parts to attach
     */
    public void setAttachedLimbs(ArrayList<BodyArm> attachedLimbs) {
        this.attachedLimbs = new ArrayList<BodyArm>(attachedLimbs);
    }

    /**
     * Mutator method for setting BodyNeck attached part
     * This method makes a deep copy of the BodyNeck body part
     * @param attachedNeck BodyNeck: The BodyNeck part to attach
     */
    public void setAttachedNeck(BodyNeck attachedNeck) {
        this.attachedNeck = new BodyNeck(attachedNeck);
    }

    /**
     * Mutator method for setting BodyTail attached part
     * This method makes a deep copy of the BodyTail body part
     * @param attachedTail BodyTail: The BodyTail part to attach
     */
    public void setAttachedTail(BodyTail attachedTail) {
        this.attachedTail = new BodyTail(attachedTail);
    }

    /**
     * Mutator method to setting the list of associated BodyParts with this Body object
     * Note: This method makes a deep copy of the ArrayList of BodyParts todo:verify
     * @param bodyPartList ArrayList<BodyPart>: An ArrayList of BodyParts objects connected to this Body object
     */
    public void setBodyPartList(ArrayList<BodyPart> bodyPartList) {
        this.bodyPartList = new ArrayList<BodyPart>(bodyPartList);
    }

    /**
     * Mutator method to set the firstName of the Body object
     * @param firstName String: first name of the object
     */
    public void setFirstName(String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * Mutator method to set the gender of a Body object
     * @param gender Gender: A Gender enum value
     */
    public void setGender( BodyPartGenerator.Gender gender )
    {
        this.gender = gender;
    }

    /**
     * Mutator method to set the unique identifier of a Body object
     * @param identifier int: An identifier for a Body object
     */
    public void setIdentifier( int identifier )
    {
        this.identifier = identifier;
    }

    /**
     * Mutator method to set the lastName of a Body object
     * @param lastName String: The lastName of a Body object
     */
    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * Mutator method to set the LimbType of a Body object
     * @param stance LimbType: A LimbType representing the configuration of Arms and Legs on the object
     */
    public void setLimbType( BodyPartGenerator.LimbType stance )
    {
        this.limbType = stance;
    }

    /**
     * Mutator method to set the type of internalBodyParts on the object
     * @param internalBodyParts ArrayList<BodyPart>: The ArrayList of internal BodyParts attached to the object
     */
    public void setInternalBodyParts( ArrayList<BodyPart> internalBodyParts )
    {
        this.internalBodyParts = internalBodyParts;
    }

    /**
     * Method to set to connect the Body object with a specific Player
     * Note, this connection does not copy the Player object, it directly connects to it
     * @param player Player: The Player object connected with the Body object
     */
    public void setPlayer( Player player )
    {
        this.player = player;
    }

    /**
     * Method to set the object's title
     * @param title String: A title (special name) given to the Body object
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method to return an ArrayList containing all Body Parts connected to the body - internal, attached, and this part
     * This should return shallow copies of the body parts (if modified that will also affect the original)
     * @return ArrayList<BodyPart>: A list containing all Body Parts connected with this body
     */
    public ArrayList<BodyPart> getAllBodyParts()
    {
        ArrayList<BodyPart> allParts = new ArrayList<BodyPart>( getInternalBodyParts() );  // include internal parts

        allParts.addAll(getAllBodyPartChildren());  // include attached body parts
        allParts.add(this);                         // include body in parts

        return allParts;
    }

    /**
     * Accessor method to get the list of associated BodyPart object to this Body object
     * Note: This method returns a deep copy of the attached body parts todo: Verify
     * @return ArrayList<BodyPart>: An ArrayList of associated BodyPart objects
     */
    public ArrayList<BodyPart> getBodyPartList() {
        return new ArrayList<BodyPart>(bodyPartList);
    }

    /**
     * Accessor method to get the firstName of a Body object
     * @return String: The firstName of a Body object
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Accessor method to get the gender associated with this object
     * @return Gender: The gender enum value associated with this Body object
     */
    public BodyPartGenerator.Gender getGender()
    {
        return gender;
    }

    /**
     * Accessor method to get the unique Identifier of a Body object
     * @return int: The identifier of a Body object
     */
    public int getIdentifier()
    {
        return identifier;
    }

    /**
     * Accessor method that gets the ArrayList of associated internalBodyParts to this Body object
     * Note: Makes a deep copy of the internal body parts associated todo: verify
     * @return ArrayList<BodyPart>: The ArrayList of associated internalBodyParts
     */
    public ArrayList<BodyPart> getInternalBodyParts()
    {
        return new ArrayList<BodyPart>(internalBodyParts);
    }

    /**
     * Accessor method to get the lastName of a Body object
     * @return String: The lastName of a Body object
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Accessor method to get the limbType associated with this Body object
     * @return LimbType: the LimbType enum value associated with the limb types associated with this Body object
     */
    public BodyPartGenerator.LimbType getLimbType()
    {
        return limbType;
    }

    /**
     * Accessor method to get the associated Player of a Body object
     * Note: This method does not make a copy, returns a reference to the Player object
     * @return Player: The Player object associated with this Body object
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Accessor method to get the title of a Body object
     * @return String: The title (special name) of a Body object
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * todo: update or delete if necessary
     * Method to determine if hands and toes have opposable digits
     * @return  OpposableType: One of the enum OpposableTypes based on if hands or toes are opposable
     */
    public BodyPartGenerator.OpposableType getOpposableType()
    {
        boolean handsOpposable = false;
        boolean backFeetOpposable = false;
        BodyHand thisHand, thisFoot;
        BodyPartGenerator.OpposableType opposableType= BodyPartGenerator.OpposableType.NONE;

        // check if hands/front hands/front claws are opposable
        if (limbType == BodyPartGenerator.LimbType.ARMS2LEGS2
                || limbType == BodyPartGenerator.LimbType.ARMS2LEGS2ARMWINGS2)
        {
            thisHand = (BodyHand) getBodyPart("right hand");
            if (thisHand!=null)
            {
                handsOpposable = thisHand.isOpposable();
            }
            else
            {
                thisHand = (BodyHand) getBodyPart("right claw");
                if (thisHand!=null)
                {
                    handsOpposable = thisHand.isOpposable();
                }

            }
        }
        else if (limbType == BodyPartGenerator.LimbType.LEGS4
                ||  limbType == BodyPartGenerator.LimbType.LEGS4ARMWINGS2) {
            thisHand = (BodyHand) getBodyPart("front right claw");
            if (thisHand != null) {
                handsOpposable = thisHand.isOpposable();
            } else {
                thisHand = (BodyHand) getBodyPart("front right hand");
                if (thisHand != null) {
                    handsOpposable = thisHand.isOpposable();
                }

            }
        }

        // check if feet/back claws are opposable
        if (limbType == BodyPartGenerator.LimbType.ARMS2LEGS2
                || limbType == BodyPartGenerator.LimbType.ARMS2LEGS2ARMWINGS2)
        {
            thisFoot = (BodyHand) getBodyPart("right foot");
            if (thisFoot!=null)
            {
                backFeetOpposable = thisFoot.isOpposable();
            }
            else
            {
                thisFoot = (BodyHand) getBodyPart("right claw");
                if (thisFoot!=null)
                {
                    backFeetOpposable = thisFoot.isOpposable();
                }

            }
        }
        else if (limbType == BodyPartGenerator.LimbType.LEGS4
                ||  limbType == BodyPartGenerator.LimbType.LEGS4ARMWINGS2)
        {
            thisFoot = (BodyHand) getBodyPart("front right claw");
            if (thisFoot!=null)
            {
                backFeetOpposable = thisFoot.isOpposable();
            }
            else
            {
                thisFoot = (BodyHand) getBodyPart("front right hand");
                if (thisFoot!=null)
                {
                    backFeetOpposable = thisFoot.isOpposable();
                }

            }

        }
        else if(limbType == BodyPartGenerator.LimbType.LEGS2WINGS2)
        {
            thisFoot = (BodyHand) getBodyPart("right claw");
            if (thisFoot!=null)
            {
                backFeetOpposable = thisFoot.isOpposable();
            }
        }

        if (handsOpposable&&backFeetOpposable)
        {
            opposableType = BodyPartGenerator.OpposableType.HANDSFEET;
        }
        else if (handsOpposable)
        {
            opposableType = BodyPartGenerator.OpposableType.HANDS;
        }
        else if (backFeetOpposable)
        {
            opposableType = BodyPartGenerator.OpposableType.FEET;
        }
        else
        {
            opposableType = BodyPartGenerator.OpposableType.NONE;
        }

        return opposableType;
    }


    /*-------------------------------------------------------------------------------------
     *  Other Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method that adds a BodyPart object to the bodyPartList
     * Note: The method does not make a copy of the part to add, it just adds the reference
     * @param partToAdd BodyPart: The BodyPart object to add to the list
     */
    public void addToBodyPartList(BodyPart partToAdd)
    {
        if (!isInPartList(partToAdd))
        {
            bodyPartList.add(partToAdd);
        }
    }

    /**
     * Method that adds multiple BodyPart objects in an ArrayList to the bodyPartList
     * Note: The method does not make a copy of the parts to add, it just adds the reference
     * @param partList ArrayList<BodyPart>:  The BodyPart objects to add to the list
     */
    public void addToBodyPartList(ArrayList<BodyPart> partList)
    {
        BodyPart onePart;
        int size = partList.size();
        for (int i=0; i<size; i++)
        {
            onePart = partList.get(i);
            if(!isInPartList(onePart))
            {
                bodyPartList.add(onePart);
            }

        }

    }


    /**
     * Accessor method to get the bodyPartType associated with this object [Body]
     * @return BodyPartType: BODY
     */
    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.BODY;
    }

    /**
     * Method that is to be implemented to load extra fields to the buffer (but this class does nothing
     * @param buffer ByteBuffer: A ByteBuffer to save data
     * @return boolean: always returns true
     */
    @Override
    protected boolean bufferExtraFields( ByteBuffer buffer )
    {
        return true;
    }

    /**
     * Method that checks if the given part is already in the bodyPartList
     * @param thisPart BodyPart: The part to verify if it is in the list
     * @return boolean: true if the part is already in the list, false otherwise
     */
    public boolean isInPartList(BodyPart thisPart)
    {
        boolean found = false;
        int size = bodyPartList.size();
        for (int i=0; i<size && !found; i++)
        {
            if (thisPart.isSamePart(bodyPartList.get(i)))
            {
                found = true;
            }
        }
        return found;
    }

    /**
     * Method that removes a BodyPart object from the bodyPartList
     * @param partToRemove BodyPart: The BodyPart object to remove from the list
     */
    public void removeFromBodyPartList(BodyPart partToRemove)
    {
        if (!isInPartList(partToRemove))
        {
            bodyPartList.remove(partToRemove); //todo: verify this works if the part is the same but not equal
        }
    }

    /**
     * Method that removes multiple BodyPart objects in an ArrayList from the bodyPartList
     * @param partList ArrayList<BodyPart>:  The BodyPart objects to remove from the list
     */
    public void removeFromBodyPartList(ArrayList<BodyPart> partList)
    {
        BodyPart onePart;
        int size = partList.size();
        for (int i=0; i<size; i++)
        {
            onePart = partList.get(i);
            if(!isInPartList(onePart))
            {
                //todo: verify this works if the part is the same but not equal
                bodyPartList.remove(onePart);
            }

        }

    }


    /**
     * Method that changes the values in the object to a ByteBuffer stream
     * @return ByteBuffer: Data from the Body object stored in the ByteBuffer
     */
    @Override
    public ByteBuffer toBuffer()
    {
        //todo: update method to include new fields

        System.out.println( "In Body toBuffer" );
        ByteBuffer buf = super.toBuffer();

        ByteBufferIO.putString( buf, firstName );
        ByteBufferIO.putString( buf, lastName );
        ByteBufferIO.putString( buf, title );
        buf.putInt( identifier );
        // ByteBufferIO.putString( buf, player.getPlayerName() );
        buf.putInt( gender.ordinal());
        buf.putInt( limbType.ordinal() );
        ByteBufferIO.putAttachedBodyParts( buf, internalBodyParts );

        return buf;
    }

    /**
     * Method that converts a basic description of the object (and its attachments) to a string
     * @return String: A String that lists some basic descriptions of the object along with padded descriptions
     *                  of its attached objects
     */
    @Override
    public String toString()
    {
        String str = "A " + String.format( "%.2f", length ) + "in creature known as " + firstName + " " + lastName;
        for (int i=0; attachedBodyParts!=null && i<attachedBodyParts.size(); i++)
        {
            str += "\n" + addPadding(attachedBodyParts.get( i ).toString(), 2);
        }
        return str;
    }

}
