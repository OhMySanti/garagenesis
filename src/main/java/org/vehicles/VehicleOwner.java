package org.vehicles;

public class VehicleOwner {
    String name = "";
    String phoneNumber = "";
    String additionalContactInfo = ""; // TODO: Why default empty values?
    Integer duePayment;

    public VehicleOwner(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.duePayment = 0; // TODO: due payment doesnt seem to be something that defines an owner
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
