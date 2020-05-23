package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BodyPart
{

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

    public void setWeight( double weight )
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
            || type == BodyPartGenerator.BodyPartType.NECK
            || type == BodyPartGenerator.BodyPartType.FRONT
            || type == BodyPartGenerator.BodyPartType.BACK)
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

    /**
     * Method that creates updates the BodyPart type with basic information regarding the BodyPart
     * @param name String: The name of the BodyPart object
     * @param side String: The side the BodyPart object is on (ie. left or right), becomes part of the name
     * @param animalType AnimalType: The type of animal is body part belongs to
     * @param color Color: The color of the body part
     */
    public void create(String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        if (side.isEmpty())
        {
            this.name = name;
        }
        else
        {
            this.name = side + " " + name;
        }
        this.description = "A " + animalType.toString() + " " + side + " " + name;
        this.color = color;
        this.animalType = animalType;
        this.type = BodyPartGenerator.BodyPartType.NA;

    }


    /**
     * Method that converts a basic description of the object (and its attachments) to a string
     * @return String: A String that lists some basic descriptions of the object along with padded descriptions
     *                  of its attached objects
     */
    @Override
    public String toString()
    {
        String str = "A " + length + "in " + color + " " + texture + " " + animalType + " " + name;
        for (int i=0; i<attachedBodyParts.size(); i++)
        {
            str += "\n" + addPadding(attachedBodyParts.get( i ).toString(), this.treeDepth()*2+ 2);
        }
        return str;
    }

    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NA;
    }



    /**
     * Method that adds padding to the front of string (like indenting)
     * @param str String: The string to add padding to the beginning of each line
     * @return String: The str string padded (indented)
     */
    public String addPadding(String str, int paddingAmount)
    {
        String newStr = "";
        String pad = strPadding(paddingAmount);

        newStr = pad + str;

        return newStr;
    }

    /**
     * Method that creates the padding given the PADDING constant
     * @return String: A string composed of PADDING spaces
     */
    public String strPadding(int padAmount)
    {
        String str = "";
        for (int i = 0; i<padAmount; i++)
        {
            str += "-";
        }
        return str;
    }

    /**
     * Method to search for a body part.  If it is not found, return null
     * @param partName String: The BodyPart to search if it is the part looked for
     * @return BodyPart: Returns the body part if it is found, otherwise returns null
     */
    public BodyPart getBodyPart(String partName)
    {
        BodyPart part = null;
        if (partName.equals( name ))
        {
            part = this;
        }
        else
        {
            // search through all attached parts until part found
            for (int i=0; i<attachedBodyParts.size() && part==null; i++)
            {
                part = attachedBodyParts.get( i ).getBodyPart( partName );
            }
        }
        return part;
    }

    public int countParts()
    {
        int count = 1;
        System.out.println( "Part:" + name );
        for (int i=0; i<attachedBodyParts.size(); i++)
        {
            count += attachedBodyParts.get( i ).countParts();
        }

        return count;
    }

    /**
     * Method to determine how deep the body part is in the body tree
     * @return int: The depth of the body part in the tree (or how far the part is from the body object)
     */
    public int treeDepth()
    {
        int depth = 0;
        BodyPart depthCheck = this;
        while (depthCheck.aboveBodyPart!=null)
        {
            depthCheck=depthCheck.aboveBodyPart;
            depth++;
        }
        return depth;
    }
}




