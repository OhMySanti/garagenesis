package org.vehicles;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)
public class VehicleOwner {
    String name;
    String phoneNumber = "";
    String additionalContactInfo = "";
    Integer duePayment;

    public VehicleOwner(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.duePayment = 0;
    }

    public VehicleOwner(){

    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getDuePayment() {
        return duePayment;
    }

    public void reduceDuePayment(Integer amountPaid) {
        duePayment -= amountPaid;
    }

}
