import java.util.*;
/**
 * Write a description of class Producer_Strategy_B here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Producer_Strategy_B extends Producer 
{
  public Producer_Strategy_B (String tag, String name, Simulation simulation)
  {
       super(tag,name,simulation);
  }
    
  @Override
    public int act(int k,String RankingStrategy){
     ActivityLog.append(this.getName()+" the "+this.getType()+" was chosen to act.\n");
     sortDocuments(RankingStrategy);
     searchResults=SearchDocs(k, simulation.totalDocuments());
     int payoff=LikeDocs((TAGS[simulation.RandomInt(TAGS.length)]),searchResults);//Pick a taste other than your own, and “like” documents that match them and user that “like” them                                                                       
     ProduceDocument();
     FollowUsers();
     return payoff;
    }
  
  @Override
  public boolean equals (Object obj)
  {
   if (this==obj) return true;
   if (this == null) return false;
   if (this.getClass() != obj.getClass()) return false;
   Producer_Strategy_B prod = (Producer_Strategy_B) obj ;
   return (this.tag.equals(prod.getTag())&& this.name.equals(prod.getName()) && this.simulation.equals(prod.getSim()));
   }
    
}