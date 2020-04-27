package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Vector3f;

public class DinoAnimation implements ActionListener, KeyListener {
    private TransformGroup car;
    private Transform3D transform3D = new Transform3D();

    private float x = (float) -1.2;
    private float y = (float) -0.7;

    private static final float rotationSpeed = 0.03f;


    private boolean w = false;
    private boolean a = false;
    private boolean d = false;
    private boolean s = false;

    public DinoAnimation(TransformGroup boat) {
        this.car = boat;
        this.car.getTransform(this.transform3D);

        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {
        if (w) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(-rotationSpeed);
            transform3D.mul(rotation);
        }
        if (a) {
            x -= 0.02f;
        }
        if (d) {
            x += 0.02f;
        }
        if (s) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(rotationSpeed);
            transform3D.mul(rotation);
        }

        transform3D.setTranslation(new Vector3f(x, y, 0));
        car.setTransform(transform3D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                w = true;
                break;
            case 'a':
                a = true;
                break;
            case 's':
                s = true;
                break;
            case 'd':
                d = true;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                w = false;
                break;
            case 's':
                s = false;
                break;
            case 'a':
                a = false;
                break;
            case 'd':
                d = false;
                break;
        }
    }
}