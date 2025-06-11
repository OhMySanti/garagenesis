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
    public Integer duePayment = 0; // TODO: why do we set it to 0 ?

    public Size size;

    public PaintType paintType = PaintType.MATTE;  // TODO: every vehicle is matte by defult ?

    public Status status = Status.PENDING; // TODO: We dont know whether it's a car or a ship but we  already know its pending ?

    public List<Service> activeServices = new LinkedList<>();
    public List<Service> historyOfServices = new LinkedList<>();
    // TODO: is a "history of services" an inherent property of a car? What if our system grows and
    // TODO: starts serving 2 or 3 or 10 garages in the near future?

    public enum Size {
        //        UNSPECIFIED(10, "UNSPECIFIED"),
        SMALL(1, "SMALL", "S"), // TODO: I believe SMALL is already "SMALL", looks like redundancy
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
        ARCHIVED(10, "ARCHIVED\t"), // TODO: Why does name contain '\t' character?
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

        // TODO: what's the point? sorting priority? why not just move archived to the bottom ?
        public Integer getSortingPriority() {
            return this.sortingPriority;
        }

        public String getDisplayName() {
            return this.displayName;
        }
    }

    public void changeStatus(Status newStatus) { // NIT: conventionally setStatus
        this.status = newStatus;
        System.out.println("Sorting by " + newStatus.getDisplayName()); // TODO: this could be dumped as logs I think
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
