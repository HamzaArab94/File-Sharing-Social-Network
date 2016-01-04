import java.util.*;
public interface Ranking_Strategies
{
    public class num_of_LikesComparator implements Comparator<Document>{
      public int compare(Document d1, Document d2){
        return d2.getNumOfLikes()- d1.getNumOfLikes();
       }
     }
    
    public class Author_PopularityComparator implements Comparator<Document>{
      public int compare(Document d1, Document d2){
        return d2.getAuthor().getFollowers().size()- d1.getAuthor().getFollowers().size();
       }
     }
     
    public class LikeSimilarity_Comparator implements Comparator<Document>{
      User user;
      LikeSimilarity_Comparator(User user){
       this.user=user; 
        }
      
      public int compare(Document d1, Document d2){
        return d2.getAuthor().LikeSimilarity(user)- d1.getAuthor().LikeSimilarity(user);
       }
     } 
     
    
    public class FollowSimilarity_Comparator implements Comparator<Document>{
      User user;
      FollowSimilarity_Comparator(User user){
       this.user=user; 
        }
      
      public int compare(Document d1, Document d2){
        return d2.getAuthor().FollowSimilarity(user)- d1.getAuthor().FollowSimilarity(user);
       }
     } 
     
    public class DistanceComparator implements Comparator<Document>{
      User user;
      DistanceComparator(User user){
       this.user=user; 
        }
      
      public int compare(Document d1, Document d2){
        return d2.getAuthor().distance(user)- d1.getAuthor().distance(user);
       }
     } 
}
