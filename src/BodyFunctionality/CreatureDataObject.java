package BodyFunctionality;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CreatureDataObject implements CreatureCreationInterface
{

    protected BodyPartGenerator.AnimalType animalTypeStyle;
    protected String animalName;

    protected double defaultArmLength, defaultBackLength, defaultEarLength, defaultEyeLength, defaultFingerLength,
                    defaultHandLength, defaultHeadLength, defaultHornLength, defaultMouthLength, defaultMuzzleLength,
                    defaultNailLength, defaultNeckLength, defaultNoseLength, defaultTailLength, defaultTongueLength,
                    defaultWingLength, defaultBodyLength;

    protected boolean opposable, horned, muzzled, tailed, winged;

    protected BodyPartGenerator.Texture bodyTextureStyle;
    protected BodyPartGenerator.EarShape earShape;
    protected BodyPartGenerator.HornStyle hornStyle;
    protected BodyPartGenerator.LimbType limbStyle;
    protected BodyPartGenerator.MouthStyle mouthStyle;
    protected BodyPartGenerator.Texture nailStyle;
    protected BodyPartGenerator.NoseShape noseShape;
    protected BodyPartGenerator.TailType tailType;
    protected BodyPartGenerator.TeethStyle teethStyle;

    protected int fingerCount, toeCount, teethCount;

    protected int defaultMaxHealth;

    protected double defaultLengthVariance = .25;
    protected double defaultWeightFactor = .33;

    Random randomNumbers = new Random();

    //todo update field types later when implemented
    protected ArrayList<Color> possibleBodyColors, possibleEyeColors;
    protected ArrayList<String> bodyFeatures;
    protected ArrayList<String> inventory;
    protected ArrayList<String> bodyResistances;
    protected ArrayList<String> bodySkills;

    protected ArrayList<String> killDrops;

    /**
     * No argument constructor, sets empty values
     */
    public CreatureDataObject()
    {
        setBlankValues();

    }

    /**
     * Constructor given a file path - loads data from file given by filepath
     * @param filepath String: The file path to the creature data
     */
    public CreatureDataObject(String filepath)
    {
        File creatureFile = new File(filepath);
        try
        {
            Scanner data = new Scanner(creatureFile);

            animalTypeStyle = BodyPartGenerator.AnimalType.valueOf( data.nextLine() );
            animalName = data.nextLine();
            defaultArmLength = data.nextDouble();
            defaultBackLength = data.nextDouble();
            defaultEarLength = data.nextDouble();
            defaultEyeLength = data.nextDouble();
            defaultFingerLength = data.nextDouble();
            defaultHandLength = data.nextDouble();
            defaultHeadLength = data.nextDouble();
            defaultHornLength = data.nextDouble();
            defaultMouthLength = data.nextDouble();
            defaultMuzzleLength = data.nextDouble();
            defaultNailLength = data.nextDouble();
            defaultNeckLength = data.nextDouble();
            defaultNoseLength = data.nextDouble();
            defaultTailLength = data.nextDouble();
            defaultTongueLength = data.nextDouble();
            defaultWingLength = data.nextDouble();

            defaultBodyLength = data.nextDouble();

            opposable = data.nextBoolean();
            horned = data.nextBoolean();
            muzzled = data.nextBoolean();
            tailed = data.nextBoolean();
            winged = data.nextBoolean();

            data.nextLine();            //needed to eat line feed

            bodyTextureStyle = BodyPartGenerator.Texture.valueOf( data.nextLine() );
            earShape = BodyPartGenerator.EarShape.valueOf( data.nextLine() );
            hornStyle = BodyPartGenerator.HornStyle.valueOf( data.nextLine() );
            limbStyle = BodyPartGenerator.LimbType.valueOf( data.nextLine() );
            mouthStyle = BodyPartGenerator.MouthStyle.valueOf( data.nextLine() );
            nailStyle = BodyPartGenerator.Texture.valueOf( data.nextLine() );
            noseShape = BodyPartGenerator.NoseShape.valueOf( data.nextLine() );
            tailType = BodyPartGenerator.TailType.valueOf( data.nextLine() );
            teethStyle = BodyPartGenerator.TeethStyle.valueOf( data.nextLine() );

            fingerCount = data.nextInt();
            toeCount = data.nextInt();
            teethCount = data.nextInt();
            defaultMaxHealth = data.nextInt();

            int count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            possibleBodyColors = new ArrayList<Color>(  );
            for (int i = 0; i < count; i++)
            {
                possibleBodyColors.add(Color.valueOf( data.nextLine() ));
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            possibleEyeColors = new ArrayList<Color>(  );
            for (int i = 0; i < count; i++)
            {
                possibleEyeColors.add(Color.valueOf( data.nextLine() ));
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            bodyFeatures = new ArrayList<String>( ); //todo update Class types when implemented
            for (int i = 0; i < count; i++)
            {
                bodyFeatures.add( data.nextLine() );
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            inventory = new ArrayList<String>( );
            for (int i = 0; i < count; i++)
            {
                inventory.add( data.nextLine() );
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            bodyResistances = new ArrayList<String>( );
            for (int i = 0; i < count; i++)
            {
                bodyResistances.add( data.nextLine() );
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            bodySkills = new ArrayList<String>( );
            for (int i = 0; i < count; i++)
            {
                bodySkills.add( data.nextLine() );
            }

            count = data.nextInt();
            data.nextLine();            //needed to eat line feed

            killDrops = new ArrayList<String>( );
            for (int i = 0; i < count; i++)
            {
                killDrops.add( data.nextLine() );
            }

            System.out.println( "Loaded character data for " + animalTypeStyle.toString() );
        }
        catch (FileNotFoundException e)
        {

            System.out.println( "File " + filepath + " not found, loading default values." );
            setBlankValues();
        }
        catch (IllegalArgumentException  e)
        {
            System.out.println( e.toString() );
            setBlankValues();
        }


    }

    protected void setBlankValues()
    {
        animalTypeStyle = BodyPartGenerator.AnimalType.NA;
        animalName="";

        defaultArmLength = 0.0;
        defaultBackLength = 0.0;
        defaultEarLength = 0.0;
        defaultEyeLength = 0.0;
        defaultFingerLength = 0.0;
        defaultHandLength = 0.0;
        defaultHeadLength = 0.0;
        defaultHornLength = 0.0;
        defaultMouthLength = 0.0;
        defaultMuzzleLength = 0.0;
        defaultNailLength = 0.0;
        defaultNeckLength = 0.0;
        defaultNoseLength = 0.0;
        defaultTailLength = 0.0;
        defaultTongueLength = 0.0;
        defaultWingLength = 0.0;

        defaultBodyLength = 0.0;

        opposable = false;
        horned = false;
        muzzled = false;
        tailed = false;
        winged = false;

        bodyTextureStyle = BodyPartGenerator.Texture.NA;
        earShape = BodyPartGenerator.EarShape.NA;
        hornStyle = BodyPartGenerator.HornStyle.NA;
        limbStyle = BodyPartGenerator.LimbType.NA;
        mouthStyle = BodyPartGenerator.MouthStyle.NA;
        nailStyle = BodyPartGenerator.Texture.NA;
        noseShape = BodyPartGenerator.NoseShape.NA;
        tailType = BodyPartGenerator.TailType.NA;
        teethStyle = BodyPartGenerator.TeethStyle.NA;

        fingerCount = 0;
        toeCount = 0;
        teethCount = 0;
        defaultMaxHealth = 0;


        possibleBodyColors = new ArrayList<Color>(  );
        possibleEyeColors = new ArrayList<Color>(  );
        bodyFeatures = new ArrayList<String>( ); //todo update Class types when implemented
        inventory = new ArrayList<String>( );
        bodyResistances = new ArrayList<String>( );
        bodySkills = new ArrayList<String>( );
        killDrops = new ArrayList<String>( );
    }

    /**
     * Copy Constructor to make a copy of the data object so loaded object isn't changed
     * @param oldObject CreatureDataObject: The object to make copies of
     */
    public CreatureDataObject(CreatureDataObject oldObject)
    {
        animalTypeStyle = oldObject.getAnimalTypeStyle();
        animalName = oldObject.getAnimalName();

        defaultArmLength = oldObject.getAverageArmLength();
        defaultBackLength = oldObject.getAverageBackLength();
        defaultEarLength = oldObject.getAverageEarLength();
        defaultEyeLength = oldObject.getAverageEyeLength();
        defaultFingerLength = oldObject.getAverageFingerLength();
        defaultHandLength = oldObject.getAverageHandLength();
        defaultHeadLength = oldObject.getAverageHeadLength();
        defaultHornLength = oldObject.getAverageHornLength();
        defaultMouthLength = oldObject.getAverageMouthLength();
        defaultMuzzleLength = oldObject.getAverageMuzzleLength();
        defaultNailLength = oldObject.getAverageNailLength();
        defaultNeckLength = oldObject.getAverageNeckLength();
        defaultNoseLength = oldObject.getAverageNoseLength();
        defaultTailLength = oldObject.getAverageTailLength();
        defaultTongueLength = oldObject.getAverageTongueLength();
        defaultWingLength = oldObject.getAverageWingLength();
        defaultBodyLength = oldObject.getAverageBodyLength();

        opposable = oldObject.isOpposable();
        horned = oldObject.isHorned();
        muzzled = oldObject.isMuzzled();
        tailed = oldObject.isTailed();
        winged = oldObject.isWinged();

        bodyTextureStyle = oldObject.getBodyTexture();
        earShape = oldObject.getEarShape();
        hornStyle = oldObject.getHornStyle();
        limbStyle = oldObject.getLimbStyle();
        mouthStyle = oldObject.getMouthStyle();
        nailStyle = oldObject.getNailStyle();
        noseShape = oldObject.getNoseShape();
        tailType = oldObject.getTailType();
        teethStyle = oldObject.getTeethStyle();

        fingerCount = oldObject.getFingerCount();
        toeCount = oldObject.getToeCount();
        teethCount = oldObject.getTeethCount();
        defaultMaxHealth = oldObject.getAverageMaxHealth();

        //todo update Class types and make deep copies when implemented
        possibleBodyColors = new ArrayList<Color>( oldObject.getBodyColors() );
        possibleEyeColors = new ArrayList<Color>( oldObject.getEyeColors() );
        bodyFeatures = new ArrayList<String>( oldObject.getBodyFeatures());
        inventory = new ArrayList<String>( oldObject.getInventory() );
        bodyResistances = new ArrayList<String>( oldObject.getBodyResistances());
        bodySkills = new ArrayList<String>( oldObject.getBodySkills());
        killDrops = new ArrayList<String>( oldObject.randomItemDrop());
    }



    public void setAnimalTypeStyle( BodyPartGenerator.AnimalType animalTypeStyle )
    {
        this.animalTypeStyle = animalTypeStyle;
    }

    public void setAnimalName( String animalName )
    {
        this.animalName = animalName;
    }

    public void setBodyFeatures( ArrayList<String> bodyFeatures )
    {
        this.bodyFeatures = bodyFeatures;
    }

    public void setBodyResistances( ArrayList<String> bodyResistances )
    {
        this.bodyResistances = bodyResistances;
    }

    public void setBodySkills( ArrayList<String> bodySkills )
    {
        this.bodySkills = bodySkills;
    }

    public void setDefaultArmLength( double defaultArmLength )
    {
        this.defaultArmLength = defaultArmLength;
    }

    public void setDefaultBackLength( double defaultBackLength )
    {
        this.defaultBackLength = defaultBackLength;
    }

    public void setDefaultBodyLength( double defaultBodyLength )
    {
        this.defaultBodyLength = defaultBodyLength;
    }

    public void setDefaultEarLength( double defaultEarLength )
    {
        this.defaultEarLength = defaultEarLength;
    }

    public void setDefaultEyeLength( double defaultEyeLength )
    {
        this.defaultEyeLength = defaultEyeLength;
    }

    public void setDefaultFingerLength( double defaultFingerLength )
    {
        this.defaultFingerLength = defaultFingerLength;
    }

    public void setDefaultHandLength( double defaultHandLength )
    {
        this.defaultHandLength = defaultHandLength;
    }

    public void setDefaultHeadLength( double defaultHandLength )
    {
        this.defaultHandLength = defaultHandLength;
    }

    public void setDefaultHornLength( double defaultHornLength )
    {
        this.defaultHornLength = defaultHornLength;
    }


    public void setDefaultMaxHealth( int defaultMaxHealth )
    {
        this.defaultMaxHealth = defaultMaxHealth;
    }

    public void setDefaultMouthLength( double defaultMouthLength )
    {
        this.defaultMouthLength = defaultMouthLength;
    }

    public void setDefaultMuzzleLength( double defaultMuzzleLength )
    {
        this.defaultMuzzleLength = defaultMuzzleLength;
    }

    public void setDefaultNailLength( double defaultNailLength )
    {
        this.defaultNailLength = defaultNailLength;
    }

    public void setDefaultNeckLength( double defaultNeckLength )
    {
        this.defaultNeckLength = defaultNeckLength;
    }

    public void setDefaultNoseLength( double defaultNoseLength )
    {
        this.defaultNoseLength = defaultNoseLength;
    }

    public void setDefaultTailLength( double defaultTailLength )
    {
        this.defaultTailLength = defaultTailLength;
    }

    public void setDefaultTongueLength( double defaultTongueLength )
    {
        this.defaultTongueLength = defaultTongueLength;
    }

    public void setDefaultWingLength( double defaultWingLength )
    {
        this.defaultWingLength = defaultWingLength;
    }

    public void setEarShape( BodyPartGenerator.EarShape earShape )
    {
        this.earShape = earShape;
    }

    public void setInventory( ArrayList<String> inventory )
    {
        this.inventory = inventory;
    }

    public void setNoseShape( BodyPartGenerator.NoseShape noseShape )
    {
        this.noseShape = noseShape;
    }

    public void setBodyColors( ArrayList<Color> possibleBodyColors ) //todo make arrayList deep copies
    {
        this.possibleBodyColors = possibleBodyColors;
    }

    public void setEyeColors( ArrayList<Color> possibleEyeColors )
    {
        this.possibleEyeColors = possibleEyeColors;
    }

    public void setHorned( boolean horned )
    {
        this.horned = horned;
    }

    public void setMuzzled( boolean muzzled )
    {
        this.muzzled = muzzled;
    }

    public void setOpposable( boolean opposable )
    {
        this.opposable = opposable;
    }

    public void setTailed( boolean tailed )
    {
        this.tailed = tailed;
    }

    public void setTailType( BodyPartGenerator.TailType tailType )
    {
        this.tailType = tailType;
    }

    public void setWinged( boolean winged )
    {
        this.winged = winged;
    }

    public void setBodyTextureStyle( BodyPartGenerator.Texture bodyTextureStyle )
    {
        this.bodyTextureStyle = bodyTextureStyle;
    }

    public void setHornStyle( BodyPartGenerator.HornStyle hornStyle )
    {
        this.hornStyle = hornStyle;
    }

    public void setLimbStyle( BodyPartGenerator.LimbType limbStyle )
    {
        this.limbStyle = limbStyle;
    }

    public void setMouthStyle( BodyPartGenerator.MouthStyle mouthStyle )
    {
        this.mouthStyle = mouthStyle;
    }

    public void setNailStyle( BodyPartGenerator.Texture nailStyle )
    {
        this.nailStyle = nailStyle;
    }

    public void setTeethStyle( BodyPartGenerator.TeethStyle teethStyle )
    {
        this.teethStyle = teethStyle;
    }

    public void setFingerCount( int fingerCount )
    {
        this.fingerCount = fingerCount;
    }

    public void setToeCount( int toeCount )
    {
        this.toeCount = toeCount;
    }

    public void setTeethCount( int teethCount )
    {
        this.teethCount = teethCount;
    }

    public void setDefaultLengthVariance( double defaultLengthVariance )
    {
        this.defaultLengthVariance = defaultLengthVariance;
    }

    public void setDefaultWeightFactor( double defaultWeightFactor )
    {
        this.defaultWeightFactor = defaultWeightFactor;
    }


    public BodyPartGenerator.AnimalType getAnimalTypeStyle()
    {
        return animalTypeStyle;
    }

    public String getAnimalName()
    {
        return animalName;
    }

    // values for arm and leg length
    public double getMinArmLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultArmLength;
    }

    public double getMaxArmLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultArmLength;
    }

    public double getAverageArmLength()
    {
        return defaultArmLength;
    }
    public double getRandomArmLength()
    {
        return randomFactor() * defaultArmLength;
    }



    // values for back and chest
    public double getMinBackLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultBackLength;
    }

    public double getMaxBackLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultBackLength;
    }

    public double getAverageBackLength()
    {
        return defaultBackLength;
    }

    public double getRandomBackLength()
    {
        return randomFactor() * defaultBackLength;
    }


    public BodyPartGenerator.Texture getBodyTexture()
    {
        return bodyTextureStyle;
    }



    public double getMinEarLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultEarLength;
    }

    public double getMaxEarLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultEarLength;
    }

    public double getAverageEarLength()
    {
        return defaultEarLength;
    }
    public double getRandomEarLength()
    {
        return randomFactor() * defaultEarLength;
    }

    public BodyPartGenerator.EarShape getEarShape()
    {
        return earShape;
    }


    public double getMinEyeLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultEyeLength;
    }

    public double getMaxEyeLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultEyeLength;
    }

    public double getAverageEyeLength()
    {
        return defaultEyeLength;
    }
    public double getRandomEyeLength()
    {
        return randomFactor() * defaultEyeLength;
    }

    public double getMinFingerLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultFingerLength;
    }

    public double getMaxFingerLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultFingerLength;
    }

    public double getAverageFingerLength()
    {
        return defaultFingerLength;
    }

    public double getRandomFingerLength()
    {
        return randomFactor() * defaultFingerLength;
    }

    public int getFingerCount()
    {
        return fingerCount;
    }

    public int getToeCount()
    {
        return toeCount;
    }
    public boolean isOpposable()
    {
        return opposable;
    }

    public double getMinHandLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultHandLength;
    }

    public double getMaxHandLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultHandLength;
    }

    public double getAverageHandLength()
    {
        return defaultHandLength;
    }

    public double getRandomHandLength()
    {
        return randomFactor() * defaultHandLength;
    }

    public double getMinHeadLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultHeadLength;
    }

    public double getMaxHeadLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultHeadLength;
    }

    public double getAverageHeadLength()
    {
        return defaultHeadLength;
    }

    public double getRandomHeadLength()
    {
        return randomFactor() * defaultHeadLength;
    }



    public double getMinHornLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultHornLength;
    }

    public double getMaxHornLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultHornLength;
    }

    public double getAverageHornLength()
    {
        return defaultHornLength;
    }

    public double getRandomHornLength()
    {
        return randomFactor() * defaultHornLength;
    }


    public boolean isHorned()
    {
        return horned;
    }

    public BodyPartGenerator.HornStyle getHornStyle()
    {
        return hornStyle;
    }

    public BodyPartGenerator.LimbType getLimbStyle()
    {
        return limbStyle;
    }

    public double getMinMouthLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultMouthLength;
    }

    public double getMaxMouthLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultMouthLength;
    }

    public double getAverageMouthLength()
    {
        return defaultMouthLength;
    }

    public double getRandomMouthLength()
    {
        return randomFactor() * defaultMouthLength;
    }

    public BodyPartGenerator.MouthStyle getMouthStyle()
    {
        return mouthStyle;
    }

    public BodyPartGenerator.TeethStyle getTeethStyle()
    {
        return teethStyle;
    }

    public int getTeethCount()
    {
        return teethCount;
    }


    /**
     * Method to return a random gender (MALE = 1, FEMALE = 2)  todo update if some creatures have no gender
     * @return BodyPartGenerator.Gender: a Gender object
     */
    public BodyPartGenerator.Gender getRandomGender()
    {
        return BodyPartGenerator.Gender.fromOrdinal( randomNumbers.nextInt(2) + 1 );
    }


    public double getMinMuzzleLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultMuzzleLength;
    }

    public double getMaxMuzzleLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultMuzzleLength;
    }

    public double getAverageMuzzleLength()
    {
        return defaultMuzzleLength;
    }

    public double getRandomMuzzleLength()
    {
        return randomFactor() * defaultMuzzleLength;
    }

    public boolean isMuzzled()
    {
        return muzzled;
    }


    public double getMinNailLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultNailLength;
    }

    public double getMaxNailLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultNailLength;
    }

    public double getAverageNailLength()
    {
        return defaultNailLength;
    }

    public double getRandomNailLength()
    {
        return randomFactor() * defaultNailLength;
    }

    public BodyPartGenerator.Texture getNailStyle()
    {
        return nailStyle;
    }

    public double getMinNeckLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultNeckLength;
    }

    public double getMaxNeckLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultNeckLength;
    }

    public double getAverageNeckLength()
    {
        return defaultNeckLength;
    }

    public double getRandomNeckLength()
    {
        return randomFactor() * defaultNeckLength;
    }


    public double getMinNoseLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultNoseLength;
    }

    public double getMaxNoseLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultNoseLength;
    }

    public double getAverageNoseLength()
    {
        return defaultNoseLength;
    }

    public double getRandomNoseLength()
    {
        return randomFactor() * defaultNoseLength;
    }

    public BodyPartGenerator.NoseShape getNoseShape()
    {
        return noseShape;
    }




    public double getMinTailLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultTailLength;
    }

    public double getMaxTailLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultTailLength;
    }

    public double getAverageTailLength()
    {
        return defaultTailLength;
    }

    public double getRandomTailLength()
    {
        return randomFactor() * defaultTailLength;
    }


    public boolean isTailed()
    {
        return tailed;
    }

    /**
     * Method to generate a random number between from (1-weightFactor) to (1+weightFactor)
     * @return double: A random value from (1-weightFactor) to (1+weightFactor)
     */
    protected double randomFactor()
    {
        double value = 1 - defaultLengthVariance;
        value += 2 * defaultLengthVariance * randomNumbers.nextDouble();

        return value;
    }

    public BodyPartGenerator.TailType getTailType()
    {
        return tailType;
    }

    // values for weight factor (body parts have weight of length * weightfactor)
    public double getMinWeightFactor()
    {
        return ( 1 - defaultLengthVariance ) * defaultWeightFactor;
    }

    public double getMaxWeightFactor()
    {
        return ( 1 + defaultLengthVariance ) * defaultWeightFactor;
    }

    public double getAverageWeightFactor()
    {
        return defaultWeightFactor;
    }
    public double getRandomWeightFactor()
    {
        return randomFactor() * defaultWeightFactor;
    }



    public double getMinTongueLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultTongueLength;
    }

    public double getMaxTongueLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultTongueLength;
    }

    public double getAverageTongueLength()
    {
        return defaultTongueLength;
    }

    public double getRandomTongueLength()
    {
        return randomFactor() * defaultTongueLength;
    }


    public double getMinWingLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultWingLength;
    }

    public double getMaxWingLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultWingLength;
    }

    public double getAverageWingLength()
    {
        return defaultWingLength;
    }

    public double getRandomWingLength()
    {
        return randomFactor() * defaultWingLength;
    }

    public boolean isWinged()
    {
        return winged;
    }



    public double getMinBodyLength()
    {
        return ( 1 - defaultLengthVariance ) * defaultBodyLength;
    }

    public double getMaxBodyLength()
    {
        return ( 1 + defaultLengthVariance ) * defaultBodyLength;
    }

    public double getAverageBodyLength()
    {
        return defaultBodyLength;
    }

    public double getRandomBodyLength()
    {
        return randomFactor() * defaultBodyLength;
    }



    public int getMinMaxHealth()
    {
        return (int) (( 1 - defaultLengthVariance ) * defaultMaxHealth);
    }

    public int getMaxMaxHealth()
    {
        return (int) ((1 + defaultLengthVariance ) * defaultMaxHealth);
    }

    public int getAverageMaxHealth()
    {
        return defaultMaxHealth;
    }

    public int getRandomMaxHealth()
    {
        return (int) (randomFactor() * defaultMaxHealth);
    }


    /**
     * Method to return a copy of the color list
     * @return ArrayList<Color>: A copy of the list of possible colors for the creature type
     */
    public ArrayList<Color> getBodyColors()
    {
        ArrayList<Color> newColorList =  new ArrayList<Color>(  );

        for (int i=0; possibleBodyColors!=null && i<possibleBodyColors.size(); i++)
        {
            newColorList.add(possibleBodyColors.get(i));
        }
        return newColorList;
    }

    /**
     * Method to return a random color from the possibleBodyColors list
     * @return Color: A color from the possibleBodyColors list
     */
    public Color getRandomColor()
    {
        return possibleBodyColors.get( randomNumbers.nextInt(possibleBodyColors.size()) );
    }

    /**
     * Method to return a copy of the features list //todo update with correct type when implemented
     * @return ArrayList<String>: A copy of the list of body features for the creature type
     */
    public ArrayList<String> getBodyFeatures()
    {
        ArrayList<String> newFeatureList =  new ArrayList<String>(  );

        for (int i=0; bodyFeatures!=null && i<bodyFeatures.size(); i++)
        {
            newFeatureList.add(bodyFeatures.get(i));
        }
        return newFeatureList;
    }


    /**
     * Method to return a copy of the inventory list //todo update with correct type when implemented
     * @return ArrayList<String>: A copy of the list of the starting inventory for the creature type
     */
    public ArrayList<String> getInventory()
    {
        ArrayList<String> newInventoryList =  new ArrayList<String>(  );

        for (int i=0; inventory!=null && i<inventory.size(); i++)
        {
            newInventoryList.add(inventory.get(i));
        }
        return newInventoryList;
    }

    /**
     * Method to return a copy of the inventory list //todo update with correct type when implemented
     * @return ArrayList<String>: A copy of the list of the starting inventory for the creature type
     */
    public ArrayList<String> getBodyResistances()
    {
        ArrayList<String> newResistancesList =  new ArrayList<String>(  );

        for (int i=0; bodyResistances!=null && i<bodyResistances.size(); i++)
        {
            newResistancesList.add(bodyResistances.get(i));
        }
        return newResistancesList;
    }

    /**
     * Method to return a copy of the inventory list //todo update with correct type when implemented
     * @return ArrayList<String>: A copy of the list of the starting inventory for the creature type
     */
    public ArrayList<String> getBodySkills()
    {
        ArrayList<String> newSkillsList =  new ArrayList<String>(  );

        for (int i=0; bodySkills!=null && i<bodySkills.size(); i++)
        {
            newSkillsList.add(bodySkills.get(i));
        }
        return newSkillsList;
    }


    /**
     * Method to return a copy of the eye color list
     * @return ArrayList<Color>: A copy of the list of possible eye colors for the creature type
     */
    public ArrayList<Color> getEyeColors()
    {
        ArrayList<Color> newColorList =  new ArrayList<Color>(  );

        for (int i=0; possibleEyeColors!=null && i<possibleEyeColors.size(); i++)
        {
            newColorList.add(possibleEyeColors.get(i));
        }
        return newColorList;
    }

    /**
     * Method to return a random eye color from the possibleEyeColors list
     * @return Color: A color from the possibleEyeColors list
     */
    public Color getRandomEyeColor()
    {
        return possibleEyeColors.get( randomNumbers.nextInt(possibleEyeColors.size()) );
    }


    // drops random items when a creature is killed
    public ArrayList<String> randomItemDrop()
    {
        ArrayList<String> newDropList =  new ArrayList<String>(  );

        for (int i=0; killDrops!=null && i<killDrops.size(); i++)
        {
            newDropList.add(killDrops.get(i));
        }
        return newDropList;
    }


    /* this method makes all the body adjustments that cannot be made by other methods
     (i.e. changes unique features, adjusts color patterns, gives unique skills, etc)
     it should be called after the body is updated
    */
    public void bodyAdjustments(Body body)
    {
        CreatureSpecial.createBody( animalTypeStyle, body );
    }

    public Body generateRandomBody()
    {
        Body newBody = new Body();


        // create the basic body

        Color color = getRandomColor();
        BodyPart neck, back, chest;

        neck = new BodyNeck();
        neck.create( "neck", "", this, color );
        neck.setAboveBodyPart( newBody );

        chest = new BodyChest();
        chest.create( "chest", "", this, color );
        chest.setAboveBodyPart( newBody );

        back = new BodyBack();
        back.create( "back", "", this, color );
        back.setAboveBodyPart( newBody );

        neck.setAllBody( newBody );
        chest.setAllBody( newBody );
        back.setAllBody( newBody );

        // todo modifying ArrayList of attached body parts here - think about shallow/deep copy usage
        newBody.getAttachedBodyParts().add( neck );
        newBody.getAttachedBodyParts().add( chest );
        newBody.getAttachedBodyParts().add( back );

        // todo update creation method to either update body here, or during body part creation
        //  update the body
        newBody.setFirstName( animalName + Integer.toString( newBody.getIdentifier() ) );
        newBody.setColor( Color.PEACHPUFF );
        newBody.setTexture( BodyPartGenerator.Texture.SKIN );
        newBody.setAnimalType( BodyPartGenerator.AnimalType.HUMAN );
        newBody.setGender( getRandomGender() );
        newBody.setDescription( "A " + newBody.getGender().toString().toLowerCase() + " " + animalName);
        newBody.setLimbType( limbStyle );
        newBody.setLength( getRandomBodyLength() );

        int maximumHealth = getRandomMaxHealth();
        newBody.setMaxHealth( maximumHealth );
        newBody.setHealth( maximumHealth );

        setBodyLengthsAndWeights(newBody);


        bodyAdjustments( newBody ); // adds special body adjustments, i.e. head hair

        return newBody;

    }

    /**
     * Method to set certain body part lengths and widths to random by symmetric values
     * body parts needing to be adjusted evenly are arms, hands, fingers, and legs
     *
     *
     * @param body Body: The body to adjust, setting length and weight balues
     */
    protected void setBodyLengthsAndWeights(Body body)
    {
        //todo add code to adjust limb lengths and weights
    }



}
