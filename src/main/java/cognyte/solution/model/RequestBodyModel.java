package cognyte.solution.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RequestBodyModel {

    private KafkaOptions kafkaOptions;
    private Map<String, String> messageTemplate = new HashMap<>();
    private List<HashMap<String, String>> randomizedFields = new ArrayList<>();


    public KafkaOptions getKafkaOptions() {
        return kafkaOptions;
    }

    public void setKafkaOptions(KafkaOptions kafkaOptions) {
        this.kafkaOptions = kafkaOptions;
    }

    public Map<String, String> getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(Map<String, String> messageTemplate) {
        this.messageTemplate = messageTemplate;
    }


    public List<HashMap<String, String>> getRandomizedFields() {
        return randomizedFields;
    }

    public void setRandomizedFields(List<HashMap<String, String>> randomizedFields) {
        this.randomizedFields = randomizedFields;
    }

}
