package cs3500.animator.util.view;

import java.io.FileWriter;

/**
 * Creates an instance of an animation view.
 */
public class ViewFactory {

  /**
   * Creates an animation view given a specific view type.
   *
   * @param type the view type
   * @return returns an animation view
   */
  public static IView create(String type, int tps, FileWriter out) {
    switch (type) {
      case "text":
        return new TextualView(tps, out);
      case "svg":
        return new SVGView(tps, out);
      case "visual":
        return new VisualView(tps);
      case "interactive":
        return new InteractiveView(tps);
      default:
        throw new IllegalArgumentException("Given view type does not exist.");
    }
  }
}
