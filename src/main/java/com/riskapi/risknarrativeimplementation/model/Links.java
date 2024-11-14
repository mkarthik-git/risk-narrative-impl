package com.riskapi.risknarrativeimplementation.model;

import lombok.Data;

@Data
public class Links {
    private OfficerLink officer;

    @Data
    public static class OfficerLink {
        private String appointments;
    }
}
