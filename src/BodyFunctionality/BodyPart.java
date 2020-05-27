package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BodyPart
{

    protected String name, description;
    protected BodyPartGenerator.BodyPartType bodyPartType;
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
        bodyPartType=BodyPartGenerator.BodyPartType.NA;
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
     * Constructor method to construct a body part from a ByteBuffer object
     * @param buffer ByteBuffer: The ByteBuffer object that contains the BodyPart data
     */
    public BodyPart(ByteBuffer buffer)
    {
        System.out.println( "In BodyPart(Buffer) constructor" );
        name = ByteBufferIO.getString( buffer );
        System.out.println( "Setting name to " + name );

        description = ByteBufferIO.getString( buffer );
        System.out.println( "Setting description to " + description );

        bodyPartType = BodyPartGenerator.BodyPartType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting bodyPartType to " + bodyPartType );

        color = ByteBufferIO.getColor( buffer );
        System.out.println( "Setting color to " + color );

        texture = BodyPartGenerator.Texture.fromOrdinal( buffer.getInt());
        System.out.println( "Setting texture to " + texture );

        animalType = BodyPartGenerator.AnimalType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting animalType to " + animalType );

        features = ByteBufferIO.getFeatures( buffer );
        System.out.println( "Setting features to " + features );

        itemsWorn = ByteBufferIO.getItemsWorn( buffer );
        System.out.println( "Setting itemsWorn to " + itemsWorn );

        resistances = ByteBufferIO.getResistances( buffer );
        System.out.println( "Setting resistances to " + resistances );

        skills = ByteBufferIO.getSkills( buffer );
        System.out.println( "Setting skills to " + skills );

        injuries = ByteBufferIO.getInjuries( buffer );
        System.out.println( "Setting injuries to " + injuries );

        length = buffer.getDouble();
        System.out.println( "Setting length to " + length );

        weight = buffer.getDouble();
        System.out.println( "Setting weight to " + weight );

        maxHealth = buffer.getInt();
        System.out.println( "Setting maxHealth to " + maxHealth );

        health = buffer.getInt();
        System.out.println( "Setting health to " + health );

        System.out.println( "Preparing to Create more subordinate body parts if they exist" );
        ArrayList<BodyPart> attachedBodyParts = ByteBufferIO.getAttachedBodyParts( buffer );

        for (int i=0; i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAboveBodyPart( this );
        }
        System.out.println( "Setting attachedBodyParts for " + name + " to " + attachedBodyParts );

    }
    /**
     * Copy constructor
     * The copy constructor should make deep copies of most objects with the exception of this and above body part objects
     * It will make a deep copy of the attachedBodyParts object
     *
     * @param oldPart: The BodyPart object to make a copy of
     */

    public BodyPart(BodyPart oldPart)
    {
        name = oldPart.getName();
        bodyPartType = oldPart.getBodyPartType();
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

    public void setBodyPartType( BodyPartGenerator.BodyPartType type )
    {
        this.bodyPartType = type;
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

    public BodyPartGenerator.BodyPartType getBodyPartType()
    {
        return bodyPartType;
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
        if ( bodyPartType == BodyPartGenerator.BodyPartType.FINGER
            || bodyPartType == BodyPartGenerator.BodyPartType.HAND
            || bodyPartType == BodyPartGenerator.BodyPartType.ARM
            || bodyPartType == BodyPartGenerator.BodyPartType.WING
            || bodyPartType == BodyPartGenerator.BodyPartType.HEAD
            || bodyPartType == BodyPartGenerator.BodyPartType.MUZZLE
            || bodyPartType == BodyPartGenerator.BodyPartType.TAIL
            || bodyPartType == BodyPartGenerator.BodyPartType.EAR
            || bodyPartType == BodyPartGenerator.BodyPartType.NOSE
            || bodyPartType == BodyPartGenerator.BodyPartType.NECK
            || bodyPartType == BodyPartGenerator.BodyPartType.FRONT
            || bodyPartType == BodyPartGenerator.BodyPartType.BACK)
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
        if (bodyPartType == BodyPartGenerator.BodyPartType.NAIL || bodyPartType == BodyPartGenerator.BodyPartType.HORN)
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
        this.bodyPartType = BodyPartGenerator.BodyPartType.NA;

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
        for (int i=0; attachedBodyParts!=null && i<attachedBodyParts.size(); i++)
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

    /**
     * Method to convert the Body Object data to a ByteBuffer, it calls the addToBuffer method to do this
     * @return ByteBuffer: The Object's data stored in a ByteBuffer
     */
    public ByteBuffer toBuffer()
    {
        System.out.println( "In BodyPart toBuffer" );

        ByteBuffer buf = ByteBuffer.allocate(16384);
        addToBuffer(buf);

        return buf;
    }

    /**
     * Method that converts the Body Object data to a ByteBuffer
     * @param buf ByteBuffer: The ByteBuffer used to store the object data
     * @return boolean: true is there was no problems in storing the buffer data, false if there was problems
     */
    public boolean addToBuffer(ByteBuffer buf)
    {
        System.out.println( "In BodyPart addToBuffer" );
        boolean noProblems = true;

        noProblems = noProblems && ByteBufferIO.putString( buf, name );
        noProblems = noProblems && ByteBufferIO.putString( buf, description );
        buf.putInt(bodyPartType.ordinal());
        System.out.println( "bodyPartType ordinal buffered:" + bodyPartType.ordinal() );

        noProblems = noProblems && ByteBufferIO.putColor( buf, color );
        buf.putInt(texture.ordinal());
        System.out.println( "texture ordinal buffered:" + texture.ordinal() );

        buf.putInt(animalType.ordinal());
        System.out.println( "animalType ordinal buffered:" + animalType.ordinal() );

        noProblems = noProblems && ByteBufferIO.putFeatures(buf, features);
        noProblems = noProblems && ByteBufferIO.putItemsWorn(buf, itemsWorn);
        noProblems = noProblems && ByteBufferIO.putResistances(buf, resistances);
        noProblems = noProblems && ByteBufferIO.putSkills(buf, skills);
        noProblems = noProblems && ByteBufferIO.putInjuries(buf, injuries);
        buf.putDouble( length );
        System.out.println( "double buffered:" + bodyPartType.ordinal() );
        buf.putDouble( weight );
        System.out.println( "double buffered:" + bodyPartType.ordinal() );
        buf.putInt( maxHealth );
        System.out.println( "int buffered:" + bodyPartType.ordinal() );
        buf.putInt( health );
        System.out.println( "int buffered:" + bodyPartType.ordinal() );
// do not store reference to thisBody, but when creating set reference to this object
        // do not store reference to aboveBodyPart, but set reference when object created

        noProblems = noProblems && ByteBufferIO.putAttachedBodyParts(buf, attachedBodyParts);


        return noProblems;
    }

}




