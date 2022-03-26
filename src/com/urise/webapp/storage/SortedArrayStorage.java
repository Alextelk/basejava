package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume r) {
        int index = Math.abs(getIndex(r.getUuid())) - 1;
        if (index == size) {
            storage[size] = r;
            size++;
        } else {
            size++;
            for (int i = size; i > index; i--) {
                storage[i] = storage[i - 1];
            }
            storage[index] = r;
        }
    }

    @Override
    public void deleteResume(String uuid) {
        size--;
        for (int i = getIndex(uuid); i <= size; i++)
            storage[i] = storage[i + 1];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}