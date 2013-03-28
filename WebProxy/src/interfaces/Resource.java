package interfaces;

public interface Resource {

	public boolean openResourceConnection();
	public String getResource();
	public boolean closeResourceConnection();
	public String getContentType();
}
