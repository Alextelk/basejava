package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume r) {
        storage[size] = r;
    }

    @Override
    public void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}