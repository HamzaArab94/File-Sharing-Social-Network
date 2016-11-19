import java.awt.*;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Collections;

public class Graph  extends JPanel{
   private static final Color GRAPH_COLOR = Color.blue;
   private static final Stroke Line_Thickness = new BasicStroke(3f);//Thickness
   private static final int X_HATCHLENGTH = 13;
   private static final int Y_HATCHLENGTH = 13;
   private int ORIGIN_X_COORDINATE=30;
   private int ORIGIN_Y_COORDINATE=300;
   private int MAXIMUM_Y_COORDINATE=30;
   private int MAXIMUM_X_COORDINATE=550;
   private List<Integer> data;
   private List<Point> graphPoints;

   public Graph(List<Integer> data) {
      this.data = data;
      graphPoints = new ArrayList<Point>();
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      addGraphPoints(); 
      drawAxes(g2);
      Hash_Y_Axis(g2);
      Hash_X_Axis(g2);
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(Line_Thickness);
      connectPoints(g2,graphPoints);  
   }

   public void addGraphPoints(){
      double xSegmentLength = ((double)(MAXIMUM_X_COORDINATE-  ORIGIN_X_COORDINATE)) / (data.size() - 1);  // The distance between two vertical Hashlines on X -Axis
      double yScale;
      int  y1 = ORIGIN_Y_COORDINATE;
      for (int i = 0; i < data.size(); i++) {
         int x1 = (int) (i *  xSegmentLength +  ORIGIN_X_COORDINATE); // Place points so their Xcoordinate are equal to X axis Hashlines
         if(data.size()>0&&Collections.max(data)>1){
          yScale = (double) (ORIGIN_Y_COORDINATE - MAXIMUM_Y_COORDINATE) / (Collections.max(data)-1);
          y1 = (int) ((Collections.max(data)  - data.get(i)) * yScale +MAXIMUM_Y_COORDINATE);//here
        }///
         graphPoints.add(new Point(x1, y1));
      }
    }
  
   /** Draws Hashlines on the X Axis**/
   public void Hash_X_Axis(Graphics2D graph){
     for (int i = 0; i < data.size() - 1; i++) {
         int x0 = (i + 1) * (MAXIMUM_X_COORDINATE -  ORIGIN_X_COORDINATE) / (data.size() - 1) +  ORIGIN_X_COORDINATE;
         int x1 = x0;
         int y0 = ORIGIN_Y_COORDINATE;
         int y1 = y0 - X_HATCHLENGTH;
         graph.drawLine(x0, y0, x1, y1);
      }
    }
   
   /** Draws Hashlines on the Y Axis**/
   public void Hash_Y_Axis(Graphics2D graph){
    int no_Segments=0;
    if(data.size()>0)
       no_Segments=Collections.max(data);
    for (int i = 0; i < no_Segments; i++) {
         int x0 =  ORIGIN_X_COORDINATE; //All Horizontal hatches start on Y axis  (X=0)
         int x1 =  ORIGIN_X_COORDINATE + Y_HATCHLENGTH;
         int y0 = (ORIGIN_Y_COORDINATE+ ORIGIN_X_COORDINATE) - (((i + 1) * (ORIGIN_Y_COORDINATE -MAXIMUM_Y_COORDINATE)) / no_Segments + ORIGIN_X_COORDINATE);
         int y1 = y0;  //Horizontal Hatch lines have constant y coordinates
         graph.drawLine(x0, y0, x1, y1);
      }
    }
 
    
  public void connectPoints(Graphics2D graph, List<Point> graphPoints){
        for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         graph.drawLine(x1, y1, x2, y2);         
      }
    }
    
  public void drawAxes(Graphics2D graph){
       graph.drawLine( ORIGIN_X_COORDINATE,ORIGIN_Y_COORDINATE, ORIGIN_X_COORDINATE, MAXIMUM_Y_COORDINATE); // Draw Y-Axis
       graph.drawLine( ORIGIN_X_COORDINATE ,ORIGIN_Y_COORDINATE, MAXIMUM_X_COORDINATE, ORIGIN_Y_COORDINATE); // Draw X-Axis
    }
}