package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (checkUuidForSave(r)) {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        if(checkUuidForDelete(uuid)){
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}