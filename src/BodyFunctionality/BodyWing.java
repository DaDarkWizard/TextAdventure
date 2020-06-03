package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyWing extends BodyPart
{


    protected BodyPartGenerator.WingType wingType;

    public BodyWing()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.WING );
        wingType = BodyPartGenerator.WingType.BIRD;
    }

    /**
     * Constructor with a buffer containing the BodyWing data including it's children
     *
     * @param buffer ByteBuffer: The buffer containing the BodyWing data
     */
    public BodyWing( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyWing(Buffer) constructor" );

        wingType = BodyPartGenerator.WingType.fromOrdinal( buffer.getInt());
        System.out.println( "Setting wing type to " + wingType );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyWing:  The original part to copy
     */
    public BodyWing(BodyWing oldPart)
    {
        super(oldPart);
        wingType = oldPart.getWingType();
    }



    public void setWingType( BodyPartGenerator.WingType wingType )
    {
        this.wingType = wingType;
    }

    public BodyPartGenerator.WingType getWingType()
    {
        return wingType;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.WING;
    }


    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.texture = creatureData.getBodyTexture();

    }

    /**
     * Method that creates a ByteBuffer containing the BodyWing's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields( ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+2)
        {
            buffer.putInt( wingType.ordinal() );
            isValid = true;
        }
        return isValid;
    }

}
