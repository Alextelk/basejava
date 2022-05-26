package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume r, int key);

    protected abstract void doDelete(String uuid, int key);

    protected abstract void doUpdate(Resume r, int key);

    protected abstract Resume doGet(int key);

    public void update(Resume r) {
        int key = findNotExistSearchKey(r.getUuid());
        doUpdate(r, key);
    }

    public void save(Resume r) {
        int key = findExistSearchKey(r.getUuid());
        doSave(r, key);
    }

    public Resume get(String uuid) {
        int key = findNotExistSearchKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        int key = findNotExistSearchKey(uuid);
        doDelete(uuid, key);
    }

    private int findExistSearchKey(String uuid) {
        int key = getIndex(uuid);
        if (key >= 0) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private int findNotExistSearchKey(String uuid) {
        int key = getIndex(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
}
