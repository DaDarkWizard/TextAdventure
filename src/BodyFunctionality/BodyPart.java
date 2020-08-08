/**
 * This class holds data about a BodyPart of a Body object of a Player/user
 *
 * It holds general information connected with body parts, names and identifiers, including
 * features, resistances, itemsWorn, skills and statusEffects
 *
 *
 * Created by Michael Clinesmith
 * Last update edit 8/8/2020
 * -added comment blocks
 * -rearranged order of methods
 * -updated ArrayList Classes
 * -added methods to manipulate ArrayLists
 * -added some todos
 */
package BodyFunctionality;

import Effects.StatusEffect;
import Items.Item;
import Resistances.Resistance;
import Skills.Skill;
import Transformation.TransformationDifferences;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class BodyPart
{

    protected String name, description;
    protected BodyPartGenerator.BodyPartType bodyPartType;
    protected Color color;
    protected BodyPartGenerator.Texture texture;
    protected BodyPartGenerator.AnimalType animalType;

    protected ArrayList<BodyFeature> features;
    protected ArrayList<Item> itemsWorn;
    protected ArrayList<Resistance> resistances;
    protected ArrayList<Skill> skills;
    protected ArrayList<StatusEffect> effects;

    protected double length, weight;

    protected Body thisBody;
    protected BodyPart aboveBodyPart;
    protected ArrayList<BodyPart> attachedBodyParts;

    protected int maxHealth, health;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    /**
     * No argument constructor, sets default values
     */
    public BodyPart()
    {
        name="";
        bodyPartType=BodyPartGenerator.BodyPartType.NA;
        color=Color.BLACK;
        texture=BodyPartGenerator.Texture.NA;
        description="";
        animalType=BodyPartGenerator.AnimalType.NA;
        features = new ArrayList<BodyFeature>(  );
        itemsWorn = new ArrayList<Item>(  );
        resistances = new ArrayList<Resistance>();
        skills = new ArrayList<Skill>();
        effects = new ArrayList<StatusEffect>();

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

        /*
        todo update to send ArrayList data to buffer
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
        */

        length = buffer.getDouble();
        System.out.println( "Setting length to " + length );

        weight = buffer.getDouble();
        System.out.println( "Setting weight to " + weight );

        maxHealth = buffer.getInt();
        System.out.println( "Setting maxHealth to " + maxHealth );

        health = buffer.getInt();
        System.out.println( "Setting health to " + health );

        System.out.println( "Preparing to Create more subordinate body parts if they exist" );
        attachedBodyParts = ByteBufferIO.getAttachedBodyParts( buffer );

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
        features = new ArrayList<BodyFeature>( oldPart.getFeatures() );
        itemsWorn = new ArrayList<Item>( oldPart.getItemsWorn() );
        resistances = new ArrayList<Resistance>( oldPart.getResistances() );
        skills = new ArrayList<Skill>( oldPart.getSkills() );
        effects = new ArrayList<StatusEffect>( oldPart.getEffects() );

        length = oldPart.getLength();
        weight = oldPart.getWeight();

        thisBody = oldPart.getThisBody();  //todo should keep this shallow copy?
        aboveBodyPart = oldPart.getAboveBodyPart(); //todo should keep this shallow copy?
        attachedBodyParts = copyArrayBodyPart(  oldPart.getAttachedBodyParts() ); //todo should this be a deep copy?

        maxHealth = oldPart.getMaxHealth();
        health = oldPart.getHealth();
    }


    /**
     * Constructor assisting method to create an ArrayList of BodyParts
     * @param bodyPartList ArrayList<BodyPart>: A list of BodyPart objects to copy
     * @return ArrayList<BodyPart></BodyPart> : A copy of the BodyPart objects
     */
    private ArrayList<BodyPart> copyArrayBodyPart(ArrayList<BodyPart> bodyPartList)
    {
        BodyPartGenerator.BodyPartType copyType;
        BodyPart copiedPart;

        ArrayList<BodyPart> newList = new ArrayList<BodyPart>(  );
        for (int i = 0; i<bodyPartList.size(); i++)
        {

            copyType = bodyPartList.get( i ).bodyPartType;
            switch(copyType)
            {
                case ARM:
                    copiedPart = new BodyArm( (BodyArm) bodyPartList.get( i ) );
                    break;
                case BACK:
                    copiedPart = new BodyBack( (BodyBack) bodyPartList.get( i ) );
                    break;
                case CHEST:
                    copiedPart = new BodyChest( (BodyChest) bodyPartList.get( i ) );
                    break;
                case EAR:
                    copiedPart = new BodyEar( (BodyEar) bodyPartList.get( i ) );
                    break;
                case EYE:
                    copiedPart = new BodyEye( (BodyEye) bodyPartList.get( i ) );
                    break;
                case FINGER:
                    copiedPart = new BodyFinger( (BodyFinger) bodyPartList.get( i ) );
                    break;
                case HAND:
                    copiedPart = new BodyHand( (BodyHand) bodyPartList.get( i ) );
                    break;
                case HEAD:
                    copiedPart = new BodyHead( (BodyHead) bodyPartList.get( i ) );
                    break;
                case HORN:
                    copiedPart = new BodyHorn( (BodyHorn) bodyPartList.get( i ) );
                    break;
                case MOUTH:
                    copiedPart = new BodyMouth( (BodyMouth) bodyPartList.get( i ) );
                    break;
                case MUZZLE:
                    copiedPart = new BodyMuzzle( (BodyMuzzle) bodyPartList.get( i ) );
                    break;
                case NECK:
                    copiedPart = new BodyNeck( (BodyNeck) bodyPartList.get( i ) );
                    break;
                case NOSE:
                    copiedPart = new BodyNose( (BodyNose) bodyPartList.get( i ) );
                    break;
                case TAIL:
                    copiedPart = new BodyTail( (BodyTail) bodyPartList.get( i ) );
                    break;
                case TONGUE:
                    copiedPart = new BodyTongue( (BodyTongue) bodyPartList.get( i ) );
                    break;
                case WING:
                    copiedPart = new BodyWing( (BodyWing) bodyPartList.get( i ) );
                    break;


                default:
                    copiedPart = null;
                    break;
            }



            newList.add( copiedPart );
        }
        return newList;
    }

    /*-------------------------------------------------------------------------------------
     *  Setter Methods
     *------------------------------------------------------------------------------------*/
    //todo check if ArrayList setter methods are to have deep or shallow copy or just reference

    /**
     * Mutator method to set a reference to the BodyPart this one is attached to
     * @param aboveBodyPart BodyPart: A reference to the attached above BodyPart
     */
    public void setAboveBodyPart( BodyPart aboveBodyPart )
    {
        this.aboveBodyPart = aboveBodyPart;
    }

    /**
     * Mutator method to set the animalType of the BodyPart
     * @param animalType AnimalType: The enum value representing the AnimalType
     */
    public void setAnimalType( BodyPartGenerator.AnimalType animalType )
    {
        this.animalType = animalType;
    }

    /**
     * Mutator method to set a reference to the ArrayList containing extra BodyParts attached
     *  to this one
     * @param attachedBodyParts ArrayList<BodyPart>: The extra attached BodyParts to this BodyPart
     */
    public void setAttachedBodyParts( ArrayList<BodyPart> attachedBodyParts )
    {
        this.attachedBodyParts = attachedBodyParts;
    }

    /**
     * Mutator method to set the BodyPartType of this BodyPart
     * @param type BodyPartType: The BodyPartType value of this BodyPart
     */
    public void setBodyPartType( BodyPartGenerator.BodyPartType type )
    {
        this.bodyPartType = type;
    }

    /**
     * Mutator method to set the color of this BodyPart
     * @param color Color: The Color of this BodyPart
     */
    public void setColor( Color color )
    {
        this.color = color;
    }

    /**
     * Mutator method to set the description of this BodyPart
     * @param description String: The String description of this BodyPart
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Mutator method to set the ArrayList of StatusEffects to the BodyPart
     * @param effects ArrayList<StatusEffects>: The list of StatusEffects on the BodyPart
     */
    public void setEffects( ArrayList<StatusEffect> effects )
    {
        this.effects = effects;
    }

    /**
     * Mutator method to set the Feature ArrayList of the BodyPart
     * @param features ArrayList<BodyFeature>: An ArrayList of BodyFeatures on the BodyPart
     */
    public void setFeatures( ArrayList<BodyFeature> features )
    {
        this.features = features;
    }

    /**
     * Mutator method to set the health of the BodyPart
     * @param health int: The health value of the BodyPart
     */
    public void setHealth( int health )
    {
        this.health = health;
    }

    /**
     * Mutator method of items equipped on the BodyPart
     * @param itemsWorn ArrayList<Item>: The list of objects equipped on the BodyPart
     */
    public void setItemsWorn( ArrayList<Item> itemsWorn )
    {
        this.itemsWorn = itemsWorn;
    }

    /**
     * Mutator method to set the length of the BodyPart
     * @param length double: The length of the BodyPart
     */
    public void setLength( double length )
    {
        this.length = length;
    }

    /**
     * Mutator method to set the maximum health of the BodyPart
     * @param maxHealth int: The maximum health value of the BodyPart
     */
    public void setMaxHealth( int maxHealth )
    {
        this.maxHealth = maxHealth;
    }

    /**
     * Mutator method to set the name of the BodyPart
     * @param name String: The name of the BodyPart
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Mutator method to set the list of Resistances of the BodyPart
     * @param resistances ArrayList<Resistances>: The list of Resistances of the BodyPart
     */
    public void setResistances( ArrayList<Resistance> resistances )
    {
        this.resistances = resistances;
    }

    /**
     * Mutator method to set the list of Skills of the BodyPart
     * @param skills ArrayList<Skill>: The list of Skills enabled by the BodyPart
     */
    public void setSkills( ArrayList<Skill> skills )
    {
        this.skills = skills;
    }

    /**
     * Mutator method to set the Texture of the BodyPart
     * @param texture Texture: The Texture value of the BodyPart
     */
    public void setTexture( BodyPartGenerator.Texture texture )
    {
        this.texture = texture;
    }

    /**
     * Mutator method to return a link to the Body of this BodyPart object
     * @param thisBody Body: A reference link to the Body of this BodyPart object
     */
    public void setThisBody( Body thisBody )
    {
        this.thisBody = thisBody;
    }

    /**
     * Mutator method to set the weight of this BodyPart object
     * @param weight double: The weight of this BodyPart object
     */
    public void setWeight( double weight )
    {
        this.weight = weight;
    }

    /*-------------------------------------------------------------------------------------
     *  Getter Methods
     *------------------------------------------------------------------------------------*/

    //todo check if ArrayList getter methods are to have deep or shallow copy or just reference

    /**
     * Accessor method to get the attached above BodyPart
     * @return BodyPart:  The attached connected BodyPart
     */
    public BodyPart getAboveBodyPart()
    {
        return aboveBodyPart;
    }

    /**
     * Accessor method to get the attached AnimalType
     * @return AnimalType: The AnimalType of the BodyPart
     */
    public BodyPartGenerator.AnimalType getAnimalType()
    {
        return animalType;
    }

    /**
     * Accessor method to get the ArrayList of extra attached BodyParts
     * @return ArrayList<BodyPart>: The list of extra attached BodyParts to this BodyPart
     */
    public ArrayList<BodyPart> getAttachedBodyParts()
    {
        return attachedBodyParts;
    }

    /**
     * Accessor method to get the BodyPartType of the BodyPart
     * @return BodyPartType: The BodyPartType of the BodyPart
     */
    public BodyPartGenerator.BodyPartType getBodyPartType()
    {
        return bodyPartType;
    }

    /**
     * Accessor method to get the Color of the BodyPart
     * @return Color: The Color of the BodyPart
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Accessor method to get the description of the BodyPart
     * @return String: The description of the BodyPart
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Accessor method to get the ArrayList of Effects attached to the BodyPart
     * @return ArrayList<Effect>: The ArrayList of Effects attached to the BodyPart
     */
    public ArrayList<StatusEffect> getEffects()
    {
        return effects;
    }

    /**
     * Accessor method to get the ArrayList of Features attached to the BodyPart
     * @return ArrayList<Feature>: The ArrayList of Features attached to the BodyPart
     */
    public ArrayList<BodyFeature> getFeatures()
    {
        return features;
    }

    /**
     * Accessor method to get the health of the BodyPart
     * @return int: The health value of the BodyPart
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Accessor method to get the list of equipped items on the BodyPart
     * @return ArrayList<Item>: The list of equipped items on the BodyPart
     */
    public ArrayList<Item> getItemsWorn()
    {
        return itemsWorn;
    }

    /**
     * Accessor method to get the length of the BodyPart
     * @return double: The length of the BodyPart
     */
    public double getLength()
    {
        return length;
    }

    /**
     * Accessor method to get the maximum health of the BodyPart
     * @return int: The maximum health value of the BodyPart
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Accessor method to get the name of the BodyPart
     * @return String: The name of the BodyPart
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to get the number of extra attached BodyParts to this BodyPart
     * @return int: The number of extra attached BodyParts to this BodyPart
     */
    public int getNumAttached()
    {
        return attachedBodyParts.size();
    }

    /**
     * Accessor method to get the ArrayList of Resistances attached to the BodyPart
     * @return ArrayList<Resistance>: The ArrayList of Resistances attached to the BodyPart
     */
    public ArrayList<Resistance> getResistances()
    {
        return resistances;
    }

    /**
     * Accessor method to get the ArrayList of Skills attached to the BodyPart
     * @return ArrayList<Skill>: The ArrayList of Skills attached to the BodyPart
     */
    public ArrayList<Skill> getSkills()
    {
        return skills;
    }

    /**
     * Accessor method to get the Texture of the BodyPart
     * @return Texture: The Texture of the BodyPart
     */
    public BodyPartGenerator.Texture getTexture()
    {
        return texture;
    }

    /**
     * Accessor method to get the reference to this BodyPart's Body
     * @return Body: A reference to the Body of this BodyPart
     */
    public Body getThisBody()
    {
        return thisBody;
    }

    /**
     * Accessor method to get the weight of this BodyPart
     * @return double: The weight value of this BodyPart
     */
    public double getWeight()
    {
        return weight;
    }

    /*-------------------------------------------------------------------------------------
     *  Set All Methods
     *------------------------------------------------------------------------------------*/

    //todo set up useful setAll methods

    /**
     * Method that sets the body reference of all body objects of the same body to the same body
     * @param body Body: The Body object reference to set all body objects equal to
     */
    public void setAllBody(Body body)
    {
        thisBody = body;                                    // sets this object's reference
        for (int i = 0; attachedBodyParts!=null && i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAllBody( body );  // sets all attached object's references
        }
    }

    /**
     * Method to set all of the regular body parts (excepting horns and nails) to a particular color
     * @param color Color: The color to set the body parts to
     */
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

    /**
     * Method to set all of the horns and nails to a particular color
     * @param color Color: The color to set the horns and nails to
     */
    public void setAllHornColor(Color color)
    {
        // color is part if it is a horn or nail outer body part
        if (isHornColorPart())
        {
            this.color = color;
        }
        else if(bodyPartType== BodyPartGenerator.BodyPartType.FINGER)
        {
            BodyFinger finger = (BodyFinger) this;
            finger.setNailColor( color );
        }
        for (int i = 0; i<attachedBodyParts.size(); i++)
        {
            attachedBodyParts.get( i ).setAllHornColor( color );
        }
    }

    /*-------------------------------------------------------------------------------------
     *  Other Methods
     *------------------------------------------------------------------------------------*/

    /**
     * Method to add a feature to the Body Part's ArrayList of BodyFeatures
     * Note: This method makes a deep copy of the BodyFeature
     * @param feature BodyFeature: BodyFeature to add to the ArrayList
     */
    public void addFeature(BodyFeature feature)
    {
        if ( !features.contains( feature ))
        {
            features.add(new BodyFeature(feature));
        }
    }

    /**
     * Method that adds padding to the front of string (like indenting)
     *  This method assists the toString method
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
     * Method to add a Item to the Body Part's ArrayList of Items
     * Note: This method makes a deep copy of the Item
     * @param item Item: Item to add to the ArrayList
     */
    public void addItem(Item item)
    {
        if ( !itemsWorn.contains( item ))
        {
            itemsWorn.add(new Item(item));
        }
    }



    /**
     * Method to add a Resistance to the Body Part's ArrayList of Resistances
     * Note: This method makes a deep copy of the Resistance
     * @param resistance Resistance: Resistance to add to the ArrayList
     */
    public void addResistance(Resistance resistance)
    {
        if ( !resistances.contains( resistance ))
        {
            resistances.add(new Resistance(resistance));
        }
    }

    /**
     * Method to add a Skill to the skill list
     * Note, this method makes a deep copy of the skill
     * @param skill Skill: Skill to be added to the Body Part's ArrayList
     */
    public void addSkill(Skill skill)
    {
        if ( !skills.contains( skill ))
        {
            skills.add(new Skill(skill));
        }
    }

    /**
     * Method to add a feature to the Body Part's ArrayList of StatusEffect
     * Note: This method makes a deep copy of the StatusEffect
     * @param effect StatusEffect: StatusEffect to add to the ArrayList
     */
    public void addStatusEffect(StatusEffect effect)
    {
        if ( !effects.contains( effect ))
        {
            effects.add(new StatusEffect(effect));
        }
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

        /* todo update to add ArrayList data to buffer
        noProblems = noProblems && ByteBufferIO.putFeatures(buf, features);
        noProblems = noProblems && ByteBufferIO.putItemsWorn(buf, itemsWorn);
        noProblems = noProblems && ByteBufferIO.putResistances(buf, resistances);
        noProblems = noProblems && ByteBufferIO.putSkills(buf, skills);
        noProblems = noProblems && ByteBufferIO.putInjuries(buf, injuries);
        */

        buf.putDouble( length );
        System.out.println( "double buffered:" + length );
        buf.putDouble( weight );
        System.out.println( "double buffered:" + weight );
        buf.putInt( maxHealth );
        System.out.println( "int buffered:" + maxHealth );
        buf.putInt( health );
        System.out.println( "int buffered:" + health );
        // do not store reference to thisBody, but when creating set reference to this object
        // do not store reference to aboveBodyPart, but set reference when object created

        noProblems = noProblems && ByteBufferIO.putAttachedBodyParts(buf, attachedBodyParts);

        noProblems = noProblems && this.bufferExtraFields(buf);

        return noProblems;
    }

    /**
     * Method that returns this Body Part's object type
     * This returns the NA value, but may be overwritten
     * @return BodyPartType: returns NA
     */
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.NA;
    }


    /**
     * Method to clear a Body Part's BodyFeature ArrayList
     */
    public void clearFeatures()
    {
        features.clear();
    }

    /**
     * Method to clear a Body Part's itemsWorn ArrayList
     */
    public void clearItems()
    {
        itemsWorn.clear();
    }

    /**
     * Method to clear a Body Part's Resistances ArrayList
     */
    public void clearResistances()
    {
        resistances.clear();
    }

    /**
     * Method to clear a Body Part's Skill ArrayList
     */
    public void clearSkills()
    {
        skills.clear();
    }

    /**
     * Method to clear a Body Part's StatusEffect ArrayList
     */
    public void clearStatusEffects()
    {
        effects.clear();
    }

    /**
     * This method counts the number of body parts attached to this one and returns the
     *      total number of attached parts [ excluding the above parts ]
     * todo this method needs to be updated to include parts attached to child objects
     * @return int: The number of attached parts
     */
    public int countParts()
    {
        int count = 1;
        System.out.println( "Part:" + name );
        for (int i=0; attachedBodyParts!=null && i<attachedBodyParts.size(); i++)
        {
            count += attachedBodyParts.get( i ).countParts();
        }

        return count;
    }

    /**
     * Method that creates updates the BodyPart type with basic information regarding the BodyPart
     * @param name String: The name of the BodyPart object
     * @param side String: The side the BodyPart object is on (ie. left or right), becomes part of the name
     * @param creatureData AnimalType: The type of animal is body part belongs to
     * @param color Color: The color of the body part
     */
    public void create(String name, String side, CreatureDataObject creatureData, Color color)
    {
        if (side.isEmpty())
        {
            this.name = name;
        }
        else
        {
            this.name = side + " " + name;
        }
        this.animalType = creatureData.getAnimalTypeStyle();
        this.description = "A " + animalType.toString() + " " + side + " " + name;
        this.color = color;
        this.bodyPartType = BodyPartGenerator.BodyPartType.NA;
        this.texture = creatureData.getBodyTexture();

    }

    /**
     * Method to determine the number of differences between two body parts
     * @param part2
     * @return
     */
    public TransformationDifferences differences(BodyPart part2)
    {
        TransformationDifferences differ = new TransformationDifferences();

        //protected String name, description;
        if (bodyPartType != part2.getBodyPartType())
        {
            differ.addBodyPartDifference();
        }
        if (!color.equals(part2.getColor()))
        {
            differ.addColorDifference();
        }
        if (texture != part2.getTexture())
        {
            differ.addTextureDifference();
        }
        if (animalType != part2.getAnimalType())
        {
            differ.addAnimalTypeDifference();
        }

        // check if features in this part are in part2
        //todo adjust when features implemented
        BodyFeature feature;
        ArrayList<BodyFeature> partFeatures = part2.getFeatures();
        for (int i=0; i<features.size(); i++)
        {
            feature = features.get(i);
            if (!partFeatures.contains(feature))
            {
                differ.addFeatureDifferences(1);
            }
        }

        // check if features in part2 are in this part
        for (int i=0; i<partFeatures.size(); i++)
        {
            feature = partFeatures.get(i);
            if (!features.contains(feature))
            {
                differ.addFeatureDifferences(1);
            }
        }


        // check if resistances in this part are in part2
        //todo adjust when resistances implemented
        Resistance resistance;
        ArrayList<Resistance> partResistances = part2.getResistances();
        for (int i=0; i<resistances.size(); i++)
        {
            resistance = resistances.get(i);
            if (!partResistances.contains(resistance))
            {
                differ.addResistanceDifferences(1);
            }
        }

        // check if resistances in part2 are in this part
        for (int i=0; i<partResistances.size(); i++)
        {
            resistance = partResistances.get(i);
            if (!resistances.contains(resistance))
            {
                differ.addResistanceDifferences(1);
            }
        }

        // check if skills in this part are in part2
        //todo adjust when skills implemented
        Skill skill;
        ArrayList<Skill> partSkills = part2.getSkills();
        for (int i=0; i<skills.size(); i++)
        {
            skill = skills.get(i);
            if (!partSkills.contains(skill))
            {
                differ.addSkillsDifferences(1);
            }
        }

        // check if skills in part2 are in this part
        for (int i=0; i<partSkills.size(); i++)
        {
            skill = partSkills.get(i);
            if (!skills.contains(skill))
            {
                differ.addSkillsDifferences(1);
            }
        }

        // check if injuries in this part are in part2
        //todo adjust when injuries implemented
        StatusEffect effect;
        ArrayList<StatusEffect> partInjuries = part2.getEffects();
        for (int i=0; i<effects.size(); i++)
        {
            effect = effects.get(i);
            if (!partInjuries.contains(effect))
            {
                differ.addInjuriesDifferences(1);
            }
        }

        // check if skills in part2 are in this part
        for (int i=0; i<partInjuries.size(); i++)
        {
            effect = partInjuries.get(i);
            if (!effects.contains(effect))
            {
                differ.addInjuriesDifferences(1);
            }
        }

        differ.addLengthDifferences( Math.abs(length - part2.getLength()));
        differ.addValueDifferences( Math.abs(maxHealth - part2.getMaxHealth()));

        return differ;
    }

    /**
     * Method that returns the list of all Body parts attached to looking at the
     *  attachedBodyPart arrays
     *  todo check method if needs to be removed or updated
     * @return ArrayList<BodyPart> The list of all body parts attached
     */
    public ArrayList<BodyPart> getAllBodyPartChildren()
    {
        ArrayList<BodyPart> allChildren = new ArrayList<BodyPart>( attachedBodyParts ); // include all attached parts
        for (int i=0; attachedBodyParts!=null && i < attachedBodyParts.size(); i++)
        {
            allChildren.addAll( attachedBodyParts.get(i).getAllBodyPartChildren() );
        }
        return allChildren;
    }

    /**
     * Method to search for a body part.  If it is not found, return null
     * Returns a shallow copy of the body part
     *
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
        else if (this.getBodyPartType()== BodyPartGenerator.BodyPartType.BODY)
        {
            Body thisBody = (Body) this;

            // search through internal body parts when searching Body
            for (int i=0; i<thisBody.internalBodyParts.size() && part==null; i++)
            {
                part = thisBody.internalBodyParts.get( i ).getBodyPart( partName );
            }
        }

        if (part==null)         //continue search in attached body parts if part not yet found
        {
            // search through all attached parts until part found
            for (int i=0; i<attachedBodyParts.size() && part==null; i++)
            {
                part = attachedBodyParts.get( i ).getBodyPart( partName );
            }
        }
        return part;
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
            || bodyPartType == BodyPartGenerator.BodyPartType.CHEST
            || bodyPartType == BodyPartGenerator.BodyPartType.BACK)
        {
            isBodyColor = true;
        }

        return isBodyColor;
    }

    /**
     * Method that compares if two BodyPart objects are the same
     * @param part2: The other BodyPart object to compare
     * @return boolean: true if the values of this object compared to part2 are the same, false otherwise
     */
    public boolean isEqual(BodyPart part2)
    {
        boolean isSame = false;

        if( name.equals(part2.getName())
                && description.equals(part2.getDescription())
                && bodyPartType==part2.getBodyPartType()
                && color.equals(part2.getColor())
                && texture==part2.getTexture()
                && animalType==part2.getAnimalType()
                && length==part2.getLength()
                && weight==part2.getWeight()
                && health==part2.getHealth()
                && maxHealth==part2.getMaxHealth())
        {
            /*
             * todo check other fields when implemented:
             * features
             * itemsWorn
             * resistances
             * skills
             * effects
             * thisBody
             * aboveBodyPart
             * attachedBodyParts
             */

            isSame = true;

        }

        return isSame;

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

    /**
     * Method that compares if two BodyPart objects are the same part
     * @param part2: The other BodyPart object to compare
     * @return boolean: true if both reference the same Body part, false if otherwise.
     */
    public boolean isSamePart(BodyPart part2)
    {
        boolean isSame = false;

        if ( this.getBodyPartType()==part2.getBodyPartType()
                && this.name.equals(part2.getName()))
        {
            isSame = true;
        }

        return isSame;
    }

    /**
     * Method to remove a BodyFeature from the Body Part's Feature ArrayList
     * @param feature BodyFeature: The BodyFeature to remove from the Feature ArrayList
     */
    public void removeFeature(BodyFeature feature)
    {
        features.remove(feature);
    }

    /**
     * Method to remove an Item from the Body Part's itemsWorn ArrayList
     * @param item Item: The Item to remove from the Item ArrayList
     */
    public void removeItem(Item item)
    {
        itemsWorn.remove(item);
    }

    /**
     * Method to remove a Resistance from the Body Part's Resistance ArrayList
     * @param resistance Resistance: The Resistance to remove from the Resistance ArrayList
     */
    public void removeResistance(Resistance resistance)
    {
        resistances.remove(resistance);
    }

    /**
     * Method to remove a Skill from the Body Part's Skill ArrayList
     * @param skill Skill: The Skill to remove from the Skill ArrayList
     */
    public void removeSkill(Skill skill)
    {
        skills.remove(skill);
    }

    /**
     * Method to remove a StatusEffect from the Body Part's StatusEffect ArrayList
     * @param effect StatusEffect: The StatusEffect to remove from the StatusEffect ArrayList
     */
    public void removeSkill(StatusEffect effect)
    {
        effects.remove(effect);
    }

    /**
     * Method that creates the padding given the PADDING constant
     * This method assists the toString method
     *
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
     * Method that converts a basic description of the object (and its attachments) to a string
     * @return String: A String that lists some basic descriptions of the object along with padded descriptions
     *                  of its attached objects
     */
    @Override
    public String toString()
    {
        String str = "A " + String.format( "%.2f", length ) + "in " + color + " " + texture + " " + animalType + " " + name;
        for (int i=0; attachedBodyParts!=null && i<attachedBodyParts.size(); i++)
        {
            str += "\n" + addPadding(attachedBodyParts.get( i ).toString(), this.treeDepth()*2+ 2);
        }
        return str;
    }


    /**
     * Method to determine how deep the body part is in the body tree
     * todo this method needs to be updated to include child body parts
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

    /*-------------------------------------------------------------------------------------
     *  Abstract Methods
     *------------------------------------------------------------------------------------*/

    protected abstract boolean bufferExtraFields(ByteBuffer buffer);

}




