package gameenginee.renderEginee;

import java.util.List;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/*
 * Loading 3D model to memory 
 */

public class Loader {
	
	// memory management which after closing game deleting all vao and vbo in memory
	private List<Integer> vao = new ArrayList<Integer>();
	private List<Integer> vbo = new ArrayList<Integer>();
	
	
	// deleting vaos and vbos from memory
	public void clean() {
		for(int vID : vao)
			GL30.glDeleteVertexArrays(vID);
		
		for(int vbID: vbo)
			GL15.glDeleteBuffers(vbID);
	}
	
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		/*
		 * @param 0 is position first index 
		 */
		storeDataInAtributeList(0, positions); 
		unbindVAO();
		return new RawModel(vaoID, positions.length / 3);
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vao.add(vaoID);
		GL30.glBindVertexArray(vaoID);        // activate vao
		
		return vaoID;
	}
	
	private void storeDataInAtributeList(int attrnumber, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbo.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, dataToFloatBuffer(data), GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attrnumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // unbind
	}

	private void unbindVAO() {
		GL30.glBindVertexArray(0); // deactivate vao by giving @param 0
	}
	
	// convert float to fbuffer
	// data which store in vbo must be in FloatBuffer format
	private FloatBuffer dataToFloatBuffer(float[] data) {
		FloatBuffer fbuffer = BufferUtils.createFloatBuffer(data.length);
		fbuffer.put(data);
		fbuffer.flip();
		return fbuffer;
	}
}
