/*
 * 
 * Name: Xinyuan Zhang
 * Class: CS480
 * 
 * Assignment 2
 * Due: 2019/10/15
 * Problem Number: 1
 * 
 * Description: 
 *  Modified Palm.java to create class for ant body object.
 *  
 */

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl

public class Body extends Circular implements Displayable {

	/**
	 * The OpenGL handle to the display list which contains all the components which
	 * comprise this cylinder.
	 */
	private int callListHandle;

	/**
	 * Instantiates this object with the specified radius and OpenGL utility toolkit
	 * object for drawing the sphere.
	 * 
	 * @param radius The radius of this object.
	 * @param glut   The OpenGL utility toolkit object for drawing the sphere.
	 */
	public Body(final double radius, final GLUT glut) {
		super(radius, glut);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param gl {@inheritDoc}
	 * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
	 */
	@Override
	public void draw(GL2 gl) {
		gl.glCallList(this.callListHandle);
	}

	/**
	 * Defines the OpenGL call list which draws a scaled sphere.
	 * 
	 * @param gl {@inheritDoc}
	 * 
	 * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
	 */
	@Override
	public void initialize(final GL2 gl) {
		this.callListHandle = gl.glGenLists(1);

		// create an ellipsoid for the palm by scaling a sphere
		gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
		gl.glPushMatrix();
		// position this so that the sphere is drawn above the x-y plane, not at
		// the origin
		gl.glTranslated(0, 0, this.radius());
		gl.glScalef(0.9f, 0.5f, 1);
		this.glut().glutSolidSphere(this.radius(), 36, 18);
		gl.glPopMatrix();
		gl.glEndList();
	}

}