package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Body extends BodyPart
{

    private static int nextIdentifier = 1;

    private String firstName, lastName, title;
    private int indentifier;
    private String player;       //to do attach to player object if controlled by a player

    private BodyPartGenerator.Gender gender;

    private BodyPartGenerator.Stance stance; // if on two or four feet

    private ArrayList<BodyPart> internalBodyParts;

    private int maxHealth, health;

    public Body()
    {
        super();
        firstName="";
        lastName="";
        title="";
        description="";
        indentifier=nextIdentifier++;
        player = null;
        color = Color.BLACK;
        gender = BodyPartGenerator.Gender.NA;
        texture = BodyPartGenerator.Texture.NA;
        animalType = BodyPartGenerator.AnimalType.NA;
        stance = BodyPartGenerator.Stance.ONFEET;
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

    public void setIndentifier( int indentifier )
    {
        this.indentifier = indentifier;
    }

    public void setPlayer( String player )
    {
        this.player = player;
    }

    public void setGender( BodyPartGenerator.Gender gender )
    {
        this.gender = gender;
    }

    public void setStance( BodyPartGenerator.Stance stance )
    {
        this.stance = stance;
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

    public int getIndentifier()
    {
        return indentifier;
    }

    public String getPlayer()
    {
        return player;
    }

    public BodyPartGenerator.Gender getGender()
    {
        return gender;
    }

    public BodyPartGenerator.Stance getStance()
    {
        return stance;
    }

    public ArrayList<BodyPart> getInternalBodyParts()
    {
        return internalBodyParts;
    }

    /**
     * Method that converts a basic description of the object (and its attachments) to a string
     * @return String: A String that lists some basic descriptions of the object along with padded descriptions
     *                  of its attached objects
     */
    @Override
    public String toString()
    {
        String str = "A " + length + "in creature known as " + firstName + " " + lastName;
        for (int i=0; i<attachedBodyParts.size(); i++)
        {
            str += "\n" + addPadding(attachedBodyParts.get( i ).toString(), 2);
        }
        return str;
    }




}
