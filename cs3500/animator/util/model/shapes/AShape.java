package cs3500.animator.util.model.shapes;

/**
 * Represents any possible shape.
 */
public abstract class AShape implements IShape {
  private final String name;
  private double x;
  private double y;
  private double width;
  private double height;
  private int r;
  private int g;
  private int b;

  /**
   * Constructs a shape.
   *
   * @param name of shape
   * @param x coordinate of shape
   * @param y coordinate of shape
   * @param width of shape
   * @param height of shape
   * @param r value of rgb for shape
   * @param g value of rgb for shape
   * @param b value of rgb for shape
   */
  public AShape(String name, double x, double y, int width, int height, int r, int g,
      int b) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the name of the shape.
   *
   * @return name of shape.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets x coordinate of shape.
   *
   * @return x coordinate of shape
   */
  public double getX() {
    return this.x;
  }

  /**
   * Sets the x value.
   *
   * @param x value to set x equal to
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Gets y coordinate of shape.
   *
   * @return y coordinate of shape
   */
  public double getY() {
    return this.y;
  }

  /**
   * Sets the y value.
   *
   * @param y coordinate of shape
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Gets width of shape.
   *
   * @return width of shape
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Sets the width value.
   *
   * @param width of the shape
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Gets height coordinate of shape.
   *
   * @return height coordinate of shape
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Sets height coordinate of shape.
   *
   * @param height of the shape
   */
  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Gets r value from rgb of shape.
   *
   * @return r value of shape
   */
  public int getR() {
    return this.r;
  }

  /**
   * Sets R value of shape.
   *
   * @param r of the shape
   */
  public void setR(int r) {
    this.r = r;
  }

  /**
   * Gets g value from rgb of shape.
   *
   * @return g value of shape
   */
  public int getG() {
    return this.g;
  }

  /**
   * Sets g value of shape.
   *
   * @param g of the shape
   */
  public void setG(int g) {
    this.g = g;
  }

  /**
   * Gets b value from rgb of shape.
   *
   * @return b value of shape
   */
  public int getB() {
    return this.b;
  }

  /**
   * Sets b value of shape.
   *
   * @param b of the shape
   */
  public void setB(int b) {
    this.b = b;
  }

  /**
   * Returns the properties of a shape in a concatenated string.
   *
   * @return all properties of a shape in a concatenated string.
   */
  public String toString() {
    return x + " " + y + " " + width + " " + height + " " + r + " " + g + " " + b;
  }

}

