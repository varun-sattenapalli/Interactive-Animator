package cs3500.animator.util.animations;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.AnimationCanvas;

import cs3500.animator.util.view.IView;
import cs3500.animator.util.view.TextualView;
import cs3500.animator.util.view.ViewFactory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains a main method that creates the output text files
 * representing the programmatically created BubbleSort animation
 * and ColorSorter.
 */
public class AnimationMaker {
  /**
   * Main method which creates Bubble sort and Color sort animations and
   * writes them to files.
   * @param args not used in this method.
   * @throws IOException for invalid FileWriters.
   */
  public static void main(String[] args) throws IOException {

    AnimateNCreate modelOne = new AnimationCanvas(0, 0, 0, 0);
    AnimateNCreate modelTwo = new AnimationCanvas(0, 0, 0, 0);
    BubbleSort bubbleSort = new BubbleSort(modelOne);
    bubbleSort.createAnimation();
    Ombre ombre = new Ombre(modelTwo);
    ombre.createAnimation();
    FileWriter out1 = new FileWriter("assignment6.zip\\BubbleSortAnimation.txt");
    FileWriter out2 = new FileWriter("assignment6.zip\\OmbreAnimation.txt");
    IView view1 = ViewFactory.create("text", 5, out1);
    try {
      out1.write(((TextualView) view1).textualAnimation(modelOne));
      out1.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("The appendable output is invalid or cannot be reached.");
    }

    IView view2 = ViewFactory.create("text", 5, out2);
    try {
      out2.write(((TextualView) view2).textualAnimation(modelTwo));
      out2.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("The appendable output is invalid or cannot be reached.");
    }
  }
}
