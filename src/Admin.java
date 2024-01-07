import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum UserRole {
    ADMIN,
    REGULAR_USER
}

public class Admin{
    private final String username;
    private final String hashedPassword;
    private final UserRole role;
    private final ConflictResolver conflictResolver;


    public Admin(final String username, final String password, final UserRole role) {
        this.username = username;
        this.hashedPassword = hashPassword(password);;
        this.role = role;
        this.conflictResolver = conflictResolver;
    }

    // Simulate authenticating an admin
   public boolean authenticate(final String enteredPassword) {
        final String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash.equals(hashedPassword);
    }

    private String hashPassword(String password) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] hashedBytes = md.digest(password.getBytes());
            final StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Simulate resolving conflicts during simultaneous updates
    public void resolveConflict(final FloorPlan localPlan, final FloorPlan serverPlan) {
        if (localPlan == null || serverPlan == null) {
            log.error("floor plan cant be null");
            throw new IllegalArgumentException("Floor plans cannot be null.");
        }

        conflictResolver.resolveConflict(localPlan, serverPlan);
        
    }
}
public class FloorPlanManagementSystem {
    public static void main(String[] args) {
        // Simulating usage
        FloorPlan localPlan = new FloorPlan(1, "OfficeFloor", 1);
        FloorPlan serverPlan = new FloorPlan(1, "OfficeFloor", 1);

        Admin admin = new Admin("admin", "admin_password", UserRole.ADMIN);

        // Simulating conflict resolution
        ConflictResolver conflictResolver = new ConflictResolver();
        conflictResolver.resolveConflict(localPlan, serverPlan, admin);
    }
}