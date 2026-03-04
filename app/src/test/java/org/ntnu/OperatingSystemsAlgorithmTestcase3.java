package org.ntnu;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OperatingSystemsAlgorithmTestcase3 {

  private List<Process> pl;
  private Context c;

  @Before
  public void initialize() {
    this.pl = new ArrayList<>(Arrays.asList(
            new Process(1, 20, 0),
            new Process(2, 2, 1),
            new Process(3, 2, 2),
            new Process(4, 2, 3),
            new Process(5, 2, 4),
            new Process(6, 2, 5)));
    this.c = new Context();
  }

  @Test
  public void FCFSAlgorithmCase3() {
    this.c.setStrategy(new FCFS(this.pl));
    List<Process> values = this.c.executeStrat();
    values.sort(Comparator.comparingInt(Process::getID));

    // Process 1: Arrival time = 0, Burst time = 20
    assertEquals(20, values.get(0).getCompletionTime());
    assertEquals(0, values.get(0).getWaitingTime());
    assertEquals(20, values.get(0).getTurnAroundTime());
    // Process 2: Arrival time = 1, Burst time = 2
    assertEquals(22, values.get(1).getCompletionTime());
    assertEquals(19, values.get(1).getWaitingTime());
    assertEquals(21, values.get(1).getTurnAroundTime());
    // Process 3: Arrival time = 2, Burst time = 2
    assertEquals(24, values.get(2).getCompletionTime());
    assertEquals(20, values.get(2).getWaitingTime());
    assertEquals(22, values.get(2).getTurnAroundTime());
    // Process 4: Arrival time = 3, Burst time = 1
    assertEquals(26, values.get(3).getCompletionTime());
    assertEquals(21, values.get(3).getWaitingTime());
    assertEquals(23, values.get(3).getTurnAroundTime());
    // Process 5: Arrival time = 4, Burst time = 3
    assertEquals(28, values.get(4).getCompletionTime());
    assertEquals(22, values.get(4).getWaitingTime());
    assertEquals(24, values.get(4).getTurnAroundTime());

    // Process 6: Arrival time = 5, Burst time = 2
    assertEquals(30, values.get(5).getCompletionTime());
    assertEquals(23, values.get(5).getWaitingTime());
    assertEquals(25, values.get(5).getTurnAroundTime());
    double[] totalWaitTime = { 0 };
    values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
    assertEquals(17.5, totalWaitTime[0] / values.size(), 0.0001);
    double[] totalTT = { 0 };
    values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
    assertEquals(22.5, totalTT[0] / values.size(), 0.0001);
  }



  @Test
  public void SJFAlgorithmCase3() {
    this.c.setStrategy(new SJF(this.pl));
    List<Process> values = this.c.executeStrat();
    values.sort(Comparator.comparingInt(Process::getID));

    // Process 1: Arrival time = 0, Burst time = 20
    assertEquals(20, values.get(0).getCompletionTime());
    assertEquals(0, values.get(0).getWaitingTime());
    assertEquals(20, values.get(0).getTurnAroundTime());
//    // Process 2: Arrival time = 1, Burst time = 2
    assertEquals(22, values.get(1).getCompletionTime());
    assertEquals(19, values.get(1).getWaitingTime());
    assertEquals(21, values.get(1).getTurnAroundTime());
//    // Process 3: Arrival time = 2, Burst time = 2
    assertEquals(24, values.get(2).getCompletionTime());
    assertEquals(20, values.get(2).getWaitingTime());
    assertEquals(22, values.get(2).getTurnAroundTime());
    // Process 4: Arrival time = 3, Burst time = 1
    assertEquals(26, values.get(3).getCompletionTime());
    assertEquals(21, values.get(3).getWaitingTime());
    assertEquals(23, values.get(3).getTurnAroundTime());
    // Process 5: Arrival time = 4, Burst time = 3
    assertEquals(28, values.get(4).getCompletionTime());
    assertEquals(22, values.get(4).getWaitingTime());
    assertEquals(24, values.get(4).getTurnAroundTime());

    // Process 6: Arrival time = 5, Burst time = 2
    assertEquals(30, values.get(5).getCompletionTime());
    assertEquals(23, values.get(5).getWaitingTime());
    assertEquals(25, values.get(5).getTurnAroundTime());

    double[] totalWaitTime = { 0 };
    values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
    assertEquals(17.5, totalWaitTime[0] / values.size(), 0.0001);
    double[] totalTT = { 0 };
    values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
    assertEquals(22.5, totalTT[0] / values.size(), 0.0001);

  }

  @Test
  public void SRTFAlgorithmCase3() {
    this.c.setStrategy(new SRTF(this.pl));
    List<Process> values = this.c.executeStrat();
    values.sort(Comparator.comparingInt(Process::getID));

    // Process 1: Arrival time = 0, Burst time = 20
    assertEquals(30, values.get(0).getCompletionTime());
    assertEquals(10, values.get(0).getWaitingTime());
    assertEquals(30, values.get(0).getTurnAroundTime());
    // Process 2: Arrival time = 1, Burst time = 2
    assertEquals(3, values.get(1).getCompletionTime());
    assertEquals(0, values.get(1).getWaitingTime());
    assertEquals(2, values.get(1).getTurnAroundTime());
    // Process 3: Arrival time = 2, Burst time = 2
    assertEquals(5, values.get(2).getCompletionTime());
    assertEquals(1, values.get(2).getWaitingTime());
    assertEquals(3, values.get(2).getTurnAroundTime());
    // Process 4: Arrival time = 3, Burst time = 1
    assertEquals(7, values.get(3).getCompletionTime());
    assertEquals(2, values.get(3).getWaitingTime());
    assertEquals(4, values.get(3).getTurnAroundTime());
    // Process 5: Arrival time = 4, Burst time = 3
    assertEquals(9, values.get(4).getCompletionTime());
    assertEquals(3, values.get(4).getWaitingTime());
    assertEquals(5, values.get(4).getTurnAroundTime());
    // Process 6: Arrival time = 5, Burst time = 2
    assertEquals(11, values.get(5).getCompletionTime());
    assertEquals(4, values.get(5).getWaitingTime());
    assertEquals(6, values.get(5).getTurnAroundTime());

    double[] totalWaitTime = { 0 };
    values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
    assertEquals(3.33, totalWaitTime[0] / values.size(), 0.01);
    double[] totalTT = { 0 };
    values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
    assertEquals(8.33, totalTT[0] / values.size(), 0.01);

  }




}
