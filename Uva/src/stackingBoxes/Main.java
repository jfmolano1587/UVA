package stackingBoxes;
// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
        String input;
        StringTokenizer idata;

        while ((input = Main.ReadLn (255)) != null)
        {
          idata = new StringTokenizer (input);
          int num_boxes = Integer.parseInt (idata.nextToken());
          int num_dimentions = Integer.parseInt (idata.nextToken());
          List<Box> boxes_order = new ArrayList<Box>();
          for (int i = 0;i<num_boxes;i++){
        	  int ordered_box [] = new int[num_dimentions];
        	  StringTokenizer box_numbers = new StringTokenizer ( Main.ReadLn (255) );
        	  int dim_sum = 0;
        	  for (int j=0;j<num_dimentions;j++){
        		  int dim = Integer.parseInt (box_numbers.nextToken());
        		  ordered_box[j] = dim;
        		  dim_sum += dim;
        	  }
        	  Arrays.sort(ordered_box);
        	  Box cu_box = new Box(ordered_box,dim_sum,i+1);
        	  boxes_order.add(cu_box);
          }
          Collections.sort(boxes_order, new BoxComparator());
          /*
          for(int k=0;k<boxes_order.size();k++){
        	  System.out.print(boxes_order.get(k).getId()+" - ");
              for(int l=0;l<boxes_order.get(k).getDim().length;l++){
            	  System.out.print(boxes_order.get(k).getDim()[l]+" ");
              }
        	  System.out.println("");
          }*/
          String answer = boxes_order.get(0).getId()+"";
          int answer_size = 1;
          int index_1 = 0;
          int index_2 = 1;
          while(index_2 != boxes_order.size()){
        	  Box b_1 = boxes_order.get(index_1);
        	  Box b_2 = boxes_order.get(index_2);
        	  if(b_1.getSum() == b_2.getSum()){
        		  index_2++;
        	  }
        	  else{
        		  boolean fits = true;
        		  for(int k = 0;k<num_dimentions && fits;k++){
        			  int dim_1 = b_1.getDim()[k];
        			  int dim_2 = b_2.getDim()[k];
        			  if(dim_2<=dim_1){
        				  fits = false;
                		  index_2++;
        			  }
        		  }
        		  if(fits){
        			  answer = answer + " " + b_2.getId();
        			  index_1 = index_2;
        			  index_2++;
        			  answer_size++;
        		  }
        	  }
          }
          System.out.println(answer_size);
    	  System.out.println(answer);
        }
    }
    
    public class Box {
    	private int[] dimentions;
    	private int sum;
    	private int box_id;
        public Box(int[] dimentions,int sum,int box_id){
        	this.dimentions = dimentions;
        	this.sum = sum;
        	this.box_id = box_id;
        }
        public int[] getDim(){
        	return dimentions;
        }
        public int getSum(){
        	return sum;
        }
        public int getId(){
        	return box_id;
        }
    }
    
    public class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box o1, Box o2) {
            return o1.getSum() - o2.getSum();
        }
    }
}



