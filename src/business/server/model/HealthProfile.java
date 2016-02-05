package business.server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by bishruti on 2/3/16.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class HealthProfile implements Serializable {
    private int idHealthProfile;
    private Date dateRegistered;
    private String measureType;
    private Float measureValue;
    private String measureValueType;

    public int getIdHealthProfile() {
        return idHealthProfile;
    }

    public void setIdHealthProfile(int idHealthProfile) {
        this.idHealthProfile = idHealthProfile;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public Float getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(Float measureValue) {
        this.measureValue = measureValue;
    }

    public String getMeasureValueType() {
        return measureValueType;
    }

    public void setMeasureValueType(String measureValueType) {
        this.measureValueType = measureValueType;
    }

}
