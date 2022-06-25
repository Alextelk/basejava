package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResume extends AbstractStorage {

    private Map<String, Resume> mapResume = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        return mapResume.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        mapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, Object resume) {
        mapResume.remove(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
        mapResume.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    public void clear() {
        mapResume.clear();
    }

    @Override
    public int size() {
        return mapResume.size();
    }

    @Override
    public List<Resume> copyStorage() {
        return new ArrayList<>(mapResume.values());
    }
}
