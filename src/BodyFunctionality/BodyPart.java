package BodyFunctionality;

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

    // todo change some Strings to objects at a later time
    protected ArrayList<BodyFeature> features;
    protected ArrayList<String> itemsWorn;    // change to item object later
    protected ArrayList<String> resistances;
    protected ArrayList<String> skills;
    protected ArrayList<String> injuries;

    protected double length, weight;

    protected Body thisBody;
    protected BodyPart aboveBodyPart;
    protected ArrayList<BodyPart> attachedBodyParts;

    protected int maxHealth, health;

    /*-------------------------------------------------------------------------------------
     *  Constructor Methods
     *------------------------------------------------------------------------------------*/

    public BodyPart()
    {
        name="";
        bodyPartType=BodyPartGenerator.BodyPartType.NA;
        color=Color.BLACK;
        texture=BodyPartGenerator.Texture.NA;
        description="";
        animalType=BodyPartGenerator.AnimalType.NA;
        features = new ArrayList<BodyFeature>(  );
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

        /*
        todo update to send Feature data to buffer
        features = ByteBufferIO.getFeatures( buffer );
        System.out.println( "Setting features to " + features );
        */

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



    public void setBodyPartType( BodyPartGenerator.BodyPartType type )
    {
        this.bodyPartType = type;
    }

    public void setColor( Color color )
    {
        this.color = color;
    }

    public void setName( String name )
    {
        this.name = name;
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

    public void setFeatures( ArrayList<BodyFeature> features )
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

    public ArrayList<BodyFeature> getFeatures()
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

    public void addFeature(BodyFeature feature)
    {
        if ( !features.contains( feature ))
        {
            features.add(feature);
        }
    }

    public void removeSkill(String skill) //todo change to skill object
    {
        skills.remove(skill);
    }


    public void removeFeature(String feature)
    {
        features.remove(feature);
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

        /* todo update to add Feature data to buffer
        noProblems = noProblems && ByteBufferIO.putFeatures(buf, features);
        */

        noProblems = noProblems && ByteBufferIO.putItemsWorn(buf, itemsWorn);
        noProblems = noProblems && ByteBufferIO.putResistances(buf, resistances);
        noProblems = noProblems && ByteBufferIO.putSkills(buf, skills);
        noProblems = noProblems && ByteBufferIO.putInjuries(buf, injuries);
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
        String resistance;
        ArrayList<String> partResistances = part2.getResistances();
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
        String skill;
        ArrayList<String> partSkills = part2.getSkills();
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
        String injury;
        ArrayList<String> partInjuries = part2.getInjuries();
        for (int i=0; i<injuries.size(); i++)
        {
            injury = injuries.get(i);
            if (!partInjuries.contains(injury))
            {
                differ.addInjuriesDifferences(1);
            }
        }

        // check if skills in part2 are in this part
        for (int i=0; i<partInjuries.size(); i++)
        {
            injury = partInjuries.get(i);
            if (!injuries.contains(injury))
            {
                differ.addInjuriesDifferences(1);
            }
        }

        differ.addLengthDifferences( Math.abs(length - part2.getLength()));
        differ.addValueDifferences( Math.abs(maxHealth - part2.getMaxHealth()));

        return differ;
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
             * injuries
             * thisBody
             * aboveBodyPart
             * attachedBodyParts
             */

            isSame = true;

        }

        return isSame;

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



    protected abstract boolean bufferExtraFields(ByteBuffer buffer);


}




