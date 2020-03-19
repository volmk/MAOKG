package lab4.com;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Vector3f - float, Vector3d - double
public class Amanita implements ActionListener {
    private float upperEyeLimit = 5.0f; // 5.0
    private float lowerEyeLimit = 1.0f; // 1.0
    private float farthestEyeLimit = 6.0f; // 6.0
    private float nearestEyeLimit = 3.0f; // 3.0

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Amanita();
    }
    
    private Amanita() {
    	Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();

    }
    
    private BranchGroup createSceneGraph() {
    	BranchGroup objRoot = new BranchGroup();

        // ��������� ��'���, �� ������ �������� �� �����
        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRoot.addChild(treeTransformGroup);
        buildAmanita();

        Background background = new Background(new Color3f(0.9f, 0.9f, 0.9f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        // ����������� ���������
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        // ������������ ��������� ���������
        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;

    }
    

    private void buildAmanita() {

    	TransformGroup tgBottom = new TransformGroup();    
    	Transform3D transform = new Transform3D();    
    	Cylinder cylinder = AmanitaBody.getCylinder(0.1f, 0.6f);    
    	Vector3f vector = new Vector3f(.0f, -0.4f, .0f);    
    	transform.setTranslation(vector);    
    	tgBottom.setTransform(transform);    
    	tgBottom.addChild(cylinder);    
    	treeTransformGroup.addChild(tgBottom);
    	
    	TransformGroup tgMiddle = new TransformGroup();
    	Transform3D transform1 = new Transform3D();    
    	Cylinder cylinder1 = AmanitaBody.getCylinderMiddle(0.3f, 0.2f);    
    	Vector3f vector1 = new Vector3f(.0f, .0f, .0f);    
    	transform.setTranslation(vector1);    
    	tgMiddle.setTransform(transform1);    
    	tgMiddle.addChild(cylinder1);    
    	treeTransformGroup.addChild(tgMiddle);
        
    	TransformGroup tgTop = new TransformGroup();
    	Transform3D transform2 = new Transform3D();    
    	Cone cone = AmanitaBody.getCone(0.2f, 0.3f);    
    	Vector3f vector2 = new Vector3f(0.f, 0.2f, 0.f);    
    	transform2.setTranslation(vector2);    
    	tgTop.setTransform(transform2);    
    	tgTop.addChild(cone);    
    	treeTransformGroup.addChild(tgTop);
        
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		float delta = 0.03f;

        // rotation of the amanita
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.1f); // sight target
        Vector3d up = new Vector3d(.0f, 4.0f, .0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
		
	}
}