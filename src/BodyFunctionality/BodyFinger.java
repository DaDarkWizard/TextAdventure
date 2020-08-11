package BodyFunctionality;

import javafx.scene.paint.Color;
import java.nio.ByteBuffer;

public class BodyFinger extends BodyPart
{
    double nailLength;
    BodyPartEnums.Texture nailTexture;
    Color nailColor;

    public BodyFinger()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.FINGER );
        nailLength = 0.1;
        nailTexture = BodyPartEnums.Texture.NAIL;
        nailColor = Color.WHITE;
    }

    /**
     * Constructor with a buffer containing the BodyFinger data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyFinger data
     */
    public BodyFinger( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyFinger(Buffer) constructor" );

        nailLength = buffer.getDouble();
        System.out.println( "Setting fingernail length to " + nailLength );

        nailTexture = BodyPartEnums.Texture.fromOrdinal( buffer.getInt());
        System.out.println( "Setting nail texture to " + nailTexture );

        nailColor = ByteBufferIO.getColor( buffer );
        System.out.println( "Setting color to " + nailColor );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyFinger:  The original part to copy
     */
    public BodyFinger(BodyFinger oldPart)
    {
        super( oldPart );
        nailLength = oldPart.getNailLength();
        nailTexture = oldPart.getNailTexture();
        nailColor = oldPart.getNailColor();
    }

    public void setNailLength( double nailLength )
    {
        this.nailLength = nailLength;
    }

    public void setNailTexture( BodyPartEnums.Texture nailTexture )
    {
        this.nailTexture = nailTexture;
    }

    public void setNailColor( Color nailColor )
    {
        this.nailColor = nailColor;
    }

    public double getNailLength()
    {
        return nailLength;
    }

    public BodyPartEnums.Texture getNailTexture()
    {
        return nailTexture;
    }

    public Color getNailColor()
    {
        return nailColor;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.FINGER;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        nailLength = 0.1;
        nailTexture = creatureData.getNailStyle();
        nailColor = Color.WHITE;

    }

    /**
     * Method that creates a ByteBuffer containing the BodyFinger's data.
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

            buffer.putDouble( nailLength );
            buffer.putInt( nailTexture.ordinal() );
            ByteBufferIO.putColor( buffer, nailColor );

            isValid = true;
        }
        return isValid;
    }


}
