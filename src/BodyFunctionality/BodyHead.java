package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyHead extends BodyPart implements BodyPartInterface
{

    protected boolean muzzled;

    public BodyHead()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.HEAD );
        muzzled = true;
    }

    public void setMuzzled( boolean muzzled )
    {
        this.muzzled = muzzled;
    }

    public boolean isMuzzled()
    {
        return muzzled;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.HEAD;
    }

    @Override
    public BodyHead create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHead thisPart = (BodyHead) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        muzzled = true;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        leftEye.create( "eye", "right", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( leftEye );

        BodyPart muzzle = new BodyMuzzle();
        muzzle.create( "muzzle", "", animalType, color );
        muzzle.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( muzzle );

        BodyPart leftEar = new BodyEar();
        leftEar.create( "ear", "left", animalType, color );
        leftEar.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( leftEar );

        BodyPart rightEar = new BodyEar();
        rightEar.create( "ear", "right", animalType, color );
        rightEar.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( rightEar );

        return thisPart;

    }

    public BodyHead createAnimalHead( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        return create( name, side, animalType, color );
    }

    public BodyHead createHumanHead( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyHead thisPart = (BodyHead) super.create( name, "", animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );
        muzzled = false;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        leftEye.create( "eye", "right", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( leftEye );

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", animalType, color );
        nose.setNoseShape( BodyNose.NoseShape.POINTED);
        nose.setAboveBodyPart( thisPart );
        thisPart.attachedBodyParts.add( nose );

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", animalType, Color.RED );
        mouth.setAboveBodyPart( thisPart );
        mouth.setMouthStyle( BodyMouth.MouthStyle.FRONT );
        mouth.setTeethStyle( BodyMouth.TeethStyle.HUMAN );
        thisPart.attachedBodyParts.add( mouth );

        BodyEar leftEar = new BodyEar();
        leftEar.create( "ear", "left", animalType, color );
        leftEar.setAboveBodyPart( thisPart );
        leftEar.setEarShape( BodyEar.EarShape.ROUND );
        thisPart.attachedBodyParts.add( leftEar );

        BodyEar rightEar = new BodyEar();
        rightEar.create( "ear", "right", animalType, color );
        rightEar.setAboveBodyPart( thisPart );
        rightEar.setEarShape( BodyEar.EarShape.ROUND );

        thisPart.attachedBodyParts.add( rightEar );

        return thisPart;
    }

}
