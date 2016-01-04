import java.util.*;
/**
 *  All of the documents that are in the network. Documents have a tag for their topic, their # of likes,
 *  a list of the users that liked the document, and the Author of the document.
 * 
 * @author Karim Hersi, Abdallah Saket, Hamza Arab
 * @version (Milestone 1- 10/16/15)
 */
public class Document
{
    private static int UID_Count;
    private String tag;
    private int num_of_likes;
    private Producer Author;
    private int unique_identifier;
    private List<User> userLikes;
    
    /**
     * Constructor for objects of class Document
     */
    public Document(String tag, Producer Author)
    {
        this.tag=tag;
        this.Author=Author;
        UID_Count++;
        unique_identifier=UID_Count;
        userLikes=new ArrayList<User>();
        Author.addDocument(this);
    }

    public String getTag(){
        return this.tag;
    }
    
    public void setTag(String tag){
        this.tag=tag;
    }
    
    public int getID(){
       return unique_identifier;
    }
    
    public int getNumOfLikes(){
        return this.num_of_likes;
    }
    
    public void setNumofLikes(int numOfLikes){
        this.num_of_likes=numOfLikes;
    }
    
     public User getAuthor(){
        return this.Author;
    }
     
     public void setAuthor(Producer a){
         this.Author=a;
     }
    
     public List<User> getUserLikes(){
        return userLikes;
    }
    
    }
