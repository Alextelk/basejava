package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectStreamFileStorage extends AbstractStorage<File> {
    private File directory;
    private StrategyOfChoice strategyOfChoice;

    protected ObjectStreamFileStorage(File directory, StrategyOfChoice strategyOfChoice) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.strategyOfChoice = strategyOfChoice;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Size error", null);
        }
        return files.length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            strategyOfChoice.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Update error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doUpdate(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategyOfChoice.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Get error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> copyStorage() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory is exist", null);
        }
        List<Resume> listFiles = new ArrayList<>();
        for (File file : files) {
            listFiles.add(doGet(file));
        }
        return listFiles;
    }
}