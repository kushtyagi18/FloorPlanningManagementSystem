import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FloorPlan {
    private final int floorPlanId;
    private final String planName;
    private final int version;
    private final List<Room> rooms;
    private final Date createdDate;
    private final Admin createdBy;
    private final DataCache dataCache;
    private final int priority;
    private String description;
    private List<String> tags;
    private Date lastModified;
    
    
    public FloorPlan(int floorPlanId, String planName, int version, Date lastModified,
                     List<Room> rooms, Date createdDate, Admin createdBy,
                     String description, List<String> tags) {
        this.floorPlanId = floorPlanId;
        this.planName = planName;
        this.version = version;
        this.lastModified = lastModified;
        this.rooms = new ArrayList<>(rooms); 
        this.createdDate = new Date(createdDate.getTime()); 
        this.createdBy = createdBy;
        this.description = description;
        this.tags = new ArrayList<>(tags); 
        this.dataCache= new DataCache();
    }

    public class DataCache {
    private Map<String, Object> cache;

    public DataCache() {
        this.cache = new HashMap<>();
    }

    public Object getFromCache(String key) {
        return cache.get(key);
    }

    public void addToCache(String key, Object data) {
        cache.put(key, data);
    }

    public void removeFromCache(String key) {
        cache.remove(key);
    }

    public void clearCache() {
        cache.clear();
    }
}

    public int getFloorPlanId() {
        return floorPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public int getVersion() {
        return version;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setPriority(final int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }

    // Additional methods
    public void addRoom(Room room) {
        rooms.add(room);
        dataCache.addToCache("Room_" + room.getId(), room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
         dataCache.removeFromCache("Room_" + room.getId());
    }

    public Room getRoomById(final int roomId) {
        final Room cachedRoom = (Room) dataCache.getFromCache("Room_" + roomId);
        if (cachedRoom != null) {
            log.info("Room found in cache!");
            return cachedRoom;
        }
        else{
        for (Room room : rooms) {
            if (room.getId() == roomId) {
                return room;
            }
        }
        }
        return null; // room not found
    }

   
    public void uploadPlan() {
        this.version++;
        this.lastModified = new Date();
        log.info("Floor plan uploaded. New version {}" , version);

    }
}


