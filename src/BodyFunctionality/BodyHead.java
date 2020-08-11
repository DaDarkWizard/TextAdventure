package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyHead extends BodyPart
{

    protected boolean muzzled;

    public BodyHead()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.HEAD );
        muzzled = true;
    }

    /**
     * Constructor with a buffer containing the BodyHead data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyHand data
     */
    public BodyHead( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyHead(Buffer) constructor" );
        muzzled = false;
        int opposableValue = buffer.getInt();
        if (opposableValue==1)
        {
            muzzled = true;
        }

    }

    /**
     * Copy Constructor
     * @param oldPart BodyHead:  The original part to copy
     */
    public BodyHead(BodyHead oldPart)
    {
        super( oldPart );
        muzzled = oldPart.isMuzzled();
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
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.HEAD;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        muzzled = true;

        Color eyeColor = creatureData.getRandomEyeColor();
        double eyeSize = creatureData.getRandomEyeLength();
        double eyeWeight = creatureData.getRandomWeightFactor() * eyeSize;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", creatureData, eyeColor );
        leftEye.setLength( eyeSize );
        leftEye.setWeight( eyeWeight );
        leftEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        rightEye.create( "eye", "right", creatureData, eyeColor );
        rightEye.setLength( eyeSize );
        rightEye.setWeight( eyeWeight );
        rightEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightEye );

        double muzzleSize = creatureData.getRandomMuzzleLength();
        double muzzleWeight = creatureData.getRandomWeightFactor() * muzzleSize;

        BodyPart muzzle = new BodyMuzzle();
        muzzle.create( "muzzle", "", creatureData, color );
        muzzle.setLength( muzzleSize );
        muzzle.setWeight( muzzleWeight );
        muzzle.setAboveBodyPart( this );
        this.attachedBodyParts.add( muzzle );

        double earSize = creatureData.getRandomEarLength();
        double earWeight = creatureData.getRandomWeightFactor() * earSize;

        BodyPart leftEar = new BodyEar();
        leftEar.create( "ear", "left", creatureData, color );
        leftEar.setAboveBodyPart( this );
        leftEar.setLength( earSize );
        leftEar.setWeight( earWeight );
        this.attachedBodyParts.add( leftEar );

        BodyPart rightEar = new BodyEar();
        rightEar.create( "ear", "right", creatureData, color );
        rightEar.setAboveBodyPart( this );
        rightEar.setLength( earSize );
        rightEar.setWeight( earWeight );
        this.attachedBodyParts.add( rightEar );

    }

    public void createAnimalHead( String name, String side, CreatureDataObject creatureData, Color color )
    {
        create( name, side, creatureData, color );
    }

    public void createHumanHead( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        muzzled = false;

        Color eyeColor = creatureData.getRandomEyeColor();
        double eyeSize = creatureData.getRandomEyeLength();
        double eyeWeight = creatureData.getRandomWeightFactor() * eyeSize;

        BodyPart leftEye = new BodyEye();
        leftEye.create( "eye", "left", creatureData, eyeColor );
        leftEye.setLength( eyeSize );
        leftEye.setWeight( eyeWeight );
        leftEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( leftEye );

        BodyPart rightEye = new BodyEye();
        rightEye.create( "eye", "right", creatureData, eyeColor );
        rightEye.setLength( eyeSize );
        rightEye.setWeight( eyeWeight );
        rightEye.setAboveBodyPart( this );
        this.attachedBodyParts.add( rightEye );

        double noseSize = creatureData.getRandomNoseLength();
        double noseWeight = creatureData.getRandomWeightFactor() * noseSize;

        BodyNose nose = new BodyNose();
        nose.create( "nose", "", creatureData, color );
        nose.setNoseShape( creatureData.getNoseShape());
        nose.setLength( noseSize );
        nose.setWeight( noseWeight );
        nose.setAboveBodyPart( this );
        this.attachedBodyParts.add( nose );

        double mouthSize = creatureData.getRandomMouthLength();
        double mouthWeight = creatureData.getRandomWeightFactor() * mouthSize;

        BodyMouth mouth = new BodyMouth();
        mouth.create( "mouth", "", creatureData, Color.RED );
        mouth.setLength( mouthSize );
        mouth.setWeight( mouthWeight );
        mouth.setMouthStyle( creatureData.getMouthStyle() );
        mouth.setTeethStyle( creatureData.getTeethStyle() );
        mouth.setNumOfTeeth( creatureData.getTeethCount() );
        mouth.setAboveBodyPart( this );this.attachedBodyParts.add( mouth );

        double earSize = creatureData.getRandomEarLength();
        double earWeight = creatureData.getRandomWeightFactor() * earSize;

        BodyEar leftEar = new BodyEar();
        leftEar.create( "ear", "left", creatureData, color );
        leftEar.setAboveBodyPart( this );
        leftEar.setLength( earSize );
        leftEar.setWeight( earWeight );
        leftEar.setEarShape( BodyPartEnums.EarShape.ROUND );
        this.attachedBodyParts.add( leftEar );

        BodyEar rightEar = new BodyEar();
        rightEar.create( "ear", "right", creatureData, color );
        rightEar.setLength( earSize );
        rightEar.setWeight( earWeight );
        rightEar.setAboveBodyPart( this );
        rightEar.setEarShape( BodyPartEnums.EarShape.ROUND );

        this.attachedBodyParts.add( rightEar );

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
            if (muzzled)
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
