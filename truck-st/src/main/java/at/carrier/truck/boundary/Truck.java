package at.carrier.truck.boundary;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Truck {

	private long id;

	private String kfz;

	private Date initialRegistration;

	private Double horsePower;

	private String model;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKfz() {
		return kfz;
	}

	public void setKfz(String kfz) {
		this.kfz = kfz;
	}

	public Date getInitialRegistration() {
		return initialRegistration;
	}

	public void setInitialRegistration(Date initialRegistration) {
		this.initialRegistration = initialRegistration;
	}

	public Double getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(Double horsePower) {
		this.horsePower = horsePower;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
    public JsonObject toJson() {
        return Json.createObjectBuilder().
                add("id", this.id).
                add("kfz", this.kfz).
                add("horsePower", this.horsePower).
                add("model", this.model).
                add("initialRegistration", new SimpleDateFormat("dd.MM.yyyy").format(this.initialRegistration)).
                build();
    }
    

}
