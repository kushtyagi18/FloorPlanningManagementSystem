


public class Booking {

    private final int bookingId;
    private final List<MeetingRoom> meetingRooms;
    private final String startTime;
    private final String endTime;
    private final int participants;
    private final String description;
    private final Admin createdBy;
    private final String databaseUrl;

    public Booking(final int bookingId,final MeetingRoom meetingRoom,final  String startTime,final String endTime,
    final int participants, String description, Admin createdBy,final String databaseUrl) {
        this.bookingId = bookingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.description = description;
        this.createdBy = createdBy;
        this.databaseUrl = databaseUrl;
    }

    public void bookRoom() {
        loadMeetingRoomsFromWebDatabase(databaseUrl);
        for(MeetingRoom meetingRoom : meetingRooms){
        if (meetingRoom.isAvailable(startTime, endTime) && meetingRoom.hasCapacity(participants)) {
            
            log.info("Room booked: {}", meetingRoom.getRoomName());
            // Update booking status or perform other actions as needed
        } else {
            log.error("Booking failed. Room not available or capacity exceeded.");
        }
    }
}
    public void loadMeetingRoomsFromWebDatabase(String databaseUrl) {
        try {
            URL url = new URL(databaseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Parse the response and populate meetingRooms
                parseAndPopulateMeetingRooms(response.toString());
            } else {
                log.error("Error loading meeting rooms from the web database. Response Code: {}", responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            log.error("Exception while loading meeting rooms from the web database", e);
        }
    }
}