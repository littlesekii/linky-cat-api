package cat.linky.linkycat_api.core.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user_email_verification")
public class EmailVerification {
    
    /* Properties */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String verificationCode;
    private Boolean verified;
    private Instant expiresAt;

    /***/

    /* Constructors */

    public EmailVerification() {}
    public EmailVerification(Long id, String email, String verificationCode, Boolean verified, Instant expiresAt) {
        this.id = id;
        this.email = email;
        this.verificationCode = verificationCode;
        this.verified = verified;
        this.expiresAt = expiresAt;
    }

    /***/

    /* Getters/Setters */

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public Boolean getVerified() {
        return verified;
    }
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    public Instant getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }    

    /***/

    /* HashCode and Equals */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailVerification other = (EmailVerification) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserEmailVerification [id=" + id + ", email=" + email + ", verificationCode=" + verificationCode
            + ", verified=" + verified + ", expiresAt=" + expiresAt + "]";
    }
    
    /***/
    
}
