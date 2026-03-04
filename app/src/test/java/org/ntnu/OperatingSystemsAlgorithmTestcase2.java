package org.ntnu;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OperatingSystemsAlgorithmTestcase2 {

  private List<Process> pl;
  private Context c;

  @Before
  public void initialize() {
    this.pl = new ArrayList<>(Arrays.asList(
            new Process(1, 20, 0),
            new Process(2, 2, 1),
            new Process(3, 2, 2),
            new Process(4, 1, 3),
            new Process(5, 3, 4)));
    this.c = new Context();
  }

  @Test
  public void FCFSAlgorithmCase2() {
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
    assertEquals(25, values.get(3).getCompletionTime());
    assertEquals(21, values.get(3).getWaitingTime());
    assertEquals(22, values.get(3).getTurnAroundTime());
    // Process 5: Arrival time = 4, Burst time = 3
    assertEquals(28, values.get(4).getCompletionTime());
    assertEquals(21, values.get(4).getWaitingTime());
    assertEquals(24, values.get(4).getTurnAroundTime());

    double[] totalWaitTime = { 0 };
    values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
    assertEquals(16.2, totalWaitTime[0] / values.size(), 0.0001);
    double[] totalTT = { 0 };
    values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
    assertEquals(21.8, totalTT[0] / values.size(), 0.0001);
   }

   @Test
   public void SJFAlgorithmCase2() {
     this.c.setStrategy(new SJF(this.pl));
     List<Process> values = this.c.executeStrat();
     values.sort(Comparator.comparingInt(Process::getID));

     // Process 1: Arrival time = 0, Burst time = 20
     assertEquals(20, values.get(0).getCompletionTime());
     assertEquals(0, values.get(0).getWaitingTime());
     assertEquals(20, values.get(0).getTurnAroundTime());
     // Process 2: Arrival time = 1, Burst time = 2
     assertEquals(23, values.get(1).getCompletionTime());
     assertEquals(20, values.get(1).getWaitingTime());
     assertEquals(22, values.get(1).getTurnAroundTime());
     // Process 3: Arrival time = 2, Burst time = 2
     assertEquals(25, values.get(2).getCompletionTime());
     assertEquals(21, values.get(2).getWaitingTime());
     assertEquals(23, values.get(2).getTurnAroundTime());
     // Process 4: Arrival time = 3, Burst time = 1
     assertEquals(21, values.get(3).getCompletionTime());
     assertEquals(17, values.get(3).getWaitingTime());
     assertEquals(18, values.get(3).getTurnAroundTime());
     // Process 5: Arrival time = 4, Burst time = 3
     assertEquals(28, values.get(4).getCompletionTime());
     assertEquals(21, values.get(4).getWaitingTime());
     assertEquals(24, values.get(4).getTurnAroundTime());

     double[] totalWaitTime = { 0 };
     values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
     assertEquals(15.8, totalWaitTime[0] / values.size(), 0.0001);
     double[] totalTT = { 0 };
     values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
     assertEquals(21.4, totalTT[0] / values.size(), 0.0001);

   }

   @Test
    public void SRTFAlgorithmCase2() {
      this.c.setStrategy(new SRTF(this.pl));
      List<Process> values = this.c.executeStrat();
      values.sort(Comparator.comparingInt(Process::getID));

      // Process 1: Arrival time = 0, Burst time = 20
      assertEquals(28, values.get(0).getCompletionTime());
      assertEquals(8, values.get(0).getWaitingTime());
      assertEquals(28, values.get(0).getTurnAroundTime());
      // Process 2: Arrival time = 1, Burst time = 2
      assertEquals(3, values.get(1).getCompletionTime());
      assertEquals(0, values.get(1).getWaitingTime());
      assertEquals(2, values.get(1).getTurnAroundTime());
      // Process 3: Arrival time = 2, Burst time = 2
      assertEquals(6, values.get(2).getCompletionTime());
      assertEquals(2, values.get(2).getWaitingTime());
      assertEquals(4, values.get(2).getTurnAroundTime());
      // Process 4: Arrival time = 3, Burst time = 1
      assertEquals(4, values.get(3).getCompletionTime());
      assertEquals(0, values.get(3).getWaitingTime());
      assertEquals(1, values.get(3).getTurnAroundTime());
      // Process 5: Arrival time = 4, Burst time = 3
      assertEquals(9, values.get(4).getCompletionTime());
      assertEquals(2, values.get(4).getWaitingTime());
      assertEquals(5, values.get(4).getTurnAroundTime());

     double[] totalWaitTime = { 0 };
     values.forEach(waitTime -> totalWaitTime[0] += waitTime.getWaitingTime());
     assertEquals(2.4, totalWaitTime[0] / values.size(), 0.0001);
     double[] totalTT = { 0 };
     values.forEach(tTime -> totalTT[0] += tTime.getTurnAroundTime());
     assertEquals(8.0, totalTT[0] / values.size(), 0.0001);

    }




}
