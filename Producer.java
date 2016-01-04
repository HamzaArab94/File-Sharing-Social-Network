import java.util.*;
/**
 * Write a description of class Producer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Producer extends User
{
   protected List<Document> myDocuments;
    /** Constructor for objects of class Producer
   */
    public Producer(String tag, String name, Simulation simulation)
    {
       super(tag,name,simulation);
       myDocuments= new ArrayList<Document>();
   }
   
   public List<Document> getMyDocuments(){
        return this.myDocuments;
   }
   
   public abstract int act(int k, String RankingStrategy);
   
   /**
     * Add the given Document to the Users list.
     */
    public void addDocument(Document doc){
        myDocuments.add(doc);
    }
  
    /**Produces a Document for the producer
     */public void ProduceDocument(){
        Document newDoc = new Document(this.getTag(),this); 
        this.like(newDoc);
       ActivityLog.append(this.getName()+" produced a "+this.getTag()+" document and also liked it.\n");
   }
  
    /**If someone likes one of the producer's documents, follow that user.
    */
    public void FollowUsers(){
        for(Document doc : myDocuments){
         for(User user: doc.getUserLikes()){
           follow(user);
       }
    }
   }
}
