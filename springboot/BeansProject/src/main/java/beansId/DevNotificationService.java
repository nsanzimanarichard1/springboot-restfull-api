package beansId;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")

public class DevNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Development notification profile activated" + message);
    }
}
