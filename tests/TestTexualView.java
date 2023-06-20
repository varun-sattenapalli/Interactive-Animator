package tests;

import cs3500.animator.util.AnimationReader;
import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.AnimationCanvas;
import cs3500.animator.util.view.IView;
import cs3500.animator.util.view.TextualView;
import cs3500.animator.util.view.ViewFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class contains tests for the methods of a textualView.
 */
public class TestTexualView {


  /**
   * Test that the textual render output is correct.
   */
  @Test
  public void testTextualOutput() {
    AnimationCanvas model = new AnimationCanvas(0, 0, 0, 0);
    FileWriter out = null;

    {
      try {
        out = new FileWriter("assignment6.zip\\output.txt");
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }

    AnimationCanvas.Builder builder = new AnimationCanvas.Builder(model);
    IView view = ViewFactory.create("text", 20, out);
    FileReader f = null;

    {
      try {
        f = new FileReader("assignment6.zip\\smalldemo.txt");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    TextualView tv = (TextualView) view;
    Readable in = f;

    AnimateNCreate updatedModel = AnimationReader.parseFile(in, builder);
    tv.render(updatedModel);

    Path outputtxt = Path.of("assignment6.zip\\output.txt");
    Path smalldemotxt = Path.of("assignment6.zip\\smalldemo.txt");
    String actual = null;
    try {
      actual = Files.readString(outputtxt);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    String expected = "canvas 200 70 360 360\n" +
            "shape R rectangle\n" +
            "motion R 0.05 200.0 200.0 50.0 100.0 255 0 0 0.5 " +
            "200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion R 0.5 200.0 200.0 50.0 100.0 255 0 0 2.5 300.0 " +
            "300.0 50.0 100.0 255 0 0\n" +
            "motion R 2.5 300.0 300.0 50.0 100.0 255 0 0 2.55 300.0 " +
            "300.0 50.0 100.0 255 0 0\n" +
            "motion R 2.55 300.0 300.0 50.0 100.0 255 0 0 3.5 300.0 " +
            "300.0 25.0 100.0 255 0 0\n" +
            "motion R 3.5 300.0 300.0 25.0 100.0 255 0 0 5.0 200.0 " +
            "200.0 25.0 100.0 255 0 0\n" +
            "shape C ellipse\n" +
            "motion C 0.3 440.0 70.0 120.0 60.0 0 0 255 1.0 440.0 " +
            "70.0 120.0 60.0 0 0 255\n" +
            "motion C 1.0 440.0 70.0 120.0 60.0 0 0 255 2.5 440.0 " +
            "250.0 120.0 60.0 0 0 255\n" +
            "motion C 2.5 440.0 250.0 120.0 60.0 0 0 255 3.5 440.0 " +
            "370.0 120.0 60.0 0 170 85\n" +
            "motion C 3.5 440.0 370.0 120.0 60.0 0 170 85 4.0 440.0 " +
            "370.0 120.0 60.0 0 255 0\n" +
            "motion C 4.0 440.0 370.0 120.0 60.0 0 255 0 5.0 440.0 " +
            "370.0 120.0 60.0 0 255 0\n";
    assertEquals(actual, expected);
  }


  /**
   * Test that the textual render output throws exception
   * for incorrectFile.
   */
  @Test(expected = FileNotFoundException.class)
  public void testTextualOutputException() throws FileNotFoundException {
    AnimationCanvas model = new AnimationCanvas(0, 0, 0, 0);
    FileWriter out = null;

    {
      try {
        out = new FileWriter("assignment6.zip\\output.txt");
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }

    AnimationCanvas.Builder builder = new AnimationCanvas.Builder(model);
    IView view = ViewFactory.create("text", 20, out);
    FileReader f = null;


    f = new FileReader("assignment6.zip\\notreal.txt");


    TextualView tv = (TextualView) view;
    Readable in = f;

    AnimateNCreate updatedModel = AnimationReader.parseFile(in, builder);
    tv.render(updatedModel);

    Path outputtxt = Path.of("assignment6.zip\\output.txt");
    Path smalldemotxt = Path.of("assignment6.zip\\smalldemo.txt");
    String actual = null;
    try {
      actual = Files.readString(outputtxt);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    String expected = "canvas 200 70 360 360\n" +
            "shape R rectangle\n" +
            "motion R 0.05 200.0 200.0 50.0 100.0 255 0 0 0.5 " +
            "200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion R 0.5 200.0 200.0 50.0 100.0 255 0 0 2.5 300.0 " +
            "300.0 50.0 100.0 255 0 0\n" +
            "motion R 2.5 300.0 300.0 50.0 100.0 255 0 0 2.55 300.0 " +
            "300.0 50.0 100.0 255 0 0\n" +
            "motion R 2.55 300.0 300.0 50.0 100.0 255 0 0 3.5 300.0 300.0 " +
            "25.0 100.0 255 0 0\n" +
            "motion R 3.5 300.0 300.0 25.0 100.0 255 0 0 5.0 200.0 200.0 " +
            "25.0 100.0 255 0 0\n" +
            "shape C ellipse\n" +
            "motion C 0.3 440.0 70.0 120.0 60.0 0 0 255 1.0 440.0 70.0 " +
            "120.0 60.0 0 0 255\n" +
            "motion C 1.0 440.0 70.0 120.0 60.0 0 0 255 2.5 440.0 " +
            "250.0 120.0 60.0 0 0 255\n" +
            "motion C 2.5 440.0 250.0 120.0 60.0 0 0 255 3.5 440.0 " +
            "370.0 120.0 60.0 0 170 85\n" +
            "motion C 3.5 440.0 370.0 120.0 60.0 0 170 85 4.0 440.0 370.0 " +
            "120.0 60.0 0 255 0\n" +
            "motion C 4.0 440.0 370.0 120.0 60.0 0 255 0 5.0 440.0 370.0 " +
            "120.0 60.0 0 255 0\n";
    assertEquals(actual, expected);
  }


}
