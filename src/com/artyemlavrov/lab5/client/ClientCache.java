package com.artyemlavrov.lab5.client;

import java.util.*;

public class ClientCache {
    private final List<String> history = new LinkedList<>();
    private final Stack<String> scriptStack = new Stack<>();

    public void addToHistory(String entry) {
        history.add(entry);
    }

    public List<String> getHistory(int maxCount) {
        int startIndex = Math.max(0, history.size() - maxCount);
        ListIterator<String> iterator = history.listIterator(startIndex);
        List<String> result = new LinkedList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    public void pushToScriptStack(String scriptName) {
        scriptStack.push(scriptName);
    }

    public void popFromScriptStack() {
        scriptStack.pop();
    }

    public boolean isInScriptStack(String scriptName) {
        return scriptStack.contains(scriptName);
    }
}
