package data.workformat;

public enum WorkFormatData {
    FULL("Полный день"),
    REMOTE("Удаленно");

    private String name;

    WorkFormatData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
