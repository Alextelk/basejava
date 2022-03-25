package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + r.getUuid() + " не найдено");
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[index];
    }

    public boolean checkUuidForSave(Resume r){
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
            return false;
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено");
            return false;
        }
        return true;
    }

    public boolean checkUuidForDelete(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
            return false;
        }
        return true;
    }

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

    protected abstract int getIndex(String uuid);
}