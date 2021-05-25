package com.artyemlavrov.lab8.server.database;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Status;
import com.artyemlavrov.lab8.common.types.Worker;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class CollectionProvider {
    private final Authentication authentication;
    private final Database database;

    public CollectionProvider(Database database, Authentication authentication) {
        this.database = database;
        this.authentication = authentication;
    }

    public Worker add(Worker element) {
        return database.createWorker(authentication, element);
    }

    public void remove(Integer key) {
        Worker element = get(key);
        database.deleteWorker(authentication, element);
    }

    public Worker get(Integer id) {
        return database.readWorkers(authentication)
                .filter(worker -> worker.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Integer getElementColor(Integer id) {
        return database.readWorkerColor(id);
    }


    public void update(Integer id, Worker value) {
        Worker key = get(id);
        database.updateWorker(authentication, key, value);
    }

    public boolean contains(Integer id) {
        return get(id) != null;
    }

    public List<Worker> getAll() {
        return database
                .readWorkers(authentication)
                .collect(Collectors.toList());
    }

    public void clear() {
        List<Integer> ids = new LinkedList<>();
        database
                .readWorkers(authentication)
                .forEach(w -> ids.add(w.getId()));
        for (Integer id : ids) {
            remove(id);
        }
    }

    public void removeLower(Worker element) {
        List<Integer> ids = new LinkedList<>();
        database
                .readWorkers(authentication)
                .filter(worker -> worker.compareTo(element) < 0)
                .forEach(w -> ids.add(w.getId()));
        for (Integer id : ids) {
            remove(id);
        }
    }

    public Worker getHead() {
        return database
                .readWorkers(authentication)
                .findFirst()
                .orElse(null);
    }

    public void removeHead() {
        Worker head = getHead();
        remove(head.getId());
    }

    public Worker getMaxByCreationDate() {
        return database
                .readWorkers(authentication)
                .min((first, second) -> {
                    LocalDate dateFirst = first.getCreationDate();
                    LocalDate dateSecond = second.getCreationDate();
                    return dateFirst.compareTo(dateSecond);
                })
                .orElse(null);
    }

    public List<Status> getStatusListDescending() {
        return database
                .readWorkers(authentication)
                .map(Worker::getStatus)
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public double getSumOfSalary() {
        return database
                .readWorkers(authentication)
                .mapToDouble(Worker::getSalary)
                .sum();
    }

    public LocalDate getInitializationDate() {
        return database.getInitializationDate();
    }

    @SuppressWarnings("SameReturnValue")
    public String getType() {
        return "PriorityQueue";
    }

    public int getElementsCount() {
        return (int) database
                .readWorkers(authentication)
                .count();
    }

    public boolean isEmpty() {
        return getElementsCount() == 0;
    }
}
