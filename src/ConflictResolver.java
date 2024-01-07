public class ConflictResolver {
    // Simulate conflict resolution based on priority, timestamp, or user roles
    public void resolveConflict(final FloorPlan localPlan, final FloorPlan serverPlan, final Admin admin) {
        if (admin != null && admin.authenticate("admin_password") && admin.getRole() == UserRole.ADMIN) {
            log.info("Admin resolving conflict. Admin's version takes priority.");
            serverPlan.uploadPlan();
        } else {
            // Check priority first
            int priorityComparison = Integer.compare(localPlan.getPriority(), serverPlan.getPriority());
    
            if (priorityComparison > 0) {
                log.info("Conflict resolved. Uploading local version based on priority...");
                serverPlan.uploadPlan();
            } else if (priorityComparison < 0) {
                log.info("Conflict resolved. Merging server version based on priority...");
                localPlan.uploadPlan();
            } else {
                // If priorities are the same, check timestamp
                int timestampComparison = localPlan.getLastModified().compareTo(serverPlan.getLastModified());
    
                if (timestampComparison > 0) {
                    log.info("Conflict resolved. Uploading local version based on timestamp...");
                    serverPlan.uploadPlan();
                } else if (timestampComparison < 0) {
                    log.info("Conflict resolved. Merging server version based on timestamp...");
                    localPlan.uploadPlan();
                } else {
                    log.error("Conflict detected. Additional resolution needed.");
                    throw new IllegalStateException("Conflict detected. Additional resolution needed.");
                }
            }
        }
    }
    
}