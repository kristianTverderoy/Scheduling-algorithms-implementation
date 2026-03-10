package org.ntnu;

/**
 * Represents a process in a CPU scheduling simulation.
 * Stores burst time, arrival time, and computed scheduling metrics.
 */
public class Process {
  private int remainingBurstTime;
  private int processID;
  private int arrivalTime;
  private boolean isFinished = false;
  private int completionTime;
  private int turnAroundTime;
  private int waitingTime;
  private int startBurstTime;


  /**
   * Creates a process with the given ID, burst time, and arrival time.
   *
   * @param processID       the unique identifier for this process
   * @param startBurstTime  the initial burst time of the process
   * @param arrivalTime     the time at which the process arrives in the queue
   */
  public Process(int processID, int startBurstTime, int arrivalTime) {
    this.remainingBurstTime = startBurstTime;
    this.processID = processID;
    this.arrivalTime = arrivalTime;
    this.startBurstTime = startBurstTime;

  }
  //UNUSED
  public Process(int processID, int startBurstTime) {
    this.processID = processID;
    this.remainingBurstTime = startBurstTime;
  }

  /**
   * Decrements the remaining burst time by one.
   * Marks the process as finished if remaining burst time drops below one.
   */
  public void minusOneBurstTime() {
    this.remainingBurstTime -= 1;

    if (this.remainingBurstTime < 1) {
      setFinished();
    }
  }

  /**
   * Returns whether the process has finished execution.
   *
   * @return {@code true} if the process is finished, {@code false}
   */
  public boolean isFinished() {
    return this.isFinished;
  }

  private void setFinished() {
    this.isFinished = true;
  }

  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public int getID() {
    return this.processID;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }

  public int getRemainingBurstTime() {
    return this.remainingBurstTime;
  }

  public int getStartBurstTime() {
    return this.startBurstTime;
  }

  public void setCompletionTime(int completionTime) {
    this.completionTime = completionTime;
  }

  public void setTurnAroundTime(int turnAroundTime) {
    this.turnAroundTime = turnAroundTime;
  }

  public void setWaitingTime(int waitingTime) {
    this.waitingTime = waitingTime;
  }

  public int getCompletionTime() {
    return this.completionTime;
  }

  public int getTurnAroundTime() {
    return this.turnAroundTime;
  }

  public int getWaitingTime() {
    return this.waitingTime;
  }

}
