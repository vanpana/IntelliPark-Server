package Controller;

import Model.Employee;
import Repository.Repository;
import static java.lang.Math.toIntExact;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Controller {
    private Repository repo;

    public Controller(Repository repo) {
        this.repo = repo;
    }

    public Employee getEmployee(String email){
        return repo.getEmployee(email);
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
            float score = daysInBetween(e.getEmploy_date()) * e.getMultiplier();
            System.out.println(score);
            scores.put(e, score);
        }

        Map<Employee, Float> sortedScores = sortByValue(scores);

        ArrayList<Employee> sortedEmployees = new ArrayList<>();
        sortedEmployees.addAll(sortedScores.keySet());
        return sortedEmployees;
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
}