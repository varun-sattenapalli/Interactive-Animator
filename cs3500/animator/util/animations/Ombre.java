package cs3500.animator.util.animations;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.shapes.IShape;

/**
 * This class creates an animation for 255 rectangles for red, green, and blue.
 * They are each assigned an rgb value from 0 - 255.
 * They are then animated to create an ombre.
 */
public class Ombre {


  AnimateNCreate model;

  /**
   * Default constructor for Ombre which accepts a model.
   *
   * @param model the model for which to create an Ombre animation.
   */
  public Ombre(AnimateNCreate model) {
    this.model = model;
  }

  /**
   * Creates an animation of 3 rows each of which are
   * an r,g,b ombre.
   */
  public void createAnimation() {
    model.setBounds(100, 100, 800, 800);
    for (int ii = 0; ii < 255 * 3; ii++) {
      String shapeName = "r" + ii;
      model.declareShape(shapeName, "rectangle");
    }
    //Initial positions.
    for (int jj = 0; jj < 255 * 3; jj++) {
      String shapeName = "r" + jj;
      if (jj < 255) {
        model.motion(shapeName, 1, 400, 600, 2, 200, jj, 0, 0, 1,
                400, 600, 2, 100, jj, 0, 0);
      } else if (jj < 510) {
        model.motion(shapeName, 1, 400, 400, 2, 200, 0,
                (jj - 255), 0, 1,
                400, 400, 2, 200, 0, (jj - 255), 0);
      } else {
        model.motion(shapeName, 1, 400, 200, 2, 200,
                0, 0, (jj - 510), 1,
                400, 200, 2, 200, 0, 0, (jj - 510));
      }

    }
    int tick = 1;
    for (IShape shape : model.getShapes()) {
      for (IMotion motion : model.getMotions(shape.getName())) {
        if (tick >= motion.gett1() && tick <= motion.gett2()) {
          motion.apply(tick);
        }
      }
    }
    //display the hues.
    for (IShape shape1 : model.getShapes()) {
      tick += 20;
      int newX = shape1.getR() + shape1.getB() + shape1.getG() + 145;
      model.motion(shape1.getName(), 30, (int) shape1.getX(), (int) shape1.getY(),
              (int) shape1.getWidth(), (int) shape1.getHeight(), shape1.getR(),
              shape1.getG(), shape1.getB(), 500, newX, (int) shape1.getY(),
              (int) shape1.getWidth(), (int) shape1.getHeight(),
              shape1.getR(), shape1.getG(), shape1.getB());
      for (IShape eachShape : model.getShapes()) {
        for (IMotion motion : model.getMotions(eachShape.getName())) {
          if (tick >= motion.gett1() && tick <= motion.gett2()) {
            motion.apply(tick);
          }
        }
      }
    }
  }
}
