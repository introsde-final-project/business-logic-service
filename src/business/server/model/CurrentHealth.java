package business.server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "measureType"
})
public class CurrentHealth {

    @XmlElement(nillable = true)
    public List<HealthProfile> measureType;

    public List<HealthProfile> getMeasureType() {
        if (measureType == null) {
            measureType = new ArrayList<HealthProfile>();
        }
        return this.measureType;
    }

}
