import java.util.*;
/**
 *  This class includes a user interface to provide a simulation to the social network.
 * @author Karim Hersi, Abdallah Saket, Hamza Arab
 * @version (Milestone 1- 10/16/15)
 */
public class Simulation
{
    public List<User> totalUsers;//A list of the total Users in the network
    public static final String[] TAGS = {"Sports","Movies","Technology","Politics","Business and Finance","Education","Music"};
    private int AccumulatedPayoff=0;
    protected List<Integer> data;
   public Simulation()
   {
      totalUsers= new ArrayList<User>();
      data = new ArrayList<Integer> ();
    }

   public void SimulationCycle(int k , String RankingStrategy){
        if(totalUsers.size()>0)
          totalUsers.get(0).ActivityLog.append("Activity Log of Execution Cycle "+(data.size()+1)+":\n");
          int RandomInteger=RandomInt(totalUsers.size());
          AccumulatedPayoff+=(totalUsers.get(RandomInteger).act(k,RankingStrategy));
          data.add(AccumulatedPayoff+1);
          totalUsers.get(0).ActivityLog.append("\n");
        
    }
    
   /** Display the Current State of the Network on the Console
       */
    public String DisplayNetwork(){
        String s;
        s=("Total Number of Users: "+totalUsers.size()+"\n");
        s+= ("Total Number of Documents: "+totalDocuments().size()+"\n");
        if(data.size()>0)
         s+= ("Total Accumulated Payoff: "+Collections.max(data)+"\n");
        s+=("\n");
        for(User user: totalUsers){
            s+=user.FollowerList();
        }
        return s;
    }
    
   /**Return a Random Integer Between 0-UpperLimit*
    */
   public int RandomInt(int UpperLimit){
       Random r= new Random();
       int RandomInt=r.nextInt(UpperLimit);
       return RandomInt;
    }
    
   /** Returns  a list of the total documents in the network
    */public List<Document> totalDocuments(){
       List<Document> totalDocuments= new ArrayList<Document>();
       for(User user: totalUsers){ 
           if(user instanceof Producer)
              for(Document doc: ((Producer)user).getMyDocuments()){
               totalDocuments.add(doc);
            }
        }
        return totalDocuments;
    }
  }