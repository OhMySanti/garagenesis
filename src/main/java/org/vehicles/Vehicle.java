package org.vehicles;

import org.services.Service;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vehicle {
    private static final AtomicLong idCounterVehicle = new AtomicLong();

    private final Long id;

    public EVehicleType.VehicleType type;
    public String registration;
    public VehicleOwner owner;
    public Integer noOfWheels;
    public Integer duePayment = 0;

    public ESize.Size size;
    public EPaintType.PaintType paintType;
    public EStatus.Status status;

    public List<Service> activeServices = new ArrayList<>();
    public List<Service> historyOfServices = new ArrayList<>();

    Vehicle() {
        this.id = idCounterVehicle.incrementAndGet();
        this.status = EStatus.Status.PENDING;
    }

    public void setStatus(EStatus.Status newStatus) {
        this.status = newStatus;
    }

    public Long getId() {
        return this.id;
    }

    public static Long getIdCounterVehicle() {
        return idCounterVehicle.get();
    }

    public static void setIdCounterVehicle(Long value) {
        idCounterVehicle.set(value);
    }

}
