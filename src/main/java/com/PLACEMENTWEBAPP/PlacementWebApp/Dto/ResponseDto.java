package com.PLACEMENTWEBAPP.PlacementWebApp.Dto;

import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.User;

public class ResponseDto {
    private User user;
    private String accessToken;
    private String refreshToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user= user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
