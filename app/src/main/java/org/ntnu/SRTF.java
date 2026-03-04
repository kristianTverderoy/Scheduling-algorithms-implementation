package org.ntnu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

public class SRTF implements Strategy {
  // Shortest Remaining Time First

  private List<Process> processes = new CopyOnWriteArrayList<>();
  private Queue<Process> pq = new PriorityBlockingQueue<>(11, Comparator.comparingInt(Process::getRemainingBurstTime)
          .thenComparingInt(Process::getArrivalTime));
  private int totalTime;


  public SRTF(List<Process> processList) {
    this.processes = processList;
  }

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
    return finishedProcesses;
  }

  public synchronized void addProcess(Process process) {
    process.setArrivalTime(this.totalTime);
    this.processes.add(process);
    this.pq.add(process);
  }

}
