package Controller;

import Model.Carpool;
import Model.Employee;
import Model.Notification;
import Model.Vacation;
import Repository.*;
import static java.lang.Math.toIntExact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Controller {
    private Repository repo;
    private NotificationRepository notifrepo;
    private VacationRepository vacrepo;
    private CarPoolRepository cprepo;
    private int parkingspots;
    private ArrayList<Integer> rejectedSpots;
    private HashMap<Integer, ArrayList<Integer>> carRequests;

    public Controller(Repository repo, NotificationRepository notifrepo, VacationRepository vacrepo, CarPoolRepository cprepo){
        this.repo = repo;
        this.notifrepo = notifrepo;
        this.vacrepo = vacrepo;
        this.cprepo = cprepo;
        this.parkingspots = countSpots("parkingmatrix.txt");
        this.rejectedSpots = new ArrayList<>();
        this.carRequests = new HashMap<>();
    }

    private int countSpots(String filename){
        int spots = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] linesplit = line.split(" ");
                if (linesplit.length == 3 && linesplit[2].equals("FREEPARKING")) spots++;
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return spots;
    }

    public void addEmployee(int id, String name, String surname, String email, String password, String car_plate, Date employ_date,
                            float multiplier, int parking_spot, boolean is_sharing){
        repo.add(new Employee(id, name, surname, email, password, car_plate, employ_date, multiplier, parking_spot, is_sharing));
    }

    public void updateMultiplier(int id, float multiplier) { repo.updateMultiplier(id, multiplier); }
    public void addCarpool(int driver_id, int passenger_id){
        cprepo.add(new Carpool(driver_id, passenger_id));
    }

    public int getCarPoolDriverId(int id){return cprepo.getCarPoolDriverID(id); }

    public Employee getEmployee(String email){
        return repo.getEmployee(email);
    }
    public Employee getEmployee(int id){
        for (Employee emp : repo.getAll()){
            if (emp.getId() == id) return emp;
        }
        return null;
    }

    public void addVacation(Date start, Date end, int id){
        vacrepo.add(new Vacation(start, end, id));
    }
    private int daysInBetween(Date date){
        DateFormat todayFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date today = new Date();
        try {
            today = todayFormat.parse(todayFormat.format(today));
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }

        long diff = today.getTime() - date.getTime();
        return toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    public ArrayList<Employee> getParkingSpots(){
        HashMap<Employee, Float> scores = new HashMap<>();
        for (Employee e : repo.getAll()){
//            System.out.println(e.getEmploy_date());
            if (!rejectedSpots.contains(e.getId())) {
                float score = daysInBetween(e.getEmploy_date()) * e.getMultiplier();
                System.out.println(score);
                scores.put(e, score);
            }
        }

        Map<Employee, Float> sortedScores = sortByValue(scores);

        ArrayList<Employee> sortedEmployees = new ArrayList<>();
        sortedEmployees.addAll(sortedScores.keySet());
        System.out.println(sortedEmployees);
        return sortedEmployees;
    }

    public boolean doIHaveSpot(int id){
        int counter = getTotalParkingSpots();
        for (Employee emp : getParkingSpots()){
            if (counter < 0) return false;
            if (emp.getId() == id) return true;
            counter--;
        }
        return false;
    }


    private static Map<Employee, Float> sortByValue(Map<Employee, Float> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Employee, Float>> list =
                new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Employee, Float>>() {
            public int compare(Map.Entry<Employee, Float> o1,
                               Map.Entry<Employee, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Employee, Float> sortedMap = new LinkedHashMap<Employee, Float>();
        for (Map.Entry<Employee, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    public ArrayList<Employee> getParkingSpotsFromZone(String neighbourhood){
        int counter = getTotalParkingSpots();
        ArrayList<Employee> fromZone = new ArrayList<>();

        for (Employee emp : getParkingSpots()){
            System.out.println(counter);
            System.out.println(emp.getNeighbourhood() + " vs " + neighbourhood);
            if (counter > 1){
                    if (emp.getNeighbourhood().equals(neighbourhood)){
                    fromZone.add(emp);
                    counter--;
                }
            }
            else break;
        }
        return fromZone;
    }

    public ArrayList<Employee> getParkingSpotsFromZone(int myid, String neighbourhood){
        int counter = getTotalParkingSpots();
        ArrayList<Employee> fromZone = new ArrayList<>();

        for (Employee emp : getParkingSpots()){
            System.out.println(counter);
            System.out.println(emp.getNeighbourhood() + " vs " + neighbourhood);
            if (counter > 1){
                if (emp.getNeighbourhood().equals(neighbourhood))
                    if (carRequests.get(myid) == null || (carRequests.get(myid) != null && !carRequests.get(myid).contains(emp.getId()))) {
                        fromZone.add(emp);
                        counter--;
                    }

                }
            else break;
        }
        return fromZone;
    }

    public ArrayList<Employee> getAll(){
        return repo.getAll();
    }

    public int getTotalParkingSpots() {
        return parkingspots;
    }

    //=====
    public void addNotification(ArrayList<String> params){
        this.notifrepo.add(params);
    }

    public void addNotification(String notification, String toWho, String fromWhom){
        this.notifrepo.add(notification, toWho, fromWhom);
    }

    public void addNotification(Notification notification){
        this.notifrepo.add(notification);
    }

    public void delNotification(int id){
        this.notifrepo.del(id);
    }

    public Notification getNotification(int id){
        return this.notifrepo.getNotification(id);
    }


    public ArrayList<ArrayList<String>> getNotifications(String email){
        ArrayList<ArrayList<String>> items = notifrepo.getAll();

        ArrayList<ArrayList<String>> notifications = new ArrayList<>();

        for(ArrayList<String> al : notifrepo.getAll()){
            if (notifrepo.getNotification(Integer.parseInt(al.get(1))).getToWho().equals(email)) notifications.add(al);
        }

        return notifications;
    }

    public void addRejectedSpot(int id){
        rejectedSpots.add(id);
    }

    public void addCarRequest(int passenger_id, int driver_id){
        if (carRequests.get(passenger_id) == null) carRequests.put(passenger_id, new ArrayList<>());
        ArrayList<Integer> temp = carRequests.get(passenger_id);
        temp.add(driver_id);
        carRequests.put(passenger_id, temp);

        System.out.println(carRequests);
    }
}