package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	public Vector3f position = new Vector3f(0,0,0);
	public float pitch;
	public float yaw;
	public float roll;
	
	public Camera()
	{
		
	}

	public void move()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
			position.z -= 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_F))
			position.z += 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			position.x -= 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_G))
			position.x += 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
			position.y -= 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			position.y += 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			yaw -= 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			yaw += 0.5f;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
}
