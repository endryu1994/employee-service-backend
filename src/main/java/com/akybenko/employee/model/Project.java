package com.akybenko.employee.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "start", "finish"})
@ToString(of = {"name", "start", "finish"})
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    @Column(name = "project_name", nullable = false, unique = true)
    private String name;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date start;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finish;
}
