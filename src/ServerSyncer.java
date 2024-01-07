
public class ServerSyncer {
    private final OfflineStorage offlineStorage;

    public ServerSyncer(final OfflineStorage offlineStorage) {
        this.offlineStorage = offlineStorage;
    }

    public void synchronizeWithServer() {
        // Assume logic to check internet/server connection
       final  boolean isConnected = checkInternetConnection();

        if (isConnected) {
            final List<FloorPlan> localPlans = offlineStorage.loadPlans();

            for (final FloorPlan floorPlan : localPlans) {
                updateFloorPlan(floorPlan);
                log.info("Synchronizing with server: {} ", floorPlan.getPlanName());
               }

            // Clear local storage after successful synchronization
            offlineStorage.clearStorage();
        } else {
           log.error("No internet connection. Synchronization postponed.");
            throw new IllegalStateException("No internet connection. Synchronization postponed.");
        }
    }

    private boolean checkInternetConnection() {
        // Assume logic to check internet connection
        return true; 
    }

    public void updateFloorPlan(FloorPlan floorPlan) {
        // Logic to update the floor plan
        log.info("floor plan updated to {}",floorPlan);
    }
}
