package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyHand extends BodyPart implements BodyPartInterface
{

    protected final String[] FINGERS = {" thumb", " first finger", " second finger", " third finger", " fourth finger", " fifth finger", "sixth finger", "seventh finger"};
    protected final String[] TOES = {" opposable toe", " first toe", " second toe", " third toe", " fourth toe", " fifth toe", "sixth toe", "seventh toe"};

    protected boolean opposable;

    public BodyHand()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.HAND );
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
    public BodyHand create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHand thisPart = (BodyHand) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        opposable = true;

        // create fingers starting with thumb
        for (int i=0; i<5; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", animalType, color);
            finger.setAboveBodyPart( thisPart );
            thisPart.attachedBodyParts.add( finger);
        }

        thisPart.addSkill( "Write" );
        thisPart.addSkill( "Wield" );
        thisPart.addSkill( "Grasp" );

        return thisPart;
    }

    public BodyHand createHand(String name, String side, BodyPartGenerator.AnimalType animalType, Color color)
    {
        return create(name, side, animalType, color);
    }


    public BodyHand createClaw( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHand thisPart = (BodyHand) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        opposable = false;

        // create fingers starting with thumb
        for (int i=1; i<6; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + FINGERS[i] , "", animalType, color);
            finger.setAboveBodyPart( thisPart );
            thisPart.attachedBodyParts.add( finger);
        }

        return thisPart;
    }

    public BodyHand createGraspingFoot( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHand thisPart = (BodyHand) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        opposable = true;

        // create toes starting with opposable toe
        for (int i=0; i<5; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", animalType, color);
            finger.setAboveBodyPart( thisPart );
            thisPart.attachedBodyParts.add( finger);
        }

        thisPart.addSkill( "Grasp" );

        return thisPart;
    }

    public BodyHand createFoot( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHand thisPart = (BodyHand) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        opposable = false;

        // create fingers starting with thumb
        for (int i=1; i<6; i++)
        {
            BodyPart finger = new BodyFinger();
            finger.create(side + TOES[i] , "", animalType, color);
            finger.setAboveBodyPart( thisPart );
            thisPart.attachedBodyParts.add( finger);
        }

        return thisPart;
    }


}
