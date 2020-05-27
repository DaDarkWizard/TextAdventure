/**
 * Class that contains static methods that assist in putting and getting class objects from the ByteBuffer
 *
 * It contains methods to handle putting and getting data from Strings, Colors, Features, Items, Resistances, Skills, and Injuries
 *
 * Created by Michael Clinesmith
 * Last updated: 5/27/2020
 */
package BodyFunctionality;

import javafx.scene.paint.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ByteBufferIO
{
    /**
     *  Static method that adds a string to the buffer as long as there is space in the buffer
     * @param buffer ByteBuffer: Bytebuffer object to add the string data to
     * @param string String: String data to be added to the buffer
     * @return boolean: true if the String data is added to the buffer, false if not
     */

    static public boolean putString( ByteBuffer buffer, String string)
    {
        boolean isValid=false;
        byte[] stringBytes;

        if (string == null && buffer.position()<buffer.limit())  // if string is null, enter -1 to indicate null but state valid
        {
            buffer.putInt(-1);
            isValid = true;
            System.out.println( "Null string, -1 is stored" );
        }
        else if (string !=null)
        {
            stringBytes = string.getBytes();

            System.out.println( "Buffer limit: " + buffer.limit() + ", Buffer position: " + buffer.position());


            if (buffer.limit() > (buffer.position() + stringBytes.length + 1)) // if string is not null and space is available
            {
                buffer.putInt( stringBytes.length );
                buffer.put( stringBytes );
                isValid = true;
                System.out.println( "Int buffered:" + stringBytes.length );
                System.out.println( "String buffered: " + string );
            }
        }
        return isValid;
    }

    /**
     * Static method that adds a color to the buffer by first converting it to a string
     * @param buffer ByteBuffer: The buffer to add the color data to
     * @param color Color: A Color object
     * @return boolean: true if the Color object is added to the buffer, false if not
     */
    static public boolean putColor( ByteBuffer buffer, Color color)
    {
        boolean isValid=false;
        String colorString = color.toString();
        return putString( buffer, colorString);

    }

    /**
     * Static method that adds an ArrayList of features to the buffer
     * @param buffer ByteBuffer: The buffer to add the ArrayList data to
     * @param features ArrayList<Features>: An ArrayList of Features to add to the buffer //todo change to Feature objects
     * @return boolean: true if the ArrayList was added to the buffer properly, false if not
     */
    static public boolean putFeatures( ByteBuffer buffer, ArrayList<String> features )
    {
        boolean isValid = false, noProblems = true;
        int arrayLength = features.size();
        int estSize = arrayLength;

        for (int i=0; i<arrayLength; i++)
        {
            estSize += features.get( i ).length();
        }

        if (buffer.limit()>buffer.position()+estSize)
        {
            buffer.putInt( arrayLength );
            for(int i=0; i<arrayLength && noProblems; i++)
            {
                noProblems = putString( buffer, features.get( i ) ); // assignment catches if a problem adding to buffer
            }
            isValid = noProblems;
        }

        return isValid;

    }

    /**
     * Static method that adds an ArrayList of itemsWorn to the buffer
     * @param buffer ByteBuffer: The buffer to add the ArrayList data to
     * @param itemsWorn ArrayList<Item>: An ArrayList of Features to add to the buffer //todo change to Item objects
     * @return boolean: true if the ArrayList was added to the buffer properly, false if not
     */
    static public boolean putItemsWorn( ByteBuffer buffer, ArrayList<String> itemsWorn )
    {
        boolean isValid = false, noProblems = true;
        int arrayLength = itemsWorn.size();
        int estSize = arrayLength;

        for (int i=0; i<arrayLength; i++)
        {
            estSize += itemsWorn.get( i ).length();
        }

        if (buffer.limit()>buffer.position()+estSize)
        {
            buffer.putInt( arrayLength );
            for(int i=0; i<arrayLength && noProblems; i++)
            {
                noProblems = putString( buffer, itemsWorn.get( i ) ); // assignment catches if a problem adding to buffer
            }
            isValid = noProblems;
        }

        return isValid;

    }


    /**
     * Static method that adds an ArrayList of resistances to the buffer
     * @param buffer ByteBuffer: The buffer to add the ArrayList data to
     * @param resistances ArrayList<Resistance>: An ArrayList of Resistances to add to the buffer //todo change to Resistance objects
     * @return boolean: true if the ArrayList was added to the buffer properly, false if not
     */
    static public boolean putResistances( ByteBuffer buffer, ArrayList<String> resistances )
    {
        boolean isValid = false, noProblems = true;
        int arrayLength = resistances.size();
        int estSize = arrayLength;

        for (int i=0; i<arrayLength; i++)
        {
            estSize += resistances.get( i ).length();
        }

        if (buffer.limit()>buffer.position()+estSize)
        {
            buffer.putInt( arrayLength );
            for(int i=0; i<arrayLength && noProblems; i++)
            {
                noProblems = putString( buffer, resistances.get( i ) ); // assignment catches if a problem adding to buffer
            }
            isValid = noProblems;
        }

        return isValid;

    }

    /**
     * Static method that adds an ArrayList of features to the buffer
     * @param buffer ByteBuffer: The buffer to add the ArrayList data to
     * @param skills ArrayList<skills>: An ArrayList of skills to add to the buffer //todo change to skills objects
     * @return boolean: true if the ArrayList was added to the buffer properly, false if not
     */
    static public boolean putSkills( ByteBuffer buffer, ArrayList<String> skills )
    {
        boolean isValid = false, noProblems = true;
        int arrayLength = skills.size();
        int estSize = arrayLength;

        for (int i=0; i<arrayLength; i++)
        {
            estSize += skills.get( i ).length();
        }

        if (buffer.limit()>buffer.position()+estSize)
        {
            buffer.putInt( arrayLength );
            for(int i=0; i<arrayLength && noProblems; i++)
            {
                noProblems = putString( buffer, skills.get( i ) ); // assignment catches if a problem adding to buffer
            }
            isValid = noProblems;
        }

        return isValid;

    }

    /**
     * Static method that adds an ArrayList of features to the buffer
     * @param buffer ByteBuffer: The buffer to add the ArrayList data to
     * @param injuries ArrayList<Injury>: An ArrayList of Features to add to the buffer //todo change to injuries objects
     * @return boolean: true if the ArrayList was added to the buffer properly, false if not
     */
    static public boolean putInjuries( ByteBuffer buffer, ArrayList<String> injuries )
    {
        boolean isValid = false, noProblems = true;
        int arrayLength = injuries.size();
        int estSize = arrayLength;

        for (int i=0; i<arrayLength; i++)
        {
            estSize += injuries.get( i ).length();
        }

        if (buffer.limit()>buffer.position()+estSize)
        {
            buffer.putInt( arrayLength );
            for(int i=0; i<arrayLength && noProblems; i++)
            {
                noProblems = putString( buffer, injuries.get( i ) ); // assignment catches if a problem adding to buffer
            }
            isValid = noProblems;
        }

        return isValid;

    }


    /**
     * Static method to save the data in the ArrayList containing body parts in the buffer
     * @param buffer ByteBuffer: The ByteBuffer to be used to save the attached parts data
     * @param bodyPartList ArrayList<BodyPart>: The ArrayList containing attached body parts
     * @return boolean: true if there was no error is saving to the ByteBuffer, false if there were errors
     */
    static public boolean putAttachedBodyParts(ByteBuffer buffer, ArrayList<BodyPart> bodyPartList)
    {
        boolean isValid=false, noProblems=true;
        int size = bodyPartList.size();

        if (buffer.limit()>buffer.position()+1)
        {
            buffer.putInt( size );
            for (int i = 0; i < size && noProblems; i++)
            {
                noProblems = bodyPartList.get( i ).addToBuffer( buffer );
            }
            isValid = noProblems;
        }
        return isValid;
    }


    /**
     * Static method to get the next String from the buffer
     * @param buffer ByteBuffer: The buffer containing the String data
     * @return String: The next String data in the buffer
     */
    static public String getString( ByteBuffer buffer )
    {
        String str;
        int len;

        len = buffer.getInt();

        if (len < 0)
        {
            str = null;
        }
        else if (len==0)
        {
            str = "";
        }
        else
        {
            byte[] bytes = new byte[len];
            buffer.get(bytes);
            str = new String(bytes);
        }

        return str;
    }

    /**
     * Static method to get the next Color stored in the buffer
     * @param buffer ByteBuffer: The buffer containing the Color object's data
     * @return Color: The Color object stored in the ByteBuffer
     */
    static public Color getColor( ByteBuffer buffer)
    {

        Color color;
        String colorString = getString( buffer );

        color = Color.valueOf( colorString );


        return color;
    }

    /**
     * Static method to get the stored ArrayList of features from the buffer todo update to feature objects
     * @param buffer ByteBuffer: the buffer with the stored ArrayList
     * @return ArrayList<Feature>: A list of Feature objects
     */
    static public ArrayList<String> getFeatures(ByteBuffer buffer)
    {
        ArrayList<String> list = new ArrayList<String>();
        String feature;
        int arraySize = buffer.getInt();
        for (int i = 0; i < arraySize; i++)
        {
            feature = getString( buffer );
            list.add( feature );
        }
        return list;
    }

    /**
     * Static method to get the stored ArrayList of itemWorn from the buffer todo update to itemWorn objects
     * @param buffer ByteBuffer: the buffer with the stored ArrayList
     * @return ArrayList<itemWorn>: A list of itemWorn objects
     */
    static public ArrayList<String> getItemsWorn(ByteBuffer buffer)
    {
        ArrayList<String> list = new ArrayList<String>();
        String itemWorn;
        int arraySize = buffer.getInt();
        for (int i = 0; i < arraySize; i++)
        {
            itemWorn = getString( buffer );
            list.add( itemWorn );
        }
        return list;
    }

    /**
     * Static method to get the stored ArrayList of resistance from the buffer todo update to resistance objects
     * @param buffer ByteBuffer: the buffer with the stored ArrayList
     * @return ArrayList<resistance>: A list of resistance objects
     */
    static public ArrayList<String> getResistances(ByteBuffer buffer)
    {
        ArrayList<String> list = new ArrayList<String>();
        String resistance;
        int arraySize = buffer.getInt();
        for (int i = 0; i < arraySize; i++)
        {
            resistance = getString( buffer );
            list.add( resistance );
        }
        return list;
    }

    /**
     * Static method to get the stored ArrayList of skill from the buffer todo update to skill objects
     * @param buffer ByteBuffer: the buffer with the stored ArrayList
     * @return ArrayList<skill>: A list of skill objects
     */
    static public ArrayList<String> getSkills(ByteBuffer buffer)
    {
        ArrayList<String> list = new ArrayList<String>();
        String skill;
        int arraySize = buffer.getInt();
        for (int i = 0; i < arraySize; i++)
        {
            skill = getString( buffer );
            list.add( skill );
        }
        return list;
    }

    /**
     * Static method to get the stored ArrayList of injury from the buffer todo update to injury objects
     * @param buffer ByteBuffer: the buffer with the stored ArrayList
     * @return ArrayList<injury>: A list of injury objects
     */
    static public ArrayList<String> getInjuries(ByteBuffer buffer)
    {
        ArrayList<String> list = new ArrayList<String>();
        String injury;
        int arraySize = buffer.getInt();
        for (int i = 0; i < arraySize; i++)
        {
            injury = getString( buffer );
            list.add( injury );
        }
        return list;
    }

    /**
     * Static method thot loads attached body parts (and all subordinate parts) from the buffer into memory
     * @param buffer ByteBuffer: The buffer that contains data about the BodyPart objects
     * @return ArrayList<BodyPart>: A list containing all of the attached (and recreated) BodyPart objects
     */
    static public ArrayList<BodyPart> getAttachedBodyParts(ByteBuffer buffer)
    {
        BodyPart bodyPart;
        ArrayList<BodyPart> bodyPartList = new ArrayList<BodyPart>(  );
        int size = buffer.getInt();
        for (int i=0; i<size; i++)
        {
            bodyPart = new BodyPart( buffer );
            bodyPartList.add( bodyPart );
        }

        return bodyPartList;

    }





}
