package Repository;

import java.util.ArrayList;

/*
"rideRequested",email,driveremail"
"rideAccepted",email,driveremail"
"rideDenied,email,driveremail"
 */

public class NotificationRepository {
    private ArrayList<ArrayList<String>> notifs;

    public NotificationRepository() {
        notifs = new ArrayList<>();
    }

    public void add(ArrayList<String> notif){
        notifs.add(notif);
    }

    public ArrayList<ArrayList<String>> getAll() {
        return notifs;
    }
}
