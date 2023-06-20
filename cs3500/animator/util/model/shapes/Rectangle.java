package cs3500.animator.util.model.shapes;

/**
 * Represents a rectangle shape.
 */
public class Rectangle extends AShape implements IShape {

  /**
   * Constructs a rectangle.
   *
   * @param name name of shape
   */
  public Rectangle(String name) {
    super(name, 0, 0, 0, 0, 0 , 0, 0);
  }

  /**
   * Constructs a rectangle.
   *
   * @param name of shape
   * @param x coordinate of shape
   * @param y coordinate of shape
   * @param width of shape
   * @param height of shape
   * @param r value of RGB for shape
   * @param g value of RGB for shape
   * @param b value of RGB for shape
   */
  public Rectangle(String name, double x, double y, int width, int height, int r, int g,
      int b) {
    super(name, x, y, width, height, r , g, b);
  }

  @Override
  public String getType() {
    return "rectangle";
  }

}
