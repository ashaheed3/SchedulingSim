
package osprog3;


public class Process {
    
    private int  priority, submissionTime, burstTime,startTime,
                waitingTime, turnTime,completionTime, processId;
    
    public Process(){
        processId = -1;
        priority = 0;
        submissionTime = 0;
        burstTime = 0;
        startTime = 0;
        waitingTime = 0;
        turnTime = 0;
        completionTime = 0;
        
    }
    
    public Process(int processId, int priority, int submissionTime, int burstTime){
        this.processId = processId;
        this.priority = priority;
        this.submissionTime = submissionTime;
        this.burstTime = burstTime;
        this.startTime = 0;
        this.waitingTime = 0;
        this.turnTime = 0;
        this.completionTime = 0;
        
    }
    
    public int setStartTime(int startTime){
        return this.startTime = startTime;
    } 
    public int setCompletionTime(int completionTime){
        return this.completionTime = completionTime;
    }
    public int calcWaitingTime(){
        return waitingTime = startTime - submissionTime;
    }
    public int calcTurnTime(){
        return turnTime = completionTime - submissionTime;
    }
    public int changeBurstTime(int x){
        return burstTime -= x;
    }
    
    
    
    public int getStartTime(){
        return startTime;
    }
    public int getCompletionTime(){
        return completionTime;
    }
    public int getWaitingTime(){
        return waitingTime;
    }
    public int getTurnTime(){
        return turnTime;
    }
    public int getPriority(){
        return priority;
    }
    public int getSubmissionTime(){
        return submissionTime;
    }
    public int getBurstTime(){
        return burstTime;
    }
    
    
     //@Override
     public String toString(){
         return  "Process Id: " + processId + "Priority: " + priority + " Submission time: " + submissionTime;
     }         
}
