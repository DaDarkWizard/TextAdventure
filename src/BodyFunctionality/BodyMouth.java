package BodyFunctionality;

import javafx.scene.paint.Color;

public class BodyMouth extends BodyPart
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
    public void create( String name, String side, BodyPartGenerator.AnimalType animalType, Color color )
    {
        super.create(name, side, animalType, color);
        this.type = bodyPartType();

        BodyPart tongue = new BodyTongue();
        tongue.create( "tongue", "", animalType, Color.RED);
        tongue.setAboveBodyPart( this );
        tongue.setTexture( BodyPartGenerator.Texture.SPECIAL );
        this.attachedBodyParts.add( tongue );

    }

}
