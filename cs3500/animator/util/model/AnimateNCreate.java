package cs3500.animator.util.model;

import cs3500.animator.util.model.shapes.IShape;
import java.util.List;


/**
 * Represents an animator that takes in shapes and has several functionalities for the shapes. This
 * interface represents the model for the animation and provides functionality for a canvas for the
 * shapes.
 */
public interface AnimateNCreate {

  /**
   * Sets bounds for the canvas for the animation.
   *
   * @param x position for canvas
   * @param y position for canvas
   * @param width of canvas
   * @param height of canvas
   * @throws IllegalArgumentException if the width or height are negative
   */
  public void setBounds(int x, int y, int width, int height) throws IllegalArgumentException;

  /**
   * Gets the bounds of this model.
   *
   * @return a List representing the model's boundaries.
   */
  public List getBounds();

  /**
   * Create a shape of given name and type.
   *
   * @param name name of the shape
   * @param type type of shape
   * @throws IllegalArgumentException if there is already a shape with the given name
   * @throws IllegalArgumentException if the given shape type does not exist
   */
  public void declareShape(String name, String type) throws IllegalArgumentException;

  /**
   * Find the shape with this given name.
   *
   * @param name name of the shape
   * @return the shape with given name
   * @throws IllegalArgumentException if shape with given name does not exist.
   */
  public IShape getShape(String name) throws IllegalArgumentException;

  /**
   * Creates a list of all shapes in the model.
   *
   * @return the list of all shapes in the model
   */
  public List<IShape> getShapes();

  /**
   * Gets the motions of a shape with the given name.
   *
   * @param name name of the shape
   * @return the list of motions for a shape.
   * @throws IllegalArgumentException if no shape with given name does not exist.
   */
  public List<IMotion> getMotions(String name);

  /**
   * Get the shape with the given name and tick.
   *
   * @param name shape
   * @param tick status at this tick
   * @return the shape with the given name
   * @throws IllegalArgumentException if shape with given name does not exist.
   * @throws IllegalArgumentException if the ticks cannot be negative.
   */
  public IShape getShapeAtTick(String name, int tick) throws IllegalArgumentException;

  /**
   * Constructs a motion for a shape with given start and end properties.
   *
   * @param name shape
   * @param t1 starting time of motion
   * @param x1 starting x coordinate of shape
   * @param y1 starting y coordinate of shape
   * @param w1 starting width of shape
   * @param h1 starting height of shape
   * @param r1 starting red of shape
   * @param g1 starting green of shape
   * @param b1 starting blue of shape
   * @param t2 ending tick of motion
   * @param x2 ending x coordinate of shape
   * @param y2 ending y coordinate of shape
   * @param w2 ending width of shape
   * @param h2 ending height of shape
   * @param r2 ending red of shape
   * @param g2 ending green of shape
   * @param b2 ending blue of shape
   * @throws IllegalArgumentException the shape does not exist
   * @throws IllegalArgumentException if startTick is less than 0 or greater than endTick
   * @throws IllegalArgumentException if this motion is overlapping with another motion
   */
  public void motion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2);

  /**
   * Check if a shape has overlapping motions.
   *
   * @param shape to check
   * @param motion to check
   * @return if a shape has overlapping motions
   */
  public boolean isOverlappingMotions(IShape shape, IMotion motion);
}
