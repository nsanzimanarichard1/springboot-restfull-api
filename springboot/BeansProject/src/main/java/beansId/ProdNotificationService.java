package beansId;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ProdNotificationService  implements NotificationService {

    @Override
    public void sendNotification(String message) {

        System.out.println("Production profile activated," + message);
    }
}