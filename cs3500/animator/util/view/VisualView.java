package cs3500.animator.util.view;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.shapes.IShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This class is the visual animation of the model implemented using Swing.
 * It represents the animation as a JPanel added to a JFrame.
 */
public class VisualView extends JFrame implements IView {
  private AnimateNCreate model;
  private VisualPanel panel;
  private int tps;
  private int tick;

  /**
   * Create a visual view of the animation with the given speed, ticks per second.
   *
   * @param tps ticks per second of animation
   */
  public VisualView(int tps) {
    this.tps = tps;
    this.model = null;
  }

  /**
   * Renders the animation in a Java Swing view on a JFrame.
   *
   * @param model a model for the MVC of the animation which the view relies on.
   */
  @Override
  public void render(AnimateNCreate model) {
    this.setVisible(true);

    this.model = model;
    this.tick = 0;
    this.panel = new VisualPanel(model.getShapes());

    this.setTitle("VisualView!");
    List<Integer> bounds = model.getBounds();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    Dimension dims = new Dimension(bounds.get(2), bounds.get(3));
    this.setPreferredSize(dims);
    panel.setSize(dims);
    pack();
    add(this.panel);

    ActionListener taskPerformer = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        List<IShape> updateShapes = panel.getShapes();
        for (int i = 0; i < updateShapes.size(); i++) {
          IShape shape = updateShapes.get(i);
          String nameOfShape = shape.getName();
          List<IMotion> shapeMotions = model.getMotions(nameOfShape);
          for (IMotion m : shapeMotions) {

            if (tick >= m.gett1() && tick <= m.gett2()) {
              m.apply(tick);
            }
          }
        }
        tick++;
        panel.repaint();
      }
    };

    Timer timer = new Timer(1000 / tps, taskPerformer);
    timer.start();

  }
}

