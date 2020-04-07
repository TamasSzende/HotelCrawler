package bookingGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingCreateItemWithStringDate {
    private String guestAccountName;
    private String remark;
    private Integer numberOfGuests;
    private String startDate;
    private String endDate;
    private List<Long> roomIdList;

    public BookingCreateItemWithStringDate(BookingCreateItem bookingCreateItem) {
        this.guestAccountName = bookingCreateItem.getGuestAccountName();
        this.remark = bookingCreateItem.getRemark();
        this.numberOfGuests = bookingCreateItem.getNumberOfGuests();
        this.startDate = bookingCreateItem.getStartDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd."));
        this.endDate = bookingCreateItem.getEndDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd."));
        this.roomIdList = bookingCreateItem.getRoomIdList();
    }


    public String getGuestAccountName() {
        return guestAccountName;
    }

    public void setGuestAccountName(String guestAccountName) {
        this.guestAccountName = guestAccountName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Long> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<Long> roomIdList) {
        this.roomIdList = roomIdList;
    }
}
