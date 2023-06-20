package cs3500.animator.util.model.shapes;

/**
 * Represents an animatable shape with an x position,
 * y position, values for red, green, and blue, width,
 * height, and a name.
 */
public interface IShape {

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape.
   */
  public String getName();

  /**
   * Gets the type of the shape.
   *
   * @return returns the type of the shape.
   */
  public String getType();

  /**
   * Gets x coordinate of shape.
   *
   * @return x coordinate of shape.
   */
  public double getX();

  /**
   * Sets the ending x coordinate.
   *
   * @param x the ending x coordinate.
   */
  void setX(double x);


  /**
   * Gets y coordinate of shape.
   *
   * @return y coordinate of shape.
   */
  public double getY();

  /**
   * Sets the ending y coordinate.
   *
   * @param y the ending y coordinate.
   */
  void setY(double y);

  /**
   * Gets width of shape.
   *
   * @return width of shape.
   */
  public double getWidth();

  /**
   * Sets the ending width.
   *
   * @param w the ending width of shape.
   */
  void setWidth(double w);

  /**
   * Gets height coordinate of shape.
   *
   * @return height coordinate of shape.
   */
  public double getHeight();

  /**
   * Sets the ending height.
   *
   * @param h the ending height of shape.
   */
  void setHeight(double h);

  /**
   * Gets r value from rgb of shape.
   *
   * @return r value of shape.
   */
  public int getR();

  /**
   * Sets the ending red value.
   *
   * @param r the ending r value.
   */
  void setR(int r);

  /**
   * Gets g value from rgb of shape.
   *
   * @return g value of shape.
   */
  public int getG();

  /**
   * Sets the ending green value.
   *
   * @param g the ending green value.
   */
  void setG(int g);

  /**
   * Gets b value from rgb of shape.
   *
   * @return b value of shape.
   */
  public int getB();

  /**
   * Sets the ending blue value.
   *
   * @param b the ending blue value.
   */
  void setB(int b);
}
