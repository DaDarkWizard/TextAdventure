package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyMuzzle extends BodyPart implements BodyPartInterface
{

    public BodyMuzzle()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.MUZZLE );
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.MUZZLE;
    }

    @Override
    public BodyMuzzle create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyMuzzle thisPart = (BodyMuzzle) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", animalType, color );
        nose.setNoseShape( BodyNose.NoseShape.HIDDEN);
        nose.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( nose );

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", animalType, Color.RED );
        mouth.setAboveBodyPart( thisPart );
        mouth.setMouthStyle( BodyMouth.MouthStyle.WIDE );
        mouth.setTeethStyle( BodyMouth.TeethStyle.SHARP );
        thisPart.attachedBodyParts.add( mouth );

        return thisPart;

    }


}
