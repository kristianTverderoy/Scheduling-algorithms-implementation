package org.ntnu;

import java.util.List;

/**
 * Context for CPU scheduling strategies.
 * Delegates execution to the currently set strategy.
 */
public class Context {

  // The currently active scheduling strategy
  private Strategy currentStrategy;

  public Context() {
  }

  public void setStrategy(Strategy newStrat) {
    this.currentStrategy = newStrat;
  }

  /**
   * Executes the current scheduling strategy.
   *
   * @return a list of processes with updated scheduling metrics, or {@code null} if no strategy is set
   */
  public List<Process> executeStrat() {
    if (this.currentStrategy != null) {
      return this.currentStrategy.execute();
    }
    return null;

  }
}
