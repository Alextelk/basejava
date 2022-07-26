package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResume extends AbstractStorage<Resume> {

    private Map<String, Resume> mapResume = new HashMap<>();

    @Override
    protected Resume findSearchKey(String uuid) {
        return mapResume.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        mapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, Resume resume) {
        mapResume.remove(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        mapResume.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
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
