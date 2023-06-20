package cs3500.animator.util.view;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.IMotion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.List;

import cs3500.animator.util.model.shapes.IShape;

/**
 * Class represents the SVG view of a model's animation.
 */
public class SVGView implements IView {
  private final FileWriter out;
  private int tps;

  /**
   * Constructs an SVG view of the animation with a given ticks per second.
   *
   * @param tps the ticks per second for the animation
   * @param out the Appendable which contains the file output for the SVG view of the animation
   */
  public SVGView(int tps, FileWriter out) {
    this.tps = tps;
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void render(AnimateNCreate model) {
    try {
      this.out.write(this.svgViewImpl(model));
      this.out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("The appendable output is invalid or cannot be reached.");
    }
  }

  /**
   * Helper method for render. Returns a StringBuilder output for the SVG view to render.
   *
   * @param model the model of the animation being used to output a view
   * @return returns a StringBuilder output/representation of the view for a model's animation
   */
  private String svgViewImpl(AnimateNCreate model) {
    StringBuilder output = new StringBuilder();
    output.append("<?xml version=\"1.0\" standalone=\"no\"?>").append("\n");
    output.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n"
            + "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">").append("\n");
    List<Integer> boundsList = model.getBounds();
    String bounds = boundsList.get(0) + " " + boundsList.get(1)
            + " " + boundsList.get(2) + " "
            + boundsList.get(3);
    int widthOriginal = boundsList.get(2);
    int heightOriginal = boundsList.get(3);
    String widthInCm = String.valueOf(widthOriginal / 100);
    String heightInCm = String.valueOf(heightOriginal / 100);
    output.append("<svg width=\"" + widthInCm
            + "\" height=\"" + heightInCm + "\" viewBox=\""
            + bounds
            + "\" \n     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">").append("\n");
    List<IShape> shapes = model.getShapes();
    for (int i = 0; i < shapes.size(); i++) {
      IShape shape = shapes.get(i);
      String shapeName = shape.getName();
      List<IMotion> motions = model.getMotions(shapeName);
      String originalColor = "rgb(" + motions.get(0).getr1()
              + "," + motions.get(0).getg1() + ","
              + motions.get(0).getb1() + ")";
      if (shape.getType().equals("rectangle")) {
        output.append("  <rect id=\"" + shapeName +
                "\" x=\"0\" y=\"0\" width=\"0\" height=\"0\"\n"
                + "        fill=\"none\" stroke=\"black\" stroke-width=\"1\"/>").append("\n");
        output.append("  </rect>").append("\n");
      } else if (shape.getType().equals("ellipse")) {
        output.append("  <ellipse id=\""
                + shapeName + "\" cx=\"0\" cy=\"0\" rx=\"0\" ry=\"0\"\n"
                + "        fill=\"none\" stroke=\"black\" stroke-width=\"1\"/>").append("\n");
        output.append("  </ellipse>").append("\n");
      } else {
        throw new IllegalArgumentException("There is no shape of this type.");
      }
      for (IMotion motion : motions) {
        double seconds1 = motion.gett1() / tps;
        double seconds2 = motion.gett2() / tps;
        double duration = seconds2 - seconds1;
      }
    }
    output.append("</svg>").append("\n");
    return output.toString();
  }
}

