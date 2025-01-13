package com.PLACEMENTWEBAPP.PlacementWebApp.Dto;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Drive;

public class DriveResponseDto  {
    private Drive drive;
    private boolean isRegistered;
    public String companyName;
    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
