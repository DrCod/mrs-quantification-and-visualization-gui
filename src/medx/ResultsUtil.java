package medx;
import java.awt.Color;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResultsUtil {

	public static ObservableList<QuantResults> getNumericalList() {
		/*returns an observable list of results */
		QuantResults p1 = new QuantResults("Ace",55.4,23.0,11.5,56,55.6,"Benign",LocalDate.of(2019, 07, 5));
		QuantResults p2 = new QuantResults("Met",55.4,23.0,11.5,56,55.6,"Malignant",LocalDate.of(2019, 07, 8));
		QuantResults p3 = new QuantResults("Tau",55.4,23.0,11.5,56,55.6,"Benign",LocalDate.of(2019, 07, 7));
		QuantResults p4 = new QuantResults("Ace",55.4,23.0,11.5,56,55.6,"Benign",LocalDate.of(2019, 07, 9));
		QuantResults p5 = new QuantResults("Ach",55.4,23.0,11.5,56,55.6,"Malignant",LocalDate.of(2019, 07, 10));
		
		return FXCollections.<QuantResults>observableArrayList(p1, p2, p3, p4, p5);
		}
	
	/* Returns Id TableColumn */
	public static TableColumn<QuantResults, Integer> getIdColumn() {
	TableColumn<QuantResults, Integer> IdCol = new TableColumn<>("Id");
	IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
	return IdCol;
	}
	 
	/* Returns Metabolite Name TableColumn */
	public static TableColumn<QuantResults, String> getMetNameColumn() {
	TableColumn<QuantResults, String> metNameCol = new TableColumn<>("Metabolite Name");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("metName"));
	return metNameCol;
	}
	
	/* Returns Summary TableColumn */
	public static TableColumn<QuantResults, String> getSummaryColumn() {
	TableColumn<QuantResults, String> metNameCol = new TableColumn<>("Summary");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("summary"));
	
	
	return metNameCol;
	}
	/* Returns Frequency Name TableColumn */
	public static TableColumn<QuantResults, Double> getFreqColumn() {
	TableColumn<QuantResults, Double> metNameCol = new TableColumn<>("Frequency");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("freq"));
	return metNameCol;
	}
	
	/* Returns Amplitude Name TableColumn */
	public static TableColumn<QuantResults, Double> getAmpColumn() {
	TableColumn<QuantResults, Double> metNameCol = new TableColumn<>("Amplitude");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("amplitude"));
	return metNameCol;
	}
	
	
	/* Returns Std amp Name TableColumn */
	public static TableColumn<QuantResults, Double> getStdColumn() {
	TableColumn<QuantResults, Double> metNameCol = new TableColumn<>("Std Amp.");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("stdAmp"));
	return metNameCol;
	}
	/* Returns phase Name TableColumn */
	public static TableColumn<QuantResults, Double> getPhaseColumn() {
	TableColumn<QuantResults, Double> metNameCol = new TableColumn<>("Phase");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("phase"));
	return metNameCol;
	}
	/* Returns phase Name TableColumn */
	public static TableColumn<QuantResults, Double> getConcColumn() {
	TableColumn<QuantResults, Double> metNameCol = new TableColumn<>("Concentration");
	metNameCol.setCellValueFactory(new PropertyValueFactory<>("concentration"));
	return metNameCol;
	}	
	
	/* Returns Date TableColumn */
	public static TableColumn<QuantResults, LocalDate> getDateColumn() {
	TableColumn<QuantResults, LocalDate> DateCol =
	new TableColumn<>("Date");
	DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
	return DateCol;
	}
	
	
	
	
	
	
}
