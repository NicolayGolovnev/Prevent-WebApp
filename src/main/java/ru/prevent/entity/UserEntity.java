package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "usr")
public class UserEntity {
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
}
