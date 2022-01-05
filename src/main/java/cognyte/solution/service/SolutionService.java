package cognyte.solution.service;

import cognyte.solution.exceptionHandler.AppException;
import cognyte.solution.model.ApiError;
import cognyte.solution.model.KafkaOptions;
import cognyte.solution.model.RequestBodyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SolutionService {

    @Autowired
    private Producer producer;

    private final List<RequestBodyModel> messagesList = new ArrayList<>();
    private static final int SECOND = 1000;

    public String transferMessage(RequestBodyModel requestBodyModel) throws Exception {
        if (requestBodyModel == null) {
            throw new AppException(new ApiError(HttpStatus.BAD_REQUEST, "Request body is empty"));
        }

        Map<String, String> messageTemplate = requestBodyModel.getMessageTemplate();
        List<HashMap<String, String>> randomizedFields = requestBodyModel.getRandomizedFields();

        for (HashMap<String, String> randomizedField : randomizedFields) {
            String field = randomizedField.keySet().iterator().next();
            String type = randomizedField.get(field);

            // I used replace in order to change only existing values.
            if (type.toLowerCase().equals("string")) {
                messageTemplate.replace(field, createRandomString());
            }
            if (type.toLowerCase().equals("number")) {
                messageTemplate.replace(field, createRandomNumber());
            }
        }

        messagesList.add(requestBodyModel);
        sendMessagesToKafka(requestBodyModel.getKafkaOptions());

        return "Success";
    }

    public Map<String, String> getLast() throws AppException {
        try {
            return messagesList.get(messagesList.size() - 1).getMessageTemplate();
        } catch (IndexOutOfBoundsException e) {
            throw new AppException(new ApiError(HttpStatus.NOT_FOUND, "Message list is empty"));
        }
    }

    private String createRandomString() {

        // create a string of uppercase and lowercase characters
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabet = upperAlphabet + lowerAlphabet;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int newStringLength = 10;

        for (int i = 0; i < newStringLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private String createRandomNumber() {
        Random random = new Random(); // creating Random object
        int number = random.nextInt();
        return String.valueOf(Math.abs(number));
    }

    private void sendMessagesToKafka(KafkaOptions kafkaOptions) throws InterruptedException, AppException {

        String topicName = kafkaOptions.getTopicName();
        int messagesCountPerSend = kafkaOptions.getMessagesCountPerSend();
        int cyclesCount = kafkaOptions.getCyclesCount();
        int sleepBetweenMessages = kafkaOptions.getSleepBetweenMessages() * SECOND;
        String messageToSend = getLast().toString();

        for (int i = 0; i < cyclesCount; i++) {
            for (int j = 0; j < messagesCountPerSend; j++) {
                producer.sendMessage(topicName, messageToSend);
            }
            Thread.sleep(sleepBetweenMessages);
        }
    }
}
