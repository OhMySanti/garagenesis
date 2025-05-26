package org.vehicles;

import org.services.Service;
import org.VehicleManager;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Vehicle {
    public String registration;
    public String type;
    public VehicleOwner owner;
    public Integer noOfWheels;
    public Integer duePayment = 0;

    public Size size;

    public PaintType paintType = PaintType.MATTE;

    public Status status = Status.PENDING;

    public List<Service> activeServices = new LinkedList<>();
    public List<Service> historyOfServices = new LinkedList<>();

    public enum Size {
        //        UNSPECIFIED(10, "UNSPECIFIED"),
        SMALL(1, "SMALL", "S"),
        MEDIUM(2, "MEDIUM", "M"),
        LARGE(3, "LARGE", "L");

        private final Integer sortingPriority;
        private final String displayName;
        private final String selector;


        Size(Integer priority, String name, String selector) {
            this.sortingPriority = priority;
            this.displayName = name;
            this.selector = selector;
        }

        public Integer getSortingPriority() {
            return this.sortingPriority;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public String getSelector(){
            return this.selector;
        }


    }

//    public static Comparator<Vehicle> bySize = Comparator.comparingInt(i -> i.size.getSortingPriority());


    public enum PaintType {
        BASIC("BASIC", "B"),
        METALLIC("METALLIC", "M"),
        MATTE("MATTE", "T");

        private final String displayName;
        private final String selector;

        PaintType(String displayName, String selector){
            this.displayName = displayName;
            this.selector = selector;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public String getSelector(){
            return this.selector;
        }

    }

    public enum Status {
        ARCHIVED(10, "ARCHIVED\t"),
        PENDING(1, "PENDING\t"),
        ACCEPTED(2, "ACCEPTED\t"),
        IN_SERVICE(3, "IN SERVICE\t"),
        READY_TO_PICKUP(4, "READY TO PICKUP");


        private final Integer sortingPriority;
        private final String displayName;


        Status(Integer priority, String name) {
            this.sortingPriority = priority;
            this.displayName = name;
        }

        public Integer getSortingPriority() {
            return this.sortingPriority;
        }

        public String getDisplayName() {
            return this.displayName;
        }
    }

    public void changeStatus(Status newStatus) {
        this.status = newStatus;
        System.out.println("Sorting by " + newStatus.getDisplayName());
    }

    void completeService(Service service) {

        this.duePayment += service.calculateFee();
        this.historyOfServices.add(service);
        this.activeServices.remove(service);
//        if (this.activeServices.isEmpty()) {
//            System.out.println("List of services empty. Mark vehicle as Ready For Pickup?"); //TODO napisać do tego funkcję?
//        }
    }
}
