// File: VotingSystem.java

class VotingSystem {
    public void processVote(String voterId, String candidate) {
        // ✅ Local Inner Class inside method
        class VoteValidator {
            public boolean validate(String id) {
                // Simple check: voterId should not be null/empty and must start with "V"
                return id != null && id.startsWith("V") && id.length() == 5;
            }
        }

        // Create object of local inner class
        VoteValidator validator = new VoteValidator();

        // Validate voter
        if (validator.validate(voterId)) {
            System.out.println("Voter " + voterId + " successfully voted for " + candidate);
        } else {
            System.out.println("Invalid Voter ID: " + voterId);
        }
    }

    public static void main(String[] args) {
        VotingSystem vs = new VotingSystem();

        // ✅ Test with various IDs
        vs.processVote("V1234", "Alice");
        vs.processVote("X5678", "Bob");
        vs.processVote("V9999", "Charlie");
        vs.processVote(null, "David");
    }
}
