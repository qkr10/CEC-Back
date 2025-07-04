package com.backend.server.model.entity;

import com.backend.server.api.admin.dto.user.AdminUserRequest;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.backend.server.model.entity.enums.Role;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String studentNumber;

    //닉네임은 엑셀파일에서 가져올 수 없으므로 기본값 = 이름
    @Column(nullable = false)
    private String nickname;

    @Column
    private String department;

    @Column(nullable = false)
    private Integer grade;

    @Column
    private String major;

    @Column(name = "\"group\"")
    private String group;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String profilePicture;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column
    private int rentalCount;

    @Column
    private int damageCount;

    @Column
    private int restrictionCount;

    @Column
    private int reportCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @PrePersist //닉네임을 이름으로 설정하기 위해 30줄가량 생성자를 일일이 써야하는 문제를 해결
    public void prePersist() {
        
        if (this.nickname == null && this.name != null) {
            this.nickname = this.name;
        }
    }

    public void update(Professor professor, AdminUserRequest request) {
        this.name = request.getName();
        this.studentNumber = request.getStudentNumber();
        this.grade = request.getGrade();
        this.gender = request.getGender();
        this.phoneNumber = request.getPhoneNumber();
        this.professor = professor;
        this.birthDate = request.parseBirthday();
        this.profilePicture = request.getProfilePicture();
    }
}