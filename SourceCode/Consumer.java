import java.util.*;
public class Consumer extends User 
{
    public Consumer(String tag, String name, Simulation simulation)
    {
        super(tag,name,simulation);
    }
    
    @Override
    public int act(int k , String RankingStrategy){
     ActivityLog.append(this.getName()+" the "+this.getType()+" was chosen to act.\n");
     sortDocuments(RankingStrategy);
     searchResults=SearchDocs(k, simulation.totalDocuments());
     int payoff=LikeDocs(tag,searchResults);
     return payoff;
    }
    
}
