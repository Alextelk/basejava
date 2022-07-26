package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK findSearchKey(String uuid);

    protected abstract boolean isExist(SK key);

    protected abstract void doSave(Resume r, SK key);

    protected abstract void doDelete(String uuid, SK key);

    protected abstract void doUpdate(Resume r, SK key);

    protected abstract Resume doGet(SK key);

    protected abstract List<Resume> copyStorage();

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK key = findExistSearchKey(r.getUuid());
        doUpdate(r, key);
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK key = findNotExistSearchKey(r.getUuid());
        doSave(r, key);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = findExistSearchKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = findExistSearchKey(uuid);
        doDelete(uuid, key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = copyStorage();
        resumeList.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return resumeList;
    }

    private SK findNotExistSearchKey(String uuid) {
        SK key = findSearchKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK findExistSearchKey(String uuid) {
        SK key = findSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

}





