package gameenginee.engineestart;

import org.lwjgl.opengl.Display;

import gameenginee.renderEginee.DisplayManager;
import gameenginee.renderEginee.Loader;
import gameenginee.renderEginee.RawModel;
import gameenginee.renderEginee.Render;

public class StartEnginee {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Render render = new Render(); 
		
		float[] positions = {
				-0.5f, 0.5f, 0f,
				-0.5f,-0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		RawModel model = loader.loadToVAO(positions);
		
		// Game loop
		while(!Display.isCloseRequested()) {
			render.prepare();
			render.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.clean();
		DisplayManager.closeDisplay();
		
	}
	
}
