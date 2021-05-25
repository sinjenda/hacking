package computer.networking;

import computer.program.logging.PermissionLevel;

import java.util.HashMap;
import java.util.UUID;

public class ServiceHelper {
    HashMap<UUID, Properties> sessionIds = new HashMap<>();

    public void endConnection(UUID id) {
        sessionIds.remove(id);
    }

    public UUID newConnection() {
        UUID id=UUID.randomUUID();
        sessionIds.put(id, new Properties());
        return id;
    }

    public void authorize(UUID id,PermissionLevel level) {
        sessionIds.get(id).setAuthorized(true);
        sessionIds.get(id).setLevel(level);
    }

    public boolean isAuthorized(UUID id) {
        return sessionIds.get(id).isAuthorized();
    }

    private static class Properties {
        public boolean isAuthorized() {
            return authorized;
        }

        public void setAuthorized(boolean authorized) {
            this.authorized = authorized;
        }

        private boolean authorized;
        private PermissionLevel level;

        public PermissionLevel getLevel() {
            return level;
        }

        public void setLevel(PermissionLevel level) {
            this.level = level;
        }
    }
}
