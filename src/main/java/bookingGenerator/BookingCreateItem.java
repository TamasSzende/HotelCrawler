package bookingGenerator;

import java.time.LocalDate;
import java.util.List;

public class BookingCreateItem {
    private String guestAccountName;
    private String remark;
    private Integer numberOfGuests;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> roomIdList;

    public BookingCreateItem(String guestAccountName, String remark, Integer numberOfGuests, LocalDate startDate, LocalDate endDate, List<Long> roomIdList) {
        this.guestAccountName = guestAccountName;
        this.remark = remark;
        this.numberOfGuests = numberOfGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomIdList = roomIdList;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Long> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<Long> roomIdList) {
        this.roomIdList = roomIdList;
    }
}
