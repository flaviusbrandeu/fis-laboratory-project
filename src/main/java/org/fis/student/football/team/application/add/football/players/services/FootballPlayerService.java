package org.fis.student.football.team.application.add.football.players.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.fis.student.football.team.application.add.football.players.exceptions.CouldNotWriteFootballPlayerException;
import org.fis.student.football.team.application.add.football.players.exceptions.PlayerAlreadyExistsException;
import org.fis.student.football.team.application.add.football.players.models.FootballPlayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class FootballPlayerService {
    private static List<FootballPlayer> players;
    private static final Path PLAYERS_PATH = FileSystemService.getPathToFile("config", "players.json");

    public static void loadPlayersFromFile() throws IOException  {
        if (!Files.exists(PLAYERS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(FootballPlayerService.class.getClassLoader().getResource("players.json")), PLAYERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        players = objectMapper.readValue(PLAYERS_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void addPlayer(String name,
                                 String post,
                                 String agent,
                                 String marketPrice,
                                 String salary,
                                 String shot,
                                 String defence,
                                 String pass,
                                 String dribbling,
                                 String physic) throws PlayerAlreadyExistsException, NumberFormatException{
        checkPlayerDoesNotAlreadyExist(name,post);
        Hashtable<String, Float> skills = new Hashtable<>();
        skills.put("Shot", Float.parseFloat(shot));
        skills.put("Defence", Float.parseFloat(defence));
        skills.put("Pass", Float.parseFloat(pass));
        skills.put("Dribbling", Float.parseFloat(dribbling));
        skills.put("Physic", Float.parseFloat(physic));

        players.add(new FootballPlayer(name, post, agent, Long.parseLong(marketPrice), Long.parseLong(salary), skills));
        persistPlayers();

    }


    private static void checkPlayerDoesNotAlreadyExist(String name,String post) throws PlayerAlreadyExistsException {
        for (FootballPlayer player : players) {
            if (Objects.equals(name, player.getName())&&Objects.equals(post,player.getPost()))
                throw new PlayerAlreadyExistsException(name);
        }
    }

    private static void persistPlayers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(PLAYERS_PATH.toFile(), players);
        } catch (IOException e) {
            throw new CouldNotWriteFootballPlayerException();
        }
    }
}