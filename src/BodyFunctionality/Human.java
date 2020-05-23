package BodyFunctionality;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Human implements CreatureCreationInterface
{


        // values for arm and leg length
        public double getMinArmLength()
        {
            return 10;
        }

        ;

        public double getMaxArmLength()
        {
            return 16;
        }

        public double getAverageArmLength()
        {
            return 13;
        }

        public double getRandomArmLength()
        {
            Random randomNumbers = new Random();

            return 10 + (16 - 10) * randomNumbers.nextDouble();
        }

        // values for weight factor (body parts have weight of length * weightfactor)
        public double getMinWeightFactor()
        {
            return .25;
        }

        public double getMaxWeightFactor()
        {
            return .4;
        }

        public double getAverageWeightFactor()
        {
            return .325;
        }

        public double getRandomWeightFactor()
        {
            Random randomNumbers = new Random();

            return .25 + (.4 - .25) * randomNumbers.nextDouble();
        }

        // values for back and chest
        public double getMinBackLength()
        {
            return 24;
        }

        public double getMaxBackLength()
        {
            return 32;
        }

        public double getAverageBackLength()
        {
            return 28;
        }

        public double getRandomBackLength()
        {
            Random randomNumbers = new Random();

            return 24 + (32 - 24) * randomNumbers.nextDouble();
        }

        public double getMinEarLength()
        {
            return 5;
        }

        public double getMaxEarLength()
        {
            return 7;
        }

        public double getAverageEarLength()
        {
            return 6;
        }

        public double getRandomEarLength()
        {
            Random randomNumbers = new Random();

            return 5 + (7 - 5) * randomNumbers.nextDouble();
        }

        public double getMinEyeLength()
        {
            return .82;
        }

        public double getMaxEyeLength()
        {
            return 1.06;
        }

        public double getAverageEyeLength()
        {
            return (.82 / 1.06) / 2;
        }

        public double getRandomEyeLength()
        {
            Random randomNumbers = new Random();

            return .82 + (1.06 - .82) * randomNumbers.nextDouble();
        }

        // values for fingers and toes
        public double getMinFingerLength()
        {
            return 2;
        }

        public double getMaxFingerLength()
        {
            return 4;
        }

        public double getAverageFingerLength()
        {
            return 3;
        }

        public double getRandomFingerLength()
        {
            Random randomNumbers = new Random();

            return 2 + (4 - 2) * randomNumbers.nextDouble();
        }

        public int getFingerCount()
        {
            return 5;
        }

        public int getToeCount()
        {
            return 5;
        }

        public boolean isOpposable()
        {
            return true;
        }

        public BodyPartGenerator.Stance getStance()
        {
            return BodyPartGenerator.Stance.UPRIGHT;
        }

        public double getMinHandLength()
        {
            return 6.4;
        }

        public double getMaxHandLength()
        {
            return 8.0;
        }

        public double getAverageHandLength()
        {
            return 7.2;
        }

        public double getRandomHandLength()
        {
            Random randomNumbers = new Random();

            return 6.4 + (8.0 - 6.4) * randomNumbers.nextDouble();
        }

        public double getMinHornLength()
        {
            return 0;
        }

        public double getMaxHornLength()
        {
            return 0;
        }

        public double getAverageHornLength()
        {
            return 0;
        }

        public double getRandomHornLength()
        {
            return 0;
        }

        public boolean isHorned()
        {
            return false;
        }

        public BodyHorn.HornStyle getHornStyle()
        {
            return BodyHorn.HornStyle.NA;
        }

        public double getMinMouthLength()
        {
            return 1.26;
        }

        public double getMaxMouthLength()
        {
            return 3.04;
        }

        public double getAverageMouthLength()
        {
            return (1.26 + 3.04) / 2;
        }

        public double getRandomMouthLength()
        {
            Random randomNumbers = new Random();

            return 1.26 + (3.04 - 1.26) * randomNumbers.nextDouble();
        }

        public BodyMouth.MouthStyle getMouthStyle()
        {
            return BodyMouth.MouthStyle.FRONT;
        }

        public BodyMouth.TeethStyle getTeethStyle()
        {
            return BodyMouth.TeethStyle.HUMAN;
        }

        public double getMinMuzzleLength()
        {
            return 0;
        }

        public double getMaxMuzzleLength()
        {
            return 0;
        }

        public double getAverageMuzzleLength()
        {
            return 0;
        }

        public double getRandomMuzzleLength()
        {
            return 0;
        }

        public boolean isMuzzled()
        {
            return false;
        }

        public double getMinNailLength()
        {
            return .1;
        }

        public double getMaxNailLength()
        {
            return .3;
        }

        public double getAverageNailLength()
        {
            return .2;
        }

        public double getRandomNailLength()
        {
            Random randomNumbers = new Random();

            return .1 + (.3 - .1) * randomNumbers.nextDouble();
        }

        public double getMinNeckLength()
        {
            return 3.9;
        }

        public double getMaxNeckLength()
        {
            return 4.7;
        }

        public double getAverageNeckLength()
        {
            return 4.3;
        }

        public double getRandomNeckLength()
        {
            Random randomNumbers = new Random();

            return 3.9 + (4.7 - 3.9) * randomNumbers.nextDouble();
        }

        public double getMinNoseLength()
        {
            return 1.8;
        }

        public double getMaxNoseLength()
        {
            return 2.4;
        }

        public double getAverageNoseLength()
        {
            return 2.1;
        }

        public double getRandomNoseLength()
        {
            Random randomNumbers = new Random();

            return 1.8 + (2.4 - 1.8) * randomNumbers.nextDouble();
        }

        public double getMinTailLength()
        {
            return 0;
        }

        public double getMaxTailLength()
        {
            return 0;
        }

        public double getAverageTailLength()
        {
            return 0;
        }

        public double getRandomTailLength()
        {
            return 0;
        }

        public boolean isTailed()
        {
            return false;
        }

        public double getMinTongueLength()
        {
            return 2.9;
        }

        public double getMaxTongueLength()
        {
            return 3.5;
        }

        public double getAverageTongueLength()
        {
            return 3.2;
        }

        public double getRandomTongueLength()
        {
            Random randomNumbers = new Random();

            return 2.9 + (3.5 - 3.2) * randomNumbers.nextDouble();
        }

        public double getMinWingLength()
        {
            return 0;
        }

        public double getMaxWingLength()
        {
            return 0;
        }

        public double getAverageWingLength()
        {
            return 0;
        }

        public double getRandomWingLength()
        {
            return 0;
        }

        public boolean isWinged()
        {
            return false;
        }

        public BodyPartGenerator.Texture getBodyTexture()
        {
            return BodyPartGenerator.Texture.SKIN;
        }

        // one of possible colors for the body
        public ArrayList<Color> getColors()
        {
            ArrayList<Color> colorList = new ArrayList<Color>();
            colorList.add( Color.PEACHPUFF );
            colorList.add( Color.TAN );
            colorList.add( Color.WHITE );
            colorList.add( Color.BROWN );
            return colorList;

        }

        public Color getRandomColor()
        {
            ArrayList<Color> colorList = getColors();
            Random randomNumbers = new Random();

            //return colorList.get( randomNumbers.nextInt( colorList.size() ) );
            return Color.BROWN;
        }

        // one of possible colors for the body
        public ArrayList<Color> getEyeColors()
        {
            ArrayList<Color> colorList = new ArrayList<Color>();
            colorList.add( Color.BLUE );
            colorList.add( Color.BROWN );
            colorList.add( Color.OLIVE );
            colorList.add( Color.GREY );
            return colorList;

        }

        public Color getRandomEyeColor()
        {
            ArrayList<Color> colorList = getEyeColors();
            Random randomNumbers = new Random();

            return colorList.get( randomNumbers.nextInt( colorList.size() ) );

        }


        // still thinking about if these are possible items, choose one, or if they pertain to all the body
        public ArrayList<String> getBodyFeatures()
        {
            return new ArrayList<String>();
        }

        public ArrayList<String> getBodyItemsWorn()    // change to item object later
        {
            return new ArrayList<String>();
        }

        public ArrayList<String> getBodyResistances()
        {
            return new ArrayList<String>();
        }

        public ArrayList<String> getBodySkills()
        {
            return new ArrayList<String>();
        }

        // drops random items when a creature is killed
        public ArrayList<String> randomItemDrop()
        {
            ArrayList<String> list = new ArrayList<String>();
            list.add( "Human Body" );
            return list;
        }

        @Override
        public double getMinBodyLength()
        {
            return 48;
        }

        @Override
        public double getMaxBodyLength()
        {
            return 84;
        }

        @Override
        public double getAverageBodyLength()
        {
            return 66;
        }

        @Override
        public double getRandomBodyLength()
        {
            Random randomNumbers = new Random();

            return 48 + (84 - 48) * randomNumbers.nextDouble();
        }

        @Override
        public double getMinMaxHealth()
        {
            return 750;
        }

        @Override
        public double getMaxMaxHealth()
        {
            return 1250;
        }

        @Override
        public double getAverageMaxHealth()
        {
            return 1000;
        }

        @Override
        public double getRandomMaxHealth()
        {
            Random randomNumbers = new Random();
            return 750 + (1250 - 750) * randomNumbers.nextDouble();
        }

        public BodyPartGenerator.Gender getRandomGender()
        {
            BodyPartGenerator.Gender gender = BodyPartGenerator.Gender.MALE;
            int value;
            Random randomNumbers = new Random();
            value = randomNumbers.nextInt( 2 );
            if (value == 1)
            {
                gender = BodyPartGenerator.Gender.FEMALE;
            }

            return gender;
        }


        public Body generateRandomBody()
        {

            System.out.println( "In generateRandomBody" );

            Body newBody = new Body();

            // create the basic body
            //todo code fudge here to use human functions calls
            Human human = new Human();

            Color color = human.getRandomColor();
            BodyPart neck, back, chest;

            neck = new BodyNeck();
            neck.create( "neck", "", BodyPartGenerator.AnimalType.HUMAN, color );
            neck.setAboveBodyPart( newBody );

            chest = new BodyChest();
            chest.create( "chest", "", BodyPartGenerator.AnimalType.HUMAN, color );
            chest.setAboveBodyPart( newBody );

            back = new BodyBack();
            back.create( "back", "", BodyPartGenerator.AnimalType.HUMAN, color );
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
            newBody.setFirstName( "Human" + Integer.toString( newBody.getIndentifier() ) );
            newBody.setColor( Color.PEACHPUFF );
            newBody.setTexture( BodyPartGenerator.Texture.SKIN );
            newBody.setAnimalType( BodyPartGenerator.AnimalType.HUMAN );
            newBody.setGender( BodyPartGenerator.Gender.MALE );
            newBody.setDescription( "A male human" );
            newBody.setStance( BodyPartGenerator.Stance.UPRIGHT );
            newBody.setLength( 70 );

            newBody.setMaxHealth( 1000 );
            newBody.setHealth( 1000 );

            human.bodyAdjustments( newBody ); // adds body adjustments, i.e. hair

            return newBody;
        }


        /* this method makes all the body adjustments that cannot be made by other methods
         (i.e. changes unique features, adjusts color patterns, gives unique skills, etc)
         it should be called after the body is updated
        */
        public void bodyAdjustments( Body body )
        {
            //add head hair
            ArrayList<Color> colorList = new ArrayList<Color>();
            colorList.add( Color.WHITE );
            colorList.add( Color.BROWN );
            colorList.add( Color.TAN );
            colorList.add( Color.BLACK );

            Random randomNumbers = new Random();

            Color hairColor = colorList.get( randomNumbers.nextInt( colorList.size() ) );

            body.getBodyPart( "head" ).addFeature( "Hair color: " + hairColor.toString().toLowerCase() );


        }

}