package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        listStorage.add(r);
    }

    @Override
    protected void doDelete(String uuid, Object index) {
        listStorage.remove((int) index);
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        listStorage.set((Integer) index, r);
    }

    @Override
    protected Resume doGet(Object index) {
        return listStorage.get((Integer) index);
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
