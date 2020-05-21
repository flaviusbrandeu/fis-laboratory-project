package org.fis.student.football.team.application.login.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.fis.student.football.team.application.login.exceptions.UserDoesNotExistException;
import org.fis.student.football.team.application.login.exceptions.WrongCredentialsException;
import org.fis.student.football.team.application.login.models.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

//import org.fis.student.football.team.application.register.users.exceptions.CouldNotWriteUserException;
//import org.fis.student.football.team.application.register.users.exceptions.UsernameAlreadyExistsException;

public class UserService {

    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {
        if (!Files.exists(USERS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("users.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<>() {
        });
    }

    private static User getUser(String username) throws UserDoesNotExistException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                return user;
        }
            throw new UserDoesNotExistException(username);
    }

    public static void verifyCredentials(String username,String password,String role) throws UserDoesNotExistException, WrongCredentialsException {
        User user=getUser(username);
        if(!user.getPassword().equals(encodePassword(username,password))||!user.getRole().equals(role))
            throw new WrongCredentialsException();
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}