package Tests.MapMaker;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class FileOpenEvent extends Event {
    public FileOpenEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public FileOpenEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }
}
