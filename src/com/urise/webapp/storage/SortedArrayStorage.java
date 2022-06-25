package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public void saveResume(Resume r) {
        int index = Math.abs(findSearchKey(r.getUuid())) - 1;
        if (index == size) {
            storage[size] = r;
        } else {
            if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
        }
    }

    @Override
    public void deleteResume(int index) {
        if (size + 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size + 1 - index);
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "name");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}