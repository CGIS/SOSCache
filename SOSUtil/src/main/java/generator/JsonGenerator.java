package generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cgis.dev.SOSResource.Resource;
import org.cgis.dev.ds.Observation;
import org.cgis.dev.ds.SOS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Zil on 2016/9/19.
 */
public class JsonGenerator {

    private SOS[] sos;

    private Observation[] observations;
    public JsonGenerator(SOS sos){
        this.sos = new SOS[]{sos};
    }

    public JsonGenerator(SOS[] sos){
        this.sos = sos;
    }

    public JsonGenerator(Observation[] observations){
        this.observations = observations;
    }

    public void setObservations(Observation[] observations) {
        this.observations = observations;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if( observations == null )
            return mapper.writeValueAsString(sos);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mapper.setDateFormat(df);
        return mapper.writeValueAsString(observations);
    }
}
