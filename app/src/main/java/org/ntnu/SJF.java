package org.ntnu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Shortest Job First.
 * No preemption.
 */
public class SJF implements Strategy {
  private int totalTime = 0;
  private List<Process> processes;
  private List<Process> finishedProcesses = new ArrayList<>();

  /**
   * Creates a SJF scheduler with the given list of processes.
   *
   * @param processes the list of processes to schedule
   */
  public SJF(List<Process> processes) {
    this.processes = processes;
  }

  /**
   * Executes the SJF scheduling algorithm.
   * Always selects the available process with the smallest burst time.
   *
   * @return a list of processes with updated completion, turnaround, and waiting times
   */
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


  /**
   * Finds the shortest available job at the current time.
   * A process is available if its arrival time is less than or equal to the current time.
   *
   * @return the process with the smallest remaining burst time, or {@code null} if none are available
   */
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

  /**
   * Prints scheduling results including per-process stats and averages.
   */
  public void printResults() {
    System.out.println("Shortest Job First:\n" +
                      "========================================");

    double averageTurnaroundTime = 0;
    double averageWaitingTime = 0;
    for (Process process : this.finishedProcesses) {
      averageTurnaroundTime += process.getTurnAroundTime();
      averageWaitingTime += process.getWaitingTime();
      System.out.println(
                          "Process " + process.getID() + " finished\n" +
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
