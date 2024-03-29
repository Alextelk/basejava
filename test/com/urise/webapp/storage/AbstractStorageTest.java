package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String UUID_5 = UUID.randomUUID().toString();
    private static final String UUID_6 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final Resume RESUME_5;
    private static final Resume RESUME_6;

    static {
        RESUME_1 = new ResumeTestData().fillResume(UUID_1, "Name1");
        RESUME_2 = new ResumeTestData().fillResume(UUID_2, "Name2");
        RESUME_3 = new ResumeTestData().fillResume(UUID_3, "Name3");
        RESUME_4 = new ResumeTestData().fillResume(UUID_4, "Name4");
        RESUME_5 = new ResumeTestData().fillResume(UUID_5, "Name5");
        RESUME_6 = new ResumeTestData().fillResume(UUID_6, "Name6");

/*      RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");
        RESUME_5 = new Resume(UUID_5, "Name5");
        RESUME_6 = new Resume(UUID_6, "Name6");

        RESUME_1.setContacts(ContactType.PHONE_NUMBER, "123");
        RESUME_1.setContacts(ContactType.SKYPE, "skype1");

        RESUME_2.setContacts(ContactType.PHONE_NUMBER, "234");
        RESUME_2.setContacts(ContactType.SKYPE, "skype2");

        RESUME_3.setContacts(ContactType.PHONE_NUMBER, "345");
        RESUME_3.setContacts(ContactType.SKYPE, "skype3");

        RESUME_4.setContacts(ContactType.PHONE_NUMBER, "456");
        RESUME_4.setContacts(ContactType.SKYPE, "skype4");

        RESUME_5.setContacts(ContactType.PHONE_NUMBER, "567");
        RESUME_5.setContacts(ContactType.SKYPE, "skype5");

        RESUME_6.setContacts(ContactType.PHONE_NUMBER, "678");
        RESUME_6.setContacts(ContactType.SKYPE, "skype6");         */
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
        storage.save(RESUME_5);
    }

    @Test
    public void size() throws Exception {
        assertSize(5);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(5, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3, RESUME_4, RESUME_5));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
        assertGet(RESUME_4);
        assertGet(RESUME_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_6);
        assertSize(6);
        assertGet(RESUME_6);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_5);
        assertSize(4);
        storage.get(UUID_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_6);
    }

    private void assertSize(int size) throws IOException {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}