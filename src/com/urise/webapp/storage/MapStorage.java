package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String findSearchKey(String uuid) {
        return mapStorage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume r, Object key) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, Object key) {
        mapStorage.remove(key);
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        mapStorage.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object key) {
        return mapStorage.get(key);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public List<Resume> copyStorage() {
        return new ArrayList<>(mapStorage.values());
    }
}
