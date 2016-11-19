import javax.swing.*; 
import java.awt.*; 
import java.util.*;
import java.awt.event.*;
/**
 * This is the GUI Framework for the AddressBook.
 * 
 * @author (Hamza Arab, Karim Hersi, Abdalla Sakhet, Atif Siddiq) 
 * @version (Version 1: Wednesday, October 14th, 2015)
 */
public class GUI  extends SimulationData implements ActionListener  {
    private JFrame Frame;
    private JMenuBar Menu;
    private JMenuItem RST,DAL,DNS,DG;
    private JMenu FileMenu,DisplayMenu;
    private JTextPane Space;
    private JTextArea ActivityLog;
    private JTextField textField;
    private ArrayList<JLabel> labels;
    private Graph GraphPanel; 
    private ArrayList<JTextField> textfields;
    private JButton Execute;
    private JPanel FieldPanel,ButtonPanel;
    private Component CenterItem, ScrollItem;
    private JComboBox<String> jcb,jcb2;
    private int no_Consumers, no_Producers,k;
    private Simulation sim;
    
	public GUI(){
	   /**Instantiate all the Components*/ 
	  Frame = new JFrame("Document Sharing Network");
	  sim= new Simulation();
	  FieldPanel=new JPanel();
	  FieldPanel.setLayout(new GridLayout(5, 2));
	  FieldPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	  ButtonPanel= new JPanel(new GridLayout(1,2));
	  Execute=new JButton("Execute");
	  labels=new  ArrayList<JLabel>();
	  textfields =new ArrayList<JTextField>();
	  Space = new JTextPane();
	  GraphPanel = new Graph(sim.data);
	  ActivityLog= new JTextArea();
	  Menu = new JMenuBar();
      FileMenu = new JMenu("File");
	  DisplayMenu = new JMenu("Display");
	  DAL = new JMenuItem("Display Activity Log");
	  DG = new JMenuItem("DisplayGraph");
      DNS = new JMenuItem("Display Network Statistics");
	  RST = new JMenuItem("Reset");
      jcb = new JComboBox<String>(RANKING_STRATEGIES);
      jcb2 = new JComboBox<String>(ACT_STRATEGY);
      
	  /**Add all the Labels, textfields and buttons into ArrayList. Add all components into their Panels**/
      for(int i =0;i<LABELS.length;i++){
            labels.add(new JLabel(LABELS[i])); 
            FieldPanel.add(labels.get(i));
            textfields.add(new JTextField(10));
            textfields.get(i).setEditable(false);
            FieldPanel.add(textfields.get(i));
        }
       
	    /**Get User Input For Simulation Parameters, initialize simulation environment*/ 
	  do{
	   no_Consumers=getIntegerInput("Enter the no_Consumers: ");
	   no_Producers=getIntegerInput("Enter the no_Producers: ");
	   if(no_Producers==0&&no_Consumers==0)
	      JOptionPane.showMessageDialog(null, "Must Have at Least One User!!!");
      }while(no_Consumers==0&&no_Producers==0);
      
	  k=getIntegerInput("Enter the number of search results to return ");
      textfields.get(0).setText(String.valueOf(no_Producers));
      JOptionPane.showMessageDialog( null, jcb2, "Select the Like/Follow Strategy for the Producer", JOptionPane.QUESTION_MESSAGE);
      jcb.setEditable(true);
      jcb2.setEditable(true);
      initializeSimulation();
      
      /**Set Up Frame**/ 
	  Frame.add(Space);
	  Frame.setJMenuBar(Menu);
	  DAL.addActionListener(this);
	  DG.addActionListener(this);
	  DNS.addActionListener(this);
	  RST.addActionListener(this);
      Execute.addActionListener(this); 
	  DisplayMenu.add(DAL);
	  DisplayMenu.add(DNS);
	  DisplayMenu.add(DG);
	  FileMenu.add(RST);
	  Menu.add(FileMenu);
	  Menu.add(DisplayMenu);
	  FieldPanel.add(new JLabel("Select the Strategy for Ranking the Top Documents: "));
	  FieldPanel.add(jcb);
	  Frame.add(FieldPanel,BorderLayout.NORTH);
	  ButtonPanel.add(Execute);
	  Frame.add(ButtonPanel,BorderLayout.SOUTH);
	  Frame.add(GraphPanel,BorderLayout.CENTER);
	  CenterItem=GraphPanel;
	  Frame.setSize(620, 550);
	  Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  Frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e ){
		if(e.getSource() == Execute){
		    sim.SimulationCycle(k,(String)jcb.getSelectedItem());		    
			UpdateTextfields();
			UpdateGUI();
		}else if(e.getSource() == DAL){
			DisplayActivityLog();
		}  else if(e.getSource() == RST){
			 Reset();
		} else if(e.getSource() == DG){
			DisplayGraph();
		} else if(e.getSource() == DNS){
		     DisplayNetworkStatistics();
		} 
		}	
		
