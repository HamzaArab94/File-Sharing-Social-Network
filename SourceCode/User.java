import java.util.*;
import javax.swing.*;
/**
 *  All the users that are in the network. Users have a tag for their taste, a name, an access variable to their
 *  simulation environment, a list of followers/following and a list of the documents that they produced.
 * 
 * @author Karim Hersi, Abdallah Saket, Hamza Arab
 * @version (Milestone 1- 10/16/15)
 */
public  abstract class User implements Ranking_Strategies 
{
    protected static int UID_Count=0;
    protected String tag;
    protected String name;
    protected int unique_identifier;
    protected List<User> followers;
    protected List<User> following;
    protected Simulation simulation;
    public static JTextArea ActivityLog;
    protected List<Document> likedDocuments;
    protected List<Document> searchResults;
    protected static final String[] TAGS = {"Sports","Movies","Technology","Politics","Business and Finance","Education","Music"};
    protected static final String[] RANKING_STRATEGIES = {"Number of Likes","Documents with the Most Popular Authors","Like Similarity","Follow Similarity","Distance  of the user in the social network"};
    protected Object comparator;
    protected int payoff;
    
    public User(String tag, String name, Simulation simulation)
    {
        this.tag=tag;
        this.name=name;
        this.unique_identifier=UID_Count++;
        this.simulation=simulation;
        followers= new ArrayList<User>();
        following= new ArrayList<User>();
        likedDocuments= new ArrayList<Document>();
        ActivityLog= new JTextArea();
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
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public List<User> getFollowers(){
        return this.followers;
    }
    
    public List<User> getFollowing(){
        return this.following;
    }
    
    /**
     * Like the given document.*/
    public void like(Document doc){
       if(!(doc.getUserLikes()).contains(this)){    //if we didn't already like this document
          doc.setNumofLikes(doc.getNumOfLikes()+1);
          doc.getUserLikes().add(this);
          likedDocuments.add(doc);
          ActivityLog.append("The document with the tag '"+doc.getTag()+"' was liked by "+this.getName()+".\n");
       }
    }
    
    /**
     * Follow the given user.*/
     public void follow(User user){
     if(!(isFollowing(user))){  //if we havent already followed this user
       following.add(user);
       user.followers.add(this);
       ActivityLog.append(this.getName()+" the "+this.getType()+ " is now following "+user.getName()+" the "+user.getType()+".\n");
      }
    }
    
    /**
     * Unfollow the given user.*/
     public void unfollow(User user){
     if((isFollowing(user))){ //if we are following this user
      following.remove(user);
      user.followers.remove(this);
      }
    }
    
    public abstract int  act(int k , String RankingStrategy);
    
    /**
     * Check if this user is following the given user*/
     public boolean isFollowing( User user){
       return this.following.contains(user);
     }
     
     /**
      * @ return- The number of documents that are liked by both users
     **/
    public int LikeSimilarity(User user){ 
        int count=0;
        for(Document doc: user.likedDocuments){
            if(this.likedDocuments.contains(doc))
            count++;
        }
        return count;
    }
    
     /**
     * @ return -The number mutual followers between the two users
     */
    public int FollowSimilarity(User user){ 
        int count=0;
        for(User userFollower: user.followers){
            if(this.followers.contains(userFollower))
            count++;
        }
        return count;
    }
    
    /**
     * Prints a list of the user's follower's names
     */public String FollowerList(){
       String s=(getName()+ "'s followers include {");
       for(User follower: followers){
            s+=(follower.getName()+" ");
       }
        s+=("}\n");
        return s;
    }
    
    /**@Return - The type of User
     */
    public String getType(){
        if(this instanceof Producer_Strategy_A){
        return "producer (Type-A)";
       }else if(this instanceof Producer_Strategy_B){
        return "producer (Type-B)";
       }else{
        return "consumer";    
        }
    }
    
     /**
     * Returns the top K results using the using the (number of likes) criteria 
     * @param  k   
     * @return   a list of the retrieved documents 
     */
    public List<Document> SearchDocs(int k, List<Document> sortedDocuments){
        if(simulation.totalDocuments().size()>k){
            return simulation.totalDocuments().subList(0,k);
        }else{
            return simulation.totalDocuments();  
        }
    }
    
    /**
     * Like Documents with the given taste and follow the author.*/
     public int LikeDocs(String taste, List<Document> Documents){
        int count=0;
        if(Documents==null)
           return 0;
           
        for(Document doc: Documents){
          if(taste.equals(doc.getTag())){//if the users tag matches the searched docs tag
            like(doc);
            follow(doc.getAuthor());
            count++;
          }
        }
        
        payoff+=count;
        return count;
    }
    
    /**
     * @return- the top 2 if (this & @param) are following eachother, 1 if they have mutual followers and 0 otherwise
     * @param - the other user   
     */
    public int distance(User user){
      if(this.followers.contains(user)){
       return 2;
      }else if(this.FollowSimilarity(user)>=1){
       return 1;
      }else{
       return 0;
     }
    }
    
    /**
     * Sort the Documents using the ranking strategy provided in the parameter
     * @param The Ranking Strategy
     */
    public void sortDocuments(String RankingStrategy){
       if(RankingStrategy.equals(RANKING_STRATEGIES[0])){
         Collections.sort(simulation.totalDocuments(), new num_of_LikesComparator());
         ActivityLog.append("The Documents have been ranked by ascending number of likes. \n");
       }else if(RankingStrategy.equals(RANKING_STRATEGIES[1])){
         Collections.sort(simulation.totalDocuments(), new Author_PopularityComparator());   
         ActivityLog.append("The Documents have been ranked  by the author's popularity. \n");
       }else if( RankingStrategy.equals(RANKING_STRATEGIES[2]) ){
         Collections.sort(simulation.totalDocuments(), new LikeSimilarity_Comparator(this));   
         ActivityLog.append("The Documents have been ranked  by the like similarity (i.e. if we tend to like the same documents.)\n");  
        }else if( RankingStrategy.equals(RANKING_STRATEGIES[3]) ){
         Collections.sort(simulation.totalDocuments(), new LikeSimilarity_Comparator(this));   
         ActivityLog.append("The Documents have been ranked  by the follow similarity (i.e. if we tend to follow the same users.)\n");  
        }else if( RankingStrategy.equals(RANKING_STRATEGIES[4]) ){
         Collections.sort(simulation.totalDocuments(), new DistanceComparator(this));   
         ActivityLog.append("The Documents have been ranked  by the  the distance of the user in the social network. \n");  
        }
    }
    }