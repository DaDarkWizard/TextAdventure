package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyMouth extends BodyPart
{


    protected BodyPartGenerator.TeethStyle teethStyle;
    protected BodyPartGenerator.MouthStyle mouthStyle;
    protected int numOfTeeth;

    public BodyMouth()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.MOUTH );
        teethStyle = BodyPartGenerator.TeethStyle.SHARP;
        mouthStyle = BodyPartGenerator.MouthStyle.WIDE;
        numOfTeeth = 40;
    }

    /**
     * Constructor with a buffer containing the BodyMouth data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyMouth data
     */
    public BodyMouth( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyMouth(Buffer) constructor" );

        teethStyle = BodyPartGenerator.TeethStyle.fromOrdinal( buffer.getInt());
        System.out.println( "Setting eye shape to " + teethStyle );

        mouthStyle = BodyPartGenerator.MouthStyle.fromOrdinal( buffer.getInt());
        System.out.println( "Setting eye shape to " + mouthStyle );

        numOfTeeth = buffer.getInt();
        System.out.println( "Setting sclera color to " + numOfTeeth );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyMouth:  The original part to copy
     */
    public BodyMouth(BodyMouth oldPart)
    {
        super(oldPart);
        teethStyle = oldPart.getTeethStyle();
        mouthStyle = oldPart.getMouthStyle();
        numOfTeeth = oldPart.getNumOfTeeth();
    }


    public void setTeethStyle( BodyPartGenerator.TeethStyle teethStyle )
    {
        this.teethStyle = teethStyle;
    }

    public void setMouthStyle( BodyPartGenerator.MouthStyle mouthStyle )
    {
        this.mouthStyle = mouthStyle;
    }

    public void setNumOfTeeth( int numOfTeeth )
    {
        this.numOfTeeth = numOfTeeth;
    }

    public BodyPartGenerator.TeethStyle getTeethStyle()
    {
        return teethStyle;
    }

    public BodyPartGenerator.MouthStyle getMouthStyle()
    {
        return mouthStyle;
    }

    public int getNumOfTeeth()
    {
        return numOfTeeth;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.MOUTH;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();

        double tongueLength = creatureData.getRandomTongueLength();
        double tongueWeight = creatureData.getRandomWeightFactor() * tongueLength;


        BodyPart tongue = new BodyTongue();
        tongue.create( "tongue", "", creatureData, Color.RED);
        tongue.setLength( tongueLength );
        tongue.setWeight( tongueWeight );
        tongue.setAboveBodyPart( this );
        tongue.setTexture( BodyPartGenerator.Texture.MUSCLE );
        this.attachedBodyParts.add( tongue );

    }

    /**
     * Method that creates a ByteBuffer containing the BodyEye's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+22)
        {
            buffer.putInt( teethStyle.ordinal() );
            System.out.println( "Buffering teeth style as " + teethStyle.ordinal() );

            buffer.putInt( mouthStyle.ordinal() );
            System.out.println( "Buffering mouth style as " + mouthStyle.ordinal() );

            buffer.putInt( numOfTeeth );
            System.out.println( "Buffering number of teeth as " + numOfTeeth );

            isValid = true;
        }
        return isValid;
    }

}