    public int getIntegerInput(String Message){
         for(;;){
	    try {
           return Integer.parseInt(JOptionPane.showInputDialog(Frame,Message));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Incorrect Input!!!");
        }
      }
    }
   
	public void Reset(){
	    sim = new Simulation();
	    initializeSimulation();
        UpdateTextfields();
		UpdateGUI();
	   }
	   
	public void UpdateGUI(){
	    if(CenterItem instanceof Graph){
			 DisplayGraph();
            }else if(CenterItem instanceof JScrollPane&&ScrollItem instanceof JTextArea){
             DisplayActivityLog();
            }else if(CenterItem instanceof JScrollPane&&ScrollItem instanceof JTextPane){
             DisplayNetworkStatistics();
            }
            }
	   
	   
	public void UpdateTextfields(){
	    textfields.get(0).setText(String.valueOf(no_Producers));
	    textfields.get(1).setText(String.valueOf(sim.totalDocuments().size()));
	    textfields.get(2).setText("Range: 0-"+String.valueOf(sim.data.size()));
	    if(sim.data.size()>0)
		 textfields.get(3).setText("Range: 0-"+String.valueOf(Collections.max(sim.data)-1));
	   }
		
	public void initializeSimulation(){
	  
	  for(int i=0;i<no_Consumers;i++){
           sim.totalUsers.add(new Consumer(TAGS[sim.RandomInt(TAGS.length)],NAMES[sim.RandomInt(NAMES.length)],sim));//Create a User with a random Tag
        } 
      
      for(int i=0;i<no_Producers;i++){
          if(jcb2.getSelectedItem().equals(ACT_STRATEGY[0])){
          sim.totalUsers.add(new Producer_Strategy_A(TAGS[sim.RandomInt(TAGS.length)],NAMES[sim.RandomInt(NAMES.length)],sim));
        }else{
         sim.totalUsers.add(new Producer_Strategy_B(TAGS[sim.RandomInt(TAGS.length)],NAMES[sim.RandomInt(NAMES.length)],sim));   
        }
        }
	   }
	   
	   
    private void DisplayGraph(){
	      GraphPanel = new Graph(sim.data);
	      ChangePage(GraphPanel);
	   }
	     
	private void DisplayNetworkStatistics(){
	   Space.setEditable(true);
	   Space= new JTextPane();
       Space.setText(sim.DisplayNetwork());
       JScrollPane scroll = new JScrollPane (Space, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       ChangePage(scroll);
       ScrollItem=Space;
       Space.setEditable(false);
	   }
	   
	 
	private void DisplayActivityLog(){
	   ActivityLog.setEditable(true);
       ActivityLog = sim.totalUsers.get(0).ActivityLog;
       JScrollPane scroll = new JScrollPane (ActivityLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       ChangePage(scroll);
       ScrollItem=ActivityLog;
       ActivityLog.setEditable(false);
	   }
	   
	public void ChangePage(Component Component){
	     Frame.remove(CenterItem);
	     Frame.add(Component,BorderLayout.CENTER);
	     Frame.revalidate();
	     CenterItem=Component;
	   }
	   
	public static void main(String[] args){
	     GUI gui = new GUI();
	   }
}
     

