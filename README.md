# Interactive-Animator
Interactive Shape Animator Created in Java Following a Model-View-Controller Structure

This project contains a design for various representations of 2D animations. A key goal for this project was to be able to represent an animation in multiple ways. To do accomplish this, the animation was represented as a model which can be rendered in different views.

Firstly, interface and abstract classes were defined for shapes that can be animated. Two specific instances were implemented: Rectangle and Ellipse.

The model is the AnimateNCreate interface which is implemented in the AnimationCanvas. As the motion aspect of an animation is the most logically complex we contain it in a separate class and interface, Motion and IMotion respectively. Here a motion is represented as any change to a shape, be it a positional change, a dimensional change, or a color change.

The view is represented by the IView interface which contains a render method. Multiple classes were created that implement the interface which are each capable of rendering a model in a distinct way. For example, TextualView displays the animation as text output and SVGView displays the animation as SVG code. In this way, a single animation (model) can be represented in numerous ways simply by using different views to render the animation.

The Interactive view uses the same panel as visual view and all views implement the render function, so there was no need to create a super interface. In addition, there was no creation of a controller as all the action events could be simply handled in the view itself through button listeners.

Also, there is the programmatic creation of two animations in the animations package via the AnimationMaker class.

Lastly, there is the driver class, Excellence, which through command line arguments, can read an animation from an input file and render it according to whatever view is specified by the user.
