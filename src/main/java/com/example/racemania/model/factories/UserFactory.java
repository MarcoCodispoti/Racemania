package com.example.racemania.model.factories;

import com.example.racemania.model.Customer;
import com.example.racemania.model.TrackOwner;
import com.example.racemania.model.User;

public class UserFactory {

    private UserFactory(){};

    public static final String ROLE_TRACKOWNER = "TrackOwner";
    public static final String ROLE_CUSTOMER = "Customer";

        public static User createUser(int userId, String email, String userRole, int trackId) {
            return switch (userRole) {
                case ROLE_CUSTOMER -> new Customer(userId, email);
                case ROLE_TRACKOWNER -> new TrackOwner(userId, userRole, trackId);
                default -> throw new IllegalArgumentException("Ruolo utente non valido: ");
            };
        }
}