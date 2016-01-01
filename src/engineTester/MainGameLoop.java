package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;


import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();

		RawModel dragon = OBJLoader.loadObjModel("dragon", loader);
		RawModel wineGlass = OBJLoader.loadObjModel("WineGlass", loader);
		
		ModelTexture camo = new ModelTexture(loader.loadTexture("camo"));
		ModelTexture glass = new ModelTexture(loader.loadTexture("glass"));
		
		TexturedModel dragonModel = new TexturedModel(dragon, camo);
		TexturedModel wineGlassModel = new TexturedModel(wineGlass, glass);
		camo.setShineDamper(10);
		camo.setReflectivity(1);
		glass.setReflectivity(0);
		glass.setShineDamper(0);
		
		Entity dragonEnt = new Entity(dragonModel, new Vector3f(0, -6, -12),0,0,0,1);
		Entity wineGlassEnt = new Entity(wineGlassModel, new Vector3f(-9, 0, -12),0,0,0,1);
		
		Light light = new Light(new Vector3f(0, 12, 10), new Vector3f(1.0f, 1.0f, 1.0f));
		Camera camera = new Camera();
		
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1, 1, loader, new ModelTexture(loader.loadTexture("grass")));
		

		float angle = 0.5f;
		
		wineGlassEnt.increaseRotation(-90, 0, 0);
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested())
		{
			camera.move();
			
			renderer.processEntity(dragonEnt);
			renderer.processEntity(wineGlassEnt);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			
			renderer.render(light, camera);
			
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
