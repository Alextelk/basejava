package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private Resume[] storage = new Resume[5];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Хранилище переполнено");
        } else if (checkUuid(r.getUuid()) < 0) {
            storage[size] = r;
            size++;
        } else if (checkUuid(r.getUuid()) >= 0 && checkUuid(r.getUuid()) <= storage.length - 1) {
            System.out.println("Резюме с таким " + r.getUuid() + " уже есть");
        }
    }

    public void update(Resume r) {
        if (checkUuid(r.getUuid()) >= 0 && checkUuid(r.getUuid()) <= storage.length) {
            storage[checkUuid(r.toString())] = r;
        } else {
            System.out.println("Резюме с таким " + r.getUuid() + " в хранилище нет");
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid) >= 0 && checkUuid(uuid) <= storage.length) {
            return storage[checkUuid(uuid)];
        }
        System.out.println("Резюме с таким " + uuid + " в хранилище нет");
        return null;
    }

    public void delete(String uuid) {
        if (checkUuid(uuid) >= 0 && checkUuid(uuid) <= storage.length) {
            storage[checkUuid(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме с таким " + uuid + " в хранилище нет");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int checkUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}