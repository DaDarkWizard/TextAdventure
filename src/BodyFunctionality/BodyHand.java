package BodyFunctionality;

import Skills.Skill;
import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyHand extends BodyPart
{

    protected final String[] FINGERS = {" thumb", " first finger", " second finger", " third finger", " fourth finger", " fifth finger", "sixth finger", "seventh finger"};
    protected final String[] TOES = {" opposable toe", " first toe", " second toe", " third toe", " fourth toe", " fifth toe", "sixth toe", "seventh toe"};

    protected boolean opposable;

    public BodyHand()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.HAND );
        opposable = false;
    }

    /**
     * Constructor with a buffer containing the BodyHand data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyHand data
     */
    public BodyHand( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyHand(Buffer) constructor" );
        opposable = false;
        int opposableValue = buffer.getInt();
        if (opposableValue==1)
        {
            opposable = true;
        }


    }


    /**
     * Copy Constructor
     * @param oldPart BodyHand:  The original part to copy
     */
    public BodyHand(BodyHand oldPart)
    {
        super( oldPart );
        opposable = oldPart.isOpposable();
    }


    public void setOpposable( boolean opposable )
    {
        this.opposable = opposable;
    }

    public boolean isOpposable()
    {
        return opposable;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.HAND;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        opposable = true;

        int fingerCount = creatureData.getFingerCount();
        // create fingers starting with thumb
        for (int i=0; i<fingerCount; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", creatureData, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger );
        }
        this.addSkill( new Skill("Write") );
        this.addSkill( new Skill("Wield") );
        this.addSkill( new Skill("Grasp") );
        this.addSkill( new Skill("Scratch: 1") );
    }

    public void createHand(String name, String side, CreatureDataObject creatureData, Color color)
    {
        create(name, side, creatureData, color);
    }


    public void createClaw( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        opposable = false;

        int fingerCount = creatureData.getFingerCount();

        // create fingers starting with first finger
        for (int i=1; i<fingerCount+1; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", creatureData, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }
        this.addSkill( new Skill("Scratch: 5") );

    }

    public void createGraspingFoot( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        opposable = true;

        int toeCount = creatureData.getToeCount();


        // create toes starting with opposable toe
        for (int i=0; i<toeCount; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", creatureData, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }

        this.addSkill( new Skill("Grasp") );
        this.addSkill( new Skill("Walk: 5") );
        this.addSkill( new Skill("Run: 15") );

    }

    public void createFoot( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        opposable = false;

        int toeCount = creatureData.getToeCount();

        // create fingers starting with first toe
        for (int i=1; i<toeCount + 1; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", creatureData, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger );
        }

        this.addSkill( new Skill("Walk: 5") );
        this.addSkill( new Skill("Run: 15") );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyHand's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+2)
        {
            if (opposable)
            {
                buffer.putInt( 1 );
            }
            else
            {
                buffer.putInt( 0 );
            }

            isValid = true;
        }
        return isValid;
    }



}
