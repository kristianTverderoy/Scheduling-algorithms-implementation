package org.ntnu;

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
    int[] turnaroundTimes;
    int[] waitingTimes;
    System.out.println("Shortest Job First:\n" +
                      "========================================");

    while (!finished) {
      Process currentProcess = shortestAvailableJob();
      if (shortestAvailableJob() != null) {


      }

        this.totalTime++;
    }
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
