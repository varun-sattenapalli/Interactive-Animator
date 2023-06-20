package cs3500.animator.util.animations;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.shapes.IShape;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates an animation in which 5 rectangles of varying height are created
 * and set to varying x positions.
 * They are then sorted left to right by height.
 */
public class BubbleSort {
  AnimateNCreate model;

  /**
   * Default constructor for BubbleSort which accepts a model.
   * @param model the model for which to create a BubbleSort animation.
   */
  public BubbleSort(AnimateNCreate model) {
    this.model = model;
  }

  /**
   * Creates an animation of 5 rectangles being sorted by bubble sort
   * according to their heights.
   */
  public void createAnimation() {
    model.setBounds(100, 100, 800, 800);
    model.declareShape("r1", "rectangle");
    model.declareShape("r2", "rectangle");
    model.declareShape("r3", "rectangle");
    model.declareShape("r4", "rectangle");
    model.declareShape("r5", "rectangle");

    //set the initial position of the rectangles
    model.motion("r1", 1, 100, 300, 20, 300, 255, 0, 0, 1,
            100, 300, 20, 300, 255, 0, 0);
    model.motion("r2", 1, 200, 300, 20, 200, 255, 255, 0, 1,
            200, 300, 20, 200, 255, 255, 0);
    model.motion("r3", 1, 300, 300, 20, 180, 0, 255, 0, 1,
            300, 300, 20, 180, 0, 255, 0);
    model.motion("r4", 1, 400, 300, 20, 40, 0, 0, 255, 1,
            400, 300, 20, 40, 0, 0, 255);
    model.motion("r5", 1, 500, 300, 20, 255, 255, 0, 255, 1,
            500, 300, 20, 255, 255, 0, 255);
    List<IShape> shapes = model.getShapes();
    for (IShape shape : shapes) {
      for (IMotion motion : model.getMotions(shape.getName())) {
        motion.apply(1);
      }
    }
    IShape r1 = model.getShape("r1");
    IShape r2 = model.getShape("r2");
    IShape r3 = model.getShape("r3");
    IShape r4 = model.getShape("r4");
    IShape r5 = model.getShape("r5");
    //new list that represents the shapes to sort
    List<IShape> shapesToSort = new ArrayList<>();
    shapesToSort.add(r1);
    shapesToSort.add(r2);
    shapesToSort.add(r3);
    shapesToSort.add(r4);
    shapesToSort.add(r5);
    int n = shapes.size();
    IShape temp = null;
    int tick = 1;
    //Bubble sort algorithm
    for (int i = 0; i < n; i++) {
      tick += 110;
      for (int j = 1; j < (n - i); j++) {
        IShape shape1 = shapesToSort.get(j - 1);
        IShape shape2 = shapesToSort.get(j);
        double shape1Height = shape1.getHeight();
        double shape2Height = shape2.getHeight();
        if (shape1Height > shape2Height) {
          temp = shape1;
          shapesToSort.set(j - 1, shape2);
          shapesToSort.set(j, temp);
          int shape1X = (int) shape1.getX();
          int shape2X = (int) shape2.getX();
          //motion for swapping first rectangle
          model.motion(shape1.getName(), tick, shape1X, (int) shape1.getY(),
                  (int) shape1.getWidth(), (int) shape1Height, shape1.getR(),
                  shape1.getG(), shape1.getB(), tick + 100, shape2X, (int) shape1.getY(),
                  (int) shape1.getWidth(), (int) shape1Height, shape1.getR(),
                  shape1.getG(), shape1.getB());
          //motion for swapping second rectangle
          model.motion(shape2.getName(), tick, shape2X, (int) shape2.getY(),
                  (int) shape2.getWidth(), (int) shape2Height, shape2.getR(),
                  shape2.getG(), shape2.getB(), tick + 100, shape1X,
                  (int) shape2.getY(), (int) shape2.getWidth(), (int) shape2Height,
                  shape2.getR(), shape2.getG(), shape2.getB());
          shape1.setX(shape2X);
          shape2.setX(shape1X);
          tick += 110;
        }
      }
      for (IShape shape : shapes) {
        for (IMotion motion : model.getMotions(shape.getName())) {
          if (tick >= motion.gett1() && tick <= motion.gett2()) {
            motion.apply(tick);
          }
        }
      }
    }
  }
}