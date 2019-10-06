package com.akybenko.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"firstName", "lastName", "username", "email", "dateOfBirth", "phone"})
@ToString(of = {"firstName", "lastName", "username", "email", "dateOfBirth", "phone"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "id", "start", "finish"})
    private Project project;
}