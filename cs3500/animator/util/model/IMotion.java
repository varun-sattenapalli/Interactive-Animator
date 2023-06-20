package cs3500.animator.util.model;

/**
 * Represents an interface for a motion. A motion
 * is any change to the dimensions, positions, or color
 * of a 2d shape. Contains necessary methods for motions.
 */
public interface IMotion {

  /**
   * Apply this motion at given tick.
   *
   * @param tick tick to apply motion.
   * @throws IllegalArgumentException Can only apply motion at a tick in between the start
   *         and end ticks.
   */
  public void apply(int tick) throws IllegalArgumentException;

  /**
   * Gets the start tick of this motion.
   *
   * @return the start tick of this motion.
   */
  public int gett1();

  /**
   * Gets the start x position of this motion.
   *
   * @return the start x position of this motion.
   */
  public double getx1();

  /**
   * Gets the start y position of this motion.
   *
   * @return the starty position of this motion.
   */
  public double gety1();

  /**
   * Gets the start width of this motion.
   *
   * @return the start width of this motion.
   */
  public double getw1();

  /**
   * Gets the start height of this motion.
   *
   * @return the start height of this motion.
   */
  public double geth1();

  /**
   * Gets the start R value of this motion.
   *
   * @return the start R value of this motion.
   */
  public int getr1();

  /**
   * Gets the start G value of this motion.
   *
   * @return the start G value of this motion.
   */
  public int getg1();

  /**
   * Gets the start B value of this motion.
   *
   * @return the start B value of this motion.
   */
  public int getb1();


  /**
   * Gets the end tick of this motion.
   *
   * @return the end tick of this motion.
   */
  public int gett2();

  /**
   * Gets the end x position of this motion.
   *
   * @return the end x position of this motion.
   */
  public double getx2();

  /**
   * Gets the end y position of this motion.
   *
   * @return the end y position of this motion.
   */
  public double gety2();

  /**
   * Gets the end width of this motion.
   *
   * @return the end width of this motion
   */
  public double getw2();

  /**
   * Gets the end height of this motion.
   *
   * @return the end height of this motion.
   */
  public double geth2();

  /**
   * Gets the end R value of this motion.
   *
   * @return the end R value of this motion.
   */
  public int getr2();

  /**
   * Gets the end G value of this motion.
   *
   * @return the end G value of this motion.
   */
  public int getg2();

  /**
   * Gets the end B value of this motion.
   *
   * @return the end B value of this motion.
   */
  public int getb2();


}

