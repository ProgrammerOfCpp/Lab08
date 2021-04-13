package com.artyemlavrov.lab6.server;

import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.types.Status;
import com.artyemlavrov.lab6.common.types.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class WorkersCollection {
    private PriorityQueue<Worker> priorityQueue = new PriorityQueue<>();
    private final LocalDate initializationDate;

    public WorkersCollection() {
        initializationDate = LocalDate.now();
    }

    public Worker add(Worker element) {
        Integer id = (int)(System.currentTimeMillis() % Integer.MAX_VALUE);
        LocalDate creationDate = LocalDate.now();
        Worker completed = new Worker(
                element,
                id,
                creationDate
        );
        priorityQueue.add(completed);
        return completed;
    }

    public void remove(Integer key) {
        Worker element = get(key);
        priorityQueue.remove(element);
    }

    public Worker get(Integer id) {
        return priorityQueue
                .stream()
                .filter(worker -> worker.getId().equals(id))
                .findAny()
                .orElse(null);
    }


    public void update(Integer id, Worker value) {
        if (!contains(id)) return;
        Worker old = get(id);
        Worker updated = new Worker(old, value);
        remove(id);
        priorityQueue.add(updated);
    }

    public boolean contains(Integer id) {
        return get(id) != null;
    }

    public List<Worker> getAll() {
        return new LinkedList<>(priorityQueue);
    }

    public void clear() {
        priorityQueue.clear();
    }

    public void removeLower(Worker element) {
        priorityQueue.removeIf(worker -> worker.compareTo(element) < 0);
    }

    public Worker getHead() {
        return priorityQueue.element();
    }

    public void removeHead() {
        Worker head = getHead();
        remove(head.getId());
    }

    public Worker getMaxByCreationDate() {
        return priorityQueue
                .stream()
                .min((first, second) -> {
                    LocalDate dateFirst = first.getCreationDate();
                    LocalDate dateSecond = second.getCreationDate();
                    return dateFirst.compareTo(dateSecond);
                })
                .orElse(null);
    }

    public List<Status> getStatusListDescending() {
        return priorityQueue.stream()
                .map(Worker::getStatus)
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public double getSumOfSalary() {
        return priorityQueue
                .stream()
                .mapToDouble(Worker::getSalary)
                .sum();
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public String getType() {
        return priorityQueue.getClass().getSimpleName();
    }

    public int getElementsCount() {
        return priorityQueue.size();
    }

    public void loadFromFile(String filename) throws IOException {
        IOManager ioManager = new IOManager();
        ioManager.setInputFile(filename);
        String json = ioManager.readUntilEnd();
        Gson gson = new Gson();
        priorityQueue = gson.fromJson(json, new TypeToken<PriorityQueue<Worker>>(){}.getType());
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public void saveToFile(String filename) {
        IOManager ioManager = new IOManager();
        try {
            ioManager.setOutputFile(filename);
            Gson gson = new Gson();
            String json = gson.toJson(priorityQueue);
            ioManager.writeLine(json);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
