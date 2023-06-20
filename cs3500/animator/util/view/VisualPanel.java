package cs3500.animator.util.view;

import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.shapes.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents a 2d animation in which shapes are drawn on and move across
 * a panel.
 */
public class VisualPanel extends JPanel {
  private List<IShape> shapesToDraw;

  /**
   * Default constructor for a visual panel which
   * instantiates the shapes that it will draw.
   * 
   * @param shapesToDraw a list of shapes the panel will draw.
   */
  public VisualPanel(List<IShape> shapesToDraw) {
    this.shapesToDraw = shapesToDraw;
  }

  /**
   * Sets the list of shapes of the panel
   * to be the updated shapes with new positions/dimensions.
   * 
   * @param shapes the updates list of shapes.
   */
  public void updateShapes(List<IShape> shapes) {
    this.shapesToDraw = shapes;
  }

  /**
   * Gets the shapes that are drawn by the Panel.
   * 
   * @return a List of the IShapes the panel contains.
   */
  public List<IShape> getShapes() {
    List<IShape> ret = new ArrayList<>();
    ret.addAll(shapesToDraw);
    return ret;
  }

  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    //g.clearRect(0, 0, getWidth(), getHeight() );

    for (int i = 0; i < shapesToDraw.size(); i++) {
      IShape shape = shapesToDraw.get(i);
      if (shape.getClass() == Rectangle.class) {
        g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
        g2d.fillRect((int) shape.getX(), (int) shape.getY(), 
                (int) shape.getWidth(), (int) shape.getHeight());
      } else {
        g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
        g2d.fillOval((int)shape.getX(), (int) shape.getY(), 
                (int) shape.getWidth(), (int) shape.getHeight());
      }
    }
  }
}
