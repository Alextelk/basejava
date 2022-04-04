package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";

    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    private static final String UUID_5 = "uuid5";
    private static final Resume RESUME_5 = new Resume(UUID_5);

    private static final String UUID_6 = "uuid6";
    private static final Resume RESUME_6 = new Resume(UUID_6);

    private static final String UUID_7 = "uuid7";
    private static final Resume RESUME_7 = new Resume(UUID_7);

    private static final String UUID_8 = "uuid8";
    private static final Resume RESUME_8 = new Resume(UUID_8);

    private static final String UUID_9 = "uuid9";
    private static final Resume RESUME_9 = new Resume(UUID_9);

    private static final String UUID_10 = "uuid10";
    private static final Resume RESUME_10 = new Resume(UUID_10);

    private static final String UUID_11 = "uuid11";
    private static final Resume RESUME_11 = new Resume(UUID_11);

    private static final Resume[] STORAGE_ARRAY_TEST = {RESUME_1, RESUME_2, RESUME_3, RESUME_4, RESUME_5};

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
        storage.save(RESUME_5);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(5, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_6);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertArrayEquals(STORAGE_ARRAY_TEST, storage.getAll());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.save(RESUME_6);
        storage.save(RESUME_7);
        storage.save(RESUME_8);
        storage.save(RESUME_9);
        storage.save(RESUME_10);
        if (storage.size() > STORAGE_LIMIT) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(RESUME_11);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_5);
        storage.get(UUID_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_6);
    }
}