package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.types.Status;
import com.artyemlavrov.lab5.common.types.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, предоставляющий методы для манипуляций над коллекцией объектов класса 'Worker'.
 */
public class WorkersCollection {
    private PriorityQueue<Worker> priorityQueue = new PriorityQueue<>();
    private final LocalDate initializationDate;

    public WorkersCollection() {
        initializationDate = LocalDate.now();
    }

    public Worker add(Worker element) {
        priorityQueue.add(element);
        return element;
    }

    public void remove(Integer key) {
        Worker element = get(key);
        priorityQueue.remove(element);
    }

    public Worker get(Integer id) {
        for (Worker element : priorityQueue) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    public List<Worker> getAll() {
        return new LinkedList<>(priorityQueue);
    }

    public void clear() {
        priorityQueue.clear();
    }

    public boolean removeLower(Worker element) {
        return priorityQueue.removeIf(worker -> worker.compareTo(element) < 0);
    }

    public Worker getHead() {
        return priorityQueue.element();
    }

    public Worker getMaxByCreationDate() {
        if (priorityQueue.isEmpty()) return null;
        Worker best = getHead();
        for (Worker element : priorityQueue) {
            if (best.getCreationDate().compareTo(element.getCreationDate()) < 0) {
                best = element;
            }
        }
        return best;
    }

    public List<Status> getStatusListDescending() {
        return priorityQueue.stream()
                .map(Worker::getStatus)
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public double getSumOfSalary() {
        double sum = 0;
        for (Worker element : priorityQueue) {
            sum += element.getSalary();
        }
        return sum;
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

    public void loadFromFile(String filename) {
        IOManager ioManager = new IOManager();
        try {
            ioManager.setInputFile(filename);
            String json = ioManager.readUntilEnd();
            Gson gson = new Gson();
            priorityQueue = gson.fromJson(json, new TypeToken<PriorityQueue<Worker>>(){}.getType());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
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
