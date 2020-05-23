package BodyFunctionality;

import static BodyFunctionality.CreatureCreator.createAverageHuman;

public class TestBody
{
    public static void main(String[] args)
    {
        Body body = createAverageHuman();
        System.out.println( body.toString() );
        System.out.println( "Total body parts: " + body.countParts() );
    }

}
