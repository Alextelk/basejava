package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, int key) {
        listStorage.add(r);
    }

    @Override
    protected void doDelete(String uuid, int key) {
        listStorage.remove(key);
    }

    @Override
    protected void doUpdate(Resume r, int key) {
        listStorage.set(key, r);
    }

    @Override
    protected Resume doGet(int key) {
        return listStorage.get(key);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[listStorage.size()]);
    }
}
