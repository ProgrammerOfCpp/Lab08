package com.artyemlavrov.lab8.server.database;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.exception.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Database {
    private final Map<Integer, Integer> elementOwners = new HashMap<>();
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<String, Integer> usernameOwners = new HashMap<>();
    private final PriorityQueue<Worker> elements = new PriorityQueue<>();
    private final LocalDate initializationDate = LocalDate.now();
    private final String databaseUrl;


    public Database(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        loadUsers();
        loadWorkers();
        try {
            createUser(new Authentication(DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD));
        } catch (Exception ignored) { }
    }

    public User readUser(Authentication authentication) {
        if (authentication.getUsername().isEmpty()) throw new AuthenticationRequiredException();
        Integer userId = usernameOwners.get(authentication.getUsername());
        User user = users.get(userId);
        if (user == null) throw new UnknownUserException();
        if (user.getPassword().equals(authentication.getPassword())) {
            return user;
        }
        throw new WrongCredentialsException();
    }

    public Integer readWorkerColor(Integer id) {
        Integer ownerId = elementOwners.get(id);
        User user = users.get(ownerId);
        return user.getColor();
    }

    public void createUser(Authentication authentication) {
        if (usernameOwners.containsKey(authentication.getUsername())) {
            throw new UserAlreadyRegisteredException();
        }
        try {
            Connection connection = connect();
            int id = getNextId(connection, "user_id_sequence");
            int color = new Random().nextInt(256*256*256);
            PreparedStatement s = connection.prepareStatement("INSERT INTO users (id, username, password) VALUES(?, ?, ?, ?)");
            s.setInt(1, id);
            s.setString(2, authentication.getUsername());
            s.setString(3, authentication.getPassword());
            s.setInt(4, color);
            s.executeUpdate();
            User user = new User(id, authentication.getUsername(), authentication.getPassword(), color);
            users.put(id, user);
            usernameOwners.put(user.getUsername(), id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public Worker createWorker(Authentication authentication, Worker value) {
        User user = readUser(authentication);
        try {
            Connection connection = connect();
            Integer id = getNextId(connection, "worker_id_sequence");
            Worker completed = new Worker(value, id);
            PreparedStatement s = connection.prepareStatement("INSERT INTO workers (id, bytes, owner_id) VALUES(?, ?, ?)");
            s.setInt(1, id);
            s.setBytes(2, Serializer.serialize(value));
            s.setInt(3, user.getId());
            s.executeUpdate();
            elements.add(completed);
            elementOwners.put(completed.getId(), user.getId());
            return completed;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Stream<Worker> readWorkers(Authentication authentication) {
        readUser(authentication);
        return elements.stream();
    }

    public void updateWorker(Authentication authentication, Worker key, Worker value) {
        throwIfNotOwner(authentication, key);
        try {
            Connection connection = connect();
            Worker completed = new Worker(key, value);
            PreparedStatement s = connection.prepareStatement("UPDATE workers SET bytes = ? WHERE id = ?");
            s.setBytes(1, Serializer.serialize(completed));
            s.setInt(2, key.getId());
            elements.removeIf(worker -> worker.getId().equals(key.getId()));
            elements.add(completed);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteWorker(Authentication authentication, Worker key) {
        if (key == null) return;
        throwIfNotOwner(authentication, key);
        try {
            Connection connection = connect();
            PreparedStatement s = connection.prepareStatement("DELETE FROM workers WHERE id = ?");
            s.setInt(1, key.getId());
            s.executeUpdate();
            elementOwners.remove(key.getId());
            elements.remove(key);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void throwIfNotOwner(Authentication authentication, Worker value) {
        User user = readUser(authentication);
        if (!isOwner(user, value)) {
            throw new AccessRestrictedException();
        }
    }

    private boolean isOwner(User user, Worker value) {
        if (isAdmin(user)) return true;
        Integer ownerId = elementOwners.get(value.getId());
        return ownerId != null && ownerId == user.getId();
    }

    private boolean isAdmin(User user) {
        return user.getUsername().equals(DatabaseConfig.USERNAME);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(databaseUrl, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
    }

    private Integer getNextId(Connection connection, String sequenceName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(String.format("SELECT nextval('%s')", sequenceName));
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private void loadUsers() {
        try {
            users.clear();
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int color = resultSet.getInt("color");
                User user = new User(id, username, password, color);
                users.put(id, user);
                usernameOwners.put(username, id);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadWorkers() {
        try {
            elementOwners.clear();
            elements.clear();
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                byte[] bytes = resultSet.getBytes("bytes");
                Integer ownerId = resultSet.getInt("owner_id");
                Worker worker = (Worker) Serializer.deserialize(bytes);
                Worker complete = new Worker(worker, id);
                elements.add(complete);
                elementOwners.put(complete.getId(), ownerId);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
