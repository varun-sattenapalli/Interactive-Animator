package cs3500.animator.util.model;


import cs3500.animator.util.model.shapes.IShape;

/**
 * Represents a motion for a shape, i.e. its features
 * (dimensions, position, and/or color) changing at a tick.
 */
public class Motion implements IMotion {
  private IShape shape;
  private int t1;
  private double x1;
  private double y1;
  private double w1;
  private double h1;
  private int r1;
  private int g1;
  private int b1;
  private int t2;
  private double x2;
  private double y2;
  private double w2;
  private double h2;
  private int r2;
  private int g2;
  private int b2;

  /**
   * Constructs a motion.
   *
   * @param shape shape
   * @param t1    starting time of motion
   * @param x1    starting x coordinate of shape
   * @param y1    starting y coordinate of shape
   * @param w1    starting width of shape
   * @param h1    starting height of shape
   * @param r1    starting red of shape
   * @param g1    starting green of shape
   * @param b1    starting blue of shape
   * @param t2    ending tick of motion
   * @param x2    ending x coordinate of shape
   * @param y2    ending y coordinate of shape
   * @param w2    ending width of shape
   * @param h2    ending height of shape
   * @param r2    ending red of shape
   * @param g2    ending green of shape
   * @param b2    ending blue of shape
   */
  public Motion(IShape shape, int t1, double x1, double y1, double w1, double h1, int r1,
                int g1, int b1, int t2, double x2, double y2, double w2, double h2, int r2,
                int g2, int b2) {
    this.shape = shape;
    this.t1 = t1;
    this.x1 = x1;
    this.y1 = y1;
    this.w1 = w1;
    this.h1 = h1;
    this.r1 = r1;
    this.g1 = g1;
    this.b1 = b1;
    this.t2 = t2;
    this.x2 = x2;
    this.y2 = y2;
    this.w2 = w2;
    this.h2 = h2;
    this.r2 = r2;
    this.g2 = g2;
    this.b2 = b2;
  }

  @Override
  public void apply(int tick) {
    if (tick < t1 || tick > t2) {
      throw new IllegalArgumentException("Can only apply motion at a tick in between the start"
              + " and end ticks.");
    }
    if (t2 == t1) {
      this.shape.setX(x2);
      this.shape.setY(y2);
      this.shape.setWidth(w2);
      this.shape.setHeight(h2);
      this.shape.setR(r2);
      this.shape.setG(g2);
      this.shape.setB(b2);
    } else {
      this.shape.setX(Math.round( (x1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + x2 * ((double) (tick - t1) / (double) (t2 - t1)))));
      this.shape.setY( Math.round((y1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + y2 * ((double) (tick - t1) / (t2 - t1)))));
      this.shape.setWidth(Math.round((w1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + w2 * ((double) (tick - t1) / (double) (t2 - t1)))));
      this.shape.setHeight(Math.round((h1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + h2 * ((double) (tick - t1) / (double) (t2 - t1)))));
      this.shape.setR((int) (r1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + r2 * ((double) (tick - t1) / (double) (t2 - t1))));
      this.shape.setG((int) (g1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + g2 * ((double) (tick - t1) / (double) (t2 - t1))));
      this.shape.setB((int) (b1 * ((double) (t2 - tick) /
              (double) (t2 - t1)) + b2 * ((double) (tick - t1) / (double) (t2 - t1))));
    }
  }

  @Override
  public int gett1() {
    return this.t1;
  }

  @Override
  public double getx1() {
    return this.x1;
  }

  @Override
  public double gety1() {
    return this.y1;
  }

  @Override
  public double getw1() {
    return this.w1;
  }

  @Override
  public double geth1() {
    return this.h1;
  }

  @Override
  public int getr1() {
    return this.r1;
  }

  @Override
  public int getg1() {
    return this.g1;
  }

  @Override
  public int getb1() {
    return this.b1;
  }

  @Override
  public int gett2() {
    return this.t2;
  }

  @Override
  public double getx2() {
    return this.x2;
  }

  @Override
  public double gety2() {
    return this.y2;
  }

  @Override
  public double getw2() {
    return this.w2;
  }

  @Override
  public double geth2() {
    return this.h2;
  }

  @Override
  public int getr2() {
    return this.r2;
  }

  @Override
  public int getg2() {
    return this.g2;
  }

  @Override
  public int getb2() {
    return this.b2;
  }

}
