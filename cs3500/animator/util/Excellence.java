package cs3500.animator.util;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.AnimationCanvas;
import cs3500.animator.util.model.AnimationCanvas.Builder;
import cs3500.animator.util.view.IView;
import cs3500.animator.util.view.ViewFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Excellence class contains a main method
 * which allows 2d animations to be run through command line
 * arguments. The animations can be represented
 * in textual, svg, visual, and interactive views.
 */
public class Excellence {

  /**
   * The main method that allows 2d animatinos to be run. The user
   * can specify the animation to render, which view is used
   * to render it, the speed, and an output file if applicable.
   * @param args the command line arguments to run the animation.
   * @throws IOException for an invalid FileWriter.
   */
  public static void main(String[] args) throws IOException {
    Readable in = null;
    String viewType = "placeholder";
    FileWriter out = null;
    int speed = 1;
    for (int i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "-in":
          FileReader f = new FileReader(args[i + 1]);
          in = f;
          break;

        case "-view":
          viewType = args[i + 1];
          break;

        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;

        case "-out":
          out = new FileWriter(args[i + 1]);
          break;
        default:
          throw new IllegalArgumentException(args[i] + " is not a valid argument.");
      }
    }

    AnimationCanvas model = new AnimationCanvas(0, 0, 0, 0);
    Builder builder = new AnimationCanvas.Builder(model);
    IView view = ViewFactory.create(viewType, speed, out);
    AnimateNCreate updatedModel = AnimationReader.parseFile(in, builder);
    view.render(updatedModel);

  }
}
