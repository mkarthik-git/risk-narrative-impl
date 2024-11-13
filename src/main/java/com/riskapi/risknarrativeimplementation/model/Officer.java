package com.riskapi.risknarrativeimplementation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Officer {

    @JsonProperty("name")
    private String name;

    @JsonProperty("officer_role")
    private String officerRole;

    @JsonProperty("appointed_on")
    private String appointedOn;

    @JsonProperty("resigned_on")
    private String resignedOn;

    @JsonProperty("address")
    private Address address;

    public Officer() {
    }
    public Officer(String name, String officerRole, String appointedOn, String resignedOn, Address address) {
        this.name = name;
        this.officerRole = officerRole;
        this.appointedOn = appointedOn;
        this.resignedOn = resignedOn;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "name='" + name + '\'' +
                ", officerRole='" + officerRole + '\'' +
                ", appointedOn=" + appointedOn +
                ", resignedOn=" + resignedOn +
                ", address=" + address +
                '}';
    }
}
