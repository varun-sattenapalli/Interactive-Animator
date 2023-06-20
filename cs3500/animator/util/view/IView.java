package cs3500.animator.util.view;

import cs3500.animator.util.model.AnimateNCreate;

/**
 * Represents a view of an animation. There are 3 types of views - a textual view, an SVG view,
 * and a visual view
 */
public interface IView {

  /**
   * Creates a rendered output of the view of the animation.
   *
   * @param model a model for the MVC of the animation which the view relies on
   */
  public void render(AnimateNCreate model);
}
