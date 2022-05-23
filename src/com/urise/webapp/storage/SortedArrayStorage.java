package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveElement(Resume r) {
        int index = Math.abs(getIndex(r.getUuid())) - 1;
        if (index == size) {
            storage[size] = r;
        } else {
            if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
        }
    }

    @Override
    public void deleteElement(int index) {
        if (size + 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size + 1 - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}