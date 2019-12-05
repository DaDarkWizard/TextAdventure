import java.util.EventListener;

public interface FrameListener extends EventListener {
    void handle(FrameEvent e);
}
