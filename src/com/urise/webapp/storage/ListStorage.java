package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        listStorage.add(r);
    }

    @Override
    protected void doDelete(Integer index) {
        listStorage.remove((int) index);
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        listStorage.set(index, r);
    }

    @Override
    protected Resume doGet(Integer index) {
        return listStorage.get(index);
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
    public List<Resume> copyStorage() {
        return new ArrayList<>(listStorage);
    }
}
