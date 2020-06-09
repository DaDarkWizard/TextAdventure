package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyInternalGland extends BodyPart
{
    int maxMaterial, material;
    BodyPartGenerator.GlandType glandType;      // indicates how substance is spread (i.e. injection, breath stream, air/gas
    //todo field needed for material type?

    /**
     * No argument constructor, sets default values
     */
    public BodyInternalGland()
    {
        super();
        this.setBodyPartType( BodyPartGenerator.BodyPartType.GLAND );
        this.setTexture( BodyPartGenerator.Texture.SPECIAL );

        maxMaterial = 1;
        material = 0;

    }

    /**
     * Constructor with a buffer containing the BodyInternalGland data
     *
     * @param buffer ByteBuffer: The buffer containing the BodyInternalGland data
     */
    public BodyInternalGland( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyInternalGland(Buffer) constructor" );

        maxMaterial = buffer.getInt();
        System.out.println( "Setting maxMaterial to " + maxMaterial );

        material = buffer.getInt();
        System.out.println( "Setting material to " + material );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyInternalGland:  The original part to copy
     */
    public BodyInternalGland(BodyInternalGland oldPart)
    {
        super( oldPart );
        maxMaterial = oldPart.getMaxMaterial();
        material = oldPart.getMaterial();

    }

    public void setMaxMaterial( int maxMaterial )
    {
        this.maxMaterial = maxMaterial;
    }

    public void setMaterial( int material )
    {
        this.material = material;
    }

    public int getMaxMaterial()
    {
        return maxMaterial;
    }

    public int getMaterial()
    {
        return material;
    }

    @Override
    public BodyPartGenerator.BodyPartType bodyPartType()
    {
        return BodyPartGenerator.BodyPartType.GLAND;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.setTexture( BodyPartGenerator.Texture.SPECIAL );


        maxMaterial = 100;
        material = 100;

        /* todo update when implemented
        maxMaterial = creatureData.getRandomMaxMaterial();
        material = maxMaterial;
        */

    }

    /**
     * Method that creates a ByteBuffer containing the BodyInternalGland's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+8)
        {

            buffer.putInt( maxMaterial );
            buffer.putInt( material );

            isValid = true;
        }
        return isValid;
    }


}
