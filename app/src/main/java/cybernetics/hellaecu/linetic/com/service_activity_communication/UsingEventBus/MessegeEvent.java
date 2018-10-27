package cybernetics.hellaecu.linetic.com.service_activity_communication.UsingEventBus;

public class MessegeEvent {

    public final int messege;


    public MessegeEvent(int messege) {
        this.messege = messege;
    }

    public int getMessageEvent() {
        return messege;

    }
}
