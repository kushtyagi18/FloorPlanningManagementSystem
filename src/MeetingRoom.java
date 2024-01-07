import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private final int roomId;
    private final String roomName;
    private final int capacity;
    private final String location;
    private final List<Booking> bookings;

    public MeetingRoom(int roomId, String roomName, int capacity, String location) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.location = location;
        this.bookings = new ArrayList<>();
    }

    public boolean isAvailable(String startTime, String endTime) {
        // Check if the room is available during the specified time
        for (Booking booking : bookings) {
            if (isTimeConflict(startTime, endTime, booking.getStartTime(), booking.getEndTime())) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCapacity(int participants) {
        // Check if the room has sufficient capacity
        return capacity >= participants;
    }

    // Helper method to check time conflict
    private boolean isTimeConflict(String start1, String end1, String start2, String end2) {
        // Convert string times to appropriate data types (e.g., LocalDateTime)
        // Compare the time ranges to check for conflicts
        // Return true if there is a conflict, false otherwise
        // This logic may vary based on the actual data types you use for time
        // Here, we are assuming a simplified representation using strings
        return !(end1.compareTo(start2) <= 0 || start1.compareTo(end2) >= 0);
    }

    public String getRoomName() {
        return roomName;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
