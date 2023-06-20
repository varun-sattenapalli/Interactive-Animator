package cs3500.animator.util.view;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.IMotion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Represents a textual view of an animation model which describes
 * the motions of the shapes during the animation in textual output.
 */
public class TextualView implements IView {
  private final FileWriter out;
  private int tps;

  /**
   * Constructs a textual view of an animation model.
   *
   * @param tps the ticks per second of the animation
   * @param out the Appendable output which contains the textual view of the animation
   */
  public TextualView(int tps, FileWriter out) {
    this.tps = tps;
    this.out = Objects.requireNonNull(out);
  }

  /**
   * Renders the animation (AnimateNCreate model) as a textual output which
   * describes the animation in terms of the movement of the shapes being
   * animated.
   *
   * @param model a model for the MVC of the animation which the view relies on.
   * @throws IllegalArgumentException for an invalid output file.
   */
  @Override
  public void render(AnimateNCreate model) throws IllegalArgumentException {
    try {
      this.out.write(this.textualViewImpl(model));
      this.out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("The appendable output is invalid or cannot be reached.");
    }
  }

  /**
   * Helped method for render. Returns a StringBuilder output for the textual view to render.
   *
   * @param model the model of the animation being used to output a view
   * @return returns a StringBuilder output/representation of the view for a model's animation
   */
  private String textualViewImpl(AnimateNCreate model) {
    List<IShape> shapes = model.getShapes();
    StringBuilder output = new StringBuilder();
    List<Integer> canvasBounds = model.getBounds();
    StringBuilder canvas = new StringBuilder("canvas " + canvasBounds.get(0) + " "
            + canvasBounds.get(1) + " " + canvasBounds.get(2) + " " + canvasBounds.get(3));
    output.append(canvas).append("\n");
    for (int i = 0; i < shapes.size(); i++) {
      IShape shape = shapes.get(i);
      String shapeName = shape.getName();
      StringBuilder shapeInitial = new StringBuilder("shape "
              + shapeName + " " + shape.getType());
      output.append(shapeInitial).append("\n");
      List<IMotion> motions = model.getMotions(shapeName);
      for (IMotion motion : motions) {
        double seconds1 = motion.gett1() / (double) tps;
        double seconds2 = motion.gett2() / (double) tps;
        StringBuilder motionText = new StringBuilder("motion "
                + shapeName + " " + seconds1 + " "
                + motion.getx1() + " " + motion.gety1() + " " + motion.getw1()
                + " " + motion.geth1() + " " + motion.getr1() + " " + motion.getg1()
                + " " + motion.getb1() + " " + seconds2 + " " + motion.getx2() + " "
                + motion.gety2() + " " + motion.getw2() + " " + motion.geth2()
                + " " + motion.getr2() + " " + motion.getg2() + " " + motion.getb2());
        output.append(motionText).append("\n");
      }
    }
    return output.toString();
  }

  /**
   * Returns an animatable textual view, i.e. one that can be used to render other animation views.
   *
   * @param model the model of the animation being used to output a view.
   * @return returns a StringBuilder output/representation of the view for a model's animation.
   */
  public String textualAnimation(AnimateNCreate model) {

    List<IShape> shapes = model.getShapes();
    StringBuilder output = new StringBuilder();
    List<Integer> canvasBounds = model.getBounds();
    StringBuilder canvas = new StringBuilder("canvas " + canvasBounds.get(0) + " "
            + canvasBounds.get(1) + " "
            + canvasBounds.get(2) + " " + canvasBounds.get(3));
    output.append(canvas).append("\n");
    for (int i = 0; i < shapes.size(); i++) {
      IShape shape = shapes.get(i);
      String shapeName = shape.getName();
      StringBuilder shapeInitial = new StringBuilder("shape "
              + shapeName + " " + shape.getType());
      output.append(shapeInitial).append("\n");
      List<IMotion> motions = model.getMotions(shapeName);
      for (IMotion motion : motions) {
        StringBuilder motionText = new StringBuilder("motion "
                + shapeName + " " + motion.gett1() + " "
                + (int) motion.getx1() + " " + (int) motion.gety1() + " "
                + (int) motion.getw1() + " " + (int) motion.geth1()
                + " " + motion.getr1() + " " + motion.getg1() + " " + motion.getb1() + " "
                + motion.gett2() + " " + (int) motion.getx2() + " "
                + (int) motion.gety2() + " " + (int) motion.getw2() + " "
                + (int) motion.geth2() + " " + motion.getr2() + " "
                + motion.getg2() + " " + motion.getb2());
        output.append(motionText).append("\n");
      }
    }
    return output.toString();
  }
}
