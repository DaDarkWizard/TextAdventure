package BodyFunctionality;

import javafx.scene.paint.Color;

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
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        opposable = true;

        // create fingers starting with thumb
        for (int i=0; i<5; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", animalType, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }
        this.addSkill( "Write" );
        this.addSkill( "Wield" );
        this.addSkill( "Grasp" );
        this.addSkill( "Scratch: 1");
    }

    public void createHand(String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        create(name, side, animalType, color);
    }


    public void createClaw( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        opposable = false;

        // create fingers starting with thumb
        for (int i=1; i<6; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", animalType, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }
        this.addSkill( "Scratch: 5" );

    }

    public void createGraspingFoot( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        opposable = true;

        // create toes starting with opposable toe
        for (int i=0; i<5; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", animalType, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }

        this.addSkill( "Grasp" );
        this.addSkill( "Walk: 5" );
        this.addSkill( "Run: 15" );

    }

    public void createFoot( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        opposable = false;

        // create fingers starting with thumb
        for (int i=1; i<6; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", animalType, color);
            finger.setAboveBodyPart( this );
            this.attachedBodyParts.add( finger);
        }

        this.addSkill( "Walk: 5" );
        this.addSkill( "Run: 15" );

    }


}
