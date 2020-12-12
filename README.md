# Frogger
 
 **University of Nottingham**\
 **G52SWM Developing Maintenance Software** Coursework\
 **Chuang Caleb hcycc2 20204134**

## Table Of Contents


## Build and Run

Tested on:
- macOS (Java 15 + JavaFX 11)
- 

Build Script:\
`mvn clean compile package exec:java`

## Source File Structure
_(insert structure)_

## Summary Report (500 words)

### Key Refactorings ###

#### General
- Renamed confusing classnames, methods and fields
- Replaced magic numbers with local constants

- Main's level building bulk deferred to LevelBuilder and Level
- Refactored out MyStage: Level to handle the Pane, MusicPlayer to handle music methods
- Divided World's responsibilities
  - Game controls the flow of each game and (redesigns) keyboard input
  - Level handles Pane (aggregating it instead of inheriting it) besides also handling the Actors
- Separated collision checking/handling into its own classes, promoting single responsibility

#### Polymorphism and Abstraction
- Pushed down methods and fields from Actor to new abstract subclasses
- MovableActor extends Actor to also handle movement and tick events
  - Frog inherits MovableActor
- PanningActor extends MovableActor to also pan, wrap and clone 
  - Car, Log and Turtles inherits PanningActor
  - pulled up common methods from aforementioned classes
- Enforces Liskov's Substitution Principle by preventing classes from inheriting unnecessary functions: e.g. Frog shouldn't be viewing clone
- The commonly inherited superclass means we can generalize objects—e.g. detect a collision between a Frog and any PanningActor—before determining the exact concrete class

#### Structure
- `java` and `resources` folder
- Appropriate sub-categorization shown above [link]

#### ```frogger.constant``` package
- Shared constants between different classes that cannot have a local scope
- Long constants that would clutter a class
- Predefined datatypes


### Design Patterns ###

#### MVC Pattern
Each set of MVC for each user interface is decoupled and stored in:
- Model: ```frogger.model```
- Controller: ```frogger.controller``` 
- View: _.fxml_ files under ```resources/frogger/view```

#### Singleton Pattern
Ensures there is never more than one instance of the following:
- ```SceneSwitcher```
- ```MusicPlayer```
- ```LaneBuilder```
- ```LevelBuilder```

#### Factory, Prototype and Builder Pattern
- LaneBuilder's build() method is a Factory Method as it creates (an ArrayList of abstract PanningActor) objects without specifying its concrete class
- Each lane of PanningActors have the same data and behavior, only differing in their x-coordinate offset
- LaneBuilder holds a prototype library of all the different types of PanningActors
- For each lane, it first determines which base prototype to clone from the library
- Base clone becomes the first PanningActor in the lane—subsequent PanningActors are cloned from the base clone
- Builder pattern is implemented on a small scale to configure the base clone before more clones are built off it; 
  then, each individual clone is configured their unique x-coordinate

### New Additions ###

- Resized all image resources and scaled player movement to a follow a 13x11 square grid, each 32x32 pixels
- Code prevents players from intentionally leaping out of bounds
- Main Menu and Info screens
- High Score popup window at the end of every round—Cumulative High Score at the end of the game
- Required "Enter nickname" field in the Main Menu that locks out Start button if empty
- LevelBuilder can easily construct new levels
- Frog dies if it floats offscreen or doesn't jump into the End properly
  - Appropriate death messages for all death types
- Custom font
- JUnit tests







