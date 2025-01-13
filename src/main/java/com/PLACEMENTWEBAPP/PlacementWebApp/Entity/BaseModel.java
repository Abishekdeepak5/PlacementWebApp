package com.PLACEMENTWEBAPP.PlacementWebApp.Entity;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {
    public Long UserId;
    public String email;
    public String message;
    public List<String> error=new ArrayList<>();
    public String token;
    public boolean isSuccess;
}
