package com.example.racemania.model.dao;

import com.example.racemania.exceptions.FailedLoginException;
import com.example.racemania.model.Account;
import java.io.*;

public class AccountFSDao {
    public Account checkAccount(String email,String password) throws FailedLoginException{
        File file = new File("src/main/resources/com/example/racemania/Users.txt");
        Account account = new Account();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/racemania/Users.txt"))) {
            String line;
            System.out.println("File assoluto: " + file.getAbsolutePath());

            while ((line = reader.readLine()) != null) {
                System.out.println("sono nel while");
                String[] parts = line.split(";");
                if (parts.length < 6) continue;

                String fileuserIdStr = parts[0];
                // String fileusername = parts[1];
                String fileEmail = parts[2].trim();
                String filePassword = parts[3].trim();
                String fileRole = parts[4].trim();
                String fileTrackIdStr = parts[5].trim();
                System.out.println("Stampo i valori letti dal file: " + '\n' + fileuserIdStr + '\n' + fileRole + '\n' + fileTrackIdStr + '\n' + '\n');


                if (email.equals(fileEmail) && password.equals(filePassword)) {
                    System.out.println("Ho trovate un utente compatibile con email e password inserite");

                    int userId = Integer.parseInt(parts[0].trim());
                    String role = parts[4].trim();
                    String trackIdStr = parts[5].trim();
                    int trackId = trackIdStr.equalsIgnoreCase("NULL") ? -1 : Integer.parseInt(trackIdStr);

                    System.out.println("Dettagli account inserito: " + '\n' + userId + '\n' + role + '\n' + trackId + '\n' + '\n');

                    account.setUserId(userId);
                    account.setRole(role);
                    account.setTrackId(trackId);
                    return account;

                } else{
                    System.out.println("Nessuna corrispondenza trovata");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file Users.txt", e);
        }
        throw new FailedLoginException("Email o password errata.");
    }

}