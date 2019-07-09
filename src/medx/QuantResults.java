package medx;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuantResults {

	
	private final ReadOnlyIntegerWrapper Id =
			new ReadOnlyIntegerWrapper(this, "Id", personSequence.incrementAndGet());
			private final StringProperty metName =new SimpleStringProperty(this, "metName", null);
			private final StringProperty summary =new SimpleStringProperty(this, "summary", null);
			private final DoubleProperty freq =new SimpleDoubleProperty();
			private final DoubleProperty stdAmp =new SimpleDoubleProperty();
			private final DoubleProperty amplitude =new SimpleDoubleProperty();
			private final DoubleProperty phase =new SimpleDoubleProperty();
			private final DoubleProperty concentration =new SimpleDoubleProperty();
			private final ObjectProperty<LocalDate> date =
					new SimpleObjectProperty<>(this, "date", null);

			
			private static AtomicInteger personSequence = new AtomicInteger(0);

			
			
	public QuantResults(String metName,double freq,double amplitude,double stdAmp,double phase,double conc,String summary,LocalDate date) {
		
		this.metName.set(metName);
		this.freq.set(freq);
		this.amplitude.set(amplitude);
		this.stdAmp.set(stdAmp);
		this.phase.set(phase);
		this.summary.set(summary);
		this.concentration.set(conc);
		this.date.set(date);
	}
	
	public final int Id() {
		return Id.get();
		}
	
	public final ReadOnlyIntegerProperty IdProperty() {
		return Id.getReadOnlyProperty();
		}
	
	
	public final LocalDate getDate() {
		return date.get();
		}
		 
		public final void setDate(LocalDate date) {
		dateProperty().set(date);
		}
		 
		public final ObjectProperty<LocalDate> dateProperty() {
		return date;
		}
	
	public String getMetName() {
		return metName.get();
	}
	public final void setMetName(String metName) {
		metNameProperty().set(metName);
		}
	public StringProperty metNameProperty() {
		return metName;
	}
	public String getSummary() {
		return summary.get();
	}
	public final void setSummary(String sum) {
		metNameProperty().set(sum);
		}
	public StringProperty summaryProperty() {
		return summary;
	}
	
	public double getFreq() {
		return freq.get();
	}
	public final void setFreq(double f) {
		freqProperty().set(f);
		}
	public DoubleProperty freqProperty() {
		return freq;
	}	
	
	
	public double getAmplitude() {
		return amplitude.get();
	}
	public final void setAmplitude(double a) {
		amplitudeProperty().set(a);
		}
	public DoubleProperty amplitudeProperty() {
		return amplitude;
	}	
		
	
	public double getStdAmp() {
		return stdAmp.get();
	}
	public final void setStdAmp(double std) {
		stdAmpProperty().set(std);
		}
	public DoubleProperty stdAmpProperty() {
		return stdAmp;
	}	
		
	
	
	public double getPhase() {
		return phase.get();
	}
	public final void setPhase(double f) {
		phaseProperty().set(f);
		}
	public DoubleProperty phaseProperty() {
		return phase;
	}	
	
	public double getConcentration() {
		return concentration.get();
	}
	public final void setConcentration(double f) {
		concentrationProperty().set(f);
		}
	public DoubleProperty concentrationProperty() {
		return concentration;
	}	
	
	
	@Override
	public String toString() {
	return "[Id=" + Id.get() +
	", meName=" + metName.get() +
	", freq=" + freq.get() +
	", amplitude=" + amplitude.get() +
	", stdAmp=" + stdAmp.get() +
	", phase=" + phase.get() +
	", concentration=" + concentration.get() +
	", date=" + date.get() +
	", summary=" + summary.get() + "]";	
	}
	
	
	
	
	
	
	
	
	
}
