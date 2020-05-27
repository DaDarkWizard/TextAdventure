package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyMuzzle extends BodyPart
{

    public BodyMuzzle()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.MUZZLE );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.MUZZLE;
    }

    @Override
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", animalType, color );
        nose.setNoseShape( BodyNose.NoseShape.HIDDEN);
        nose.setAboveBodyPart( this );
        this.attachedBodyParts.add( nose );

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", animalType, Color.RED );
        mouth.setAboveBodyPart( this );
        mouth.setMouthStyle( BodyMouth.MouthStyle.WIDE );
        mouth.setTeethStyle( BodyMouth.TeethStyle.SHARP );
        this.attachedBodyParts.add( mouth );

    }


}
