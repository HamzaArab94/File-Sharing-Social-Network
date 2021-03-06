import java.util.*;
public class Producer_Strategy_A extends Producer 
{
  public Producer_Strategy_A (String tag, String name, Simulation simulation)
  {
       super(tag,name,simulation);
  }
    
  @Override
    public int act(int k, String RankingStrategy){
     ActivityLog.append(this.getName()+" the "+this.getType()+ " was chosen to act.\n");
    sortDocuments(RankingStrategy);
     searchResults=SearchDocs(k, simulation.totalDocuments());
     int payoff=LikeDocs(tag,searchResults);//like your own document and others that match your taste, and follow “users” that like them                           
     ProduceDocument();
     FollowUsers();
     return payoff;
    }
}