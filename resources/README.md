This project contains our design for various representations of 2D animations. A key goal
for this project was to be able to represent an animation in multiple ways. To do accomplish this
we represented the animation as a model which can be rendered in different views.

Firstly, we defined an interface and abstract class for shapes that can be animated. Then
we implemented two specific instances of these: Rectangle and Ellipse.

Our model is the AnimateNCreate interface which is implemented in the AnimationCanvas.
As the motion aspect of an animation is the most logically complex we contain it in a separate
class and interface, Motion and IMotion respectively. Here we represent a motion as any change
to a shape, be it a positional change, a dimensional change, or a color change.

Our view is represented by the IView interface which contains a render method. We then 
created multiple classes that implement the interface which are each capable of rendering
a model in a distinct way. For example, TextualView displays the animation as text output and
SVGView displays the animation as SVG code. In this way we can represent a single animation 
(model) in numerous ways simply by using different views to render the animation.

The Interactive view uses the same panel as visual view and all views implement the 
render function, so we did not feel the need to create a super interface. In addition,
we didn't find a need for a controller as all the action events could be simply handled
in the view itself through button listeners.

We also programmatically created two animations in the animations package which are created by
the AnimationMaker class.

Lastly, we have our driver class, Excellence, which through command line arguments,
can read an animation from an input file and render it according to whatever view is specified
by the user.

We made a number of changes from the last submission.
First we were able to get TextualView and VisualView working properly.
We had to fix the "tweening" by casting it to doubles for division and then rounding the result
with Math.round().
We also created a new view for the InteractiveView which simply uses the same JPanel
that VisualView relies on.
We also got the interactive view to work correctly and created two programmatically generated
animations.
We decided to keep our view interface as a single interface from which all views inherit since
we only found a need for views to have a render function.