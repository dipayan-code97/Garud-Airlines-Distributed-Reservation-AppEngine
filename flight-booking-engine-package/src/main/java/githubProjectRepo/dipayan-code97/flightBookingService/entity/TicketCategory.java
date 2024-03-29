package flightBookingService;

public enum TicketCategory {

    PROPELLER_CLASS_TICKET,
    ECONOMY_CLASS_TICKET,
    BUSINESS_CLASS_TICKET,
    TURBO_PROPELLER_CLASS_TICKET,
    JET_CLASS_TICKET,
    PASSENGER_SERVICE_CLASS_TICKET,
    AIRLINER_CLASS_TICKET,
    GOODS_INTO_SERVICE_CLASS_TICKET,
    MILITARY_GOODS_INTO_SERVICE_CLASS_TICKET,
    AGRICULTURAL_GOOD_INTO_SERVICE_CLASS_TICKET,
    VIP_CLASS_TICKET,
    ROYAL_LUXURY_DULEX_CLASS_TICKET,
    TECNO_AIR_BUS_CLASS_TICKET;

    public String toString() {
        return switch(this) {
            case PROPELLER_CLASS_TICKET -> "PROPELLER-CLASS-TICKET";
            case TURBO_PROPELLER_CLASS_TICKET -> "TURBO-PROPELLER-CLASS-TICKET";
            case JET_CLASS_TICKET -> "JET-CLASS-TICKET";
            case AIRLINER_CLASS_TICKET -> "AIRLINER-CLASS-TICKET";
            case PASSENGER_SERVICE_CLASS_TICKET -> "PASSENGER-SERVICE-CLASS-TICKET";
            case BUSINESS_CLASS_TICKET -> "BUSINESS-CLASS-TICKET";
            case ECONOMY_CLASS_TICKET -> "ECONOMY-CLASS-TICKET";
            case GOODS_INTO_SERVICE_CLASS_TICKET -> "GOODS-INTO-SERVICE-CLASS-TICKET";
            case MILITARY_GOODS_INTO_SERVICE_CLASS_TICKET -> "MILITARY-GOODS-INTO-SERVICE-CLASS-TICKET";
            case AGRICULTURAL_GOOD_INTO_SERVICE -> "AGRICULTURE-GOOD-INTO-SERVICE-CLASS-TICKET";
            case VIP_CLASS_TICKET -> "VIP-CLASS-TICKET";
            case ROYAL_LUXURY_DULEX_CLASS_TICKET -> "ROYAL-LUXURY-DULEX-CLASS-TICKET";
            case TECNO_AIR_BUS_CLASS_TICKET -> "TECNO-AIR-BUS-CLASS-TICKET";
        };

}