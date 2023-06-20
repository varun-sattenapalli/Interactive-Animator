package cs3500.animator.util.view;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.shapes.IShape;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an interactive view for a 2d animation. The animation is played in a JPanel
 * that is on a JFrame which includes interactive components (buttons, textfields, and checkboxes)
 * that allow the user to manipulate certain features of the animation. These features include
 * modifying the speed, pausing/resuming the animation, looping, and restarting.
 */
public class InteractiveView extends JFrame implements IView {

  private final JButton startRestart;
  private final JButton pauseResume;
  private final JButton submitSpeed;
  private final JTextField speedInput;
  private final JLabel currentSpeed;
  private final JLabel speedInstruction;
  private AnimateNCreate model;
  private VisualPanel panel;
  private int tps;
  private int tick;
  private boolean isPlaying;
  private boolean started;
  private boolean loop;
  private Timer timer;
  private final JCheckBox loopCBox;

  /**
   * The default constructor for an Interactive View.
   *
   * @param tps the starting ticks per second of the InteractiveView.
   */
  public InteractiveView(int tps) {
    this.model = null;
    this.tps = tps;
    this.startRestart = new JButton("Start");
    this.pauseResume = new JButton("Pause");
    this.pauseResume.setEnabled(false);
    this.submitSpeed = new JButton("Set speed");
    ButtonListener listener = new ButtonListener();
    this.startRestart.addActionListener(listener);
    this.pauseResume.addActionListener(listener);
    this.submitSpeed.addActionListener(listener);
    this.started = false;
    this.isPlaying = false;
    this.loop = false;
    this.speedInput = new JTextField("", 5);
    speedInput.setSize(20, 10);
    this.currentSpeed = new JLabel();
    this.currentSpeed.setText("Current Speed: " + tps);
    this.speedInstruction = new JLabel();
    this.speedInstruction.setText("Enter a positive integer speed: ");
    this.loopCBox = new JCheckBox("Loops Enabled ", false);
    this.loopCBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        loop = ! loop;
      }
    });

  }

  /**
   * Renders the model as a 2d animation in a JFrame with interactive features.
   * These features include modifying the speed,
   * pausing/resuming the animation, looping, and restarting.
   *
   * @param model a model for the MVC of the animation which the view relies on.
   */
  @Override
  public void render(AnimateNCreate model) {
    this.setVisible(true);

    this.model = model;
    this.tick = 0;
    this.panel = new VisualPanel(model.getShapes());

    this.setTitle("InteractiveView!");
    List<Integer> bounds = model.getBounds();
    this.setSize(bounds.get(2) , bounds.get(3) + 50 );
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Dimension dims = new Dimension(bounds.get(2), bounds.get(3));
    panel.setSize(dims);
    //add the components using GridBagConstraints.
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    this.setLayout(gridBagLayout);
    constraints.gridwidth = 8;
    constraints.gridheight = 3;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(this.startRestart);
    constraints.gridy = 2;
    add(this.pauseResume);
    constraints.gridy = 3;
    constraints.ipadx = 10;
    add(this.speedInstruction);
    constraints.gridy = 4;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    add(this.speedInput);
    constraints.gridy = 5;
    add(this.submitSpeed);
    constraints.gridy = 6;
    add(this.currentSpeed);
    constraints.gridy = 7;
    add(this.loopCBox);
    constraints.gridy = 1;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.anchor = GridBagConstraints.FIRST_LINE_START;
    add(this.panel, constraints);

    this.setMinimumSize(this.getMinimumSize());
    List<IShape> updateShapes = panel.getShapes();
    List<Integer> ticks = new ArrayList<Integer>();
    for (IShape s : updateShapes) {
      for (IMotion m : model.getMotions(s.getName())) {
        ticks.add(m.gett2());
      }
    }
    final int maxTick = Collections.max(ticks);
    ActionListener taskPerformer = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        if (started && isPlaying) {

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
          if (tick <= maxTick) {
            panel.repaint();
          }
          else {
            if (loop) {
              tick = 0;
            }
          }
          tick++;
        }
      }
    };
    timer = new Timer(1000 / tps, taskPerformer);
    timer.start();
  }

  /**
   * A private class that represents a listener that
   * controls the responses for the JButtons of this frame.
   */
  private class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {

      if (event.getSource() == startRestart) {
        if (!started) {
          pauseResume.setEnabled(true);
        }
        pauseResume.setText("Pause");
        tick = 0;
        started = true;
        isPlaying = true;
        startRestart.setText("Restart");
      } else if (event.getSource() == pauseResume) {
        if (!isPlaying) {
          isPlaying = true;
          pauseResume.setText("Pause");
        } else {
          isPlaying = false;
          pauseResume.setText("Resume");
        }
      } else if (event.getSource() == submitSpeed) {
        if (!( speedInput.getText().equals("") || speedInput.getText() == null) ) {
          String textFromSpeedInput = speedInput.getText();
          int speed = Integer.parseInt(textFromSpeedInput);
          if (speed > 0) {
            tps = speed;
            currentSpeed.setText("Current Speed: " + tps);
            timer.setDelay(1000 / tps);
          }
        }
      }
    }
  }
}
