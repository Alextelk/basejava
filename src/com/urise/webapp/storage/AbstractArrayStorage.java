package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

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

    protected void doUpdate(Resume r, int key) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[index] = r;

    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected Resume doGet(int key) {
        return storage[key];
    }

    protected void doSave(Resume r, int key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено", r.getUuid());
        }
        saveElement(r);
        size++;
    }

    protected void doDelete(String uuid, int key) {
        int index = getIndex(uuid);
        deleteElement(index);
        storage[size - 1] = null;
        size--;

    }

    public abstract void saveElement(Resume r);

    public abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}