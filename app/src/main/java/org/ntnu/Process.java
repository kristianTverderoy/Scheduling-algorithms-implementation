package org.ntnu;

public class Process {
  private int remainingBurstTime;
  private int processID;
  private int arrivalTime;
  private boolean isFinished = false;

  public Process(int processID, int startBurstTime, int arrivalTime) {
    this.remainingBurstTime = startBurstTime;
    this.processID = processID;
    this.arrivalTime = arrivalTime;
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

  public int getID() {
    return this.processID;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }
}
