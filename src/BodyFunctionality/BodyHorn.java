package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyHorn extends BodyPart
{

    protected BodyPartEnums.HornStyle hornStyle;

    public BodyHorn()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.HORN );
        hornStyle = BodyPartEnums.HornStyle.STRAIGHT;

    }

    /**
     * Constructor with a buffer containing the BodyHorn data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyHorn data
     */
    public BodyHorn( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyEar(Buffer) constructor" );

        hornStyle = BodyPartEnums.HornStyle.fromOrdinal( buffer.getInt());
        System.out.println( "Setting ear shape to " + hornStyle );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyHorn:  The original part to copy
     */
    public BodyHorn(BodyHorn oldPart)
    {
        super(oldPart);
        hornStyle = oldPart.getHornStyle();
    }

    public void setHornStyle( BodyPartEnums.HornStyle hornStyle )
    {
        this.hornStyle = hornStyle;
    }

    public BodyPartEnums.HornStyle getHornStyle()
    {
        return hornStyle;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.HORN;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.texture = BodyPartEnums.Texture.BONE;

    }

    /**
     * Method that creates a ByteBuffer containing the BodyEar's data.
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
            buffer.putInt( hornStyle.ordinal() );
            isValid = true;
        }
        return isValid;
    }


}
