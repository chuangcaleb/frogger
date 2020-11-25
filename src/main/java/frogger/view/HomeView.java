//package frogger.view;
//
//import frogger.controller.HomeController;
//import frogger.model.actor.BackgroundImage;
//import frogger.model.actor.Level;
//import frogger.model.actor.Frog;
//
///**
// * {@code Homeview} is a View that handles display of elements in the Home state
// */
//public class HomeView extends Level {
//
//    public HomeView(final HomeController homeController) {
//
//        BackgroundImage froggerback = new BackgroundImage("file:src/main/resources/frogger/bg/GameBackground.png");
//        add(froggerback);
//
//        Frog frog = new Frog("file:src/main/resources/frogger/frog/froggerUp.png");
//        add(frog);
//        // To add like this? Don't need variables since sole purpose (for now) is instantiation?
////        add(new Frog("file:src/main/resources/frogger/frog/froggerUp.png"));
//
//        /* To move to GameView class
//

//
//        start();
//
//    }
//
//    @Override
//    public void act(long now) {
//
//    }
//}
