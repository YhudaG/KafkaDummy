package cognyte.solution.model;

import org.springframework.stereotype.Component;

@Component
public class KafkaOptions {

    private String topicName;
    private int messagesCountPerSend;
    private int cyclesCount;
    private int sleepBetweenMessages;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getMessagesCountPerSend() {
        return messagesCountPerSend;
    }

    public void setMessagesCountPerSend(int messagesCountPerSend) {
        this.messagesCountPerSend = messagesCountPerSend;
    }

    public int getCyclesCount() {
        return cyclesCount;
    }

    public void setCyclesCount(int cyclesCount) {
        this.cyclesCount = cyclesCount;
    }

    public int getSleepBetweenMessages() {
        return sleepBetweenMessages;
    }

    public void setSleepBetweenMessages(int sleepBetweenMessages) {
        this.sleepBetweenMessages = sleepBetweenMessages;
    }
}
