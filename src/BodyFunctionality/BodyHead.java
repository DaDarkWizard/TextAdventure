package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyHead extends BodyPart
{

    protected boolean muzzled;

    public BodyHead()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.HEAD );
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

    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        muzzled = true;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        rightEye.create( "eye", "right", animalType, Color.GREEN );
        rightEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightEye );

        BodyPart muzzle = new BodyMuzzle();
        muzzle.create( "muzzle", "", animalType, color );
        muzzle.setAboveBodyPart( this );
        this.attachedBodyParts.add( muzzle );

        BodyPart leftEar = new BodyEar();
        leftEar.create( "ear", "left", animalType, color );
        leftEar.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftEar );

        BodyPart rightEar = new BodyEar();
        rightEar.create( "ear", "right", animalType, color );
        rightEar.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightEar );

    }

    public void createAnimalHead( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        create( name, side, animalType, color );
    }

    public void createHumanHead( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.bodyPartType = bodyPartType();
        muzzled = false;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", animalType, Color.GREEN );
        leftEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        rightEye.create( "eye", "right", animalType, Color.GREEN );
        rightEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightEye );

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", animalType, color );
        nose.setNoseShape( BodyNose.NoseShape.POINTED);
        nose.setAboveBodyPart( this );
        this.attachedBodyParts.add( nose );

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", animalType, Color.RED );
        mouth.setAboveBodyPart( this );
        mouth.setMouthStyle( BodyMouth.MouthStyle.FRONT );
        mouth.setTeethStyle( BodyMouth.TeethStyle.HUMAN );
        this.attachedBodyParts.add( mouth );

        BodyEar leftEar = new BodyEar();
        leftEar.create( "ear", "left", animalType, color );
        leftEar.setAboveBodyPart( this );
        leftEar.setEarShape( BodyEar.EarShape.ROUND );
        this.attachedBodyParts.add( leftEar );

        BodyEar rightEar = new BodyEar();
        rightEar.create( "ear", "right", animalType, color );
        rightEar.setAboveBodyPart( this );
        rightEar.setEarShape( BodyEar.EarShape.ROUND );

        this.attachedBodyParts.add( rightEar );

    }

}
