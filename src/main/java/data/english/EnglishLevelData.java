package data.english;

public enum EnglishLevelData {

    BEGINNER("Начальный уровень (Beginner)");

    private String nameEnglishLevel;
    EnglishLevelData(String nameEnglishLevel) {
        this.nameEnglishLevel = nameEnglishLevel;
    }
    public String getNameEnglishLevel() {
        return nameEnglishLevel;
    }
}
