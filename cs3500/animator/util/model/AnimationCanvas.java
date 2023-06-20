package cs3500.animator.util.model;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.model.shapes.Ellipse;
import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.shapes.Rectangle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Implementation of {@link AnimateNCreate} and it serves as the canvas of your animation.
 * INVARIANT : The shapes must fit within the canvas.
 * We decided to leave this implementation to the view. This is because it will be extremely easy
 * for the view to determine if any point is outside the canvas or not.
 * INVARIANT : The RGB values cannot be less than 0 or greater than 255.
 * INVARIANT : A shape can not have motions with overlapping ticks.
 */
public final class AnimationCanvas implements AnimateNCreate {

  private int x;
  private int y;
  private int width;
  private int height;
  private LinkedHashMap<IShape, List<IMotion>> motions;

  /**
   * Create an empty canvas with the given bounds.
   *
   * @param x the x bound.
   * @param y the y bound
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   */
  public AnimationCanvas(int x, int y, int width, int height) {
    this.width = width;
    this.height = height;
    this.motions = new LinkedHashMap<>();
  }

  /**
   * Represents a builder that builds an AnimateNCreate model.
   */
  public static final class Builder implements AnimationBuilder<AnimateNCreate> {
    AnimateNCreate model;

    /**
     * Default constructor for a Builder.
     * @param model the model that the Builder will be based on.
     */
    public Builder(AnimateNCreate model) {
      this.model = model;
    }

    /**
     * Returns the built AnimateNCreate model.
     *
     * @return the AnimateNCreate model that has been built.
     */
    @Override
    public AnimateNCreate build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimateNCreate> setBounds(int x, int y, int width, int height) {
      this.model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimateNCreate> declareShape(String name, String type) {
      this.model.declareShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimateNCreate> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      this.model.motion(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2,
          b2);
      return this;
    }
  }

  @Override
  public void setBounds(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative.");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public List<Integer> getBounds() {
    List<Integer> list = new ArrayList<>();
    list.add(this.x);
    list.add(this.y);
    list.add(this.width);
    list.add(this.height);
    return list;
  }

  @Override
  public void declareShape(String name, String type) {
    for (IShape shape : this.motions.keySet()) {
      if (name.equals(shape.getName())) {
        throw new IllegalArgumentException("There is already a shape with the given name.");
      }
    }
    switch (type) {
      case "rectangle":
        IShape rectangle = new Rectangle(name);
        this.motions.put(rectangle, new ArrayList<>());
        break;
      case "ellipse":
        IShape ellipse = new Ellipse(name);
        this.motions.put(ellipse, new ArrayList<>());
        break;
      default:
        throw new IllegalArgumentException("The given shape type does not exist.");
    }
  }

  @Override
  public IShape getShape(String name) {
    for (IShape shape : this.motions.keySet()) {
      String shapeName = shape.getName();
      if (shapeName.equals(name)) {
        return shape;
      }
    }
    throw new IllegalArgumentException("No shape with the given name exists.");
  }

  @Override
  public List<IShape> getShapes() {
    return new ArrayList<IShape>(motions.keySet());
  }

  @Override
  public List<IMotion> getMotions(String name) {
    IShape shape = this.getShape(name);
    return this.motions.get(shape);
  }

  @Override
  public IShape getShapeAtTick(String name, int tick) {
    IShape shape = this.getShape(name);
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative.");
    }
    List<IMotion> motionsForThisShape = getMotions(name);
    IMotion applyThisMotion = null;
    for (int i = 0; i < motionsForThisShape.size(); i++) {
      IMotion motion = motionsForThisShape.get(i);
      if (motion.gett1() <= tick && tick <= motion.gett2()) {
        applyThisMotion = motion;
        applyThisMotion.apply(tick);
        break;
      }
    }
    return shape;
  }

  @Override
  public void motion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    IShape shape = this.getShape(name);
    if (t1 < 0 || t1 > t2) {
      throw new IllegalArgumentException("startTick is less than 0 or greater than endTick.");
    }
    IMotion motion = new Motion(shape, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2,
        b2);
    if (isOverlappingMotions(shape, motion)) {
      throw new IllegalArgumentException("This motion is overlapping with another motion.");
    }
    getMotions(name).add(motion);
  }

  @Override
  public boolean isOverlappingMotions(IShape shape, IMotion motion) {
    List<IMotion> motions = this.motions.get(shape);
    for (IMotion checkThis : motions) {
      if (((motion.gett1() == checkThis.gett1()) && (motion.gett2() == checkThis.gett2()))
          || ((motion.gett1() < checkThis.gett1()) && (motion.gett2() > checkThis.gett1()))
          || ((motion.gett1() < checkThis.gett2()) && (motion.gett2() > checkThis.gett2()))
          || ((motion.gett1() > checkThis.gett1()) && (motion.gett2() < checkThis.gett2()))
          || ((motion.gett1() < checkThis.gett1()) && (motion.gett2() > checkThis.gett2()))) {
        return true;
      }
    }
    return false;
  }


}












