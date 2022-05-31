package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object key);

    protected abstract void doSave(Resume r, Object key);

    protected abstract void doDelete(String uuid, Object key);

    protected abstract void doUpdate(Resume r, Object key);

    protected abstract Resume doGet(Object key);

    public void update(Resume r) {
        Object key = findExistSearchKey(r.getUuid());
        doUpdate(r, key);
    }

    public void save(Resume r) {
        Object key = findNotExistSearchKey(r.getUuid());
        doSave(r, key);
    }

    public Resume get(String uuid) {
        Object key = findExistSearchKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        Object key = findExistSearchKey(uuid);
        doDelete(uuid, key);
    }

    private Object findNotExistSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private Object findExistSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
}
