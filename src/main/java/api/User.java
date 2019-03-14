package api;

public class User {

    private String email;
    private String password;
    private String token;
    private String secret;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String secret) {
        this.email = email;
        this.secret = secret;
    }

    public static class UserCreds {
        private String email;
        private String password;

        public UserCreds(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class Password {
        private String new_password;

        public Password(String password) {
            this.new_password = password;

        }
    }

    public static class Verification {
        private String verification_token;
        private String verification_code;

        public Verification(String token, String code) {
            this.verification_token = token;
            this.verification_code = code;
        }
    }
}

