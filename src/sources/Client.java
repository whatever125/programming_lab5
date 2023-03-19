package sources;

public interface Client {
    void help();

    void exit();

    void executeScript(String path);
}
