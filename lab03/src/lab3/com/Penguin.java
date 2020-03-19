package lab3.com;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Penguin extends Application {

	private static double X (double originalX){
        return originalX + 300;
    }
    private static double Y (double originalY){
        return originalY + 200;
    }

    public static void main(String args[]) {
        launch(args);
    }
    
	public void start(Stage primaryStage) {
		Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);
        
        //top
        Ellipse head = new Ellipse(X(45), Y(0), 60, 70);
        head.setFill(Color.rgb(11, 145, 218));
        root.getChildren().add(head);
        
        //bottom
        Circle legs = new Circle(X(45), Y(70), 75);
        legs.setFill(Color.rgb(11, 145, 218));
        root.getChildren().add(legs);
        
        //middle
        Ellipse mid = new Ellipse(X(45), Y(70), 50, 70);
        mid.setFill(Color.rgb(198, 225, 254));
        root.getChildren().add(mid);

        //arms
        Ellipse arm1 = new Ellipse(X(-40), Y(53), 15, 43);
        arm1.setRotate(45);
        arm1.setFill(Color.rgb(3, 66, 163));
        root.getChildren().add(arm1);
        
        Ellipse arm2 = new Ellipse(X(130), Y(53), 15, 43);
        arm2.setRotate(-45);
        arm2.setFill(Color.rgb(3, 66, 163));
        root.getChildren().add(arm2);
        
        //legs
        Ellipse leg1 = new Ellipse(X(-10), Y(140), 40, 25);
        leg1.setFill(Color.rgb(242, 187, 0));
        root.getChildren().add(leg1);
        
        Ellipse leg2 = new Ellipse(X(90), Y(145), 40, 25);
        leg2.setFill(Color.rgb(242, 187, 0));
        root.getChildren().add(leg2);
        
        Ellipse leg1_2 = new Ellipse(X(-18), Y(141), 27, 19);
        leg1_2.setFill(Color.rgb(254, 219, 155));
        root.getChildren().add(leg1_2);
        
        Ellipse leg2_2 = new Ellipse(X(94), Y(141), 27, 19);
        leg2_2.setFill(Color.rgb(254, 219, 155));
        root.getChildren().add(leg2_2);
        
        //mouth
        Polygon mouth = new Polygon();
        mouth.getPoints().addAll(new Double[]{
        	327.0, 200.0,
        	360.0, 200.0,
        	345.0, 220.0
			});
        mouth.setFill(Color.rgb(251,176,22));
		root.getChildren().add(mouth);
        
        //eyes
        Ellipse eye1 = new Ellipse(X(27), Y(-15), 13, 20);
        eye1.setStrokeWidth(1);
        eye1.setStroke(Color.BLACK);
        eye1.setFill(Color.WHITE);
        root.getChildren().add(eye1);
        
        Ellipse eye2 = new Ellipse(X(60), Y(-10), 20, 13);
        eye2.setStrokeWidth(1);
        eye2.setStroke(Color.BLACK);
        eye2.setFill(Color.WHITE);
        root.getChildren().add(eye2);
        
        Circle cirle1 = new Circle(X(34), Y(-10), 4);
        cirle1.setFill(Color.BLACK);
        root.getChildren().add(cirle1);
        
        Circle cirle2 = new Circle(X(46), Y(-10), 4);
        cirle2.setFill(Color.BLACK);
        root.getChildren().add(cirle2);
        
        // Animation
        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(50);
        translateTransition.setCycleCount(cycleCount+1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(150);
        translateTransition2.setCycleCount(cycleCount+1);
        translateTransition2.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                scaleTransition2,
                translateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
        // End of animation
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
