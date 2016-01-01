package toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;

public class Maths {
	
	private static final Vector3f x_axis = new Vector3f(1,0,0);
	private static final Vector3f y_axis = new Vector3f(0,1,0);
	private static final Vector3f z_axis = new Vector3f(0,0,1);
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		Matrix4f.translate(translation, matrix, matrix);
		
		Matrix4f.rotate((float)Math.toRadians(rx), x_axis, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), y_axis, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), z_axis, matrix, matrix);
		
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()), x_axis, viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()), y_axis, viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getRoll()), z_axis, viewMatrix, viewMatrix);
		
		Vector3f pos = camera.getPosition();
		Vector3f negativePos = new Vector3f(-pos.x, -pos.y, -pos.z);
		Matrix4f.translate(negativePos, viewMatrix, viewMatrix);
		
		return viewMatrix;
	}
}
