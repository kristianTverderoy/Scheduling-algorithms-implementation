package org.ntnu;

import java.util.List;

public class Context {

  private Strategy currentStrategy;

  public Context() {
  }

  public void setStrategy(Strategy newStrat) {
    this.currentStrategy = newStrat;
  }

  public List<Process> executeStrat() {
    if (this.currentStrategy != null) {
      return this.currentStrategy.execute();
    }
    return null;

  }
}
