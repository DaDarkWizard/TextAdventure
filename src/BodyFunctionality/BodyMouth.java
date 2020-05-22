package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyMouth extends BodyPart implements BodyPartInterface
{
    enum TeethStyle{NA, HUMAN, GNAWING, SHARP, FANGED, SHARPRAGGED, OTHER}
    enum MouthStyle{NA, FRONT, SMALL, WIDE, OTHER}

    protected TeethStyle teethStyle;
    protected MouthStyle mouthStyle;
    protected int numOfTeeth;

    public BodyMouth()
    {
        super();
        this.setType( BodyPartGenerator.BodyPartType.MOUTH );
        teethStyle = TeethStyle.SHARP;
        mouthStyle = MouthStyle.WIDE;
        numOfTeeth = 40;
    }

    public void setTeethStyle( TeethStyle teethStyle )
    {
        this.teethStyle = teethStyle;
    }

    public void setMouthStyle( MouthStyle mouthStyle )
    {
        this.mouthStyle = mouthStyle;
    }

    public void setNumOfTeeth( int numOfTeeth )
    {
        this.numOfTeeth = numOfTeeth;
    }

    public TeethStyle getTeethStyle()
    {
        return teethStyle;
    }

    public MouthStyle getMouthStyle()
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
    public BodyMouth create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        BodyMouth thisPart = (BodyMouth) super.create( name, side, animalType, color  );
        thisPart.setType( bodyPartType() );
        thisPart.setDescription( "A " + animalType.toString() + " " + name );

        BodyPart tongue = new BodyTongue();
        tongue.create( "tongue", "", animalType, Color.RED);
        tongue.setAboveBodyPart( thisPart );
        tongue.setTexture( BodyPartGenerator.Texture.SPECIAL );
        thisPart.attachedBodyParts.add( tongue );

        return thisPart;
    }

}
