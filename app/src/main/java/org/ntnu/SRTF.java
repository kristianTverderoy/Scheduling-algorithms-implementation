package org.ntnu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Shortest Remaining Time First CPU scheduling.
 * Preemptive
 */
public class SRTF implements Strategy {
  // Shortest Remaining Time First
  private List<Process> processes = new CopyOnWriteArrayList<>();
  private Queue<Process> pq = new PriorityBlockingQueue<>(11, Comparator.comparingInt(Process::getRemainingBurstTime)
          .thenComparingInt(Process::getArrivalTime));
  private int totalTime;


  /**
   * Creates an SRTF scheduler with the given list of processes.
   *
   * @param processList the list of processes to schedule
   */
  public SRTF(List<Process> processList) {
    this.processes = processList;
  }

  /**
   * Executes the SRTF scheduling algorithm.
   * At each time unit, the process with the shortest remaining burst time is selected.
   *
   * @return a list of processes with updated completion, turnaround, and waiting times
   */
  @Override
  public List<Process> execute() {
    List<Process> finishedProcesses = new ArrayList<>();
    int currentTime = 0;
    int completed = 0;

    while (completed < processes.size()) {
      // Add all processes that have arrived by currentTime
      for (Process p : processes) {
        if (p.getArrivalTime() <= currentTime
            && !p.isFinished()
            && !pq.contains(p)) {
          pq.add(p);
        }
      }

      if (!pq.isEmpty()) {
        Process current = pq.poll();
        if (current.getID() == 3) {
          System.out.println("Process 3 finished at time: " + currentTime);
        }
        current.minusOneBurstTime();
        currentTime++;

//        // Re-check for newly arrived processes before re-adding
//        for (Process p : processes) {
//
//          if (p.getArrivalTime() <= currentTime
//              && !p.isFinished()
//              && !pq.contains(p)
//              && p != current) {
//            pq.add(p);
//          }
//        }

        if (current.isFinished()) {

          current.setCompletionTime(currentTime);
          current.setTurnAroundTime(currentTime - current.getArrivalTime());
          current.setWaitingTime(current.getTurnAroundTime() - current.getStartBurstTime());
          finishedProcesses.add(current);
          completed++;
        } else {
            pq.add(current);


        }
      } else {
        // No process available, advance time
        currentTime++;
      }
    }
    printResults(finishedProcesses, currentTime);
    return finishedProcesses;
  }

  /**
   * Adds a new process to the scheduler at the current total time.
   *
   * @param process the process to add
   */
  public synchronized void addProcess(Process process) {
    process.setArrivalTime(this.totalTime);
    this.processes.add(process);
    this.pq.add(process);
  }

  /**
   * Prints scheduling results including per-process stats and averages.
   *
   * @param p the list of finished processes
   * @param time the total time spent executing all processes
   */
  public void printResults(List<Process> p, int time) {
    System.out.println("Shortest Remaining Time First:\n" +
                      "========================================");

    double averageTurnaroundTime = 0;
    double averageWaitingTime = 0;
    for (Process process : p) {
      averageTurnaroundTime += process.getTurnAroundTime();
      averageWaitingTime += process.getWaitingTime();
      System.out.println(
                          "Process " + process.getID() + " finished\n" +
                          "Completion time = " + process.getCompletionTime() + "\n" +
                          "Turnaround time = " + process.getTurnAroundTime() + "\n" +
                          "Waiting time = " + process.getWaitingTime() + "\n"
                        );
    }
    System.out.println("Average turnaround time = " + averageTurnaroundTime/p.size());
    System.out.println("Average waiting time = " + averageWaitingTime/p.size());
    System.out.println("Total time spent = " + time);
    System.out.println("========================================\n");
  }
}
