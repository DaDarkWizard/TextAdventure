package Transformation;

import BodyFunctionality.Body;

public class TransformationCompare {

    public static TransformationDifferences compare(Body body1, Body body2)
    {

        return new TransformationDifferences(body1, body2);
    }
}
