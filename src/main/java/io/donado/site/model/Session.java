package io.donado.site.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;

@Entity
@Table(name="sessions")
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="session_id")
    @Getter
    @Setter
    private Integer sessionId;

    @Column(name="refresh_token")
    @Getter
    @Setter
    private String refreshToken;

    @Column(name="updated_at")
    @Getter
    @Setter
    @LastModifiedDate
    private Timestamp updatedAt;

    @Column
    @Getter
    @Setter
    private String location;

    public Session (String refreshToken, Timestamp updatedAt, String location) {
        this.refreshToken = refreshToken;
        this.updatedAt = updatedAt;
        this.location = location;
    }
}

//@Entity
//@Table(name="roles")
//public class Role implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="role_id")
//    @Getter @Setter private Integer roleId;
//
//    @Setter private String authority;
//
//    public Role() {
//        super();
//    }
//
//    public Role (String authority) {
//        this.authority = authority;
//    }
//
//    public Role (Integer roleId, String authority) {
//        this.roleId = roleId;
//        this.authority = authority;
//    }
//    @Override
//    public String getAuthority() {
//        return this.authority;
//    }
//}
