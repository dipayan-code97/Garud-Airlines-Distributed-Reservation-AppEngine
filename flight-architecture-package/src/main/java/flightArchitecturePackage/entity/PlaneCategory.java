package flightArchitecture.entity;

public enum PlaneCategory {

    PROPELLER,
    TURBO_PROPELLER,
    JET,
    AIRLINER,
    PASSENGER_SERVICE,
    BUSINESS_CLASS,
    ECONOMY_CLASS,
    GOODS_INTO_SERVICE,
    MILITARY_GOODS_INTO_SERVICE,
    AGRICULTURAL_GOOD_INTO_SERVICE,
    VIP_CLASS,
    ROYAL_LUXURY_DULEX_CLASS,
    TECNO_AIR_BUS;

    public String toString() {
        return switch(this) {
            case PROPELLER -> "PROPELLER";
            case TURBO_PROPELLER -> "TURBO-PROPELLER";
            case JET -> "JET";
            case AIRLINER -> "AIRLINER";
            case PASSENGER_SERVICE -> "PASSENGER-SERVICE";
            case BUSINESS_CLASS -> "BUSINESS-CLASS";
            case ECONOMY_CLASS -> "ECONOMY-CLASS";
            case GOODS_INTO_SERVICE -> "GOODS-INTO-SERVICE";
            case MILITARY_GOODS_INTO_SERVICE -> "MILITARY-GOODS-INTO-SERVICE";
            case AGRICULTURAL_GOOD_INTO_SERVICE -> "AGRICULTURE-GOOD-INTO-SERVICE";
            case VIP_CLASS -> "VIP-CLASS";
            case ROYAL_LUXURY_DULEX_CLASS -> "ROYAL-LUXURY-DULEX-CLASS";
            case TECNO_AIR_BUS -> "TECNO-AIR-BUS";
        };
    }
}