package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено");
        } else {
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
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
        } else {
            size--;
            for (int i = index; i <= size; i++)
                storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}