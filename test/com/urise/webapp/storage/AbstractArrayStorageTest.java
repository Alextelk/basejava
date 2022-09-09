package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.io.IOException;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + (i + 1)));
            }
        } catch (StorageException e) {
            fail("Переполнение произошло раньше времени");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage.save(new Resume("name"));
    }
}