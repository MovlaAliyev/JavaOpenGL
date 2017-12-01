package gameenginee.renderEginee;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public int getVaoId() {
		return vaoID;
	}
	
	public int vertexCount() {
		return vertexCount;
	}
}
