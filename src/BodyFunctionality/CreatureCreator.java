package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CreatureCreator
{

    public static Body createAverageHuman()
    {
        Human human = new Human();
        Body body = human.generateRandomBody();
        return body;
    }

}
