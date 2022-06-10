package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "usr")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "third_name")
    String thirdName;

    @Column(name = "sex")
    String sex;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    @JsonIgnore
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<UserAndQuizzesEntity> quizzes = new ArrayList<>();

    public int getAge() {
        LocalDate timeNow = LocalDate.now();
        LocalDate birthday = this.getBirthday();
        int years = timeNow.getYear() - birthday.getYear();
        if (timeNow.getMonthValue() > birthday.getMonthValue())
            return years;
        else {
            if (timeNow.getDayOfMonth() >= birthday.getDayOfMonth())
                return years;
            else
                return years - 1;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return telephone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
