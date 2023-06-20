package cs3500.animator.util.model.shapes;

/**
 * Represents a circle shape.
 */
public class Ellipse extends AShape implements IShape {
  /**
   * Constructs a Ellipse.
   *
   * @param name name of shape
   */
  public Ellipse(String name) {
    super(name, 0, 0, 0, 0, 0 , 0, 0);
  }

  /**
   * Constructs an Ellipse.
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
  public Ellipse(String name, int x, int y, int width, int height, int r, int g,
      int b) {
    super(name, x, y, width, height, r , g, b);
  }

  @Override
  public String getType() {
    return "ellipse";
  }
}
