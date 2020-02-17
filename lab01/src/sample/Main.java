package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private final static short WIDTH = 610;
    private final static short HEIGHT = 367;
    Color orange = Color.rgb(255, 128, 65);
    Color yellow = Color.rgb(255, 255, 1);
    Color green = Color.rgb(2, 139, 131);
    Color red = Color.rgb(254, 0, 0);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Lab1");

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(orange);

        Polygon sun = new Polygon(
                219.0, 127.0,
                310.0, 102.0,
                425.0, 180.0,
                398.0, 259.0,
                319.0, 290.0,
                230.0, 272.0,
                182.0, 200.0);
        sun.setFill(yellow);
        root.getChildren().add(sun);

        Rectangle rightEye = new Rectangle(260, 172, 12, 10);
        rightEye.setFill(green);
        root.getChildren().add(rightEye);

        Rectangle leftEye = new Rectangle(336, 175, 12, 10);
        leftEye.setFill(green);
        root.getChildren().add(leftEye);


        Polygon mouth = new Polygon(
                308.0, 237.0,
                347.0, 211.0,
                268.0, 211.0);
        mouth.setFill(red);
        root.getChildren().add(mouth);

        List<Polygon> lines = new ArrayList<>();
        lines.add(new Polygon(258, 22, 269, 21, 292, 110, 282, 115));
        lines.add(new Polygon(356, 34, 368, 39, 326, 117, 317, 108));
        lines.add(new Polygon(419, 66, 428, 72, 355, 139, 347, 134));
        lines.add(new Polygon(390, 158, 480, 126, 486, 136, 400, 167));
        lines.add(new Polygon(485, 227, 483, 237, 405, 221, 410, 212));
        lines.add(new Polygon(472, 295, 464, 303, 397, 257, 402, 248));
        lines.add(new Polygon(391, 335, 380, 340, 349, 278, 360, 271));
        lines.add(new Polygon(271, 347, 283, 347, 282, 276, 271, 276));
        lines.add(new Polygon(154, 297, 162, 305, 218, 247, 212, 237));
        lines.add(new Polygon(100, 213, 98, 203, 187, 195, 188, 207));
        lines.add(new Polygon(131, 118, 137, 109, 214, 153, 208, 159));
        lines.add(new Polygon(188, 49, 198, 44, 245, 126, 234, 131));

        for (Polygon line : lines) {
            line.setFill(yellow);
            root.getChildren().add(line);
        }

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
