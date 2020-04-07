package hotelGenerator.typeEnums;

public enum RoomFeatureType {

    TEAKAVEFOZO("Tea-/ kávéfőző"),
    HAJSZARITO("Hajszárító"),
    HUTOSZEKRENY("Hűtőszekrény"),
    TV("Síkképernyős TV"),
    BABAAGY("Babaágy betehető"),
    MINIBAR("Minibár"),
    SZOBASZEF("Szobaszéf"),
    LEGKONDICIONALT("Légkondícionálás"),
    ERKELYTERASZ("Erkély/terasz"),
    WIFI("WIFI"),
    PEZSGOFURDO("Pezsgőfürdő");

    private String displayName;

    private RoomFeatureType(String displayName) {
        this.displayName = displayName;
    }

    public static RoomFeatureType findByDisplayName(String featureString) {
        for (RoomFeatureType value : RoomFeatureType.values()) {
            if (value.displayName.equals(featureString)) {
                return value;
            }
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }
}
