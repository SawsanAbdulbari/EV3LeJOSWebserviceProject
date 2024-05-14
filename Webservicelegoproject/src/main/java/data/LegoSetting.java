package data;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="legosetting")
public class LegoSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // Unique identifier for the LegoSetting entity.
	private int speed; // Speed setting for the Lego robot.
	private int direction; // Direction setting for the Lego robot. 0 represents forward, 1 represents backward.
	private int turn; // Turn setting for the Lego robot.
	private int running; // Flag to track if the Lego robot is running.
	private java.sql.Timestamp time = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()); // Timestamp of the Lego setting.

	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonBackReference // To handle bidirectional relationship serialization.
	@JoinColumn(name="legoid")	
	private Lego lego; // Lego entity associated with this LegoSetting.

	public LegoSetting() {
		super(); // Default constructor.
	}
	public LegoSetting(int id, int speed, int direction, Timestamp time, int turn, int running) {
		super();
		this.id = id; // Initializes LegoSetting ID.
		this.speed = speed; // Initializes speed setting.
		this.direction = direction; // Initializes direction setting.
		this.turn = turn; // Initializes turn setting.
		this.time = time; // Initializes timestamp.
        this.running = running; // Initializes running flag.
	}
	public int getId() {
		return id; // Returns the ID of the LegoSetting.
	}
	public void setId(int id) {
		this.id = id; // Sets the ID of the LegoSetting.
	}
	public int getSpeed() {
		return speed; // Returns the speed setting of the Lego robot.
	}
	public void setSpeed(int speed) {
		this.speed = speed; // Sets the speed setting of the Lego robot.
	}
	public int getDirection() {
		return direction; // Returns the direction setting of the Lego robot.
	}
	public void setDirection(int direction) {
		this.direction = direction; // Sets the direction setting of the Lego robot.
	}
	public java.sql.Timestamp getTime() {
		return time; // Returns the timestamp of the LegoSetting.
	}
	public void setTime(java.sql.Timestamp time) {
		this.time = time; // Sets the timestamp of the LegoSetting.
	}
	public Lego getLego() {
		return lego; // Returns the Lego entity associated with this LegoSetting.
	}
	public void setLego(Lego lego) {
		this.lego = lego; // Sets the Lego entity associated with this LegoSetting.
	}
	public int getTurn() {
	    return turn; // Returns the turn setting of the Lego robot.
	}

	public void setTurn(int turn) {
	    this.turn = turn; // Sets the turn setting of the Lego robot.
	}
	
	public int getRunning() {
        return running; // Returns the running flag indicating whether the Lego robot is running.
    }

    public void setRunning(int running) {
        this.running = running; // Sets the running flag indicating whether the Lego robot is running.
    }
	@Override
	public String toString() {
		return id + "#" + speed + "#" + direction + "#" + turn + "#" + time + "#" + running; // Returns a string representation of the LegoSetting entity.
	}

	
}
