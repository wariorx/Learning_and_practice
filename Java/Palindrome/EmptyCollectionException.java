

/**
 * Represents the situation in which a collection is empty.
 
 FROM TEXT BOOK
 *
 * @author Kevin Neilson
 * @version 6/5/2016
 */
public class EmptyCollectionException extends RuntimeException
{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public EmptyCollectionException(String collection)
    {
        super("The " + collection + " is empty.");
    }
}
