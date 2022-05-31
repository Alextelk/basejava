package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    protected void doSave(Resume r, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено", r.getUuid());
        }
        saveResume(r);
        size++;
    }

    protected void doDelete(String uuid, Object index) {
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    public abstract void saveResume(Resume r);

    public abstract void deleteResume(int index);
}