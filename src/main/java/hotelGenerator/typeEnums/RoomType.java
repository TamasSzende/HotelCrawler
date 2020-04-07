package hotelGenerator.typeEnums;

public enum RoomType {

    EGYAGYASSZOBA("egy ágyas szoba"),
    KETAGYASSZOBA("két ágyas szoba"),
    HAROMAGYASSZOBA("három ágyas szoba"),
    NEGYAGYASSZOBA("négy ágyas szoba"),
    TOBBAGYASSZOBA("több ágyas szoba"),
    STUDIO("stúdió"),
    APARTMAN("apartman"),
    LAKOSZTALY("lakosztály"),
    ;

    private String displayName;

    private RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
