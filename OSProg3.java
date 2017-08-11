

/*Program simulates the following process scheduling algorithms:
    FCFS
    SJF
    Priority First
    Round Robin

  From the command line, program takes in the input file name as one of the arguments
  The input file will be a CSV file in which the columns contain information about
  process priority, submision time, CPU burst time, and IO burst time. The number
  of rows in the file is the number of process profiles.
  

  Program analyzes the given sets of process profiles:
    Average Wait time
    Average Turnaround time
    Throughput
    per mys */

package osprog3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;






public class OSProg3 {
    
    private static void simulation( String alg,LinkedList<Process> process){
        int timer = 0,avgWait = 0, avgTurn = 0;
        
        for(int j = 0; j < process.size();j++){            
            //Set start time
            process.get(j).setStartTime(timer); 
            //Incement timer
            timer += process.get(j).getBurstTime();
            //Set completion time
            process.get(j).setCompletionTime(timer);
            //Calc waiting time
            avgWait += process.get(j).calcWaitingTime();
            //Set turn time
            avgTurn += process.get(j).calcTurnTime();
        }
        System.out.println(alg);
        System.out.println("Order of Completion");
        for(int i = 0; i < process.size(); i++){
            System.out.println(process.get(i));   
        }
        System.out.println(" Avg wait time: " + avgWait/process.size()+ " Avg tunaround time " + avgTurn/process.size() + "Throughput: " + process.size()/(double)timer );
       

}
    
    private static void RRsimulation( LinkedList<Process> queue){
        int timer = 0,avgWait = 0, avgTurn = 0, cpuBurst = 0, j = 0;
        LinkedList<Process> doneQueue = new LinkedList<>();
        
        for(int i = 0; i < queue.size();i++){             
            cpuBurst += queue.get(i).getBurstTime();
        }
        cpuBurst /= queue.size();
        
        while(!queue.isEmpty()){
            for(;j <queue.size();j++ ){
                queue.get(j).setStartTime(timer);
                queue.get(j).changeBurstTime(cpuBurst);
                
                if(queue.get(j).getBurstTime() <= 0){
                    timer += (queue.get(j).getBurstTime()+ cpuBurst);  
                    queue.get(j).setCompletionTime(timer);
                    avgTurn += queue.get(j).calcTurnTime();
                    avgWait += queue.get(j).calcWaitingTime();
                    doneQueue.add(queue.remove(j));
                }else{
                    timer += cpuBurst;
                }
             
            }  
            if(!queue.isEmpty()){
                j = 0;   
            }
            
        }
        
        System.out.println("Round Robin");
        System.out.println("Order of Completion");
        for(int i = 0; i < doneQueue.size(); i++){
            System.out.println(doneQueue.get(i));   
        }
        System.out.println(" Avg wait time: " + avgWait/doneQueue.size()+ " Avg tunaround time " + avgTurn/doneQueue.size() + "Throughput: " + doneQueue.size()/(double)timer );
       

}
    

    public static void sortByBurst(Process x, LinkedList<Process> target){
        if (target.isEmpty()){
            target.add(x);
        }else{
            for(int i = 0; i < target.size(); i++){
              if(x.getBurstTime() < target.get(i).getBurstTime()){
                  target.add(i,x);
                  break;
              }
            }
        }
        
        if(!target.contains(x)){
            target.addLast(x);
        }
    }
    
    public static void sortByPriority(Process x, LinkedList<Process> target){
        if (target.isEmpty()){
            target.add(x);
        }else{
            for(int i = 0; i < target.size(); i++){
              if(x.getPriority() < target.get(i).getPriority()){
                  target.add(i,x);
                  break;
              }
            }
        }
        if(!target.contains(x)){
            target.addLast(x);
        }       
    }
    
    public static void sortBySubmission(Process x, LinkedList<Process> target){
        if (target.isEmpty()){
            target.add(x);
        }else{
            for(int i = 0; i < target.size(); i++){
              if(x.getPriority() < target.get(i).getSubmissionTime()){
                  target.add(i,x);
                  break;
              }
            }
        }
        if(!target.contains(x)){
            target.addLast(x);
        }       
    }
    
    public static void main(String[] args) {
        
        LinkedList<Process> queue = new LinkedList<>();
        BufferedReader br = null;
        String filename = args[0], line = "";
        int j = 0;
       
        //Fills linked list with process objects using information from csv file
        try{
            br = new BufferedReader(new FileReader(filename));
            while((line = br.readLine()) != null){
                String[] data = line.split(","); 
                queue.add(new Process(j,Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
                j++;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }    
        }
        
        LinkedList<Process> submissionSorted = new LinkedList<>();
        for(int i = 0; i < queue.size(); i++){
            sortBySubmission(queue.get(i), submissionSorted);
        }
        simulation("FCFS",submissionSorted);
               
               
        LinkedList<Process> burstSorted = new LinkedList<>();
        for(int i = 0; i < queue.size(); i++){
            sortByBurst(queue.get(i), burstSorted);
        }
        simulation("SJF",burstSorted);
        
        LinkedList<Process> prioritySorted = new LinkedList<>();
        for(int i = 0; i < queue.size(); i++){
            sortByPriority(queue.get(i), prioritySorted);
        }
        simulation("Priority First",prioritySorted);
               
        RRsimulation(queue);
    }
        
     
    }
    
