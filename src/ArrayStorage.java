import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int count = 0;
    private int size = 0;
    Resume[] storage = new Resume[5];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private boolean checkUuid(String uuid) {
        for (count = 0; count < size; count++) {
            if (storage[count].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void save(Resume r) {
        if (checkUuid(r.toString())) {
            System.out.println("Резюме с таким uuid уже есть");
        } else if (size == storage.length) {
            System.out.println("Хранилище заполнено");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if (checkUuid(r.toString())) {
            storage[count] = r;
        } else {
            System.out.println("Резюме с таким uuid в хранилище нет");
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid)) {
            return storage[count];
        }
        System.out.println("Резюме с таким uuid в хранилище нет");
        return null;
    }


    public void delete(String uuid) {
        if (checkUuid(uuid)) {
            storage[count] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме с таким uuid в хранилище нет");
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