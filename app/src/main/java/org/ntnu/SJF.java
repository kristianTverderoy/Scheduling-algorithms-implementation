package org.ntnu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF implements Strategy {
  private int totalTime = 0;
  private List<Process> processes;
  private List<Process> finishedProcesses = new ArrayList<>();

  public SJF(List<Process> processes) {
    this.processes = processes;
  }

  @Override
  public List<Process> execute() {
    boolean finished = false;
    

    while (!finished) {
      Process currentProcess = shortestAvailableJob();
      if (shortestAvailableJob() != null) {
        currentProcess.setCompletionTime(this.totalTime + currentProcess.getRemainingBurstTime());
        currentProcess.setTurnAroundTime(currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
        currentProcess.setWaitingTime(currentProcess.getTurnAroundTime() - currentProcess.getRemainingBurstTime());

        this.finishedProcesses.add(currentProcess);
        this.processes.remove(currentProcess);
        this.totalTime += currentProcess.getStartBurstTime();
      } else {
        this.totalTime++;
      }


      if (this.processes.isEmpty()) {
        finished = true;
        printResults();
        return this.finishedProcesses;
      }
    }

    return null;
  }


  public Process shortestAvailableJob() {
    Process shortestProcess = null;
    for (Process process : this.processes) {
      if (process.getArrivalTime() <= this.totalTime) {
        if (shortestProcess == null
          || shortestProcess.getRemainingBurstTime() > process.getRemainingBurstTime()) {

          shortestProcess = process;
        }
      }
    }
    return shortestProcess;
  }

  public void printResults() {
    System.out.println("Shortest Job First:\n" +
                      "========================================");

    double averageTurnaroundTime = 0;
    double averageWaitingTime = 0;
    for (Process process : this.finishedProcesses) {
      System.out.println(
                          "Process " + process.getID() + "finished\n" +
                          "Completion time = " + process.getCompletionTime() + "\n" +
                          "Turnaround time = " + process.getTurnAroundTime() + "\n" +
                          "Waiting time = " + process.getWaitingTime() + "\n"
                        );
    }
    System.out.println("Average turnaround time = " + averageTurnaroundTime/this.finishedProcesses.size());
    System.out.println("Average waiting time = " + averageWaitingTime/this.finishedProcesses.size());
    System.out.println("Total time spent = " + this.totalTime);
    System.out.println("========================================\n");
  }
}
