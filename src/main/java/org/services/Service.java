package org.services;

import org.vehicles.Vehicle;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cleaning.class, name = "cleaning"),
        @JsonSubTypes.Type(value = DentRepair.class, name = "dentrepair"),
        @JsonSubTypes.Type(value = EngineDiagnostics.class, name = "enginediagnostics"),
        @JsonSubTypes.Type(value = PaintRetouch.class, name = "paintretouch"),
        @JsonSubTypes.Type(value = TyreChange.class, name = "tyrechange")
})
public interface Service {
    Integer calculateFee();

    boolean isLegalService();

    String getDisplayName();

    Vehicle getVehicle();

    boolean remove();

    boolean complete();

    Long getId();

}
