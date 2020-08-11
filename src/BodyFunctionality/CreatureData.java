package BodyFunctionality;

import java.util.ArrayList;

public class CreatureData
{

    ArrayList<CreatureDataObject> creatureDataArrayList;
    String filePath = "animals/";
    String[] filenames;


    public CreatureData()
    {
        creatureDataArrayList = new ArrayList<CreatureDataObject>(  );
        createFilenames();
        loadCreatureData();
    }

    /**
     * Method to convert the enum AnimalType values to strings to indicate files to load for each type
     */
    protected void createFilenames()
    {

        int creatureNumber = BodyPartEnums.AnimalType.values().length;
        filenames = new String[creatureNumber];
        for(int i=0; i<creatureNumber; i++)
        {
            filenames[i] = BodyPartEnums.AnimalType.fromOrdinal( i ).toString().toLowerCase() + ".txt";
        }

    }


    /**
     * Method to load the data values for creating creatures into the array
     */
    protected void loadCreatureData()
    {
        int creatureNumber = BodyPartEnums.AnimalType.values().length;
        CreatureDataObject  creature;

        for(int i=0; i<filenames.length; i++)
        {
            creature = loadCreatureDataObject(filenames[i]);
            creatureDataArrayList.add( creature );

        }

    }

    protected CreatureDataObject loadCreatureDataObject(String filename)
    {
        String totalPath = filePath+filename;
        CreatureDataObject thisCreature;

        // for types without creature data
        if (filename.equals(  "na.txt" ) || filename.equals( "mixed.txt" ) || filename.equals( "other.txt" ))
        {
            thisCreature = new CreatureDataObject();
        }
        else
        {
            thisCreature = new CreatureDataObject(totalPath);
        }

        return thisCreature;
    }

    public int size()
    {
        return creatureDataArrayList.size();
    }

    public CreatureDataObject getCreatureDataObject(int i)
    {
        return new CreatureDataObject( creatureDataArrayList.get(i) );

    }

    public CreatureDataObject getCreatureDataObject( BodyPartEnums.AnimalType animalType )
    {
        int size = size();
        boolean found = false;
        CreatureDataObject dataObject = creatureDataArrayList.get(0);
        for (int i=1; i<size && !found; i++)
        {
            if (creatureDataArrayList.get(i).getAnimalTypeStyle()==animalType)
            {
                found = true;
                dataObject = creatureDataArrayList.get(i);
            }
        }
        return new CreatureDataObject( dataObject );
    }




}
