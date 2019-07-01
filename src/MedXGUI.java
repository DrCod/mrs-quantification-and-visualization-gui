import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MedXGUI  extends Application{

	public static void main(String[] args) {
		
	 Application.launch(args);

	}
	@Override
	public void start(Stage primaryStage) {
 
		primaryStage.setFullScreen(true);
		StackPane pane =createMainStackPane();
		
		
		
		HBox mainRoot = new HBox();
		
	
		
		
		
		VBox vroot =new VBox();
		Scene scene =new Scene(mainRoot);
		
		Stage dialogStage =new Stage();
		
		dialogStage.initModality(Modality.WINDOW_MODAL);
		vroot.getChildren().add(pane);
        Scene s =new Scene(vroot);
        dialogStage.setScene(s);
		dialogStage.setTitle("MedX V.1.0");

		primaryStage.setTitle("MedX V.1.0");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		dialogStage.show();

		
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
		
		
		
	}
	private StackPane createMainStackPane() {

		 StackPane spane =new StackPane();
		 
		double requestedWidth = 50;
		double requestedHeight = 50;
		boolean preserveRatio = false;
		boolean smooth = true;
		
		Text tdBtn =new Text("Time Series");
		tdBtn.setFill(Color.BLUE);
		tdBtn.setTextAlignment(TextAlignment.CENTER);
		Text simulBtn =new Text("Simulation"); 
		simulBtn.setFill(Color.BLUE);
		simulBtn.setTextAlignment(TextAlignment.CENTER);
		Text mrsiBtn =new Text("MRSI");
		mrsiBtn.setFill(Color.BLUE);
		mrsiBtn.setTextAlignment(TextAlignment.CENTER);
		Text exitBtn =new Text("Exit");
		exitBtn.setFill(Color.BLUE);
		exitBtn.setTextAlignment(TextAlignment.CENTER);

		
		String simulation_img = "/simulation.gif";
		String td_img = "/1dts.gif";
		String mrsi_img = "/mrsi.gif";
		String close = "/close.gif";
		
		Image img_a =new Image(simulation_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_b =new Image(td_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_c =new Image(mrsi_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_d =new Image(close,requestedWidth,requestedHeight,preserveRatio,smooth);

		//Time series button
		
		ImageView simulView =new ImageView(img_a);
		ImageView tdView =new ImageView(img_b);
		ImageView mrsiView =new ImageView(img_c);
		ImageView exitView =new ImageView(img_d);
		
		VBox simulBox  = new VBox(10);
		simulBox.getChildren().addAll(simulView,simulBtn);
		simulBox.setOnMouseClicked(v->startSimulation());
		
		VBox tdBox  = new VBox(10);
		tdBox.getChildren().addAll(tdView,tdBtn);
		tdBox.setOnMouseClicked(v->loadTDsignals());
		
		VBox mrsiBox  = new VBox(10);
		mrsiBox.getChildren().addAll(mrsiView,mrsiBtn);
		mrsiBox.setOnMouseClicked(v->mrsiVisualization());
		
		VBox exitBox  = new VBox(10);
		exitBox.getChildren().addAll(exitView,exitBtn);
		exitBox.setOnMouseClicked(v->Platform.exit());
	
		GridPane root =new GridPane();
		root.setHgap(20);
		root.setVgap(20);
		root.setStyle("-fx-background-color: lightgray;");

		//set grid elements
		root.getChildren().addAll(mrsiBox,simulBox,tdBox,exitBox);
		
		GridPane.setConstraints(mrsiBox, 0, 0);
		GridPane.setConstraints(simulBox, 0, 1);		
		GridPane.setConstraints(tdBox, 1, 0);
		GridPane.setConstraints(exitBox, 1, 1);


		
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");
		
		spane.getChildren().add(root);
	
		
		return spane;
	}
	private Object mrsiVisualization() {
		// TODO Auto-generated method stub
		return null;
	}
	private Object loadTDsignals() {
		// TODO Auto-generated method stub
		return null;
	}
	private Object startSimulation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
