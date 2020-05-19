package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Body
{

    private static int nextIdentifier = 1;

    private String firstName, lastName, title, description;
    private int indentifier;
    private String player;       //to do attach to player object if controlled by a player
    private Color color;
    private BodyPartGenerator.Gender gender;
    private BodyPartGenerator.Texture texture;
    private BodyPartGenerator.AnimalType animalType;
    private BodyPartGenerator.Stance stance; // if on two or four feet

    // todo change some Strings to objects at a later time
    private ArrayList<String> features;
    private ArrayList<String> itemsWorn;    // change to item object later
    private ArrayList<String> resistances;
    private ArrayList<String> skills;
    private ArrayList<String> injuries;

    private double length, width, depth;

    private ArrayList<BodyPart> attachedBodyParts;
    private ArrayList<BodyPart> internalBodyParts;

    private int maxHealth, health;

    public Body()
    {
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
        width = 0;
        depth = 0;
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

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setIndentifier( int indentifier )
    {
        this.indentifier = indentifier;
    }

    public void setPlayer( String player )
    {
        this.player = player;
    }

    public void setColor( Color color )
    {
        this.color = color;
    }

    public void setGender( BodyPartGenerator.Gender gender )
    {
        this.gender = gender;
    }

    public void setTexture( BodyPartGenerator.Texture texture )
    {
        this.texture = texture;
    }

    public void setAnimalType( BodyPartGenerator.AnimalType animalType )
    {
        this.animalType = animalType;
    }

    public void setStance( BodyPartGenerator.Stance stance )
    {
        this.stance = stance;
    }

    public void setFeatures( ArrayList<String> features )
    {
        this.features = features;
    }

    public void setItemsWorn( ArrayList<String> itemsWorn )
    {
        this.itemsWorn = itemsWorn;
    }

    public void setResistances( ArrayList<String> resistances )
    {
        this.resistances = resistances;
    }

    public void setSkills( ArrayList<String> skills )
    {
        this.skills = skills;
    }

    public void setInjuries( ArrayList<String> injuries )
    {
        this.injuries = injuries;
    }

    public void setLength( double length )
    {
        this.length = length;
    }

    public void setWidth( double width )
    {
        this.width = width;
    }

    public void setDepth( double depth )
    {
        this.depth = depth;
    }

    public void setAttachedBodyParts( ArrayList<BodyPart> attachedBodyParts )
    {
        this.attachedBodyParts = attachedBodyParts;
    }

    public void setInternalBodyParts( ArrayList<BodyPart> internalBodyParts )
    {
        this.internalBodyParts = internalBodyParts;
    }

    public void setMaxHealth( int maxHealth )
    {
        this.maxHealth = maxHealth;
    }

    public void setHealth( int health )
    {
        this.health = health;
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

    public String getDescription()
    {
        return description;
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

    public Color getColor()
    {
        return color;
    }

    public BodyPartGenerator.Texture getTexture()
    {
        return texture;
    }

    public BodyPartGenerator.AnimalType getAnimalType()
    {
        return animalType;
    }

    public BodyPartGenerator.Stance getStance()
    {
        return stance;
    }

    public ArrayList<String> getFeatures()
    {
        return features;
    }

    public ArrayList<String> getItemsWorn()
    {
        return itemsWorn;
    }

    public ArrayList<String> getSkills()
    {
        return skills;
    }

    public ArrayList<String> getResistances()
    {
        return resistances;
    }

    public ArrayList<String> getInjuries()
    {
        return injuries;
    }

    public double getLength()
    {
        return length;
    }

    public double getWidth()
    {
        return width;
    }

    public double getDepth()
    {
        return depth;
    }

    public ArrayList<BodyPart> getAttachedBodyParts()
    {
        return attachedBodyParts;
    }

    public ArrayList<BodyPart> getInternalBodyParts()
    {
        return internalBodyParts;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }





}
