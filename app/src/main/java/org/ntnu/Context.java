package org.ntnu;

public class Context {

  private Strategy currentStrategy;

  public Context() {
  }

  public void setStrategy(Strategy newStrat) {
    this.currentStrategy = newStrat;
  }

  public void executeStrat() {
    if (this.currentStrategy != null) {
      this.currentStrategy.execute();
    }
  }
}
