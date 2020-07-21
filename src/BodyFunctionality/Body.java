package BodyFunctionality;

import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class Body extends BodyPart
{

    protected static int nextIdentifier = 1;

    protected String firstName, lastName, title;
    protected int identifier;
    protected String player;       //to do attach to player object if controlled by a player

    protected BodyPartGenerator.Gender gender;

    protected BodyPartGenerator.LimbType limbType; // the different kinds of limbs

    protected ArrayList<BodyPart> internalBodyParts;

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
        features = new ArrayList<String>();
        itemsWorn = new ArrayList<String>();
        resistances = new ArrayList<String>();
        skills = new ArrayList<String>();
        injuries = new ArrayList<String>();
        length = 0;
        aboveBodyPart = null;
        attachedBodyParts = new ArrayList<BodyPart>();
        internalBodyParts = new ArrayList<BodyPart>();
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

        player = ByteBufferIO.getString( buffer );
        System.out.println( "Setting Body Player: " + player );

        gender = BodyPartGenerator.Gender.fromOrdinal( buffer.getInt());
        System.out.println( "Setting Body Gender Type: " + gender);

        limbType = BodyPartGenerator.LimbType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting Body Stance Type: " + limbType);

        internalBodyParts = ByteBufferIO.getAttachedBodyParts( buffer );
        System.out.println( "Setting Body Internal Body Parts: " + internalBodyParts);

        this.setAllBody( this );

    }



    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public void setIdentifier( int identifier )
    {
        this.identifier = identifier;
    }

    public void setPlayer( String player )
    {
        this.player = player;
    }

    public void setGender( BodyPartGenerator.Gender gender )
    {
        this.gender = gender;
    }

    public void setLimbType( BodyPartGenerator.LimbType stance )
    {
        this.limbType = stance;
    }

    public void setInternalBodyParts( ArrayList<BodyPart> internalBodyParts )
    {
        this.internalBodyParts = internalBodyParts;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getTitle()
    {
        return title;
    }

    public int getIdentifier()
    {
        return identifier;
    }

    public String getPlayer()
    {
        return player;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.BODY;
    }

    public BodyPartGenerator.Gender getGender()
    {
        return gender;
    }

    public BodyPartGenerator.LimbType getLimbType()
    {
        return limbType;
    }

    /**
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

    public ArrayList<BodyPart> getInternalBodyParts()
    {
        return internalBodyParts;
    }

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

    @Override
    public ByteBuffer toBuffer()
    {
        System.out.println( "In Body toBuffer" );
        ByteBuffer buf = super.toBuffer();

        ByteBufferIO.putString( buf, firstName );
        ByteBufferIO.putString( buf, lastName );
        ByteBufferIO.putString( buf, title );
        buf.putInt( identifier );
        ByteBufferIO.putString( buf, player );
        buf.putInt( gender.ordinal());
        buf.putInt( limbType.ordinal() );
        ByteBufferIO.putAttachedBodyParts( buf, internalBodyParts );

        return buf;
    }

    /**
     * Method that is to be implemented to load extra fields to the buffer (but this class does nothing
     * @param buffer: A ByteBuffer to save data
     * @return boolean: always returns true
     */
    @Override
    protected boolean bufferExtraFields( ByteBuffer buffer )
    {
        return true;
    }
}
