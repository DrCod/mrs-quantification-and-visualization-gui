import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LauncherView{

	
	Button tdBtn =new Button("Time Series"); 
	Button simulBtn =new Button("Simulation"); 
	Button mrsiBtn =new Button("MRSI"); 
	Button exitBtn =new Button("Exit"); 
	
	
	String simulation_img = "/simulation.gif";
	String td_img = "/1dts.gif";
	String mrsi_img = "/mrsi.gif";
	String close = "/close.gif";
	
	Image img_a =new Image(simulation_img);
	Image img_b =new Image(td_img);
	Image img_c =new Image(mrsi_img);
	Image img_d =new Image(close);

	//Time series button
	
	VBox simulBox  = new VBox(10);
	
	
	//MRSI button
	
	
	//Simulation button
	
	//Exit button
	
	/*
	URL simulationURL = getClass().getResource(simulation_img);
	URL tdURL = getClass().getResource(td_img);
	URL mrsiURL = getClass().getResource(mrsi_img);
	URL closeURL = getClass().getResource(close);

	
	String simulURLString = simulationURL.toExternalForm();	
	String tdURLString = tdURL.toExternalForm();
	String mrsiURLString = mrsiURL.toExternalForm();
	String closeURLString = closeURL.toExternalForm();
	
	*/
	
	
	GridPane root =new GridPane();
	
	
	
}
