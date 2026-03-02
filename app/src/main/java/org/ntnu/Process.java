package org.ntnu;

public class Process {
  private int remainingBurstTime;
  private int processID;
  private int arrivalTime;
  private boolean isFinished = false;
  private int completionTime;
  private int turnAroundTime;
  private int waitingTime;
  private int startBurstTime;

  public Process(int processID, int startBurstTime, int arrivalTime) {
    this.remainingBurstTime = startBurstTime;
    this.processID = processID;
    this.arrivalTime = arrivalTime;
    this.startBurstTime = startBurstTime;

  }

  public Process(int processID, int startBurstTime) {
    this.processID = processID;
    this.remainingBurstTime = startBurstTime;
  }

  public void minusOneBurstTime() {
    this.remainingBurstTime -= 1;

    if (this.remainingBurstTime < 1) {
      setFinished();
    }
  }

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
