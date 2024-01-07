import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class OfflineStorage {
    private final List<FloorPlan> localPlans;

    public OfflineStorage() {
        this.localPlans = new ArrayList<>();
    }

    public void savePlan(final FloorPlan floorPlan) {
        // Save the floor plan locally
        localPlans.add(floorPlan);
        log.info("Floor plan saved locally {} ",floorPlan.getPlanName());
    }

    public List<FloorPlan> loadPlans() {
        // Load locally stored floor plans
        log.info("Loading locally stored floor plans...");
        return new ArrayList<>(localPlans);
    }

    public void clearStorage() {
        // Clear local storage
        localPlans.clear();
        log.info("Local storage cleared.");
    }
}