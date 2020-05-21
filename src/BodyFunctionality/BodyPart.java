package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BodyPart
{
    protected final int PADDING = 2;

    protected String name, description;
    protected BodyPartGenerator.BodyPartType type;
    protected Color color;
    protected BodyPartGenerator.Texture texture;
    protected BodyPartGenerator.AnimalType animalType;

    // todo change some Strings to objects at a later time
    protected ArrayList<String> features;
    protected ArrayList<String> itemsWorn;    // change to item object later
    protected ArrayList<String> resistances;
    protected ArrayList<String> skills;
    protected ArrayList<String> injuries;

    protected double length, weight;

    protected Body thisBody;
    protected BodyPart aboveBodyPart;
    protected ArrayList<BodyPart> attachedBodyParts;

    protected int maxHealth, health;

    public BodyPart()
    {
        name="";
        type=BodyPartGenerator.BodyPartType.NA;
        color=Color.BLACK;
        texture=BodyPartGenerator.Texture.NA;
        description="";
        animalType=BodyPartGenerator.AnimalType.NA;
        features = new ArrayList<String>(  );
        itemsWorn = new ArrayList<String>(  );
        resistances = new ArrayList<String>();
        skills = new ArrayList<String>();
        injuries = new ArrayList<String>();

        length = 0.0;
        weight = 0.0;

        thisBody = null;
        aboveBodyPart = null;
        attachedBodyParts = new ArrayList<BodyPart>(  );

        maxHealth = 0;
        health = 0;

    }

    /**
     * Copy constructor
     * The copy constructor should make deep copies of most objects with the exception of this and above body part objects
     * It will make a deep copy of the attachedBodyParts object
     *
     *
     * @param oldPart:
     */

    public BodyPart(BodyPart oldPart)
    {
        name = oldPart.getName();
        type = oldPart.getType();
        color = oldPart.getColor();
        texture = oldPart.getTexture();
        description = oldPart.getDescription();
        animalType = oldPart.getAnimalType();
        features = new ArrayList<String>( oldPart.getFeatures() );
        itemsWorn = new ArrayList<String>( oldPart.getItemsWorn() );
        resistances = new ArrayList<String>( oldPart.getResistances() );
        skills = new ArrayList<String>( oldPart.getSkills() );
        injuries = new ArrayList<String>( oldPart.getInjuries() );

        length = oldPart.getLength();
        weight = oldPart.getWeight();

        thisBody = oldPart.getThisBody();  //todo should keep this shallow copy?
        aboveBodyPart = oldPart.getAboveBodyPart(); //todo should keep this shallow copy?
        attachedBodyParts = copyArrayBodyPart(  oldPart.getAttachedBodyParts() ); //todo should this be a deep copy?

        maxHealth = oldPart.getMaxHealth();
        health = oldPart.getHealth();
    }

    public BodyPart copy(BodyPart oldPart)
    {
        return new BodyPart(oldPart);
    }

    private ArrayList<BodyPart> copyArrayBodyPart(ArrayList<BodyPart> bodyPartList)
    {
        ArrayList<BodyPart> newList = new ArrayList<BodyPart>(  );
        for (int i = 0; i<bodyPartList.size(); i++)
        {
            newList.add(new BodyPart( bodyPartList.get( i )));
        }
        return newList;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setType( BodyPartGenerator.BodyPartType type )
    {
        this.type = type;
    }

    public void setColor( Color color )
    {
        this.color = color;
    }

    public void setTexture( BodyPartGenerator.Texture texture )
    {
        this.texture = texture;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public void setAnimalType( BodyPartGenerator.AnimalType animalType )
    {
        this.animalType = animalType;
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

    public void setWidth( double weight )
    {
        this.weight = weight;
    }

    public void setThisBody( Body thisBody )
    {
        this.thisBody = thisBody;
    }

    public void setAboveBodyPart( BodyPart aboveBodyPart )
    {
        this.aboveBodyPart = aboveBodyPart;
    }

    public void setAttachedBodyParts( ArrayList<BodyPart> attachedBodyParts )
    {
        this.attachedBodyParts = attachedBodyParts;
    }

    public void setMaxHealth( int maxHealth )
    {
        this.maxHealth = maxHealth;
    }

    public void setHealth( int health )
    {
        this.health = health;
    }

    public String getName()
    {
        return name;
    }

    public BodyPartGenerator.BodyPartType getType()
    {
        return type;
    }

    public Color getColor()
    {
        return color;
    }

    public BodyPartGenerator.Texture getTexture()
    {
        return texture;
    }

    public String getDescription()
    {
        return description;
    }

    public BodyPartGenerator.AnimalType getAnimalType()
    {
        return animalType;
    }

    public ArrayList<String> getFeatures()
    {
        return features;
    }

    public ArrayList<String> getItemsWorn()
    {
        return itemsWorn;
    }

    public ArrayList<String> getResistances()
    {
        return resistances;
    }

    public ArrayList<String> getSkills()
    {
        return skills;
    }

    public ArrayList<String> getInjuries()
    {
        return injuries;
    }

    public double getLength()
    {
        return length;
    }

    public double getWeight()
    {
        return weight;
    }

    public Body getThisBody()
    {
        return thisBody;
    }

    public BodyPart getAboveBodyPart()
    {
        return aboveBodyPart;
    }

    public ArrayList<BodyPart> getAttachedBodyParts()
    {
        return attachedBodyParts;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public int getNumAttached()
    {
        return attachedBodyParts.size();
    }


    //todo set up useful setAll methods

    public void setAllBodyColor(Color color)
    {
        // color is part if it is a colored outer body part
        if (isBodyColorPart())
        {
            this.color = color;
        }
        for (int i = 0; i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAllBodyColor( color );
        }

    }

    public void setAllHornColor(Color color)
    {
        // color is part if it is a colored outer body part
        if (isBodyColorPart())
        {
            this.color = color;
        }
        for (int i = 0; i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAllHornColor( color );
        }
    }

    /**
     * Method that sets the body reference of all body objects of the same body to the same body
     * @param body Body: The Body object reference to set all body objects equal to
     */
    public void setAllBody(Body body)
    {
        thisBody = body;                                    // sets this object's reference
        for (int i = 0; i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAllBody( body );  // sets all attached object's references
        }
    }
    /**
     * Private method that returns true if the body part is one that should be the general body color, to allow easier coloring
     * @return boolean: true if the body part is part of the general body color, false otherwise
     */
    private boolean isBodyColorPart()
    {
        boolean isBodyColor = false;
        if ( type == BodyPartGenerator.BodyPartType.FINGER
            || type == BodyPartGenerator.BodyPartType.HAND
            || type == BodyPartGenerator.BodyPartType.ARM
            || type == BodyPartGenerator.BodyPartType.WING
            || type == BodyPartGenerator.BodyPartType.HEAD
            || type == BodyPartGenerator.BodyPartType.MUZZLE
            || type == BodyPartGenerator.BodyPartType.TAIL
            || type == BodyPartGenerator.BodyPartType.EAR
            || type == BodyPartGenerator.BodyPartType.NOSE
            || type == BodyPartGenerator.BodyPartType.NECK)
        {
            isBodyColor = true;
        }

        return isBodyColor;
    }

    /**
     * Private method that returns true if the body part is a horn or nail, to allow easier coloring
     * @return boolean: true if the body part is a horn or nail, false otherwise
     */
    private boolean isHornColorPart()
    {
        boolean isHornColor = false;
        if (type == BodyPartGenerator.BodyPartType.NAIL || type == BodyPartGenerator.BodyPartType.HORN)
        {
            isHornColor = true;
        }

        return isHornColor;
    }


    public void addSkill(String skill) //todo change to skill object
    {
        if ( !skills.contains( skill ))
        {
            skills.add(skill);
        }
    }

    public void addFeature(String feature)
    {
        if ( !features.contains( feature ))
        {
            features.add(feature);
        }
    }

    public void removeSkill(String skill) //todo change to skill object
    {
        if ( skills.contains( skill ))
        {
            skills.remove(skill);
        }
    }


    public void removeFeature(String feature)
    {
        if ( features.contains( feature ))
        {
            features.remove(feature);
        }
    }

    public void clearSkills()
    {
        skills.clear();
    }

    public void clearFeatures()
    {
        features.clear();
    }

    public BodyPart create(String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        BodyPart thisPart = new BodyPart();

        thisPart.setName( name );
        thisPart.setType( BodyPartGenerator.BodyPartType.NAIL );
        thisPart.setColor( color );
        thisPart.setAnimalType( animalType );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        return thisPart;
    }


    /**
     * Method that converts a basic description of the object (and its attachments) to a string
     * @return String: A String that lists some basic descriptions of the object along with padded descriptions
     *                  of its attached objects
     */
    @Override
    public String toString()
    {
        String str = "A " + length + "in " + color + texture + animalType + name;
        for (int i=0; i<attachedBodyParts.size(); i++)
        {
            str += addPadding(attachedBodyParts.get( i ).toString());
        }
        return str;
    }


    /**
     * Method that adds padding to the front of string (like indenting)
     * @param str String: The string to add padding to the beginning of each line
     * @return String: The str string padded (indented)
     */
    public String addPadding(String str)
    {
        String newStr = "";
        String pad = strPadding();

        String[] strArray = str.split("\n");
        for (int i=0; i<strArray.length; i++)
        {
            str += pad + strArray[i];
        }

        return str;
    }

    /**
     * Method that creates the padding given the PADDING constant
     * @return String: A string composed of PADDING spaces
     */
    public String strPadding()
    {
        String str = "";
        for (int i = 0; i<PADDING; i++)
        {
            str += " ";
        }
        return str;
    }



}




