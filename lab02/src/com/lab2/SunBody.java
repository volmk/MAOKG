package com.lab2;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

public class SunBody extends JPanel implements ActionListener{  
	
	public static int maxWidth;
    public static int maxHeight;
    
    Timer timer;

    private double angle = 0;
    private double rotateAngle = 0;
    private double scale = 1;
    private double delta = 0.01;

    // for movement animation
    private double tx = 1;
    private double ty = 0;
    private int radius = 100;
    
    
    public SunBody() {
        timer = new Timer(10, this);
        timer.start();
    }
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        
        g2d.setBackground(new Color(255, 128, 64));
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        
		double pointsSun[][] = {
				{150, 200}, 
				{180, 140},
				{250, 120},
				{350, 180},
				{330, 250},
				{260, 280},
				{190, 260}
		};
		
		double smileSun[][] = {
				{215, 210},
				{280, 210},
				{247, 230}
		};

		GeneralPath sun = new GeneralPath();
		GeneralPath smile = new GeneralPath();
		
		//task 1 and 2
		g2d.translate(0, 125);
		GradientPaint gp1 = new GradientPaint(5, 25, Color.YELLOW, 20, 2, Color.MAGENTA, true);         
		g2d.setPaint(gp1);
		sun.moveTo(pointsSun[0][0], pointsSun[0][1]);
		
		for (int k = 1; k < pointsSun.length; k++)             
			sun.lineTo(pointsSun[k][0], pointsSun[k][1]); 
		
		sun.closePath();
		g2d.fill(sun);
		g2d.draw(sun);

		g2d.setPaint(Color.BLACK);
		g2d.fillRect(210, 170, 8, 8);
		g2d.fillRect(270, 170, 8, 8);
		g2d.setPaint(Color.BLACK);
		g2d.drawOval(230, 210, 40, 40);
		
		//animation
        BasicStroke bs2 = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs2);
        g2d.drawRect(520, -90, 800, 750);
        g2d.translate(800, 70);
        g2d.translate(tx, ty);
        g2d.setPaint(Color.YELLOW);
        
        //draw sun body
        for (int k = 1; k < pointsSun.length; k++)             
			sun.lineTo(pointsSun[k][0], pointsSun[k][1]);
		
		sun.closePath();
		g2d.rotate(-rotateAngle, 150, 200);
		g2d.fill(sun);
        g2d.draw(sun);
        
        //draw eyes
        g2d.setPaint(new Color(0,128,128));
        g2d.fillRect(210,170,8,8);
        g2d.fillRect(270,170,8,8);
        
        //draw smile
        g2d.setPaint(Color.RED);
        smile.moveTo(smileSun[0][0], smileSun[0][1]);
		
		for (int k = 1; k < smileSun.length; k++)             
			smile.lineTo(smileSun[k][0], smileSun[k][1]); 
		
		smile.closePath();
		g2d.fill(smile);
		g2d.draw(smile);
		
		//draw lines
		g2d.setPaint(Color.YELLOW);
		g2d.drawLine(200, 140, 150, 80);
		g2d.drawLine(240, 130, 215, 60);
		g2d.drawLine(255, 140, 290, 70);
		g2d.drawLine(290, 150, 340, 100);
		g2d.drawLine(310, 180, 400, 155);
		g2d.drawLine(310, 200, 400, 220);
		g2d.drawLine(320, 240, 380, 280);
		g2d.drawLine(290, 260, 320, 320);
		g2d.drawLine(225, 270, 220, 330);
		g2d.drawLine(190, 230, 130, 280);
		g2d.drawLine(150, 200, 90, 210);
		g2d.drawLine(170, 165, 110, 130);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        // scaling
//        if (scale < 0.01) {
//            delta = -delta;
//        } else if (scale > 0.99) {
//            delta = -delta;
//        }
//        scale += delta;
        
        // movement
        tx = radius * Math.cos(Math.toRadians(angle));
        ty = - radius * Math.sin(Math.toRadians(angle));
        
        //angle++;
        rotateAngle += 0.01;
     

        repaint();
    }
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Lab 2");         
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 900);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
		maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
        
		frame.add(new SunBody());
		
		
		frame.setVisible(true);

	}
}
