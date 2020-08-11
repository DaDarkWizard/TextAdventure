package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;

public class BodyInternalStomach extends BodyPart
{
    int food, drink, maxFood, maxDrink;
    double scentStrength;

    //todo create field indicating favored/disfavored foods?

    /**
     * No argument constructor, sets default values
     */
    public BodyInternalStomach()
    {
        super();
        this.setBodyPartType( BodyPartEnums.BodyPartType.STOMACH );
        this.setTexture( BodyPartEnums.Texture.SPECIAL );
        food = 0;
        drink = 0;
        maxFood = 1;
        maxDrink = 1;
        scentStrength = 0.0;
    }

    /**
     * Constructor with a buffer containing the BodyInternalStomach data
     *
     * @param buffer ByteBuffer: The buffer containing the BodyInternalStomach data
     */
    public BodyInternalStomach( ByteBuffer buffer)
    {
        super(buffer);
        System.out.println( "In BodyInternalStomach(Buffer) constructor" );

        maxFood = buffer.getInt();
        System.out.println( "Setting maxFood to " + maxFood );

        food = buffer.getInt();
        System.out.println( "Setting food to " + food );

        maxDrink = buffer.getInt();
        System.out.println( "Setting maxDrink to " + maxDrink );

        drink = buffer.getInt();
        System.out.println( "Setting drink to " + drink );

        scentStrength = buffer.getDouble();
        System.out.println( "Setting scentStrength to " + scentStrength );

    }

    /**
     * Copy Constructor
     * @param oldPart BodyInternalStomach:  The original part to copy
     */
    public BodyInternalStomach(BodyInternalStomach oldPart)
    {
        super( oldPart );
        maxFood = oldPart.getMaxFood();
        maxDrink = oldPart.getMaxDrink();
        food = oldPart.getFood();
        drink = oldPart.getDrink();
        scentStrength = oldPart.getScentStrength();

    }

    public void setMaxFood( int maxFood )
    {
        this.maxFood = maxFood;
    }

    public void setMaxDrink( int maxDrink )
    {
        this.maxDrink = maxDrink;
    }

    public void setFood( int food )
    {
        this.food = food;
    }

    public void setDrink( int drink )
    {
        this.drink = drink;
    }

    public void setScentStrength( double scentStrength )
    {
        this.scentStrength = scentStrength;
    }

    public int getMaxFood()
    {
        return maxFood;
    }

    public int getMaxDrink()
    {
        return maxDrink;
    }

    public int getFood()
    {
        return food;
    }

    public int getDrink()
    {
        return drink;
    }

    public double getScentStrength()
    {
        return scentStrength;
    }

    @Override
    public BodyPartEnums.BodyPartType bodyPartType()
    {
        return BodyPartEnums.BodyPartType.STOMACH;
    }

    @Override
    public void create( String name, String side, CreatureDataObject creatureData, Color color )
    {
        super.create(name, side, creatureData, color);
        this.bodyPartType = bodyPartType();
        this.setTexture( BodyPartEnums.Texture.SPECIAL );


        maxFood = 1000;
        food = 1000;
        maxDrink = 1000;
        drink = 1000;
        scentStrength = 100;


        /* todo update when implemented
        maxFood = creatureData.getRandomMaxFood();
        food = maxFood;
        maxDrink = creatureData.getRandomMaxDrink();
        drink = maxDrink;
        scentStrength = creatureData.getRandomScentStrength();
        */

    }

    /**
     * Method that creates a ByteBuffer containing the BodyInternalStomach's data.
     *
     * @param buffer ByteBuffer: The ByteBuffer to add data to
     * @return boolean: true if there is enough room to add data, false otherwise
     */
    @Override
    public boolean bufferExtraFields(ByteBuffer buffer)
    {
        boolean isValid=false;
        if (buffer.limit()>buffer.position()+24)
        {

            buffer.putInt( maxFood );
            buffer.putInt( food );
            buffer.putInt( maxDrink );
            buffer.putInt( drink );
            buffer.putDouble( scentStrength );

            isValid = true;
        }
        return isValid;
    }








}
