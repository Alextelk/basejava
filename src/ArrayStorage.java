import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size, 0);
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(uuid)) {
                storage[i] = null;
                for (int j = 0; j < storage.length; j++) {
                    storage[i] = storage[i + 1];
                    storage[i + 1] = null;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}


