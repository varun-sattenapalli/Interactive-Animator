package tests;

import static org.junit.Assert.assertEquals;
import cs3500.animator.util.model.shapes.IShape;
import cs3500.animator.util.model.shapes.Rectangle;
import org.junit.Test;

/**
 * Contains tests for the methods of the class AShape.
 */
public class TestAShape {
  IShape rectangle = new Rectangle("blicky", 1, 2, 3, 4, 5, 6, 7);

  @Test
  // Test the getters of AShape
  public void getters() {
    assertEquals("blicky", this.rectangle.getName());
    assertEquals(1, this.rectangle.getX(), .1);
    assertEquals(2, this.rectangle.getY(), .1);
    assertEquals(3, this.rectangle.getWidth(), .1);
    assertEquals(4, this.rectangle.getHeight(), .1);
    assertEquals(5, this.rectangle.getR(), .1);
    assertEquals(6, this.rectangle.getG(), .1);
    assertEquals(7, this.rectangle.getB(), .1);
  }

  @Test
  // Test the setters of AShape
  public void setters() {
    assertEquals("blicky", this.rectangle.getName());
    this.rectangle.setX(10);
    assertEquals(10, this.rectangle.getX(), .1);
    this.rectangle.setY(20);
    assertEquals(20, this.rectangle.getY(), .1);
    this.rectangle.setWidth(30);
    assertEquals(30, this.rectangle.getWidth(), .1);
    this.rectangle.setHeight(40);
    assertEquals(40, this.rectangle.getHeight(), .1);
    this.rectangle.setR(50);
    assertEquals(50, this.rectangle.getR(), .1);
    this.rectangle.setG(60);
    assertEquals(60, this.rectangle.getG(), .1);
    this.rectangle.setB(70);
    assertEquals(70, this.rectangle.getB(), .1);
  }
}
