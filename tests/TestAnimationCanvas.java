package tests;

import cs3500.animator.util.model.AnimateNCreate;
import cs3500.animator.util.model.AnimationCanvas;
import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.shapes.Ellipse;
import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for the methods of AnimationCanvas.
 */
public class TestAnimationCanvas {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();
  AnimateNCreate animationCanvas1 = new AnimationCanvas(100, 100, 100, 100);

  // declareShape(String name, String type) ________________________________________________________
  @Test
  // Test declareShape for when there is already a shape with the given name
  public void declareShapeNameAlreadyExists() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("There is already a shape with the given name.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.declareShape("ayo", "ellipse");
    this.animationCanvas1.declareShape("r1", "rectangle");
  }

  @Test
  // Test declareShape for given type does not exist
  public void declareShapeIllegalType() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("The given shape type does not exist.");
    this.animationCanvas1.declareShape("r1", "rectanglular");
  }

  @Test
  // Test declareShape for rectangle
  public void declareShapeRectangle() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    IShape bitchass = this.animationCanvas1.getShape("r1");
    assertEquals(Rectangle.class, bitchass.getClass());
    assertEquals("r1", bitchass.getName());
  }

  @Test
  // Test declareShape for ellipse
  public void declareShapeEllipse() {
    this.animationCanvas1.declareShape("e1", "ellipse");
    IShape bitchass = this.animationCanvas1.getShape("e1");
    assertEquals(Ellipse.class, bitchass.getClass());
    assertEquals("e1", bitchass.getName());
  }


  // getShape(String name) ________________________________________________________
  @Test
  // Test getShape for when there is no shape with the given name
  public void getShapeNoShapeExists() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("No shape with the given name exists.");
    this.animationCanvas1.getShape("r2");
  }

  @Test
  // Test getShape
  public void getShape() {
    this.animationCanvas1.declareShape("e1", "ellipse");
    IShape bitchass = this.animationCanvas1.getShape("e1");
    assertEquals("e1", bitchass.getName());
  }


  // getMotions(String name) ________________________________________________________
  @Test
  // Test getMotions for a non-existent shape
  public void getMotionsNoShape() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("No shape with the given name exists.");
    this.animationCanvas1.getMotions("shape");
  }

  @Test
  // Test getMotions for an empty list of motions
  public void getMotionsEmptyList() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    assertEquals(new ArrayList<>(), this.animationCanvas1.getMotions("r1"));
  }

  @Test
  // Test getMotions
  public void getMotions() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    IShape bitch = new Rectangle("r1");
    this.animationCanvas1.motion("r1", 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 2, 2, 2, 2, 2, 2, 2,
            3, 2, 2, 2, 2, 2, 2, 2);
    List<IMotion> motions = this.animationCanvas1.getMotions("r1");
    IMotion motion1 = motions.get(0);
    IMotion motion2 = motions.get(1);
    assertEquals(1, motion1.gett1(), .1);
    assertEquals(3, motion2.gett2(), .1);
  }


  // getShapeAtTick(String name, int tick) ________________________________________________________
  @Test
  // Test getShapeAtTick for a non-existent shape
  public void getShapeAtTickNoShape() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("No shape with the given name exists.");
    this.animationCanvas1.getShapeAtTick("r1", 1);
  }

  @Test
  // Test getShapeAtTick for a negative tick
  public void getShapeAtTickBadTick() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("Tick cannot be negative.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.getShapeAtTick("r1", -1);
  }

  @Test
  // Test getShapeAtTick when one motion has the same endTick as the startTick of another motion
  public void getShapeAtTickOverlappingMotionTicks() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            6, 50, 1, 1, 1, 1, 1, 1);
    IShape updatedShape = this.animationCanvas1.getShapeAtTick("r1", 1);
    assertEquals(0, updatedShape.getX(), .1);
  }

  @Test
  // Test getShapeAtTick
  public void getShapeAtTick() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            6, 50, 1, 1, 1, 1, 1, 1);
    IShape updatedShape = this.animationCanvas1.getShapeAtTick("r1", 2);
    assertEquals(10, updatedShape.getX(), .1);
  }


  @Test
  // Test motion for a non-existent shape
  public void motionNoShape() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("No shape with the given name exists.");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            6, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test motion for when the startTick is after the endTick
  public void motionIllegalTicks() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("startTick is less than 0 or greater than endTick.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            0, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test motion for when a motion is overlapping with another motion
  public void motionIsOverlapping() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            2, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            4, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test motion
  public void motion() {
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            6, 50, 1, 1, 1, 1, 1, 1);
    IShape updatedShape = this.animationCanvas1.getShapeAtTick("r1", 1);
    assertEquals(0, updatedShape.getX(), .1);
    updatedShape = this.animationCanvas1.getShapeAtTick("r1", 3);
    assertEquals(20, updatedShape.getX(), .1);
  }


  // isOverlappingMotions(IShape shape, IMotion motion); ________________________
  @Test
  // Test isOverlappingMotions for when there are two motins with the exact same ticks
  public void IsOverlappingMotionsExactSameTicks() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    IShape shape = this.animationCanvas1.getShape("r1");
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            2, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            2, 50, 1, 1, 1, 1, 1, 1);
  }


  @Test
  // Test isOverlappingMotions for when motion1 has a startTick less than motion2 starTick but
  // endTick is greater than motion2 startTick
  public void IsOverlappingMotions2() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 2, 0, 1, 1, 1, 1, 1, 1,
            4, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            3, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test isOverlappingMotions for when motion1 has an startTick less than motion2 endTick but
  // motion1 endTick is greater than motion2 endTick
  public void IsOverlappingMotions3() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 2, 0, 1, 1, 1, 1, 1, 1,
            4, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            3, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test isOverlappingMotions for when motion1 is inside motion2
  public void IsOverlappingMotions4() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 2, 0, 1, 1, 1, 1, 1, 1,
            4, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 1, 0, 1, 1, 1, 1, 1, 1,
            5, 50, 1, 1, 1, 1, 1, 1);
  }

  @Test
  // Test isOverlappingMotions for when motion2 is inside motion1
  public void IsOverlappingMotions5() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("This motion is overlapping with another motion.");
    this.animationCanvas1.declareShape("r1", "rectangle");
    this.animationCanvas1.motion("r1", 1, 1, 1, 1, 1, 1, 1, 1,
            5, 50, 1, 1, 1, 1, 1, 1);
    this.animationCanvas1.motion("r1", 2, 0, 1, 1, 1, 1, 1, 1,
            4, 50, 1, 1, 1, 1, 1, 1);
  }
}
