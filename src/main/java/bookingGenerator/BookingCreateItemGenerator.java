package bookingGenerator;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import hotelGenerator.HotelDataStolen;
import hotelGenerator.HotelsToJSON;
import hotelGenerator.RoomDataStolen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class BookingCreateItemGenerator {
    private final static String[] guestAccountNames = new String[]{"hotel.team.five.u@gmail.com", "lilly.harris@example.com", "arthur.fernandez@example.com", "christos.stotz@example.com", "alberte.nielsen@example.com", "myrtha.charles@example.com", "kristen.cox@example.com", "brad.kuhn@example.com", "john.harris@example.com"};
    private final static String[] remarks = new String[]{"", "", "", "", "Legyen sok sör", "Van babaágy?", "Mennyire van messze a parkoló?", "11-kor érkezünk.", "13:00 körül érkezünk", "Van vegán menü?", "Csak este érünk oda"};
    // TODO fill guestAccountNames and remarks
    private List<BookingCreateItem> bookings = new ArrayList<>();
    private static final Random random = new Random();
    private final List<HotelDataStolen> hotels;
    private Map<Integer, Long[]> roomIdMap;
    private HotelDataStolen hotel;
    private Long roomIdDifference;
    private List<Long> roomIdList;


    public BookingCreateItemGenerator() {
        hotels = HotelsToJSON.reparseHotelData("Res/hotelsAvg.json");
        roomIdMap = new HashMap<>();
        int roomCounter = 1;
        for (int i = 0; i < hotels.size(); i++) {
            HotelDataStolen hotel = hotels.get(i);
            Long[] rooms = new Long[hotel.getRooms().size()];
            for (int j = 0; j < hotel.getRooms().size(); j++) {
                rooms[j] = (long) roomCounter;
                roomCounter++;
            }
            roomIdMap.put(i + 1, rooms);
        }
        System.out.println(roomIdMap);
    }

    public void generateBooking(int hotelId, LocalDate minStart, LocalDate maxEnd) {
        hotel = hotels.get(hotelId - 1);
        String guestAccountName = generateAccountName();
        String remark = generateRemark();
        roomIdList = generateRoomIds(hotelId);
        Integer numberOfGuests = generateNumberOfGuests();
        LocalDate endDate = null;
        LocalDate startDate = null;
        while (endDate == null) {
            startDate = generateStartDate(minStart, maxEnd);
            endDate = generateEndDate(startDate, maxEnd);
        }
        BookingCreateItem bookingCreateItem = new BookingCreateItem(guestAccountName, remark, numberOfGuests, startDate, endDate, roomIdList);
        bookings.add(bookingCreateItem);
        printSomething(bookingCreateItem);
    }

    private void printSomething(BookingCreateItem bookingCreateItem) {
        System.out.println(bookingCreateItem.getStartDate() + " ->  " + bookingCreateItem.getEndDate());
        System.out.println();
        for (Long aLong : bookingCreateItem.getRoomIdList()) {
            System.out.print(aLong + ", ");
        }
        System.out.println();
    }


    private List<Long> generateRoomIds(int hotelId) {
        Long[] allRoomIds = roomIdMap.get(hotelId);
        roomIdDifference = allRoomIds[0];
        int numOfRooms;
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.70) {
            numOfRooms = 1;
        } else if (randomDouble < 0.9) {
            if (allRoomIds.length > 1) {
                numOfRooms = 2;
            } else {
                numOfRooms = 1;
            }
        } else {
            numOfRooms = Math.min(1 + random.nextInt(allRoomIds.length - 1), 3);
        }
        List<Long> roomIdList = new ArrayList<>();
        for (int i = 0; i < numOfRooms; i++) {
            Long selectedId = allRoomIds[random.nextInt(allRoomIds.length)];
            if (!roomIdList.contains(selectedId)) {
                roomIdList.add(selectedId);
            } else {
                i--;
            }
        }
        return roomIdList;
    }

    private Integer generateNumberOfGuests() {
        int numOfBeds = 0;
        for (Long roomId : roomIdList) {
            numOfBeds += hotel.getRooms().get((int) (roomId - roomIdDifference)).getNumberOfBeds();
        }
        return numOfBeds - random.nextInt(roomIdList.size());
    }

    private LocalDate generateStartDate(LocalDate minStart, LocalDate maxEnd) {
        LocalDate selectedDate = null;
        while (!isValidStartDate(selectedDate)) {
            int numOfDaysFromStart = random.nextInt((int) (maxEnd.toEpochDay() - minStart.toEpochDay()));
            selectedDate = LocalDate.ofEpochDay(minStart.toEpochDay() + numOfDaysFromStart);
        }
        return selectedDate;
    }

    private boolean isValidStartDate(LocalDate selectedDate) {
        if (selectedDate == null) {
            return false;
        }
        boolean isValid = true;
        for (BookingCreateItem booking : bookings) {
            boolean hasSameRoom = false;
            for (Long roomIdInBooking : booking.getRoomIdList()) {
                if (roomIdList.contains(roomIdInBooking)) {
                    hasSameRoom = true;
                    break;
                }
            }
            if (hasSameRoom) {
                if (selectedDate.isEqual(booking.getStartDate()) || (selectedDate.isAfter(booking.getStartDate()) && selectedDate.isBefore(booking.getEndDate()))) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    private LocalDate generateEndDate(LocalDate startDate, LocalDate maxEnd) {
        LocalDate selectedDate = null;
        int bound = Math.min((int) (maxEnd.toEpochDay() - startDate.toEpochDay()), 10);
        int numOfDaysFromStart = random.nextInt(bound);
        while (numOfDaysFromStart > 0 && !isValidEndDate(selectedDate)) {
            selectedDate = LocalDate.ofEpochDay(startDate.toEpochDay() + numOfDaysFromStart);
            numOfDaysFromStart--;
        }
        return selectedDate;
    }

    private boolean isValidEndDate(LocalDate selectedDate) {
        if (selectedDate == null) {
            return false;
        }
        boolean isValid = true;
        for (BookingCreateItem booking : bookings) {
            boolean hasSameRoom = false;
            for (Long roomIdInBooking : booking.getRoomIdList()) {
                if (roomIdList.contains(roomIdInBooking)) {
                    hasSameRoom = true;
                    break;
                }
            }
            if (hasSameRoom) {
                if (selectedDate.isEqual(booking.getEndDate()) || (selectedDate.isAfter(booking.getStartDate()) && selectedDate.isBefore(booking.getEndDate()))) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    private String generateRemark() {
        return remarks[random.nextInt(remarks.length)];
    }

    private String generateAccountName() {
        return guestAccountNames[random.nextInt(guestAccountNames.length)];
    }


    public void saveBookingsToJSON() {
        List<BookingCreateItemWithStringDate> bookingCreateItemWithStringDates = new ArrayList<>();
        for (BookingCreateItem booking : bookings) {
            BookingCreateItemWithStringDate bookingCreateItemWithStringDate = new BookingCreateItemWithStringDate(booking);
            bookingCreateItemWithStringDates.add(bookingCreateItemWithStringDate);
        }
        File file = new File("Res/bookings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy. MM. dd"));
        try {
            OutputStream outputStream = new FileOutputStream(file);
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
            objectMapper.writeValue(jsonGenerator, bookingCreateItemWithStringDates);
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BookingCreateItem> getBookings() {
        return bookings;
    }
}
