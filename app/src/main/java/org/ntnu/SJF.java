package org.ntnu;

import java.util.ArrayList;
import java.util.List;

public class SJF implements Strategy {
  private int totalTime = 0;
  private List<Process> processes;

  public SJF(List<Process> processes) {
    this.processes = processes;
  }

  @Override
  public void execute() {
    boolean finished = false;
    List<Integer> turnaroundTimes = new ArrayList<Integer>();
    List<Integer> waitingTimes = new ArrayList<Integer>();
    System.out.println("Shortest Job First:\n" +
                      "========================================");

    while (!finished) {
      Process currentProcess = shortestAvailableJob();
      if (shortestAvailableJob() != null) {
        int completionTime = totalTime + currentProcess.getRemainingBurstTime();
        int turnaroundTime = completionTime - currentProcess.getArrivalTime();
        int waitingTime = turnaroundTime - currentProcess.getRemainingBurstTime();

        turnaroundTimes.add(turnaroundTime);
        waitingTimes.add(waitingTime);

        System.out.println(
                            "Process " + currentProcess.getID() + "finished\n" +
                            "Completion time = " + completionTime + "\n" +
                            "Turnaround time = " + turnaroundTime + "\n" +
                            "Waiting time = " + waitingTime + "\n"
                          );
        this.processes.remove(currentProcess);
      }
      this.totalTime++;

      if (this.processes.isEmpty()) {
        finished = true;
      }
    }
    double averageTurnaroundTime = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    double averageWaitingTime = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0.0);

    System.out.println("Average turnaround time = " + averageTurnaroundTime);
    System.out.println("Average waiting time = " + averageWaitingTime);
    System.out.println("Total time spent = " + this.totalTime);
    System.out.println("========================================\n");
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
}
