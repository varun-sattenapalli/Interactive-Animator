package tests;

import cs3500.animator.util.model.IMotion;
import cs3500.animator.util.model.Motion;
import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.shapes.Rectangle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for the methods in the Motion class and IMotion interface.
 */
public class TestMotion {
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();
  IShape rectangle = new Rectangle("rectangular");
  IMotion motion = new Motion(rectangle, 1, 0, 0, 0, 0, 0, 0, 0,
          4, 30, 30, 30, 30, 60, 60, 60);

  // Test apply(double tick) ______________________________________________________________
  @Test
  // Test apply for when given tick is not within the start and end ticks, inclusive
  public void applyInvalidTick() {
    this.expectedEx.expect(IllegalArgumentException.class);
    this.expectedEx.expectMessage("Can only apply motion at a tick in between the start"
            + " and end ticks.");
    this.motion.apply(500);
  }

  @Test
  // Test apply when the start and end ticks are the same
  public void applySameStartEndTicks() {
    IMotion motion = new Motion(rectangle, 1, 0, 0, 0, 0, 0, 0, 0,
            1, 30, 30, 30, 30, 60, 60, 60);
    motion.apply(1);
    assertEquals(30, this.rectangle.getX(), .1);
    assertEquals(60, this.rectangle.getR(), .1);
  }

  @Test
  // Test apply
  public void apply() {
    this.motion.apply(2);
    assertEquals(10, this.rectangle.getX(), .1);
    assertEquals(20, this.rectangle.getR(), .1);
  }


  // Test gett1() ________________________________________________________________________
  @Test
  public void gett1() {
    assertEquals(1, this.motion.gett1(), .1);
  }


  // Test gett2() ________________________________________________________________________
  @Test
  public void gett2() {
    assertEquals(4, this.motion.gett2(), .1);
  }
}
