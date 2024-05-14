package data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="lego")
public class Lego {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // Unique identifier for the Lego entity.
	private String name=""; // Name of the Lego entity.

	@OneToMany(mappedBy="lego", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonManagedReference // To handle bidirectional relationship serialization.
	private List<LegoSetting> legoSetting; // List of LegoSettings associated with this Lego entity.
	

	public List<LegoSetting> getLegoSetting() {
		return legoSetting; // Returns the list of LegoSettings associated with this Lego.
	}
	public void setLegoSetting(List<LegoSetting> legoSetting) {
		this.legoSetting = legoSetting; // Sets the list of LegoSettings associated with this Lego.
	}
	public Lego() {
		super(); // Default constructor.
	}
	public Lego(int id, String name) {
        super();
        this.id = id; // Initializes Lego ID.
        this.name = name; // Initializes Lego name.
    }
	
	public int getId() {
		return id; // Returns the ID of the Lego.
	}
	public void setId(int id) {
		this.id = id; // Sets the ID of the Lego.
	}
	public String getName() {
		return name; // Returns the name of the Lego.
	}
	public void setName(String name) {
		this.name = name; // Sets the name of the Lego.
	}

	@Override
	public String toString() {
		return id+"#"+name; // Returns a string representation of the Lego entity.
	}
	
}
