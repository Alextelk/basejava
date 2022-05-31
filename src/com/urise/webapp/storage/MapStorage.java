package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String findSearchKey(String uuid) {
        boolean exist = mapStorage.containsKey(uuid);
        return (exist) ? uuid : null;
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
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[mapStorage.size()]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
