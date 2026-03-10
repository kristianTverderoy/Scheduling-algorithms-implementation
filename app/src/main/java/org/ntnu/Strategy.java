package org.ntnu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for CPU scheduling strategies.
 * All scheduling algorithms must implement this interface
 */
public interface Strategy {

  /**
   * Executes the scheduling algorithm.
   *
   * @return a list of processes with updated completion, turnaround, and waiting times
   */
  public List<Process> execute();
}
