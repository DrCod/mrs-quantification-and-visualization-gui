package medx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableColumn;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.zavtech.morpheus.frame.DataFrame;
import com.zavtech.morpheus.viz.chart.Chart;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MedXGUI  extends Application{
	
	
	private static int APP_MODE = 0;
	private final Label statusLabel = new Label("MRSI");
	private final HBox statusBox = new HBox();
	private Stage dialogStage =null;
	private final VBox mainRoot= new VBox();

	private Label[] signalFileName;
	private String fileChosen;
	private Stage stage;
	
	private final FileChooser fileDialog =new FileChooser();
	private final MedXModel model =new MedXModel();

	private final Label fileLabel = new Label();
	private final Button FileBtn = new Button("File");
	private ListView<String> lv =new ListView<String>();
	
   private final Label gLabel =new Label();
   private final Label selectedItemsLbl =new Label();
	
    ScrollPane signals =new ScrollPane();
    
   
    Vector<Double> vec =new Vector();

   
	public static void main(String[] args) {
		
	 Application.launch(args);
     
	}
	
	@Override
	public void start(Stage primaryStage) {
 
		stage =primaryStage;
		StackPane pane =createMainStackPane();
		// MAIN LAYOUT

		 
		double requestedWidth = 20;
		double requestedHeight = 20;
		boolean preserveRatio = false;
		boolean smooth = true;
		
		String simulation_img = "/simulation.gif";
		String td_img = "/1dts.gif";
		String mrsi_img = "/mrsi.gif";
		String close = "/close.gif";
		String saveImg ="/save.gif";
		
		Image img_a =new Image(simulation_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_b =new Image(td_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_c =new Image(mrsi_img,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_d =new Image(close,requestedWidth,requestedHeight,preserveRatio,smooth);
		Image img_e =new Image(saveImg,requestedWidth,requestedHeight,preserveRatio,smooth);

		ImageView simulView =new ImageView(img_a);
		ImageView tdView =new ImageView(img_b);
		ImageView mrsiView =new ImageView(img_c);
		ImageView exitView =new ImageView(img_d);
		ImageView saveView =new ImageView(img_e);
	    //Status is set
		statusLabel.setTextFill(Color.WHITE);
		if(statusLabel.getText() != null) {
		   statusBox.getChildren().addAll(statusLabel);
		}
		else {
			String t =statusLabel.getText();
			t ="MRSI";
			statusLabel.setText(t);
 		   statusBox.getChildren().addAll(statusLabel);

		}
		BorderPane bp = new BorderPane();
		BorderPane.setMargin(statusBox, new Insets(10,10,0,0));
		statusBox.setStyle("-fx-background-color: green;" +
				"-fx-font-size: 12pt;" +
				"-fx-padding: 10 10 10 10;" );
		bp.setRight(statusBox);
		/// end of status
		
		// Tool bar
		ToolBar topbar =new ToolBar();
		Button Edit =new Button("Edit");
		Button display= new Button("Display");
		Button quant =new Button("Quantiation");
		Button preprocessing =new Button("Preprocessing");
		Button win = new Button("Window");
		Button help =new Button("Help"); 
		Button exitBtn =new Button("Exit");
		exitBtn.setTextFill(Color.RED);
		exitBtn.setOnAction(e->Platform.exit());
		
		 FileBtn.setOnAction(v->showFilePickerDialog());

		
		topbar.getItems().addAll(FileBtn,Edit,display,quant,preprocessing,win,help,exitBtn);
		//end of tool bar
		
		//Set file chooser event
		
		// create tabs below the top bar
		Button mrsiTab = new Button();
		mrsiTab.setGraphic(mrsiView);
		
		Button visualizationTab =new Button();
		visualizationTab.setGraphic(simulView);
		
		Button tdTab = new Button();
		tdTab.setGraphic(tdView);
		
		Button saveTab =new Button();
		saveTab.setGraphic(saveView);
		
		Button printTab =new Button("Print");
		
		
		//create Tab pane to hold all tabs
		ToolBar tp =new ToolBar();
		tp.getItems().addAll(mrsiTab,visualizationTab,tdTab,saveTab,printTab);
		
		BorderPane tabroot = new BorderPane();
		tabroot.setCenter(tp);
		tabroot.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		
		//end of tab pane and border pane
		
		
		//get center of application
		//Parent node is an HBOX containing two main child nodes
		
		HBox centernode= new HBox();
		//child node 1 => VBox
		VBox node1 =new VBox();
		//node1.setMinSize(200, 300);
		// node1.setMaxSize(800, 800);
		 node1.setStyle("-fx-padding: 10;" +
					"-fx-border-style: solid inside;" +
					"-fx-border-width: 2;" +
					"-fx-border-insets: 5;" +
					"-fx-border-radius: 5;" +
					"-fx-border-color: blue;");
		// add 3 elements inside node 1
	
		    Label signalLabel =new Label("Signals");
			 //fileLabel.setTextAlignment(TextAlignment.CENTER);
			// ScrollAble MRSI signals
			
		//add the form below the list
		Label dateLbl = new Label("Date");
		TextField dateFld = new TextField();
		dateFld.setPromptText("DD-MM-YYYY");
	
		Label dirLbl = new Label("Directory");
		TextField dirFld = new TextField();
		dirFld.setPromptText("Work");
		
		Label calcLbl = new Label("Calc. Time(ms)");
		TextField calFld = new TextField();
		calFld.setPromptText("77");
		
		GridPane grid =new GridPane();
		grid.setHgap(10); // hgap = 10px
		grid.setVgap(5); // vgap = 5px
		grid.setStyle("-fx-padding: 10;-fx-background-color: lightgray;");
		
		grid.addRow(0,dateLbl,dateFld);
		grid.addRow(1,dirLbl,dirFld);
		grid.addRow(2,calcLbl,calFld);
		
		VBox f =new VBox();
		Label l =new Label("Experiment");
		f.getChildren().addAll(l,grid);
		f.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-background-color: lavender;" +
				"-fx-border-color: blue;");
		
		//table for displaying the numerical results
		//and the cancer diagnosis results
		
	
		TableView<QuantResults> results =new TableView<>(ResultsUtil.getNumericalList());
		
		//add columns to table
		results.getColumns().addAll(ResultsUtil.getIdColumn(),ResultsUtil.getMetNameColumn(),
				ResultsUtil.getAmpColumn(),ResultsUtil.getFreqColumn(),
				ResultsUtil.getConcColumn(),ResultsUtil.getStdColumn(),ResultsUtil.getPhaseColumn(),
				ResultsUtil.getSummaryColumn(),ResultsUtil.getDateColumn()
				);
	
				
		
		Label numericResults =new Label("Numerical Results");
		
		//set list view items selected listener
		
	
		lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		lv.setOnMouseClicked(new EventHandler<Event>() {
			  @Override
		        public void handle(Event event) {
				  
				  // read data from the file path and plot the chart
				  
				  try (BufferedReader reader = new BufferedReader(new FileReader(lv.getSelectionModel().getSelectedItem()))) {

				        String line;
				        while ((line = reader.readLine()) != null) {
				        	
				          line = line.substring(2, 16);
				          double temp = Double.parseDouble(line);
				          vec.add(temp);
				        }
				         
				        reader.close();

				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				  DataFrame<Double,Integer> frame = DataFrame.of(NumpyUtils.arange(0, 2048, 1), Integer.class, columns -> {
					    columns.add(0, vec);
					});
					
				    Chart.create().withLinePlot(frame, chart -> {
					    chart.show();
					});
				
				}
		});
			
		node1.getChildren().addAll(signalLabel,lv,f,numericResults,results);
		
		//child node 2 => VBox
		// for holding the graphs and visualization
		VBox node2 =new VBox();	
		
		// add graph node of the corresponding selected item
	
	   
	   //node2.getChildren().addAll(gLabel);
		
		centernode.getChildren().addAll(node1);
		
		//Add all Application child nodes here
		mainRoot.getChildren().addAll(bp,topbar,tabroot,centernode);
		
		StackPane vroot =new StackPane();
		Scene scene =new Scene(mainRoot);
		
		dialogStage =new Stage();
		dialogStage.setMaxHeight(300);
		dialogStage.setMaxWidth(300);
		
		dialogStage.initModality(Modality.WINDOW_MODAL);
		vroot.getChildren().add(pane);
        Scene s =new Scene(vroot);
        dialogStage.setScene(s);
		dialogStage.setTitle("MedX V.1.0");

		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(600);
		primaryStage.setFullScreen(true);

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

	

	private void showFilePickerDialog() {

		fileDialog.setTitle("Choose MRSI file");
		
		File recordsDir = new File(System.getProperty("user.home"), ".MedX/records");
		if (! recordsDir.exists()) {
		    recordsDir.mkdirs();
		}

		fileDialog.setInitialDirectory(recordsDir);
		
		
		fileDialog.getExtensionFilters().addAll( 
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		fileDialog.setSelectedExtensionFilter(fileDialog.getExtensionFilters().get(1));
		
		List<File> files = fileDialog.showOpenMultipleDialog(stage);
		
		if(files !=null) {
			for(File f: files) {
				//get the file string path and set it to the label in the scroll pane
			   			
			   			fileChosen =f.getPath().toString();	
			   			fileLabel.setText(fileChosen);
			   			//model.setFileName(fileChosen);
				      // signals.setContent(fileLabel);
			   			lv.getItems().add(fileChosen);
			   		
			   			
			}
		}
		else{
		// no file chosen
		FileBtn.setTooltip(new Tooltip("No files chosen"));
		}
		
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
		exitBtn.setFill(Color.RED);
        exitBtn.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
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
			
		simulBox.setPadding(new Insets(10, 10, 10, 10));
		simulBox.getChildren().addAll(simulView,simulBtn);
		simulBox.setStyle("-fx-border-width: 2.0; -fx-border-color: blue;");
		simulBox.setOnMouseClicked(v->startSimulation());
		
		VBox tdBox  = new VBox(10);
		tdBox.setPadding(new Insets(10, 10, 10, 10));
		tdBox.getChildren().addAll(tdView,tdBtn);
		tdBox.setStyle("-fx-border-width: 2.0; -fx-border-color: blue;");
		tdBox.setOnMouseClicked(v->loadTDsignals());
		
		
		VBox mrsiBox  = new VBox(10);
		mrsiBox.setPadding(new Insets(10, 10, 10, 10));
		mrsiBox.getChildren().addAll(mrsiView,mrsiBtn);
		mrsiBox.setStyle("-fx-border-width: 2.0; -fx-border-color: blue;");
		mrsiBox.setOnMouseClicked(v->mrsiVisualization());
		
		VBox exitBox  = new VBox(10);
		exitBox.setPadding(new Insets(10, 10, 10, 10));
		exitBox.getChildren().addAll(exitView,exitBtn);
		exitBox.setStyle("-fx-border-width: 2.0; -fx-border-color: red;");
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
	private void loadTDsignals() {
		statusLabel.setText("TD Signals");
	    dialogStage.close();
	}
	private void mrsiVisualization() {
		statusLabel.setText("MRSI");
		dialogStage.close();		
	} 
	
	private void startSimulation() {
		statusLabel.setText("Simulation");
		dialogStage.close();
		
	}

	
}
