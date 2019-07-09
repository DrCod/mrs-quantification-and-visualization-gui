package medx;
import java.util.List;

import javafx.scene.control.Label;

public class MedXModel {
	
	
	private Label status;
	
	private String fileName;
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	

	
	public void setStatus(Label status) {
		this.status = status;
	}
	
	
	public Label getStatus() {
		return status;
	}

}
